package br.unipar.assetinsight.mappers;

import br.unipar.assetinsight.dtos.responses.NotificacaoResponse;
import br.unipar.assetinsight.entities.NotificacaoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NotificacaoMapper {
    NotificacaoMapper INSTANCE = Mappers.getMapper(NotificacaoMapper.class);

    NotificacaoEntity toEntity(NotificacaoResponse response);

    NotificacaoResponse toResponse(NotificacaoEntity entity);
}
