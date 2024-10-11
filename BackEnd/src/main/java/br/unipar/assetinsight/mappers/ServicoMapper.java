package br.unipar.assetinsight.mappers;

import br.unipar.assetinsight.dtos.requests.ServicoRequest;
import br.unipar.assetinsight.dtos.responses.ServicoRespose;
import br.unipar.assetinsight.entities.AmbienteEntity;
import br.unipar.assetinsight.entities.CategoriaEntity;
import br.unipar.assetinsight.entities.ServicoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(uses = {CategoriaMapper.class, AmbienteMapper.class, UsuarioMapper.class})
public interface ServicoMapper {
    ServicoMapper INSTANCE = Mappers.getMapper(ServicoMapper.class);

    ServicoEntity toEntity(ServicoRequest request);

    @Mapping(source = "lastChange", target = "dtRecord")
    @Mapping(source = "lastChangedBy", target = "usuarioEntityCriador")
    @Mapping(source = "categoria", target = "categoriaEntity")
    @Mapping(source = "ambiente", target = "ambienteEntity")
    @Mapping(source = "id", target = "idServico")
    @Mapping(source = "descricao", target = "dsPatrimonio")
    ServicoEntity toEntity(ServicoRespose response);


    @Mapping(source = "categoriaEntity", target = "categoria")
    @Mapping(source = "ambienteEntity", target = "ambiente")
    @Mapping(source = "dsPatrimonio", target = "descricao")
    ServicoRequest toRequest(ServicoEntity entity);

    ServicoRequest toRequest(ServicoRespose request);


    @Mapping(source = "dtRecord", target = "lastChange")
    @Mapping(source = "usuarioEntityCriador", target = "lastChangedBy")
    @Mapping(source = "categoriaEntity", target = "categoria")
    @Mapping(source = "ambienteEntity", target = "ambiente")
    @Mapping(source = "idServico", target = "id")
    @Mapping(source = "dsPatrimonio", target = "descricao")
    ServicoRespose toResponse(ServicoEntity entity);

    ServicoRespose toResponse(ServicoRequest request);


    ServicoEntity updateEntity(ServicoRequest request, @MappingTarget ServicoEntity entity);

    List<ServicoEntity> toEntityList(List<ServicoRequest> request);
    List<ServicoRequest> toRequestList(List<ServicoEntity> entity);
    List<ServicoRespose> toResponseList(List<ServicoEntity> entity);

    default Page<ServicoRespose> toResponsePage(Page<ServicoEntity> entityPage) {
        List<ServicoEntity> entities = entityPage.getContent();
        List<ServicoRespose> responses = toResponseList(entities);
        return new PageImpl<>(responses, entityPage.getPageable(), entityPage.getTotalElements());
    }
}