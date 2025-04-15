package banco.pichincha.web.cuenta;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping(path = "api/v1/cuentas")
public class CuentaController {
    private final CuentaServicio ctaServicio;

    public CuentaController(CuentaServicio cuentaServicio) {
        this.ctaServicio = cuentaServicio;
    }

    @GetMapping("")
    ResponseEntity<List<CuentaResponseDTO>> getCuentas() {
        return ResponseEntity.status(200).body(this.ctaServicio.getCuentas());
    }

    @GetMapping("/{ctaNro}")
    ResponseEntity<List<CuentaResponseDTO>> getCuentaByCtaNro(@PathVariable String ctaNro) {
        var res = this.ctaServicio.getCuentaByNumeroCta(ctaNro);
        return ResponseEntity.status(200).body(res);
    }

    @PostMapping("/{id}")
    ResponseEntity<CuentaResponseDTO> createCuenta(
            @Valid @RequestBody CuentaRequestDTO request,
            @PathVariable Long id) {
        var res = this.ctaServicio.createCuenta(request, id);
        return ResponseEntity.status(201).body(res);
    }

    @PutMapping("/{ctaId}")
    ResponseEntity<CuentaResponseDTO> updateCuenta(
            @Valid @RequestBody CuentaRequestDTO request,
            @PathVariable Long ctaId) {
        var res = this.ctaServicio.updateCuenta(request, ctaId);
        return ResponseEntity.status(200).body(res);
    }

    @DeleteMapping("/{ctaId}")
    ResponseEntity<Long> deleteCuenta(@PathVariable Long ctaId) {
        this.ctaServicio.deleteCuenta(ctaId);
        return ResponseEntity.status(200).body(ctaId);
    }
}
