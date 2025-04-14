package banco.pichincha.web.movimiento;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import banco.pichincha.web.cuenta.Cuenta;
import banco.pichincha.web.cuenta.CuentaRepository;
import banco.pichincha.web.exception.BusinessException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class MovimientoService {
    private final MovimientoRepositorio movRepo;
    private final CuentaRepository ctaRepo;
    private final MovimientoMapper movMapper;

    public MovimientoService(MovimientoRepositorio mr, CuentaRepository ctaRepo, MovimientoMapper m) {
        this.movRepo = mr;
        this.ctaRepo = ctaRepo;
        this.movMapper = m;
    }

    List<MovimientoResponseDTO> getMovimientosByCliente(LocalDate desde, LocalDate hasta, Long clienteId) {
        if (hasta.isBefore(desde)) {
            throw new BusinessException("Fecha hasta debe ser mayor o igual que fecha desde");
        }

        List<Long> cuentaIds = ctaRepo.findByClienteId(clienteId)
                .stream()
                .map(Cuenta::getId)
                .collect(Collectors.toList());

        if (cuentaIds.isEmpty()) {
            throw new EntityNotFoundException("No se encontraron cuentas para el cliente con id: " + clienteId);
        }

        List<Movimiento> movimientos = movRepo.findByCuentaIdsAndFechaBetween(cuentaIds, desde, hasta);

        return movimientos.stream()
                .map(movMapper::toResponse)
                .collect(Collectors.toList());

    }

    @Transactional
    MovimientoResponseDTO createMovimiento(MovimientoPostDTO request) {
        var cuentaId = request.getCuentaId();
        var monto = request.getMonto();
        var cuenta = ctaRepo.findById(cuentaId)
                .orElseThrow(() -> new EntityNotFoundException("Cuenta no encontrada con id: " + cuentaId));

        if (monto.compareTo(BigDecimal.ZERO) == 0) {
            throw new BusinessException("El monto no puede ser cero");
        }

        BigDecimal previousSaldo = this.movRepo.findTopByCuentaIdOrderByFechaDesc(cuentaId)
                .map(Movimiento::getSaldo)
                .orElse(cuenta.getSaldoInicial());

        BigDecimal newSaldo = previousSaldo.add(monto);

        if (newSaldo.compareTo(BigDecimal.ZERO) < 0) {
            throw new BusinessException("Saldo no disponible");
        }

        Movimiento movimiento = new Movimiento();
        var tipo = monto.compareTo(BigDecimal.ZERO) > 0 ? TipoMovimiento.CREDITO : TipoMovimiento.DEBITO;
        movimiento.setFecha(LocalDate.now());
        movimiento.setTipoMovimiento(tipo);
        movimiento.setValor(monto);
        movimiento.setSaldo(newSaldo);
        movimiento.setCuenta(cuenta);

        movimiento = movRepo.save(movimiento);
        return movMapper.toResponse(movimiento);
    }

    @Transactional
    public MovimientoResponseDTO updateMovimiento(MovimientoPutDTO request) {
        var movimientoId = request.getMovimientoId();
        var newMonto = request.getMonto();
        var movimiento = movRepo.findById(movimientoId)
                .orElseThrow(() -> new EntityNotFoundException("Movimiento no encontrado con id: " + movimientoId));

        if (newMonto.compareTo(BigDecimal.ZERO) == 0) {
            throw new BusinessException("El monto no puede ser cero");
        }

        var cuentaId = movimiento.getCuenta().getId();
        var fecha = movimiento.getFecha();

        BigDecimal previousSaldo = this.movRepo.findTopByCuentaIdAndFechaBefore(cuentaId, fecha, movimientoId)
                .map(Movimiento::getSaldo)
                .orElse(ctaRepo.findById(cuentaId)
                        .map(Cuenta::getSaldoInicial)
                        .orElseThrow(() -> new EntityNotFoundException("Cuenta no encontrada")));

        BigDecimal newSaldo = previousSaldo.add(newMonto);

        if (newSaldo.compareTo(BigDecimal.ZERO) < 0) {
            throw new BusinessException("Saldo no disponible");
        }

        var tipo = newMonto.compareTo(BigDecimal.ZERO) > 0 ? TipoMovimiento.CREDITO : TipoMovimiento.DEBITO;
        movimiento.setValor(newMonto);
        movimiento.setTipoMovimiento(tipo);
        movimiento.setSaldo(newSaldo);

        List<Movimiento> subsequent = movRepo.findSubsequentByCuentaId(cuentaId, fecha, movimientoId);
        var currentSaldo = newSaldo;

        for (Movimiento next : subsequent) {
            currentSaldo = currentSaldo.add(next.getValor());

            if (currentSaldo.compareTo(BigDecimal.ZERO) < 0) {
                throw new BusinessException("Saldo insuficiente en movimientos posteriores");
            }

            next.setSaldo(currentSaldo);
        }

        movRepo.save(movimiento);
        movRepo.saveAll(subsequent);
        return movMapper.toResponse(movimiento);
    }

    @Transactional
    public void deleteMovimiento(Long movimientoId) {
        var movimiento = movRepo.findById(movimientoId)
                .orElseThrow(() -> new EntityNotFoundException("Movimiento no encontrado con id: " + movimientoId));

        var cuentaId = movimiento.getCuenta().getId();
        var fecha = movimiento.getFecha();

        List<Movimiento> subsequent = movRepo.findSubsequentByCuentaId(cuentaId, fecha, movimientoId);
        var previousSaldo = this.movRepo.findTopByCuentaIdAndFechaBefore(cuentaId, fecha, movimientoId)
                .map(Movimiento::getSaldo)
                .orElse(ctaRepo.findById(cuentaId)
                        .map(Cuenta::getSaldoInicial)
                        .orElseThrow(() -> new EntityNotFoundException("Cuenta no encontrada")));

        for (Movimiento next : subsequent) {
            previousSaldo = previousSaldo.add(next.getValor());

            if (previousSaldo.compareTo(BigDecimal.ZERO) < 0) {
                throw new BusinessException("Saldo insuficiente en movimientos posteriores tras eliminación");
            }

            next.setSaldo(previousSaldo);
        }

        movRepo.deleteById(movimientoId);
        movRepo.saveAll(subsequent);
    }
}
