package banco.pichincha.web.cuenta;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CuentaMapper {
    @Mapping(source = "tipoCuenta", target = "tipo")
    @Mapping(source = "cliente.nombre", target = "clienteNombre")
    @Mapping(source = "saldoInicial", target = "saldo")
    CuentaResponseDTO toResponse(Cuenta cuenta);
}
