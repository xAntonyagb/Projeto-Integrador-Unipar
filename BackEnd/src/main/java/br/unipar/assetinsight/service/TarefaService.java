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
    private final AmbienteService ambienteService;
    private final CategoriaService categoriaService;

    @Override
    public TarefaEntity getById(long id) {
        Optional<TarefaEntity> retornoBusca = tarefaRepository.findById(id);

        TarefaEntity tarefa = retornoBusca.orElseThrow(
                () -> new NotFoundException("tarefa", "Nenhuma tarefa foi encontrada com o id: " + id)
        );

        return validateTarefa(tarefa);
    }

    @Override
    public Page<TarefaEntity> getAll(Pageable pageable) {
        Page<TarefaEntity> tarefas = tarefaRepository.findAll(pageable);

        if (tarefas.isEmpty()) {
            throw new NotFoundException("tarefa", "Nenhuma tarefa foi encontrada.");
        }

        tarefas.stream().forEach(tarefa -> {
            TarefaEntity e = validateTarefa(tarefa);
            tarefa.setAmbienteEntity(e.getAmbienteEntity());
            tarefa.setCategoriaEntity(e.getCategoriaEntity());
        });

        return tarefas;
    }

    @Override
    public TarefaEntity save(TarefaEntity entity) {
        entity = validateTarefa(entity); //Validar

        if(entity.getStatus() == StatusTarefaEnum.ABERTA && entity.getDtPrevisao().before(entity.getDtRecord())) {
            entity.setStatus(StatusTarefaEnum.ATRASADA);
        }
        else if (entity.getStatus() == StatusTarefaEnum.ATRASADA && entity.getDtPrevisao().after(entity.getDtRecord())) {
            entity.setStatus(StatusTarefaEnum.ABERTA);
        }

        entity.setDtRecord(DataUtils.getNow());
        entity.setUsuarioEntityCriador(securityService.getUsuario());
        entity = tarefaRepository.save(entity);

        return validateTarefa(entity); //Retornar com os dados atualizados de ambiente e categoria
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
        AmbienteEntity ambiente = new AmbienteEntity();
        CategoriaEntity categoria = new CategoriaEntity();

        try {
            ambiente = ambienteService.getById(tarefaEntity.getAmbienteEntity().getId());
        } catch (Exception e) {
            listErros.put("ambiente", "Nenhum ambiente foi encontrado com o id: " + tarefaEntity.getAmbienteEntity().getId());
        }

        try {
            categoria = categoriaService.getById(tarefaEntity.getCategoriaEntity().getId());
        } catch (Exception e) {
            listErros.put("categoria", "Nenhuma categoria foi encontrada com o id: " + tarefaEntity.getCategoriaEntity().getId()+ ".");
        }

        if (!listErros.isEmpty()) {
            throw new ValidationException(listErros);
        }

        tarefaEntity.setCategoriaEntity(categoria);
        tarefaEntity.setAmbienteEntity(ambiente);
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
