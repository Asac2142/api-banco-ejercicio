package banco.pichincha.web.movimiento;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;

public class MovimientoPostDTO {
    @NotNull(message = "Monto es obligatorio")
    private BigDecimal monto;

    @NotNull(message = "Cuenta ID es obligatorio")
    private Long cuentaId;

    public MovimientoPostDTO(BigDecimal monto, Long cuentaId) {
        this.monto = monto;
        this.cuentaId = cuentaId;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public Long getCuentaId() {
        return cuentaId;
    }
}
