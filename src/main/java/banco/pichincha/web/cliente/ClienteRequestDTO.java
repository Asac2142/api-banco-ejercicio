
package banco.pichincha.web.cliente;

import banco.pichincha.web.persona.Genero;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ClienteRequestDTO {
    @NotBlank(message = "Nombre es mandatorio")
    @Size(max = 100, message = "Nombre debe ser menor a 100 caracteres")
    private String nombre;
    private Genero genero;

    @NotNull(message = "Edad es mandatorio")
    private Integer edad;

    @NotBlank(message = "Identificacion es mandatorio")
    @Size(max = 13, min = 10, message = "Identificacion debe ser al menos 10 caracteres y maximo 13 caracteres")
    private String identificacion;
    private String direccion;
    @Size(max = 10, min = 7, message = "Telefono deber tener al menos 7 digitos hasta 10 como maximo")
    private String telefono;

    @NotBlank(message = "Password es mandatorio")
    @Size(min = 8, message = "Password tiene que ser al menos 8 caracteres")
    private String password;
    private Boolean estado;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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
