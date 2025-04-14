package banco.pichincha.web.cuenta;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CuentaRequestDTO {
    @NotNull
    private Tipo tipo;
    @NotNull
    @Positive
    private BigDecimal saldo;

    public void setTipo(Tipo t) {
        this.tipo = t;
    }

    public Tipo getTipo() {
        return this.tipo;
    }

    public void setSaldo(BigDecimal s) {
        this.saldo = s;
    }

    public BigDecimal getSaldo() {
        return this.saldo;
    }
}
