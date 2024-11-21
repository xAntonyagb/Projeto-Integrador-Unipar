package br.unipar.assetinsight.mappers;

import br.unipar.assetinsight.dtos.requests.BlocoRequest;
import br.unipar.assetinsight.dtos.responses.principal.BlocoResponse;
import br.unipar.assetinsight.entities.BlocoEntity;
import br.unipar.assetinsight.utils.DataUtils;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(uses = UsuarioMapper.class)
public interface BlocoMapper {
    BlocoMapper INSTANCE = Mappers.getMapper(BlocoMapper.class);

    @AfterMapping
    default void setDtRecord(BlocoRequest request, @MappingTarget BlocoEntity entity) {
        entity.setDtRecord(DataUtils.getNow());
    }

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

    default Page<BlocoResponse> toResponsePage(Page<BlocoEntity> entityPage) {
        List<BlocoEntity> entities = entityPage.getContent();
        List<BlocoResponse> responses = toResponseList(entities);
        return new PageImpl<>(responses, entityPage.getPageable(), entityPage.getTotalElements());
    }

    default BlocoEntity mapLongToEntity(Long id) {
        if (id == null) {
            return null;
        }
        BlocoEntity entity = new BlocoEntity();
        entity.setId(id);
        return entity;
    }

    default Long mapEntityToLong(BlocoEntity entity) {
        if (entity == null) {
            return null;
        }
        return entity.getId();
    }
}
