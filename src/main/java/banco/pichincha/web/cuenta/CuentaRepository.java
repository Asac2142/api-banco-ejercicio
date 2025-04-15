package banco.pichincha.web.cuenta;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    boolean existsByNumeroCuenta(String numeroCuenta);

    @Modifying
    @Query("DELETE FROM Cuenta c WHERE c.cliente.id = :clienteId")
    void deleteByClienteId(Long clienteId);

    @Query("SELECT cta FROM Cuenta cta WHERE LOWER(cta.numeroCuenta) LIKE LOWER(CONCAT('%', :numeroCuenta, '%'))")
    List<Cuenta> findByNumeroCuenta(@Param("numeroCuenta") String numeroCuenta);

    List<Cuenta> findByClienteId(Long clienteId);
}
