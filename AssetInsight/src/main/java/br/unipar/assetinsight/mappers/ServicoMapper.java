package br.unipar.assetinsight.mappers;

import br.unipar.assetinsight.dtos.requests.ServicoRequest;
import br.unipar.assetinsight.dtos.responses.ServicoRespose;
import br.unipar.assetinsight.entities.ServicoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {CategoriaMapper.class, AmbienteMapper.class, UsuarioMapper.class})
public interface ServicoMapper {
    ServicoMapper INSTANCE = Mappers.getMapper(ServicoMapper.class);

    ServicoEntity toEntity(ServicoRequest request);

    @Mapping(source = "lastChange", target = "dtRecord")
    @Mapping(source = "lastChangedBy", target = "usuarioEntityCriador")
    @Mapping(source = "categoria", target = "categoriaEntity")
    @Mapping(source = "ambiente", target = "ambienteEntity")
    @Mapping(source = "id", target = "idServico")
    ServicoEntity toEntity(ServicoRespose response);


    ServicoRequest toRequest(ServicoEntity entity);

    ServicoRequest toRequest(ServicoRequest request);

    ServicoRespose toResponse(ServicoEntity entity);
}