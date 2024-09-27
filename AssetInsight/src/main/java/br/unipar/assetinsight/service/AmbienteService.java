package br.unipar.assetinsight.service;

import br.unipar.assetinsight.entities.AmbienteEntity;
import br.unipar.assetinsight.entities.BlocoEntity;
import br.unipar.assetinsight.entities.ServicoEntity;
import br.unipar.assetinsight.entities.TarefaEntity;
import br.unipar.assetinsight.exceptions.NotFoundException;
import br.unipar.assetinsight.exceptions.ValidationException;
import br.unipar.assetinsight.repositories.AmbienteRepository;
import br.unipar.assetinsight.repositories.BlocoRepository;
import br.unipar.assetinsight.repositories.ServicoRepository;
import br.unipar.assetinsight.repositories.TarefaRepository;
import br.unipar.assetinsight.service.interfaces.IService;
import br.unipar.assetinsight.utils.DataUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AmbienteService implements IService<AmbienteEntity> {
    private final TarefaRepository tarefaRepository;
    private final BlocoRepository blocoRepository;
    private AmbienteRepository ambienteRepository;
    private SecurityService securityService;
    private ServicoRepository servicoRepository;

    @Override
    public AmbienteEntity getById(long id) {
        Optional<AmbienteEntity> ambiente = ambienteRepository.findById(id);

        return ambiente.orElseThrow(
                () -> new NotFoundException("Nenhum ambiente foi encontrado com o id: " + id)
        );
    }

    @Override
    public Page<AmbienteEntity> getAll(Pageable pagable) {
        Page<AmbienteEntity> ambientes = ambienteRepository.findAll(pagable);

        if (ambientes.isEmpty()) {
            throw new NotFoundException("Nenhum ambiente foi encontrado");
        }

        return ambientes;
    }

    @Override
    public AmbienteEntity save(AmbienteEntity ambiente) {
        Optional<BlocoEntity> bloco = blocoRepository.findById(ambiente.getBlocoEntity().getId());
        if (bloco.isEmpty()) {
            throw new ValidationException("Nenhum bloco foi encontrado com o id: " + ambiente.getBlocoEntity().getId());
        }

        ambiente.setUsuarioEntityCriador(securityService.getUsuario());
        ambiente.setDtRecord(DataUtils.getNow());

        ambiente = ambienteRepository.save(ambiente);
        return ambiente;
    }

    public void transferirAmbientes(long ambienteOrigemId, long ambienteDestinoId) {
        Optional<AmbienteEntity> ambienteOrigem = ambienteRepository.findById(ambienteOrigemId);
        Optional<AmbienteEntity> ambienteDestino = ambienteRepository.findById(ambienteDestinoId);

        List<String> errorList = new ArrayList<>();
        if (!ambienteOrigem.isPresent()) {
            errorList.add("Nenhum ambiente de origem foi encontrado com o id: "+ ambienteOrigemId +".");
        }
        if (!ambienteDestino.isPresent()) {
            errorList.add("Nenhum ambiente de destino foi encontrado com o id: "+ ambienteDestinoId +".");
        }
        if (ambienteOrigem.get().getId() == ambienteDestino.get().getId()) {
            errorList.add("O ambiente de origem e de destino não podem ser o mesmo.");
        }

        if(!errorList.isEmpty()) {
            throw new ValidationException(errorList);
        }

        List<TarefaEntity> tarefas = tarefaRepository.findAllByAmbienteEntity_Id(ambienteOrigemId).orElse(new ArrayList<>());
        for (TarefaEntity tarefa : tarefas) {
            tarefa.setAmbienteEntity(ambienteDestino.get());
            tarefaRepository.save(tarefa);
        }
    }

    @Override
    public void deleteById(long id) {
        Optional<AmbienteEntity> ambiente = ambienteRepository.findById(id);
        if (ambiente.isEmpty()) {
            throw new NotFoundException("Nenhum ambiente foi encontrado.");
        }

        List<String> errorList = new ArrayList<>();
        Optional<List<ServicoEntity>> servicos = servicoRepository.findAllByAmbienteEntity_Id(id);
        if (servicos.isPresent()) {
            errorList.add("Não é possível deletar o ambiente pois existem serviços associados a ele.");
        }

        Optional<List<TarefaEntity>> tarefas = tarefaRepository.findAllByAmbienteEntity_Id(id);
        if (tarefas.isPresent()) {
            errorList.add("Não é possível deletar o ambiente pois existem tarefas associadas a ele.");
        }

        if(!errorList.isEmpty()) {
            throw new ValidationException(errorList);
        }
    }

}