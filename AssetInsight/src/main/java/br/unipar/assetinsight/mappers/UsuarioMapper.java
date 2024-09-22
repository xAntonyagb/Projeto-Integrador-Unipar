package br.unipar.assetinsight.mappers;

import br.unipar.assetinsight.dtos.responses.UsuarioResponse;
import br.unipar.assetinsight.entities.UsuarioEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = RoleMapper.class)
public interface UsuarioMapper {
    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    @Mapping(target = "listRoles", source = "permissoes")
    @Mapping(target = "dtRecord", source = "dtCriacao")
    @Mapping(target = "dtLogin", source = "lastLogin")
    UsuarioEntity toEntity(UsuarioResponse request);

    @Mapping(target = "permissoes", source = "listRoles")
    @Mapping(target = "dtCriacao", source = "dtRecord")
    @Mapping(target = "lastLogin", source = "dtLogin")
    UsuarioResponse toResponse(UsuarioEntity entity);

}
