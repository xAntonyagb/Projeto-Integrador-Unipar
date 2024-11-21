package br.unipar.assetinsight.repositories.custom.implementations;

import br.unipar.assetinsight.entities.OrdemServicoEntity;
import br.unipar.assetinsight.entities.ServicoEntity;
import br.unipar.assetinsight.enums.StatusOrdemServicoEnum;
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
public class OrdemServicoRepositoryImpl implements ICustomRepository<OrdemServicoEntity> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<OrdemServicoEntity> findAllWithFilters(Pageable pageable, Map<String, String> filtros) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<OrdemServicoEntity> query = cb.createQuery(OrdemServicoEntity.class);
        Root<OrdemServicoEntity> ordemServico = query.from(OrdemServicoEntity.class);
        List<Predicate> listWhere = new ArrayList<>();

        CriteriaQuery<Long> queryCount = cb.createQuery(Long.class);
        Root<OrdemServicoEntity> ordemServicoCount = queryCount.from(OrdemServicoEntity.class);
        List<Predicate> listWhereCount = new ArrayList<>();

        filtros.forEach((key, value) -> {
            switch (key) {
                case "descricao":
                    listWhere.add(cb.like(cb.upper(ordemServico.get("descricao")), "%" + value.toUpperCase() + "%"));
                    listWhereCount.add(cb.like(cb.upper(ordemServicoCount.get("descricao")), "%" + value.toUpperCase() + "%"));
                    break;

                case "data":
                    listWhere.add(cb.equal(ordemServico.get("data"), Timestamp.valueOf(value)));
                    listWhereCount.add(cb.equal(ordemServicoCount.get("data"), Timestamp.valueOf(value)));
                    break;

                case "servico":
                    Join<OrdemServicoEntity, ServicoEntity> servicos = ordemServico.join("listServicoEntities", JoinType.LEFT);
                    listWhere.add(cb.equal(servicos.get("id"), Long.valueOf(value)));

                    Join<OrdemServicoEntity, ServicoEntity> servicosCount = ordemServicoCount.join("listServicoEntities", JoinType.LEFT);
                    listWhereCount.add(cb.equal(servicosCount.get("id"), Long.valueOf(value)));
                    break;

                case "valorTotal":
                    listWhere.add(cb.equal(ordemServico.get("valorTotal"), Double.valueOf(value)));
                    listWhereCount.add(cb.equal(ordemServicoCount.get("valorTotal"), Double.valueOf(value)));
                    break;

                case "status":
                    listWhere.add(cb.equal(ordemServico.get("status"), StatusOrdemServicoEnum.valueOf(value)));
                    listWhereCount.add(cb.equal(ordemServicoCount.get("status"), StatusOrdemServicoEnum.valueOf(value)));
                    break;

                case "arquivada":
                    listWhere.add(cb.equal(ordemServico.get("arquivado"), Boolean.valueOf(value)));
                    listWhereCount.add(cb.equal(ordemServicoCount.get("arquivado"), Boolean.valueOf(value)));
                    break;
            }
        });

        query.where(listWhere.toArray(new Predicate[0]));

        TypedQuery<OrdemServicoEntity> consultaPaginada = entityManager.createQuery(query); //rodou a query
        consultaPaginada.setFirstResult((int) pageable.getOffset());
        consultaPaginada.setMaxResults(pageable.getPageSize());

        List<OrdemServicoEntity> resultado = consultaPaginada.getResultList();

        //Pra dar count na quantidade de registros que tem com esse filtro
        queryCount.select(cb.count(ordemServicoCount)).where(listWhereCount.toArray(new Predicate[0]));
        long total = entityManager.createQuery(queryCount).getSingleResult(); //rodou a query do count

        return new PageImpl<>(resultado, pageable, total);
    }
}