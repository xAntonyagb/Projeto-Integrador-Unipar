package br.unipar.assetinsight.mappers;

import br.unipar.assetinsight.dtos.requests.OrdemServicoRequest;
import br.unipar.assetinsight.dtos.responses.OrdemServicoResponse;
import br.unipar.assetinsight.entities.OrdemServicoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(uses = {UsuarioMapper.class, ServicoMapper.class})
public interface OrdemServicoMapper {
    OrdemServicoMapper INSTANCE = Mappers.getMapper(OrdemServicoMapper.class);

    @Mapping(source = "servicos", target = "listServicoEntities")
    OrdemServicoEntity toEntity(OrdemServicoRequest request);

    @Mapping(source = "servicos", target = "listServicoEntities")
    @Mapping(source = "lastChangedBy", target = "usuarioEntityCriador")
    @Mapping(source = "lastChange", target = "dtRecord")
    OrdemServicoEntity toEntity(OrdemServicoResponse response);


    @Mapping(source = "listServicoEntities", target = "servicos")
    OrdemServicoRequest toRequest(OrdemServicoEntity entity);

    OrdemServicoRequest toRequest(OrdemServicoResponse response);


    @Mapping(source = "listServicoEntities", target = "servicos")
    @Mapping(source = "usuarioEntityCriador", target = "lastChangedBy")
    @Mapping(source = "dtRecord", target = "lastChange")
    OrdemServicoResponse toResponse(OrdemServicoEntity entity);

    OrdemServicoResponse toResponse(OrdemServicoRequest request);


    OrdemServicoEntity updateEntity(OrdemServicoRequest request, @MappingTarget OrdemServicoEntity entity);

    List<OrdemServicoEntity> toEntityList(List<OrdemServicoRequest> request);
    List<OrdemServicoRequest> toRequestList(List<OrdemServicoEntity> entity);
    List<OrdemServicoResponse> toResponseList(List<OrdemServicoEntity> entity);

    default Page<OrdemServicoResponse> toResponsePage(Page<OrdemServicoEntity> entityPage) {
        List<OrdemServicoEntity> entities = entityPage.getContent();
        List<OrdemServicoResponse> responses = toResponseList(entities);
        return new PageImpl<>(responses, entityPage.getPageable(), entityPage.getTotalElements());
    }



    default OrdemServicoEntity mapLongToEntity(Long id) {
        if (id == null) {
            return null;
        }
        OrdemServicoEntity entity = new OrdemServicoEntity();
        entity.setId(id);
        return entity;
    }

    default Long mapEntityToLong(OrdemServicoEntity entity) {
        if (entity == null) {
            return null;
        }
        return entity.getId();
    }
}
