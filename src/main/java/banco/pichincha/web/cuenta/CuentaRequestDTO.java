package banco.pichincha.web.cuenta;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CuentaRequestDTO {
    @NotNull
    private CuentaTipo tipo;
    @NotNull
    @Positive
    private BigDecimal saldo;

    public void setTipo(CuentaTipo t) {
        this.tipo = t;
    }

    public CuentaTipo getTipo() {
        return this.tipo;
    }

    public void setSaldo(BigDecimal s) {
        this.saldo = s;
    }

    public BigDecimal getSaldo() {
        return this.saldo;
    }
}
