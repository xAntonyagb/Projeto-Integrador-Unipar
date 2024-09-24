package br.unipar.assetinsight.mappers;

import br.unipar.assetinsight.dtos.requests.BlocoRequest;
import br.unipar.assetinsight.dtos.responses.BlocoResponse;
import br.unipar.assetinsight.entities.BlocoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = UsuarioMapper.class)
public interface BlocoMapper {
    BlocoMapper INSTANCE = Mappers.getMapper(BlocoMapper.class);

    BlocoEntity toEntity(BlocoRequest request);

    @Mapping(source = "lastChange", target = "dtRecord")
    @Mapping(source = "lastChangedBy", target = "usuarioEntityCriador")
    BlocoEntity toEntity(BlocoResponse response);


    BlocoRequest toRequest(BlocoEntity entity);

    BlocoRequest toRequest(BlocoResponse response);


    @Mapping(source = "dtRecord", target = "lastChange")
    @Mapping(source = "usuarioEntityCriador", target = "lastChangedBy")
    BlocoResponse toResponse(BlocoEntity entity);

    BlocoResponse toResponse(BlocoRequest request);


    BlocoEntity updateEntity(BlocoRequest request, @MappingTarget BlocoEntity entity);

    List<BlocoEntity> toEntityList(List<BlocoRequest> request);
    List<BlocoRequest> toRequestList(List<BlocoEntity> entity);
    List<BlocoResponse> toResponseList(List<BlocoEntity> entity);
}