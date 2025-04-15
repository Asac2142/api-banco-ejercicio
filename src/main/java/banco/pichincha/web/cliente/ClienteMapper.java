package banco.pichincha.web.cliente;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    @Mapping(target = "estado", ignore = true)
    @Mapping(target = "id", ignore = true)
    Cliente toEntity(ClienteRequestDTO request);

    ClienteResponseDTO toResponse(Cliente entity);
}
