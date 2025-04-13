package banco.pichincha.web.persona;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

// @Entity
// @Table
// @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Persona {
    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Genero genero;
    private Integer edad;
    private String identificacion;
    private String direccion;
    private String telefono;

    public Persona() {
    }

    public Persona(
            Long id,
            String nombre,
            Genero genero,
            Integer edad,
            String identificacion,
            String direccion,
            String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.edad = edad;
        this.identificacion = identificacion;
        this.telefono = telefono;
        this.genero = genero;
    }

    public Persona(
            String nombre,
            Genero genero,
            Integer edad,
            String identificacion,
            String direccion,
            String telefono) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.edad = edad;
        this.identificacion = identificacion;
        this.telefono = telefono;
        this.genero = genero;
    }

    public Long getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String n) {
        this.nombre = n;
    }

    public Genero getGenero() {
        return this.genero;
    }

    public void setGenero(Genero g) {
        this.genero = g;
    }

    public Integer getEdad() {
        return this.edad;
    }

    public void setEdad(Integer e) {
        this.edad = e;
    }

    public String getIdentificacion() {
        return this.identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String tlf) {
        this.telefono = tlf;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", genero=" + genero +
                ", edad=" + edad +
                ", identificacion='" + identificacion + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
