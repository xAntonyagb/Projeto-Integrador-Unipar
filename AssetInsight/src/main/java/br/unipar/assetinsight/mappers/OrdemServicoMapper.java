package br.unipar.assetinsight.mappers;

import br.unipar.assetinsight.dtos.requests.OrdemServicoRequest;
import br.unipar.assetinsight.dtos.responses.OrdemServicoResponse;
import br.unipar.assetinsight.entities.OrdemServicoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

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
}
