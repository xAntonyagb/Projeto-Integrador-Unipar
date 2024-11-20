package br.unipar.assetinsight.mappers;


import br.unipar.assetinsight.dtos.requests.CategoriaRequest;
import br.unipar.assetinsight.dtos.responses.main.CategoriaResponse;
import br.unipar.assetinsight.entities.CategoriaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(uses = UsuarioMapper.class)
public interface CategoriaMapper {
    CategoriaMapper INSTANCE = Mappers.getMapper(CategoriaMapper.class);

    CategoriaEntity toEntity(CategoriaRequest request);

    @Mapping(source = "lastChange", target = "dtRecord")
    @Mapping(source = "lastChangedBy", target = "usuarioEntityCriador")
    CategoriaEntity toEntity(CategoriaResponse response);


    CategoriaRequest toRequest(CategoriaEntity entity);

    CategoriaRequest toRequest(CategoriaResponse response);


    @Mapping(source = "dtRecord", target = "lastChange")
    @Mapping(source = "usuarioEntityCriador", target = "lastChangedBy")
    CategoriaResponse toResponse(CategoriaEntity entity);

    CategoriaResponse toResponse(CategoriaRequest request);


    CategoriaEntity updateEntity(CategoriaRequest request, @MappingTarget CategoriaEntity entity);

    List<CategoriaEntity> toEntityList(List<CategoriaRequest> request);
    List<CategoriaRequest> toRequestList(List<CategoriaEntity> entity);
    List<CategoriaResponse> toResponseList(List<CategoriaEntity> entity);

    default Page<CategoriaResponse> toResponsePage(Page<CategoriaEntity> entityPage) {
        List<CategoriaEntity> entities = entityPage.getContent();
        List<CategoriaResponse> responses = toResponseList(entities);
        return new PageImpl<>(responses, entityPage.getPageable(), entityPage.getTotalElements());
    }


    default CategoriaEntity mapLongToEntity(Long id) {
        if (id == null) {
            return null;
        }
        CategoriaEntity entity = new CategoriaEntity();
        entity.setId(id);
        return entity;
    }

    default Long mapEntityToLong(CategoriaEntity entity) {
        if (entity == null) {
            return null;
        }
        return entity.getId();
    }
}