package br.unipar.assetinsight.repositories.custom.implementations;

import br.unipar.assetinsight.entities.CategoriaEntity;
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
public class CategoriaRepositoryImpl implements ICustomRepository<CategoriaEntity> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<CategoriaEntity> findAllWithFilters(Pageable pageable, Map<String, String> filtros) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CategoriaEntity> query = cb.createQuery(CategoriaEntity.class);
        Root<CategoriaEntity> categoria = query.from(CategoriaEntity.class);
        List<Predicate> listWhere = new ArrayList<>();

        CriteriaQuery<Long> queryCount = cb.createQuery(Long.class);
        Root<CategoriaEntity> categoriaCount = queryCount.from(CategoriaEntity.class);
        List<Predicate> listWhereCount = new ArrayList<>();

        filtros.forEach((key, value) -> {
            if (key.equals("descricao")) {
                final String descricao = value.toUpperCase();
                listWhere.add(cb.like(cb.upper(categoria.get("descricao")), "%" + descricao + "%"));
                listWhereCount.add(cb.like(cb.upper(categoriaCount.get("descricao")), "%" + descricao + "%"));
            }
        });

        query.where(listWhere.toArray(new Predicate[0]));

        TypedQuery<CategoriaEntity> consultaPaginada = entityManager.createQuery(query); //rodou a query
        consultaPaginada.setFirstResult((int) pageable.getOffset());
        consultaPaginada.setMaxResults(pageable.getPageSize());

        List<CategoriaEntity> resultado = consultaPaginada.getResultList();

        //Pra dar count na quantidade de registros que tem com esse filtro
        queryCount.select(cb.count(categoriaCount)).where(listWhereCount.toArray(new Predicate[0]));
        long total = entityManager.createQuery(queryCount).getSingleResult(); //rodou a query do count

        return new PageImpl<>(resultado, pageable, total);
    }

}