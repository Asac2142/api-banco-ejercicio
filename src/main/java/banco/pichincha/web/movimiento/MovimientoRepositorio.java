package banco.pichincha.web.movimiento;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface MovimientoRepositorio extends JpaRepository<Movimiento, Long> {
        List<Movimiento> findAllByCuentaId(Long cuentaId);

        @Query("SELECT m FROM Movimiento m WHERE m.cuenta.id IN :cuentaIds AND m.fecha BETWEEN :desde AND :hasta")
        List<Movimiento> findByCuentaIdsAndFechaBetween(
                        @Param("cuentaIds") List<Long> cuentaIds,
                        @Param("desde") LocalDate desde,
                        @Param("hasta") LocalDate hasta);

        @Query(value = "SELECT * FROM movimientos WHERE cuenta_id = :cuentaId ORDER BY fecha DESC, id DESC LIMIT 1", nativeQuery = true)
        Optional<Movimiento> findTopByCuentaIdOrderByFechaDesc(@Param("cuentaId") Long cuentaId);

        @Query("SELECT m FROM Movimiento m WHERE m.cuenta.id = :cuentaId AND (m.fecha > :fecha OR (m.fecha = :fecha AND m.id > :movimientoId)) ORDER BY m.fecha ASC, m.id ASC")
        List<Movimiento> findSubsequentByCuentaId(
                        @Param("cuentaId") Long cuentaId,
                        @Param("fecha") LocalDate fecha,
                        @Param("movimientoId") Long movimientoId);

        @Query("SELECT m FROM Movimiento m WHERE m.cuenta.id = :cuentaId AND m.fecha < :fecha OR (m.fecha = :fecha AND m.id < :movimientoId) ORDER BY m.fecha DESC, m.id DESC LIMIT 1")
        Optional<Movimiento> findTopByCuentaIdAndFechaBefore(
                        @Param("cuentaId") Long cuentaId,
                        @Param("fecha") LocalDate fecha,
                        @Param("movimientoId") Long movimientoId);

        @Modifying
        @Query("DELETE FROM Movimiento m WHERE m.cuenta.id = :cuentaId")
        void deleteAllByCuentaId(@Param("cuentaId") Long cuentaId);
}
