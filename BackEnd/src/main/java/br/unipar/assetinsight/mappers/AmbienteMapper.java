package br.unipar.assetinsight.mappers;

import br.unipar.assetinsight.dtos.requests.AmbienteRequest;
import br.unipar.assetinsight.dtos.responses.main.AmbienteResponse;
import br.unipar.assetinsight.dtos.responses.simple.AmbienteSimpleResponse;
import br.unipar.assetinsight.entities.AmbienteEntity;
import br.unipar.assetinsight.utils.DataUtils;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collections;
import java.util.List;

@Mapper(uses = {BlocoMapper.class, UsuarioMapper.class, PatrimonioMapper.class})
public interface AmbienteMapper {
    AmbienteMapper INSTANCE = Mappers.getMapper(AmbienteMapper.class);

    @AfterMapping
    default void setDtRecord(AmbienteRequest request, @MappingTarget AmbienteEntity entity) {
        entity.setDtRecord(DataUtils.getNow());
    }

    @Mapping(source = "bloco", target = "blocoEntity")
    @Mapping(source = "patrimonios", target = "listPatrimonioEntities")
    AmbienteEntity toEntity(AmbienteRequest request);

    @Mapping(source = "bloco", target = "blocoEntity")
    @Mapping(source = "patrimonios", target = "listPatrimonioEntities", qualifiedByName = "toEntityFromSimpleResponse")
    @Mapping(source = "lastChange", target = "dtRecord")
    @Mapping(source = "lastChangedBy", target = "usuarioEntityCriador")
    AmbienteEntity toEntity(AmbienteResponse response);


    @Mapping(source = "blocoEntity", target = "bloco")
    @Mapping(source = "listPatrimonioEntities", target = "patrimonios")
    AmbienteRequest toRequest(AmbienteEntity entity);

    AmbienteRequest toRequest(AmbienteResponse response);


    @Mapping(source = "blocoEntity", target = "bloco")
    @Mapping(source = "listPatrimonioEntities", target = "patrimonios")
    @Mapping(source = "dtRecord", target = "lastChange")
    @Mapping(source = "usuarioEntityCriador", target = "lastChangedBy")
    AmbienteResponse toResponse(AmbienteEntity entity);

    AmbienteResponse toResponse(AmbienteRequest request);

    AmbienteSimpleResponse toSimpleResponse(AmbienteEntity entity);
    AmbienteSimpleResponse toSimpleResponse(AmbienteRequest request);
    AmbienteSimpleResponse toSimpleResponse(AmbienteResponse response);
    AmbienteEntity simpleResponseToEntity(AmbienteSimpleResponse response);

    AmbienteEntity updateEntity(AmbienteRequest request, @MappingTarget AmbienteEntity entity);

    List<AmbienteEntity> toEntityList(List<AmbienteRequest> request);
    List<AmbienteRequest> toRequestList(List<AmbienteEntity> entity);
    List<AmbienteResponse> toResponseList(List<AmbienteEntity> entity);

    default Page<AmbienteResponse> toResponsePage(Page<AmbienteEntity> entityPage) {
        List<AmbienteEntity> entities = entityPage.getContent();
        List<AmbienteResponse> responses = toResponseList(entities);
        return new PageImpl<>(responses, entityPage.getPageable(), entityPage.getTotalElements());
    }


    default AmbienteEntity mapLongToEntity(Long id) {
        if (id == null) {
            return null;
        }
        AmbienteEntity entity = new AmbienteEntity();
        entity.setId(id);
        return entity;
    }

    default Long mapEntityToLong(AmbienteEntity entity) {
        if (entity == null) {
            return null;
        }
        return entity.getId();
    }
}
