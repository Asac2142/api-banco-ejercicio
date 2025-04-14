package banco.pichincha.web.cliente;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import banco.pichincha.web.cuenta.Cuenta;
import banco.pichincha.web.cuenta.CuentaRepository;
import banco.pichincha.web.exception.BusinessException;
import banco.pichincha.web.utils.GenerateCuenta;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ClienteServicio {
    private final ClienteRepository clienteRep;
    private final ClienteMapper mapper;
    private final PasswordEncoder encoder;
    private final CuentaRepository cuentaRep;

    public ClienteServicio(
            ClienteRepository clienteRep,
            ClienteMapper mapper,
            PasswordEncoder ps,
            CuentaRepository cr) {
        this.clienteRep = clienteRep;
        this.mapper = mapper;
        this.encoder = ps;
        this.cuentaRep = cr;
    }

    public List<ClienteResponseDTO> getClientes() {
        var clientes = this.clienteRep.findAll();
        List<ClienteResponseDTO> res = Collections.emptyList();

        if (!clientes.isEmpty()) {
            return clienteRep.findAll()
                    .stream()
                    .map(mapper::toResponse)
                    .collect(Collectors.toList());
        }

        return res;
    }

    public List<ClienteResponseDTO> getClienteByNombre(String name) {
        var clientes = this.clienteRep.findClienteByNombre(name);
        List<ClienteResponseDTO> res = Collections.emptyList();

        if (!clientes.isEmpty()) {
            clientes.forEach((cliente) -> {
                var mapped = this.mapper.toResponse(cliente);
                res.add(mapped);
            });
        }

        return res;
    }

    public ClienteResponseDTO getClienteByIdentificacion(String identificacion) {
        var cliente = this.clienteRep.findClienteByIdentificacion(identificacion);
        if (cliente.isPresent()) {
            return this.mapper.toResponse(cliente.get());
        }

        return null;
    }

    @Transactional
    public ClienteResponseDTO createCliente(ClienteRequestDTO request) {
        if (this.clienteRep.existsByIdentificacion(request.getIdentificacion())) {
            throw new BusinessException("Identificacion de cliente ya existe: " + request.getIdentificacion());
        }

        var cliente = this.mapper.toEntity(request);
        cliente.setPassword(this.encoder.encode(request.getPassword()));
        cliente.setEstado(request.getEstado() != null ? request.getEstado() : true);

        cliente = this.clienteRep.save(cliente);
        saveCuenta(request, cliente);
        return this.mapper.toResponse(cliente);
    }

    @Transactional
    public ClienteResponseDTO updateCliente(ClienteRequestDTO request, Long id) {
        Cliente cliente = this.clienteRep
                .findById(id.longValue())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Cliente no encontrado con identificacion: " + request.getIdentificacion()));
        var clienteDiferente = cliente.getIdentificacion().equals(request.getIdentificacion());

        if (!clienteDiferente &&
                !this.clienteRep.existsByIdentificacion(request.getIdentificacion())) {
            throw new BusinessException("Cliente posee distinta identificacion");
        }

        cliente.setNombre(request.getNombre());
        cliente.setGenero(request.getGenero());
        cliente.setEdad(request.getEdad());
        cliente.setIdentificacion(request.getIdentificacion());
        cliente.setDireccion(request.getDireccion());
        cliente.setTelefono(request.getTelefono());

        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            cliente.setPassword(this.encoder.encode(request.getPassword()));
        }

        cliente.setEstado(request.getEstado() != null ? request.getEstado() : cliente.getEstado());
        cliente = this.clienteRep.save(cliente);
        return this.mapper.toResponse(cliente);
    }

    @Transactional
    public void deleteCliente(Long id) {
        if (!clienteRep.existsById(id)) {
            throw new EntityNotFoundException("Cliente no encontrado con id: " + id);
        }

        cuentaRep.deleteByClienteId(id);
        clienteRep.deleteById(id);
    }

    private Cuenta saveCuenta(ClienteRequestDTO request, Cliente cliente) {
        var cuenta = new Cuenta();
        cuenta.setNumeroCuenta(generateUniqueNumeroCuenta());
        cuenta.setTipoCuenta(request.getTipoCuenta());
        cuenta.setSaldoInicial(BigDecimal.valueOf(request.getSaldo()));
        cuenta.setEstado(true);
        cuenta.setCliente(cliente);

        return this.cuentaRep.save(cuenta);
    }

    private String generateUniqueNumeroCuenta() {
        String numeroCuenta = GenerateCuenta.generarCuenta();

        if (!this.cuentaRep.existsByNumeroCuenta(numeroCuenta)) {
            return numeroCuenta;
        }

        throw new BusinessException("No se pudo generar un número de cuenta único");
    }
}
