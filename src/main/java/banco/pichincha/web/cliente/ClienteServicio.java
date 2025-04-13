package banco.pichincha.web.cliente;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import banco.pichincha.web.exception.BusinessException;

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
}
