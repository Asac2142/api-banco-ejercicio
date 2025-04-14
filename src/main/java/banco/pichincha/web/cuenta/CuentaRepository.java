package banco.pichincha.web.cuenta;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    boolean existsByNumeroCuenta(String numeroCuenta);

    @Modifying
    @Query("DELETE FROM Cuenta c WHERE c.cliente.id = :clienteId")
    void deleteByClienteId(Long clienteId);

    Optional<Cuenta> findByNumeroCuenta(String numeroCuenta);
}
