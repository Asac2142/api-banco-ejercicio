package banco.pichincha.web.cliente;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import banco.pichincha.web.exception.BusinessException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ClienteServicio {
    private final ClienteRepository clienteRep;
    private final ClienteMapper mapper;
    private final PasswordEncoder encoder;

    public ClienteServicio(ClienteRepository clienteRep, ClienteMapper mapper, PasswordEncoder ps) {
        this.clienteRep = clienteRep;
        this.mapper = mapper;
        this.encoder = ps;
    }

    public List<Cliente> getClientes() {
        return this.clienteRep.findAll();
    }

    public List<Cliente> getClienteByNombre(String name) {
        return this.clienteRep.findClienteByNombre(name);
    }

    public Optional<Cliente> getClienteByIdentificacion(String identificacion) {
        return this.clienteRep.findClienteByIdentificacion(identificacion);
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
        return this.mapper.toResponse(cliente);
    }

    public ClienteResponseDTO updateCliente(ClienteRequestDTO request, Long id) {
        var cliente = this.clienteRep
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
}
