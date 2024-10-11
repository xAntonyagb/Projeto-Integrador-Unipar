package br.unipar.assetinsight.mappers;

import br.unipar.assetinsight.dtos.requests.TarefaRequest;
import br.unipar.assetinsight.dtos.responses.TarefaResponse;
import br.unipar.assetinsight.entities.AmbienteEntity;
import br.unipar.assetinsight.entities.CategoriaEntity;
import br.unipar.assetinsight.entities.TarefaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(uses = {CategoriaMapper.class, AmbienteMapper.class, UsuarioMapper.class})
public interface TarefaMapper {
    TarefaMapper INSTANCE = Mappers.getMapper(TarefaMapper.class);

    @Mapping(source = "categoria", target = "categoriaEntity")
    @Mapping(source = "ambiente", target = "ambienteEntity")
    @Mapping(source = "previsao", target = "dtPrevisao")
    TarefaEntity toEntity(TarefaRequest request);

    @Mapping(source = "categoria", target = "categoriaEntity")
    @Mapping(source = "ambiente", target = "ambienteEntity")
    @Mapping(source = "lastChangedBy", target = "usuarioEntityCriador")
    @Mapping(source = "lastChange", target = "dtRecord")
    @Mapping(source = "previsao", target = "dtPrevisao")
    TarefaEntity toEntity(TarefaResponse response);


    @Mapping(source = "categoriaEntity", target = "categoria")
    @Mapping(source = "ambienteEntity", target = "ambiente")
    @Mapping(source = "dtPrevisao", target = "previsao")
    TarefaRequest toRequest(TarefaEntity entity);

    TarefaRequest toRequest(TarefaResponse response);


    @Mapping(source = "categoriaEntity", target = "categoria")
    @Mapping(source = "ambienteEntity", target = "ambiente")
    @Mapping(source = "usuarioEntityCriador", target = "lastChangedBy")
    @Mapping(source = "dtRecord", target = "lastChange")
    @Mapping(source = "dtPrevisao", target = "previsao")
    TarefaResponse toResponse(TarefaEntity entity);

    TarefaResponse toResponse(TarefaRequest request);


    TarefaEntity updateEntity(TarefaRequest request, @MappingTarget TarefaEntity entity);

    List<TarefaEntity> toEntityList(List<TarefaRequest> request);
    List<TarefaRequest> toRequestList(List<TarefaEntity> entity);
    List<TarefaResponse> toResponseList(List<TarefaEntity> entity);

    default Page<TarefaResponse> toResponsePage(Page<TarefaEntity> entityPage) {
        List<TarefaEntity> entities = entityPage.getContent();
        List<TarefaResponse> responses = toResponseList(entities);
        return new PageImpl<>(responses, entityPage.getPageable(), entityPage.getTotalElements());
    }



    default TarefaEntity mapLongToEntity(Long id) {
        if (id == null) {
            return null;
        }
        TarefaEntity entity = new TarefaEntity();
        entity.setId(id);
        return entity;
    }

    default Long mapEntityToLong(TarefaEntity entity) {
        if (entity == null) {
            return null;
        }
        return entity.getId();
    }
}