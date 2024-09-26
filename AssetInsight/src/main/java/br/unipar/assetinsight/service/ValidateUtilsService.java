package br.unipar.assetinsight.service;

import br.unipar.assetinsight.entities.OrdemServicoEntity;
import br.unipar.assetinsight.entities.TarefaEntity;
import br.unipar.assetinsight.exceptions.ValidationException;
import br.unipar.assetinsight.repositories.OrdemServicoRepository;
import br.unipar.assetinsight.repositories.TarefaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ValidateUtilsService {
    private final OrdemServicoRepository ordemServicoRepository;
    private final TarefaRepository tarefaRepository;


    public TarefaEntity validarTarefa(TarefaEntity tarefa) {
        if (tarefa == null){
            return null;
        }

        Optional<TarefaEntity> tarefaOptional = tarefaRepository.findById(tarefa.getId());
        if (tarefaOptional.isEmpty()) {
            throw new ValidationException("Nenhuma tarefa foi encontrada com o id: " + tarefa.getId());
        }
        else {
            return tarefaOptional.get();
        }
    }

    public OrdemServicoEntity validarOrdemServico(OrdemServicoEntity ordemServico) {
        if (ordemServico == null){
            return null;
        }

        Optional<OrdemServicoEntity> ordemServicoOptional = ordemServicoRepository.findById(ordemServico.getId());
        if (ordemServicoOptional.isEmpty()) {
            throw new ValidationException("Nenhuma ordem de serviço foi encontrada com o id: " + ordemServico.getId());
        }
        else {
            return ordemServicoOptional.get();
        }
    }


}
