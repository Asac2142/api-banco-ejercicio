package banco.pichincha.web.movimiento;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping(path = "api/v1/movimientos")
public class MovimientoController {
    private final MovimientoService movService;

    public MovimientoController(MovimientoService ms) {
        this.movService = ms;
    }

    @GetMapping("/cliente/{clienteId}/movimientos")
    ResponseEntity<List<MovimientoResponseDTO>> getMovimientosByCliente(
            @PathVariable Long clienteId,
            @RequestParam @NotNull(message = "Fecha desde es obligatoria") LocalDate desde,
            @RequestParam @NotNull(message = "Fecha hasta es obligatoria") LocalDate hasta) {
        var movimientos = movService.getMovimientosByCliente(desde, hasta, clienteId);
        return ResponseEntity.ok(movimientos);
    }

    @PostMapping("")
    ResponseEntity<MovimientoResponseDTO> createMovimiento(@Valid @RequestBody MovimientoPostDTO request) {
        var movimiento = movService.createMovimiento(request);
        return ResponseEntity.status(201).body(movimiento);
    }

    @PutMapping("")
    ResponseEntity<MovimientoResponseDTO> updateMovimiento(@Valid @RequestBody MovimientoPutDTO request) {
        var movimiento = movService.updateMovimiento(request);
        return ResponseEntity.ok(movimiento);
    }

    @DeleteMapping("/{movimientoId}")
    ResponseEntity<Void> deleteMovimiento(@PathVariable Long movimientoId) {
        movService.deleteMovimiento(movimientoId);
        return ResponseEntity.noContent().build();
    }
}
