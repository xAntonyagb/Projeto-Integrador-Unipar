package br.unipar.assetinsight.repositories.custom.implementations;

import br.unipar.assetinsight.entities.AmbienteEntity;
import br.unipar.assetinsight.entities.TarefaEntity;
import br.unipar.assetinsight.enums.PrioridadeEnum;
import br.unipar.assetinsight.enums.StatusTarefaEnum;
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
public class TarefaRepositoryImpl implements ICustomRepository<TarefaEntity> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<TarefaEntity> findAllWithFilters(Pageable pageable, Map<String, String> filtros) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<TarefaEntity> query = cb.createQuery(TarefaEntity.class);
        Root<TarefaEntity> tarefa = query.from(TarefaEntity.class);
        List<Predicate> listWhere = new ArrayList<>();

        CriteriaQuery<Long> queryCount = cb.createQuery(Long.class);
        Root<TarefaEntity> tarefaCount = queryCount.from(TarefaEntity.class);
        List<Predicate> listWhereCount = new ArrayList<>();

        filtros.forEach((key, value) -> {
            switch (key) {
                case "titulo":
                    listWhere.add(cb.like(cb.upper(tarefa.get("titulo")), "%" + value.toUpperCase() + "%"));
                    listWhereCount.add(cb.like(cb.upper(tarefaCount.get("titulo")), "%" + value.toUpperCase() + "%"));
                    break;

                case "descricao":
                    listWhere.add(cb.like(cb.upper(tarefa.get("descricao")), "%" + value.toUpperCase() + "%"));
                    listWhereCount.add(cb.like(cb.upper(tarefaCount.get("descricao")), "%" + value.toUpperCase() + "%"));
                    break;

                case "previsao":
                    listWhere.add(cb.equal(tarefa.get("dtPrevisao"), Timestamp.valueOf(value)));
                    listWhereCount.add(cb.equal(tarefaCount.get("dtPrevisao"), Timestamp.valueOf(value)));
                    break;

                case "ambiente":
                    listWhere.add(cb.equal(tarefa.get("ambienteEntity").get("id"), Long.valueOf(value)));
                    listWhereCount.add(cb.equal(tarefaCount.get("ambienteEntity").get("id"), Long.valueOf(value)));
                    break;

                case "categoria":
                    listWhere.add(cb.equal(tarefa.get("categoriaEntity").get("id"), Long.valueOf(value)));
                    listWhereCount.add(cb.equal(tarefaCount.get("categoriaEntity").get("id"), Long.valueOf(value)));
                    break;

                case "prioridade":
                    listWhere.add(cb.equal(tarefa.get("prioridade"), PrioridadeEnum.valueOf(value)));
                    listWhereCount.add(cb.equal(tarefaCount.get("prioridade"), PrioridadeEnum.valueOf(value)));
                    break;

                case "status":
                    listWhere.add(cb.equal(tarefa.get("status"), StatusTarefaEnum.valueOf(value)));
                    listWhereCount.add(cb.equal(tarefaCount.get("status"), StatusTarefaEnum.valueOf(value)));
                    break;

                case "arquivado":
                    listWhere.add(cb.equal(tarefa.get("arquivado"), Boolean.valueOf(value)));
                    listWhereCount.add(cb.equal(tarefaCount.get("arquivado"), Boolean.valueOf(value)));
                    break;
            }
        });

        query.where(listWhere.toArray(new Predicate[0]));

        TypedQuery<TarefaEntity> consultaPaginada = entityManager.createQuery(query); //rodou a query
        consultaPaginada.setFirstResult((int) pageable.getOffset());
        consultaPaginada.setMaxResults(pageable.getPageSize());

        List<TarefaEntity> resultado = consultaPaginada.getResultList();

        //Pra dar count na quantidade de registros que tem com esse filtro
        queryCount.select(cb.count(tarefaCount)).where(listWhereCount.toArray(new Predicate[0]));
        long total = entityManager.createQuery(queryCount).getSingleResult(); //rodou a query do count

        return new PageImpl<>(resultado, pageable, total);
    }

}