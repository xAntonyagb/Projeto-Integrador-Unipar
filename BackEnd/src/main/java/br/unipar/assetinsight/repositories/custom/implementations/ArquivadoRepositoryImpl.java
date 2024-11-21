package br.unipar.assetinsight.repositories.custom.implementations;

import br.unipar.assetinsight.entities.ArquivadoEntity;
import br.unipar.assetinsight.entities.TarefaEntity;
import br.unipar.assetinsight.enums.TipoArquivadoEnum;
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
public class ArquivadoRepositoryImpl implements ICustomRepository<ArquivadoEntity> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<ArquivadoEntity> findAllWithFilters(Pageable pageable, Map<String, String> filtros) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ArquivadoEntity> query = cb.createQuery(ArquivadoEntity.class);
        Root<ArquivadoEntity> arquivado = query.from(ArquivadoEntity.class);
        List<Predicate> listWhere = new ArrayList<>();

        CriteriaQuery<Long> queryCount = cb.createQuery(Long.class);
        Root<ArquivadoEntity> arquivadoCount = queryCount.from(ArquivadoEntity.class);
        List<Predicate> listWhereCount = new ArrayList<>();

        filtros.forEach((key, value) -> {
            switch (key) {
                case "tipo":
                    listWhere.add(cb.equal(arquivado.get("tipo"), TipoArquivadoEnum.valueOf(value)));
                    listWhereCount.add(cb.equal(arquivadoCount.get("tipo"), TipoArquivadoEnum.valueOf(value)));
                    break;

                case "dtArquivado":
                    listWhere.add(cb.equal(arquivado.get("dtArquivado"), Timestamp.valueOf(value)));
                    listWhereCount.add(cb.equal(arquivadoCount.get("dtArquivado"), Timestamp.valueOf(value)));
                    break;

                case "dtExcluir":
                    listWhere.add(cb.equal(arquivado.get("dtExcluir"), Timestamp.valueOf(value)));
                    listWhereCount.add(cb.equal(arquivadoCount.get("dtExcluir"), Timestamp.valueOf(value)));
                    break;

                case "arquivadoBy":
                    listWhere.add(cb.like(cb.upper(arquivado.get("usuarioEntityResponsavel").get("username")), "%" + value.toUpperCase() + "%"));
                    listWhereCount.add(cb.like(cb.upper(arquivadoCount.get("usuarioEntityResponsavel").get("username")), "%" + value.toUpperCase() + "%"));
                    break;

                case "ordemServico":
                    listWhere.add(cb.equal(arquivado.get("ordemServicoEntity").get("id"), Long.valueOf(value)));
                    listWhereCount.add(cb.equal(arquivadoCount.get("ordemServicoEntity").get("id"), Long.valueOf(value)));
                    break;

                case "tarefa":
                    listWhere.add(cb.equal(arquivado.get("tarefaEntity").get("id"), Long.valueOf(value)));
                    listWhereCount.add(cb.equal(arquivadoCount.get("tarefaEntity").get("id"), Long.valueOf(value)));
                    break;
            }
        });

        query.where(listWhere.toArray(new Predicate[0]));

        TypedQuery<ArquivadoEntity> consultaPaginada = entityManager.createQuery(query); //rodou a query
        consultaPaginada.setFirstResult((int) pageable.getOffset());
        consultaPaginada.setMaxResults(pageable.getPageSize());

        List<ArquivadoEntity> resultado = consultaPaginada.getResultList();

        //Pra dar count na quantidade de registros que tem com esse filtro
        queryCount.select(cb.count(arquivadoCount)).where(listWhereCount.toArray(new Predicate[0]));
        long total = entityManager.createQuery(queryCount).getSingleResult(); //rodou a query do count

        return new PageImpl<>(resultado, pageable, total);
    }
}
