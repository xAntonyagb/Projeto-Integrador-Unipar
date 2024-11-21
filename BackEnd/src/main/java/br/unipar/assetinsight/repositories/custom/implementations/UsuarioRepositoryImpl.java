package br.unipar.assetinsight.repositories.custom.implementations;

import br.unipar.assetinsight.entities.TarefaEntity;
import br.unipar.assetinsight.entities.UsuarioEntity;
import br.unipar.assetinsight.enums.PermissoesEnum;
import br.unipar.assetinsight.repositories.custom.interfaces.ICustomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@Transactional(readOnly = true)
public class UsuarioRepositoryImpl implements ICustomRepository<UsuarioEntity> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<UsuarioEntity> findAllWithFilters(Pageable pageable, Map<String, String> filtros) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UsuarioEntity> query = cb.createQuery(UsuarioEntity.class);
        Root<UsuarioEntity> usuario = query.from(UsuarioEntity.class);
        List<Predicate> listWhere = new ArrayList<>();

        CriteriaQuery<Long> queryCount = cb.createQuery(Long.class);
        Root<UsuarioEntity> usuarioCount = queryCount.from(UsuarioEntity.class);
        List<Predicate> listWhereCount = new ArrayList<>();

        filtros.forEach((key, value) -> {
            switch (key) {
                case "username":
                    listWhere.add(cb.like(cb.upper(usuario.get("username")), "%" + value.toUpperCase() + "%"));
                    listWhereCount.add(cb.like(cb.upper(usuarioCount.get("username")), "%" + value.toUpperCase() + "%"));
                    break;

                case "dtCriacao":
                    listWhere.add(cb.equal(usuario.get("dtRecord"), Timestamp.valueOf(value)));
                    listWhereCount.add(cb.equal(usuarioCount.get("dtRecord"), Timestamp.valueOf(value)));
                    break;

                case "lastLogin":
                    listWhere.add(cb.equal(usuario.get("dtLogin"), Timestamp.valueOf(value)));
                    listWhereCount.add(cb.equal(usuarioCount.get("dtLogin"), Timestamp.valueOf(value)));
                    break;

                case "permissao":
                    Join<UsuarioEntity, PermissoesEnum> roles = usuario.join("listRoles");
                    listWhere.add(cb.equal(roles, PermissoesEnum.valueOf(value)));

                    Join<UsuarioEntity, PermissoesEnum> rolesCount = usuarioCount.join("listRoles");
                    listWhereCount.add(cb.equal(rolesCount, PermissoesEnum.valueOf(value)));
                    break;
            }
        });

        query.where(listWhere.toArray(new Predicate[0]));

        TypedQuery<UsuarioEntity> consultaPaginada = entityManager.createQuery(query);
        consultaPaginada.setFirstResult((int) pageable.getOffset());
        consultaPaginada.setMaxResults(pageable.getPageSize());

        List<UsuarioEntity> resultado = consultaPaginada.getResultList();

        //Pra dar count na quantidade de registros que tem com esse filtro
        queryCount.select(cb.count(usuarioCount)).where(listWhereCount.toArray(new Predicate[0]));
        long total = entityManager.createQuery(queryCount).getSingleResult(); //rodou a query do count

        return new PageImpl<>(resultado, pageable, total);
    }
}