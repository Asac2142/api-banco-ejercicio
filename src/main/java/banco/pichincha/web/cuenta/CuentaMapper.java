package banco.pichincha.web.cuenta;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CuentaMapper {
    @Mapping(source = "tipoCuenta", target = "tipo")
    @Mapping(source = "cliente.nombre", target = "clienteNombre")
    @Mapping(source = "cliente.id", target = "clienteId")
    @Mapping(source = "saldoInicial", target = "saldo")
    @Mapping(source = "id", target = "cuentaId")
    CuentaResponseDTO toResponse(Cuenta cuenta);
}
