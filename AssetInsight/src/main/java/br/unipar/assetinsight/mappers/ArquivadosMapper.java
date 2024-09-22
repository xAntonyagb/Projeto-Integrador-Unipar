package br.unipar.assetinsight.mappers;

import br.unipar.assetinsight.dtos.requests.ArquivadoRequest;
import br.unipar.assetinsight.dtos.responses.ArquivadoResponse;
import br.unipar.assetinsight.entities.ArquivadoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ArquivadosMapper {
    ArquivadosMapper INSTANCE = Mappers.getMapper(ArquivadosMapper.class);

    @Mapping(source = "ordemServico", target = "ordemServicoEntity")
    @Mapping(source = "tarefa", target = "tarefaEntity")
    ArquivadoEntity toEntity(ArquivadoRequest request);

    @Mapping(source = "ordemServico", target = "ordemServicoEntity")
    @Mapping(source = "tarefa", target = "tarefaEntity")
    @Mapping(source = "arquivadoBy", target = "usuarioEntityResponsavel")
    ArquivadoEntity toEntity(ArquivadoResponse response);


    @Mapping(source = "ordemServicoEntity", target = "ordemServico")
    @Mapping(source = "tarefaEntity", target = "tarefa")
    ArquivadoRequest toRequest(ArquivadoEntity entity);

    ArquivadoRequest toRequest(ArquivadoResponse response);


    @Mapping(source = "ordemServico", target = "ordemServicoEntity")
    @Mapping(source = "tarefa", target = "tarefaEntity")
    @Mapping(source = "arquivadoBy", target = "usuarioEntityResponsavel")
    ArquivadoResponse toResponse(ArquivadoEntity entity);

    ArquivadoResponse toResponse(ArquivadoRequest request);
}
