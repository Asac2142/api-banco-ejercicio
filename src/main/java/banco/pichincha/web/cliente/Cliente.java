package banco.pichincha.web.cliente;

import banco.pichincha.web.persona.Genero;
import banco.pichincha.web.persona.Persona;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "cliente")
@PrimaryKeyJoinColumn(name = "persona_id")
public class Cliente extends Persona {
    private String password;
    private Boolean estado;

    public Cliente(
            String password,
            Boolean estado,
            String nombre,
            Genero genero,
            Integer edad,
            String identificacion,
            String direccion,
            String telefono) {
        super(nombre, genero, edad, identificacion, direccion, telefono);
        this.password = password;
        this.estado = estado;
    }

    public Cliente() {
    }

    public void setPassword(String psw) {
        this.password = psw;
    }

    public String getPassword() {
        return this.password;
    }

    public void setEstado(Boolean est) {
        this.estado = est;
    }

    public Boolean getEstado() {
        return this.estado;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "estado=" + estado +
                '}';
    }
}
