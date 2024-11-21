package br.unipar.assetinsight.service;

import br.unipar.assetinsight.entities.CategoriaEntity;
import br.unipar.assetinsight.entities.ServicoEntity;
import br.unipar.assetinsight.entities.TarefaEntity;
import br.unipar.assetinsight.exceptions.NotFoundException;
import br.unipar.assetinsight.exceptions.ValidationException;
import br.unipar.assetinsight.repositories.CategoriaRepository;
import br.unipar.assetinsight.repositories.ServicoRepository;
import br.unipar.assetinsight.repositories.TarefaRepository;
import br.unipar.assetinsight.service.interfaces.IService;
import br.unipar.assetinsight.utils.DataUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class CategoriaService implements IService<CategoriaEntity> {

    private final CategoriaRepository categoriaRepository;
    private final SecurityService securityService;
    private final TarefaRepository tarefaRepository;
    private final ServicoRepository servicoRepository;

    @Override
    public CategoriaEntity getById(long id) {
        Optional<CategoriaEntity> categoria = categoriaRepository.findById(id);

        if (categoria.isPresent()) {
            CategoriaEntity categoriaEntity = categoria.get();
            categoriaEntity.setQtdTarefas(tarefaRepository.countByCategoriaEntity_Id(categoriaEntity.getId()));
            categoriaEntity.setQtdServicos(servicoRepository.countByCategoriaEntity_Id(categoriaEntity.getId()));
            categoriaEntity.setQtdTotalTarefas(tarefaRepository.count());
            categoriaEntity.setQtdTotalServicos(servicoRepository.count());
            return categoriaEntity;
        }
        else {
            throw new NotFoundException("categoria", "Nenhuma categoria foi encontrada com o id: " + id);
        }
    }

    @Override
    public Page<CategoriaEntity> getAll(Pageable pageable, Map<String, String> filtros) {
        Page<CategoriaEntity> categorias = categoriaRepository.findAllWithFilters(pageable, filtros);

        if (categorias.isEmpty()) {
            throw new NotFoundException("categoria", "Nenhuma categoria foi encontrada");
        }

        long totalServicos = servicoRepository.count();
        long totalTarefas = tarefaRepository.count();

        categorias.forEach(categoria -> {
            categoria.setQtdTarefas(tarefaRepository.countByCategoriaEntity_Id(categoria.getId()));
            categoria.setQtdServicos(servicoRepository.countByCategoriaEntity_Id(categoria.getId()));
            categoria.setQtdTotalTarefas(totalTarefas);
            categoria.setQtdTotalServicos(totalServicos);
        });

        return categorias;
    }

    @Override
    public CategoriaEntity save(CategoriaEntity entity) {
        boolean exists = categoriaRepository.existsByDescricaoIgnoreCase(entity.getDescricao());
        if (exists) {
            throw new ValidationException("descricao", "Já existe uma categoria com a descrição: " + entity.getDescricao());
        }

        entity.setDtRecord(DataUtils.getNow());
        entity.setUsuarioEntityCriador(securityService.getUsuario());
        long id = categoriaRepository.save(entity).getId();
        return getById(id);
    }

    public void transferirCategoria(long idCategoriaOrigem, long idCategoriaDestino) {
        Optional<CategoriaEntity> categoriaOrigem = categoriaRepository.findById(idCategoriaOrigem);
        Optional<CategoriaEntity> categoriaDestino = categoriaRepository.findById(idCategoriaDestino);

        Map<String, String> listErros = new HashMap<>();
        if (!categoriaOrigem.isPresent()) {
            listErros.put("categoriaOrigem", "Nenhuma categoria de origem foi encontrada com o id: " + idCategoriaOrigem);
        }
        if (!categoriaDestino.isPresent()) {
            listErros.put("categoriaDestino", "Nenhuma categoria de destino foi encontrada com o id: " + idCategoriaDestino);
        }
        if (categoriaOrigem.get().getId() == categoriaDestino.get().getId()) {
            listErros.put("categorias", "A categoria de origem e a categoria de destino não podem ser iguais.");
        }

        if (!listErros.isEmpty()) {
            throw new ValidationException(listErros);
        }

        List<TarefaEntity> tarefas = tarefaRepository.findAllByCategoriaEntity_Id(idCategoriaOrigem).orElse(new ArrayList<>());
        for (TarefaEntity tarefa : tarefas) {
            tarefa.setCategoriaEntity(categoriaDestino.get());
            tarefaRepository.save(tarefa);
        }

        List<ServicoEntity> servicos = servicoRepository.findAllByCategoriaEntity_Id(idCategoriaOrigem).orElse(new ArrayList<>());
        for (ServicoEntity servico : servicos) {
            servico.setCategoriaEntity(categoriaDestino.get());
            servicoRepository.save(servico);
        }

    }

    @Override
    public void deleteById(long id) {
        Optional<CategoriaEntity> categoria = categoriaRepository.findById(id);
        if (categoria.isEmpty()) {
            throw new NotFoundException("categoria", "Nenhuma categoria foi encontrada com o id: " + id);
        }

        Map<String, String> listErros = new HashMap<>();

        Optional<List<TarefaEntity>> tarefas = tarefaRepository.findAllByCategoriaEntity_Id(id);
        if (tarefas.isPresent()) {
            listErros.put("tarefa", "Existem tarefas associadas a esta categoria.");
        }

        Optional<List<ServicoEntity>> servicos = servicoRepository.findAllByCategoriaEntity_Id(id);
        if (servicos.isPresent()) {
            listErros.put("servico", "Existem serviços associados a esta categoria.");
        }

        if (!listErros.isEmpty()) {
            throw new ValidationException(listErros);
        }

        categoriaRepository.deleteById(id);
    }
}
