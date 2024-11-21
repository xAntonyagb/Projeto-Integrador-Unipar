package br.unipar.assetinsight.repositories.custom.implementations;

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
public class PatrimonioRepositoryImpl implements ICustomRepository<PatrimonioEntity> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<PatrimonioEntity> findAllWithFilters(Pageable pageable, Map<String, String> filtros) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PatrimonioEntity> query = cb.createQuery(PatrimonioEntity.class);
        Root<PatrimonioEntity> patrimonio = query.from(PatrimonioEntity.class);
        List<Predicate> listWhere = new ArrayList<>();

        CriteriaQuery<Long> queryCount = cb.createQuery(Long.class);
        Root<PatrimonioEntity> patrimonioCount = queryCount.from(PatrimonioEntity.class);
        List<Predicate> listWhereCount = new ArrayList<>();

        filtros.forEach((key, value) -> {
            switch (key) {
                case "descricao":
                    listWhere.add(cb.like(cb.upper(patrimonio.get("descricao")), "%" + value.toUpperCase() + "%"));
                    listWhereCount.add(cb.like(cb.upper(patrimonioCount.get("descricao")), "%" + value.toUpperCase() + "%"));
                    break;

                case "ambiente":
                    listWhere.add(cb.equal(patrimonio.get("ambienteEntity").get("id"), Long.valueOf(value)));
                    listWhereCount.add(cb.equal(patrimonioCount.get("ambienteEntity").get("id"), Long.valueOf(value)));
                    break;
            }
        });

        query.where(listWhere.toArray(new Predicate[0]));

        TypedQuery<PatrimonioEntity> consultaPaginada = entityManager.createQuery(query); //rodou a query
        consultaPaginada.setFirstResult((int) pageable.getOffset());
        consultaPaginada.setMaxResults(pageable.getPageSize());

        List<PatrimonioEntity> resultado = consultaPaginada.getResultList();

        //Pra dar count na quantidade de registros que tem com esse filtro
        queryCount.select(cb.count(patrimonioCount)).where(listWhereCount.toArray(new Predicate[0]));
        long total = entityManager.createQuery(queryCount).getSingleResult(); //rodou a query do count

        return new PageImpl<>(resultado, pageable, total);
    }
}