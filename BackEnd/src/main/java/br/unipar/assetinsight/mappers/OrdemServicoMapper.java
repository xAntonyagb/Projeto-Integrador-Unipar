package br.unipar.assetinsight.mappers;

import br.unipar.assetinsight.dtos.requests.OrdemServicoRequest;
import br.unipar.assetinsight.dtos.responses.principal.OrdemServicoResponse;
import br.unipar.assetinsight.entities.OrdemServicoEntity;
import br.unipar.assetinsight.entities.ServicoEntity;
import br.unipar.assetinsight.utils.DataUtils;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(uses = {UsuarioMapper.class, ServicoMapper.class})
public interface OrdemServicoMapper {
    OrdemServicoMapper INSTANCE = Mappers.getMapper(OrdemServicoMapper.class);

    @AfterMapping
    default void setDtRecord(OrdemServicoRequest request, @MappingTarget OrdemServicoEntity entity) {
        entity.setDtRecord(DataUtils.getNow());
    }

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
    @Mapping(source = "listServicoEntities", target = "qtdServicos", qualifiedByName = "countServicos")
    OrdemServicoResponse toResponse(OrdemServicoEntity entity);

    @Named("countServicos")
    default long countServicos(List<ServicoEntity> servicos) {
        return servicos.size();
    }

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
