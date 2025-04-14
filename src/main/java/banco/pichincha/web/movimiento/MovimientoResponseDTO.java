package banco.pichincha.web.movimiento;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MovimientoResponseDTO {
    private final Long id;
    private final LocalDate fecha;
    private final String clienteNombre;
    private final String numeroCuenta;
    private final TipoMovimiento tipoMovimiento;
    private final BigDecimal saldo;
    private final Boolean estado;
    private final BigDecimal monto;
    private final BigDecimal saldoDisponible;

    public MovimientoResponseDTO(
            Long id,
            LocalDate fecha,
            String clienteNombre,
            String numeroCuenta,
            TipoMovimiento tipoMovimiento,
            BigDecimal saldo,
            Boolean estado,
            BigDecimal monto,
            BigDecimal saldoDisponible) {
        this.fecha = fecha;
        this.clienteNombre = clienteNombre;
        this.numeroCuenta = numeroCuenta;
        this.tipoMovimiento = tipoMovimiento;
        this.saldo = saldo;
        this.estado = estado;
        this.monto = monto;
        this.saldoDisponible = saldoDisponible;
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getClienteNombre() {
        return clienteNombre;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public TipoMovimiento getTipoMovimiento() {
        return tipoMovimiento;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public Boolean getEstado() {
        return estado;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public BigDecimal getSaldoDisponible() {
        return saldoDisponible;
    }

    public Long getId() {
        return this.id;
    }
}