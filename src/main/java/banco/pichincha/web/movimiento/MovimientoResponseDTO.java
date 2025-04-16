package banco.pichincha.web.movimiento;

import java.math.BigDecimal;
import java.time.LocalDate;

import banco.pichincha.web.cuenta.CuentaTipo;

public class MovimientoResponseDTO {
    private Long id;
    private Long cuentaId;
    private LocalDate fecha;
    private String clienteNombre;
    private String numeroCuenta;
    private TipoMovimiento tipoMovimiento;
    private CuentaTipo cuentaTipo;
    private BigDecimal saldo;
    private Boolean estado;
    private BigDecimal monto;
    private BigDecimal saldoDisponible;

    public MovimientoResponseDTO(
            Long id,
            Long cuentaId,
            LocalDate fecha,
            String clienteNombre,
            String numeroCuenta,
            TipoMovimiento tipoMovimiento,
            CuentaTipo cuentaTipo,
            BigDecimal saldo,
            Boolean estado,
            BigDecimal monto,
            BigDecimal saldoDisponible) {
        this.cuentaId = cuentaId;
        this.fecha = fecha;
        this.clienteNombre = clienteNombre;
        this.numeroCuenta = numeroCuenta;
        this.tipoMovimiento = tipoMovimiento;
        this.saldo = saldo;
        this.estado = estado;
        this.monto = monto;
        this.saldoDisponible = saldoDisponible;
        this.id = id;
        this.cuentaTipo = cuentaTipo;
    }

    public void setCuentaTipo(CuentaTipo ct) {
        this.cuentaTipo = ct;
    }

    public CuentaTipo getCuentaTipo() {
        return this.cuentaTipo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCuentaId(Long cuentaId) {
        this.cuentaId = cuentaId;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setClienteNombre(String clienteNombre) {
        this.clienteNombre = clienteNombre;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public void setTipoMovimiento(TipoMovimiento tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public void setSaldoDisponible(BigDecimal saldoDisponible) {
        this.saldoDisponible = saldoDisponible;
    }

    public Long getCuentaId() {
        return this.cuentaId;
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