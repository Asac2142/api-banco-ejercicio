package banco.pichincha.web.cliente;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/clientes")
public class ClienteController {
    private final ClienteServicio cs;

    public ClienteController(ClienteServicio cs) {
        this.cs = cs;
    }

    @GetMapping("")
    List<Cliente> getClientes() {
        return this.cs.getClientes();
    }
}
