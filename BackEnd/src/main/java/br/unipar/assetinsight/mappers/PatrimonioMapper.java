package br.unipar.assetinsight.mappers;

import br.unipar.assetinsight.dtos.requests.PatrimonioRequest;
import br.unipar.assetinsight.dtos.responses.principal.PatrimonioResponse;
import br.unipar.assetinsight.dtos.responses.simple.AmbienteSimpleResponse;
import br.unipar.assetinsight.dtos.responses.simple.PatrimonioSimpleResponse;
import br.unipar.assetinsight.entities.AmbienteEntity;
import br.unipar.assetinsight.entities.PatrimonioEntity;
import br.unipar.assetinsight.utils.DataUtils;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(uses = UsuarioMapper.class)
public interface PatrimonioMapper {
    PatrimonioMapper INSTANCE = Mappers.getMapper(PatrimonioMapper.class);

    @AfterMapping
    default void setDtRecord(PatrimonioRequest request, @MappingTarget PatrimonioEntity entity) {
        entity.setDtRecord(DataUtils.getNow());
    }

    @Mapping(source = "patrimonio", target = "id")
    @Mapping(source = "ambiente", target = "ambienteEntity")
    PatrimonioEntity toEntity(PatrimonioRequest request);

    @Mapping(source = "lastChange", target = "dtRecord")
    @Mapping(source = "ambiente", target = "ambienteEntity")
    @Mapping(source = "lastChangedBy", target = "usuarioEntityCriador")
    PatrimonioEntity toEntity(PatrimonioResponse response);
    PatrimonioRequest toRequest(PatrimonioEntity entity);
    PatrimonioRequest toRequest(PatrimonioResponse response);


    @Mapping(source = "dtRecord", target = "lastChange")
    @Mapping(source = "ambienteEntity", target = "ambiente")
    @Mapping(source = "usuarioEntityCriador", target = "lastChangedBy")
    PatrimonioResponse toResponse(PatrimonioEntity entity);
    PatrimonioResponse toResponse(PatrimonioRequest request);

    @Named("toSimpleResponseFromEntity")
    @Mapping(source = "id", target = "patrimonio")
    PatrimonioSimpleResponse toSimpleResponse(PatrimonioEntity entity);

    @Named("toSimpleResponseFromRequest")
    PatrimonioSimpleResponse toSimpleResponse(PatrimonioRequest request);

    @Named("toSimpleResponseFromResponse")
    PatrimonioSimpleResponse toSimpleResponse(PatrimonioResponse response);

    @Named("toEntityFromSimpleResponse")
    @Mapping(source = "patrimonio", target = "id")
    PatrimonioEntity toEntityFromSimpleResponse(PatrimonioSimpleResponse response);

    @Named("toEntityFromSimpleRequest")
    @Mapping(source = "patrimonio", target = "id")
    PatrimonioEntity toEntityFromSimpleRequest (PatrimonioSimpleResponse request);


    PatrimonioEntity updateEntity(PatrimonioRequest request, @MappingTarget PatrimonioEntity entity);

    List<PatrimonioEntity> toEntityList(List<Long> request);
    List<PatrimonioRequest> toRequestList(List<PatrimonioEntity> entity);
    List<PatrimonioResponse> toResponseList(List<PatrimonioEntity> entity);

    @Mapping(source = "patrimonio", target = "id")
    PatrimonioEntity toEntity(PatrimonioSimpleResponse response);

    default Page<PatrimonioResponse> toResponsePage(Page<PatrimonioEntity> entityPage) {
        List<PatrimonioEntity> entities = entityPage.getContent();
        List<PatrimonioResponse> responses = toResponseList(entities);
        return new PageImpl<>(responses, entityPage.getPageable(), entityPage.getTotalElements());
    }

    /* Feche os olhos, a partir daq é só dor, tristeza e go horse */

    default PatrimonioEntity mapLongToEntity(Long id) {
        if (id == null) {
            return null;
        }
        PatrimonioEntity entity = new PatrimonioEntity();
        entity.setId(id);
        return entity;
    }

    default Long mapEntityToLong(PatrimonioEntity entity) {
        if (entity == null) {
            return null;
        }
        return entity.getId();
    }

    default List<PatrimonioSimpleResponse> toSimpleResponseList(List<PatrimonioEntity> entity) {
        return (entity == null)
            ? new ArrayList<>()
            : entity.stream().map(this::toSimpleResponse).collect(Collectors.toList());
    }

    default AmbienteSimpleResponse mapLongToSimpleAmbienteResponse(Long id) {
        return (id == null)
            ? null
            : new AmbienteSimpleResponse(id, null);
    }

    default Long mapSimpleAmbienteResponseToLong(AmbienteSimpleResponse simpleResponse) {
        return (simpleResponse == null)
            ? null
            : simpleResponse.id();
    }

    default AmbienteEntity mapLongToAmbienteEntity(Long value) {
        if (value == null) {
            return null;
        }
        AmbienteEntity entity = new AmbienteEntity();
        entity.setId(value);
        return entity;
    }

}
