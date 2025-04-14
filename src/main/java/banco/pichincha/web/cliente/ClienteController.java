package banco.pichincha.web.cliente;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@RestController
@RequestMapping(path = "api/v1/clientes")
public class ClienteController {
    private final ClienteServicio cs;

    public ClienteController(ClienteServicio cs) {
        this.cs = cs;
    }

    @GetMapping("")
    ResponseEntity<List<ClienteResponseDTO>> getClientes() {
        var res = this.cs.getClientes();
        return ResponseEntity.status(200).body(res);
    }

    @GetMapping("/identificacion/{identificacion}")
    ResponseEntity<ClienteResponseDTO> getClienteByIdentificacion(@PathVariable String identificacion) {
        var res = this.cs.getClienteByIdentificacion(identificacion);
        if (res != null) {
            return ResponseEntity.status(200).body(res);
        }

        return ResponseEntity.status(404).body(null);
    }

    @GetMapping("/nombre/{nombre}")
    ResponseEntity<List<ClienteResponseDTO>> getClienteByNombre(@PathVariable String nombre) {
        var res = this.cs.getClienteByNombre(nombre);
        return ResponseEntity.status(200).body(res);
    }

    @PostMapping("")
    ResponseEntity<ClienteResponseDTO> createCliente(@Valid @RequestBody ClienteRequestDTO request) {
        var res = this.cs.createCliente(request);
        return ResponseEntity.status(201).body(res);
    }

    @PutMapping("/{id}")
    ResponseEntity<ClienteResponseDTO> updateCliente(
            @Valid @RequestBody ClienteRequestDTO request,
            @PathVariable @Positive(message = "Id tiene que ser mayor que cero") Long id) {
        var res = this.cs.updateCliente(request, id);
        return ResponseEntity.status(200).body(res);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Long> deleteCliente(@PathVariable @Positive(message = "Id tiene que ser mayor que cero") Long id) {
        this.cs.deleteCliente(id);
        return ResponseEntity.status(200).body(id);
    }
}
