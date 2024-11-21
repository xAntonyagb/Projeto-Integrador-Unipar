package br.unipar.assetinsight.repositories.custom.implementations;

import br.unipar.assetinsight.entities.AmbienteEntity;
import br.unipar.assetinsight.entities.PatrimonioEntity;
import br.unipar.assetinsight.entities.TarefaEntity;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
@Transactional(readOnly = true)
public class AmbienteRepositoryImpl implements ICustomRepository<AmbienteEntity> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<AmbienteEntity> findAllWithFilters(Pageable pageable, Map<String, String> filtros) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<AmbienteEntity> query = cb.createQuery(AmbienteEntity.class);
        Root<AmbienteEntity> ambiente = query.from(AmbienteEntity.class);
        List<Predicate> listWhere = new ArrayList<>();

        CriteriaQuery<Long> queryCount = cb.createQuery(Long.class);
        Root<AmbienteEntity> ambienteCount = queryCount.from(AmbienteEntity.class);
        List<Predicate> listWhereCount = new ArrayList<>();

        filtros.forEach((key, value) -> {
            switch (key) {
                case "descricao":
                    listWhere.add(cb.like(cb.upper(ambiente.get("descricao")), "%" + value.toUpperCase() + "%"));
                    listWhereCount.add(cb.like(cb.upper(ambienteCount.get("descricao")), "%" + value.toUpperCase() + "%"));
                    break;

                case "bloco":
                    listWhere.add(cb.equal(ambiente.get("blocoEntity").get("id"), Long.valueOf(value)));
                    listWhereCount.add(cb.equal(ambienteCount.get("blocoEntity").get("id"), Long.valueOf(value)));
                    break;

                case "patrimonio":
                    Join<AmbienteEntity, PatrimonioEntity> patrimonios = ambiente.join("listPatrimonioEntities", JoinType.LEFT);
                    listWhere.add(cb.equal(patrimonios.get("id"), Long.valueOf(value)));

                    Join<AmbienteEntity, PatrimonioEntity> patrimoniosCount = ambienteCount.join("listPatrimonioEntities", JoinType.LEFT);
                    listWhereCount.add(cb.equal(patrimoniosCount.get("id"), Long.valueOf(value)));
                    break;
            }
        });

        query.where(listWhere.toArray(new Predicate[0]));

        TypedQuery<AmbienteEntity> consultaPaginada = entityManager.createQuery(query); //rodou a query
        consultaPaginada.setFirstResult((int) pageable.getOffset());
        consultaPaginada.setMaxResults(pageable.getPageSize());

        List<AmbienteEntity> resultado = consultaPaginada.getResultList();

        //Pra dar count na quantidade de registros que tem com esse filtro
        queryCount.select(cb.count(ambienteCount)).where(listWhereCount.toArray(new Predicate[0]));
        long total = entityManager.createQuery(queryCount).getSingleResult(); //rodou a query do count

        return new PageImpl<>(resultado, pageable, total);
    }

}