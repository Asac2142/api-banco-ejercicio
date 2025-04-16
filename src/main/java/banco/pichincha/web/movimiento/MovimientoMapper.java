package banco.pichincha.web.movimiento;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MovimientoMapper {
    @Mapping(target = "clienteNombre", source = "cuenta.cliente.nombre")
    @Mapping(target = "numeroCuenta", source = "cuenta.numeroCuenta")
    @Mapping(target = "tipoMovimiento", source = "tipoMovimiento")
    @Mapping(target = "monto", source = "valor")
    @Mapping(target = "saldoDisponible", source = "saldo")
    @Mapping(target = "estado", source = "cuenta.estado")
    @Mapping(target = "cuentaId", source = "cuenta.id")
    @Mapping(target = "cuentaTipo", source = "cuenta.tipoCuenta")
    MovimientoResponseDTO toResponse(Movimiento movimiento);
}
