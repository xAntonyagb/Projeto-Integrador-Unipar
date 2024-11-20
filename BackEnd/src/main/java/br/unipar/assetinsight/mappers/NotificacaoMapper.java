package br.unipar.assetinsight.mappers;

import br.unipar.assetinsight.dtos.responses.main.NotificacaoResponse;
import br.unipar.assetinsight.entities.NotificacaoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper
public interface NotificacaoMapper {
    NotificacaoMapper INSTANCE = Mappers.getMapper(NotificacaoMapper.class);

    NotificacaoEntity toEntity(NotificacaoResponse response);

    NotificacaoResponse toResponse(NotificacaoEntity entity);


    NotificacaoEntity updateEntity(NotificacaoResponse response, @MappingTarget NotificacaoEntity entity);

    List<NotificacaoEntity> toEntityList(List<NotificacaoResponse> response);
    List<NotificacaoResponse> toResponseList(List<NotificacaoEntity> entity);

    default Page<NotificacaoResponse> toResponsePage(Page<NotificacaoEntity> entityPage) {
        List<NotificacaoEntity> entities = entityPage.getContent();
        List<NotificacaoResponse> responses = toResponseList(entities);
        return new PageImpl<>(responses, entityPage.getPageable(), entityPage.getTotalElements());
    }
}
