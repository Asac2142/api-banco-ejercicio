package banco.pichincha.web.cuenta;

public class Cuenta {
    private Long id;
    private String cuenta;
    private Tipo tipo;
    private Double saldo;
    private Boolean estado;

    public Cuenta(String cuenta, Tipo tipo, Double saldo, Boolean estado) {
        this.cuenta = cuenta;
        this.tipo = tipo;
        this.saldo = saldo;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
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

    @Override
    public String toString() {
        return "Cuenta{" +
                "id=" + id +
                ", cuenta='" + cuenta + '\'' +
                ", tipo=" + tipo +
                ", saldo=" + saldo +
                ", estado=" + estado +
                '}';
    }
}
