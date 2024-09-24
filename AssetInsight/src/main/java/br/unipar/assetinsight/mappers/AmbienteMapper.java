package br.unipar.assetinsight.mappers;

import br.unipar.assetinsight.dtos.requests.AmbienteRequest;
import br.unipar.assetinsight.dtos.responses.AmbienteResponse;
import br.unipar.assetinsight.entities.AmbienteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = {BlocoMapper.class, UsuarioMapper.class})
public interface AmbienteMapper {
    AmbienteMapper INSTANCE = Mappers.getMapper(AmbienteMapper.class);

    @Mapping(source = "bloco", target = "blocoEntity")
    AmbienteEntity toEntity(AmbienteRequest request);

    @Mapping(source = "bloco", target = "blocoEntity")
    @Mapping(source = "lastChange", target = "dtRecord")
    @Mapping(source = "lastChangedBy", target = "usuarioEntityCriador")
    AmbienteEntity toEntity(AmbienteResponse response);


    @Mapping(source = "blocoEntity", target = "bloco")
    AmbienteRequest toRequest(AmbienteEntity entity);

    AmbienteRequest toRequest(AmbienteResponse response);


    @Mapping(source = "blocoEntity", target = "bloco")
    @Mapping(source = "dtRecord", target = "lastChange")
    @Mapping(source = "usuarioEntityCriador", target = "lastChangedBy")
    AmbienteResponse toResponse(AmbienteEntity entity);

    AmbienteResponse toResponse(AmbienteRequest request);


    AmbienteEntity updateEntity(AmbienteRequest request, @MappingTarget AmbienteEntity entity);

    List<AmbienteEntity> toEntityList(List<AmbienteRequest> request);
    List<AmbienteRequest> toRequestList(List<AmbienteEntity> entity);
    List<AmbienteResponse> toResponseList(List<AmbienteEntity> entity);
}
