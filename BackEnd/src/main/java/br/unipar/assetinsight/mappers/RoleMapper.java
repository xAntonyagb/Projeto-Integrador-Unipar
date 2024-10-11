package br.unipar.assetinsight.mappers;

import br.unipar.assetinsight.entities.RolesEntity;
import br.unipar.assetinsight.enums.PermissoesEnum;
import br.unipar.assetinsight.repositories.RoleRepository;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public abstract class RoleMapper {
    @Autowired
    private RoleRepository roleRepository;

    public PermissoesEnum toPermissoesEnum(RolesEntity roleEntity) {
        return roleEntity == null ? null : roleEntity.getPermisao();
    }

    public RolesEntity toRolesEntity(PermissoesEnum permissao) {
        Optional<RolesEntity> retorno = roleRepository.findByPermisao(permissao);
        return retorno.orElseGet(
                () -> roleRepository.save(new RolesEntity(permissao.getId(), permissao))
        );
    }


    public abstract RolesEntity updateEntity(PermissoesEnum permissoesEnum, @MappingTarget RolesEntity roleEntity);

    public abstract List<RolesEntity> toRolesEntityList(List<PermissoesEnum> permissoesEnum);
    public abstract List<PermissoesEnum> toPermissoesEnumList(List<RolesEntity> roleEntity);
}
