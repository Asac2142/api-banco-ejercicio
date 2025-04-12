package banco.pichincha.web.movimiento;

import java.time.LocalDate;

public class Movimiento {
    private LocalDate fecha;
    private Double valor;
    private Double saldo;
    private MTipo tipo;

    public Movimiento(LocalDate fecha, Double valor, Double saldo, MTipo tipo) {
        this.fecha = fecha;
        this.valor = valor;
        this.saldo = saldo;
        this.tipo = tipo;
    }

    public void setTipo(MTipo t) {
        this.tipo = t;
    }

    public MTipo getTipo() {
        return this.tipo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "Movimiento{" +
                "fecha=" + fecha +
                ", valor=" + valor +
                ", saldo=" + saldo +
                ", tipo=" + tipo +
                '}';
    }
}
