package br.unipar.assetinsight.mappers;

import br.unipar.assetinsight.entities.RolesEntity;
import br.unipar.assetinsight.enums.PermissoesEnum;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    default PermissoesEnum toPermissoesEnum(RolesEntity roleEntity) {
        return roleEntity == null ? null : roleEntity.getPermisao();
    }

    default RolesEntity toRolesEntity(PermissoesEnum permissoesEnum) {
        return permissoesEnum == null ? null : new RolesEntity(permissoesEnum.getId(), permissoesEnum);
    }

    List<RolesEntity> toRolesEntityList(List<PermissoesEnum> permissoesEnum);
    List<PermissoesEnum> toPermissoesEnumList(List<RolesEntity> roleEntity);
}
