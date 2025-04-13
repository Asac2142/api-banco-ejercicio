package banco.pichincha.web.cliente;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findClienteByNombre(String name);

    Optional<Cliente> findClienteByIdentificacion(String identificacion);
}
