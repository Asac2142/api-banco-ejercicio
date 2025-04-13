package banco.pichincha.web.cliente;

import banco.pichincha.web.persona.Genero;
import banco.pichincha.web.persona.Persona;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "cliente")
@PrimaryKeyJoinColumn(name = "id")
public class Cliente extends Persona {

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "estado")
    private Boolean estado;

    public Cliente() {
    }

    public Cliente(
            String nombre,
            Genero genero,
            Integer edad,
            String identificacion,
            String direccion,
            String telefono,
            String password,
            Boolean estado) {
        super(nombre, genero, edad, identificacion, direccion, telefono);
        this.password = password;
        this.estado = estado;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}