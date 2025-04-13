package banco.pichincha.web.cliente;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class ClienteServicio {
    private final ClienteRepository cr;

    public ClienteServicio(ClienteRepository cr) {
        this.cr = cr;
    }

    public List<Cliente> getClientes() {
        return this.cr.findAll();
    }

    public List<Cliente> getClienteByNombre(String name) {
        return this.cr.findClienteByNombre(name);
    }

    public Optional<Cliente> getClienteByIdentificacion(String identificacion) {
        return this.cr.findClienteByIdentificacion(identificacion);
    }
}
