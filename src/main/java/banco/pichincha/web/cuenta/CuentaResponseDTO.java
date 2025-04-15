package banco.pichincha.web.cuenta;

public class CuentaResponseDTO {
    private String numeroCuenta;
    private CuentaTipo tipo;
    private Double saldo;
    private Boolean estado;
    private String clienteNombre;
    private Long clienteId;
    private Long cuentaId;

    public void setClienteId(Long id) {
        this.clienteId = id;
    }

    public Long getClienteId() {
        return this.clienteId;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public CuentaTipo getTipo() {
        return tipo;
    }

    public void setTipo(CuentaTipo tipo) {
        this.tipo = tipo;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getClienteNombre() {
        return clienteNombre;
    }

    public void setClienteNombre(String clienteNombre) {
        this.clienteNombre = clienteNombre;
    }

    public void setCuentaId(Long ctaId) {
        this.cuentaId = ctaId;
    }

    public Long getCuentaId() {
        return this.cuentaId;
    }
}
