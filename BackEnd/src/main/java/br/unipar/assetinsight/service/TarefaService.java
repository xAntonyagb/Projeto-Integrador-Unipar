package br.unipar.assetinsight.service;

import br.unipar.assetinsight.entities.AmbienteEntity;
import br.unipar.assetinsight.entities.CategoriaEntity;
import br.unipar.assetinsight.entities.TarefaEntity;
import br.unipar.assetinsight.enums.StatusTarefaEnum;
import br.unipar.assetinsight.exceptions.NotFoundException;
import br.unipar.assetinsight.exceptions.ValidationException;
import br.unipar.assetinsight.repositories.AmbienteRepository;
import br.unipar.assetinsight.repositories.CategoriaRepository;
import br.unipar.assetinsight.repositories.TarefaRepository;
import br.unipar.assetinsight.service.interfaces.IService;
import br.unipar.assetinsight.utils.DataUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class TarefaService implements IService<TarefaEntity> {
    private final TarefaRepository tarefaRepository;
    private final AmbienteRepository ambienteRepository;
    private final CategoriaRepository categoriaRepository;
    private final SecurityService securityService;

    @Override
    public TarefaEntity getById(long id) {
        Optional<TarefaEntity> tarefa = tarefaRepository.findById(id);

        return tarefa.orElseThrow(
                () -> new NotFoundException("tarefa", "Nenhuma tarefa foi encontrada com o id: " + id)
        );
    }

    @Override
    public Page<TarefaEntity> getAll(Pageable pageable) {
        Page<TarefaEntity> tarefas = tarefaRepository.findAll(pageable);

        if (tarefas.isEmpty()) {
            throw new NotFoundException("tarefa", "Nenhuma tarefa foi encontrada.");
        }

        return tarefas;
    }

    @Override
    public TarefaEntity save(TarefaEntity entity) {
        entity = validateTarefa(entity);

        if(entity.getStatus() == StatusTarefaEnum.ABERTA && entity.getDtPrevisao().before(entity.getDtRecord())) {
            entity.setStatus(StatusTarefaEnum.ATRASADA);
        }
        else if (entity.getStatus() == StatusTarefaEnum.ATRASADA && entity.getDtPrevisao().after(entity.getDtRecord())) {
            entity.setStatus(StatusTarefaEnum.ABERTA);
        }

        entity.setDtRecord(DataUtils.getNow());
        entity.setUsuarioEntityCriador(securityService.getUsuario());

        return tarefaRepository.save(entity);
    }

    @Override
    public void deleteById(long id) {
        Optional<TarefaEntity> tarefa = tarefaRepository.findById(id);

        if (tarefa.isEmpty()) {
            throw new NotFoundException("tarefa", "Nenhuma tarefa foi encontrada com o id: " + id);
        }

        tarefaRepository.deleteById(id);
    }



    public TarefaEntity validateTarefa(TarefaEntity tarefaEntity) {
        Map<String, String> listErros = new HashMap<>();

        Optional<AmbienteEntity> ambiente = ambienteRepository.findById(tarefaEntity.getAmbienteEntity().getId());
        if (ambiente.isEmpty()) {
            listErros.put("ambiente", "Nenhum ambiente foi encontrado com o id: " + tarefaEntity.getAmbienteEntity().getId());
        }


        Optional<CategoriaEntity> categoria = categoriaRepository.findById(tarefaEntity.getCategoriaEntity().getId());
        if (categoria.isEmpty()) {
            listErros.put("categoria", "Nenhuma categoria foi encontrada com o id: " + tarefaEntity.getCategoriaEntity().getId()+ ".");
        }

        if (!listErros.isEmpty()) {
            throw new ValidationException(listErros);
        }

        tarefaEntity.setCategoriaEntity(categoria.get());
        tarefaEntity.setAmbienteEntity(ambiente.get());
        return tarefaEntity;
    }

    @Scheduled(cron = "0 0 0 * * *") // todo dia a meia noite exeucta automatico
    public void updateStatusTarefas() {
        List<TarefaEntity> tarefas = tarefaRepository.findAll();

        for (TarefaEntity tarefa : tarefas) {
            if(tarefa.getStatus() == StatusTarefaEnum.ABERTA && tarefa.getDtPrevisao().before(DataUtils.getNow())) {
                tarefa.setStatus(StatusTarefaEnum.ATRASADA);
            }
            else if (tarefa.getStatus() == StatusTarefaEnum.ATRASADA && tarefa.getDtPrevisao().after(DataUtils.getNow())) {
                tarefa.setStatus(StatusTarefaEnum.ABERTA);
            }
        }
    }

}
