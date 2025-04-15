package banco.pichincha.web.cuenta;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import banco.pichincha.web.cliente.ClienteRepository;
import banco.pichincha.web.exception.BusinessException;
import banco.pichincha.web.utils.GenerateCuenta;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class CuentaServicio {
    private final CuentaRepository ctaRepo;
    private final CuentaMapper ctaMapper;
    private final ClienteRepository cltRepo;

    public CuentaServicio(CuentaRepository cr, ClienteRepository clr, CuentaMapper cm) {
        this.ctaRepo = cr;
        this.cltRepo = clr;
        this.ctaMapper = cm;
    }

    List<CuentaResponseDTO> getCuentaByNumeroCta(String nroCta) {
        var ctas = this.ctaRepo.findByNumeroCuenta(nroCta);
        List<CuentaResponseDTO> res = new ArrayList<>();

        if (!ctas.isEmpty()) {
            ctas.forEach((cuenta) -> {
                var mapped = this.ctaMapper.toResponse(cuenta);
                res.add(mapped);
            });
        }

        return res;
    }

    List<CuentaResponseDTO> getCuentas() {
        var ctas = this.ctaRepo.findAll();
        return ctas.stream().map(ctaMapper::toResponse).collect(Collectors.toList());
    }

    @Transactional
    CuentaResponseDTO createCuenta(CuentaRequestDTO request, Long clienteId) {
        var cliente = this.cltRepo.findById(clienteId)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no existe con id: " + clienteId));

        var cuenta = new Cuenta();
        var nroCta = GenerateCuenta.generarCuenta();
        var existCuenta = this.ctaRepo.existsByNumeroCuenta(nroCta);

        if (existCuenta) {
            throw new BusinessException("Ya existe una cuenta con ese numero");
        }

        cuenta.setNumeroCuenta(nroCta);
        cuenta.setCliente(cliente);
        cuenta.setEstado(true);
        cuenta.setSaldoInicial(request.getSaldo());
        cuenta.setTipoCuenta(request.getTipo());

        cuenta = this.ctaRepo.save(cuenta);
        return this.ctaMapper.toResponse(cuenta);
    }

    @Transactional
    CuentaResponseDTO updateCuenta(CuentaRequestDTO request, Long cuentaId) {
        var cuenta = this.ctaRepo.findById(cuentaId)
                .orElseThrow(() -> new EntityNotFoundException("Cuenta no encontrada con Id: " + cuentaId));

        cuenta.setTipoCuenta(request.getTipo());
        cuenta.setSaldoInicial(request.getSaldo());
        cuenta = this.ctaRepo.save(cuenta);
        return this.ctaMapper.toResponse(cuenta);
    }

    @Transactional
    void deleteCuenta(Long cuentaId) {
        if (!this.ctaRepo.existsById(cuentaId)) {
            throw new EntityNotFoundException("Cuenta no encontrada con Id: " + cuentaId);
        }

        this.ctaRepo.deleteById(cuentaId);
    }
}
