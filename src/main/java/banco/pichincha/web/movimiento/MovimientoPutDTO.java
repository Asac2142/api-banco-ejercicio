package banco.pichincha.web.movimiento;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;

public class MovimientoPutDTO {
    @NotNull(message = "Movimiento ID es obligatorio")
    private Long movimientoId;

    @NotNull(message = "Monto es obligatorio")
    private BigDecimal monto;

    public MovimientoPutDTO(Long movimientoId, BigDecimal monto) {
        this.movimientoId = movimientoId;
        this.monto = monto;
    }

    public Long getMovimientoId() {
        return movimientoId;
    }

    public BigDecimal getMonto() {
        return monto;
    }
}
