package banco.pichincha.web.cliente;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

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

    @GetMapping("/identificacion/{identificacion}")
    Optional<Cliente> getClienteByIdentificacion(@PathVariable String identificacion) {
        return this.cs.getClienteByIdentificacion(identificacion);
    }

    @GetMapping("/nombre/{nombre}")
    List<Cliente> getClienteByNombre(@PathVariable String nombre) {
        return this.cs.getClienteByNombre(nombre);
    }

    @PostMapping("")
    ResponseEntity<ClienteResponseDTO> createCliente(@Valid @RequestBody ClienteRequestDTO request) {
        var response = this.cs.createCliente(request);
        return ResponseEntity.status(201).body(response);
    }
}
