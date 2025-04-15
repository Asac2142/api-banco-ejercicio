package banco.pichincha.web.cliente;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    @Query("SELECT c FROM Cliente c INNER JOIN Persona p ON p.id = c.id WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Cliente> findClienteByNombre(@Param("nombre") String nombre);

    Optional<Cliente> findClienteByIdentificacion(String identificacion);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END " +
            "FROM Cliente c " +
            "WHERE c.identificacion = ?1")
    boolean existsByIdentificacion(String identificacion);
}
