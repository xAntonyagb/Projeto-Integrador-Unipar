package br.unipar.assetinsight.mappers;

import br.unipar.assetinsight.dtos.requests.ServicoRequest;
import br.unipar.assetinsight.dtos.responses.principal.ServicoRespose;
import br.unipar.assetinsight.entities.AmbienteEntity;
import br.unipar.assetinsight.entities.ServicoEntity;
import br.unipar.assetinsight.utils.DataUtils;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

@Mapper(uses = {CategoriaMapper.class, AmbienteMapper.class, UsuarioMapper.class, PatrimonioMapper.class})
public interface ServicoMapper {
    ServicoMapper INSTANCE = Mappers.getMapper(ServicoMapper.class);

    @AfterMapping
    default void setDtRecord(ServicoRequest request, @MappingTarget ServicoEntity entity) {
        entity.setDtRecord(DataUtils.getNow());
    }

    @Mapping(source = "categoria", target = "categoriaEntity")
    @Mapping(source = "ambiente", target = "ambienteEntity", qualifiedByName = "mapLongToAmbienteEntity")
    @Mapping(source = "id", target = "idServico")
    @Mapping(source = "patrimonio", target = "patrimonioEntity")
    ServicoEntity toEntity(ServicoRequest request);

    @Mapping(source = "lastChange", target = "dtRecord")
    @Mapping(source = "lastChangedBy", target = "usuarioEntityCriador")
    @Mapping(source = "categoria", target = "categoriaEntity")
    @Mapping(source = "ambiente", target = "ambienteEntity")
    @Mapping(source = "id", target = "idServico")
    @Mapping(source = "patrimonio", target = "patrimonioEntity")
    ServicoEntity toEntity(ServicoRespose response);


    @Mapping(source = "categoriaEntity", target = "categoria")
    @Mapping(source = "ambienteEntity", target = "ambiente")
    @Mapping(source = "patrimonioEntity", target = "patrimonio")
    ServicoRequest toRequest(ServicoEntity entity);

    //TODO: Ajustar os mappers pra ter os SimpleReponses seprados em outra package -Antony
    //ServicoRequest toRequest(ServicoRespose request);


    @Mapping(source = "dtRecord", target = "lastChange")
    @Mapping(source = "usuarioEntityCriador", target = "lastChangedBy")
    @Mapping(source = "categoriaEntity", target = "categoria")
    @Mapping(source = "ambienteEntity", target = "ambiente")
    @Mapping(source = "idServico", target = "id")
    @Mapping(source = "patrimonioEntity", target = "patrimonio")
    ServicoRespose toResponse(ServicoEntity entity);

    //TODO: Ajustar os mappers pra ter os SimpleReponses seprados em outra package -Antony
    //ServicoRespose toResponse(ServicoRequest request);


    ServicoEntity updateEntity(ServicoRequest request, @MappingTarget ServicoEntity entity);

    List<ServicoEntity> toEntityList(List<ServicoRequest> request);
    List<ServicoRequest> toRequestList(List<ServicoEntity> entity);
    List<ServicoRespose> toResponseList(List<ServicoEntity> entity);

    default Page<ServicoRespose> toResponsePage(Page<ServicoEntity> entityPage) {
        List<ServicoEntity> entities = entityPage.getContent();
        List<ServicoRespose> responses = toResponseList(entities);
        return new PageImpl<>(responses, entityPage.getPageable(), entityPage.getTotalElements());
    }

    default AmbienteEntity mapLongToEntity(Long id) {
        if (id == null) {
            return null;
        }
        AmbienteEntity entity = new AmbienteEntity();
        entity.setId(id);
        return entity;
    }
}