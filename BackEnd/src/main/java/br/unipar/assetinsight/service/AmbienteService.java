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

import java.util.*;

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

        if (ambiente.isPresent()) {
            AmbienteEntity ambienteEntity = ambiente.get();

            ambienteEntity.setQtdPatrimonios(
                    servicoRepository.countByAmbienteEntity_Id(ambienteEntity.getId())
            );

            return ambienteEntity;
        }
        else {
            throw new NotFoundException("ambiente","Nenhum ambiente foi encontrado com o id: " + id);
        }
    }

    @Override
    public Page<AmbienteEntity> getAll(Pageable pagable) {
        Page<AmbienteEntity> ambientes = ambienteRepository.findAll(pagable);

        if (ambientes.isEmpty()) {
            throw new NotFoundException("ambiente","Nenhum ambiente foi encontrado");
        }

        ambientes.forEach(ambiente -> {
            ambiente.setQtdPatrimonios(servicoRepository.countByAmbienteEntity_Id(ambiente.getId()));
        });

        return ambientes;
    }

    @Override
    public AmbienteEntity save(AmbienteEntity ambiente) {
        Optional<BlocoEntity> bloco = blocoRepository.findById(ambiente.getBlocoEntity().getId());
        if (bloco.isEmpty()) {
            throw new ValidationException("bloco","Nenhum bloco foi encontrado com o id: " + ambiente.getBlocoEntity().getId());
        }

        ambiente.setBlocoEntity(bloco.get());
        ambiente.setUsuarioEntityCriador(securityService.getUsuario());
        ambiente.setDtRecord(DataUtils.getNow());

        ambiente = ambienteRepository.save(ambiente);
        return ambiente;
    }

    public void transferirAmbientes(long ambienteOrigemId, long ambienteDestinoId) {
        Optional<AmbienteEntity> ambienteOrigem = ambienteRepository.findById(ambienteOrigemId);
        Optional<AmbienteEntity> ambienteDestino = ambienteRepository.findById(ambienteDestinoId);

        Map<String, String> listErros = new HashMap<>();

        if (!ambienteOrigem.isPresent()) {
            listErros.put("ambienteOrigem", "Nenhum ambiente de origem foi encontrado com o id: "+ ambienteOrigemId +".");
        }
        if (!ambienteDestino.isPresent()) {
            listErros.put("ambienteDestino", "Nenhum ambiente de destino foi encontrado com o id: "+ ambienteDestinoId +".");
        }
        if(!listErros.isEmpty()) {
            throw new ValidationException(listErros);
        }
        if (ambienteOrigem.get().getId() == ambienteDestino.get().getId()) {
            throw new ValidationException("ambientes","O ambiente de origem e de destino não podem ser o mesmo.");
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

        Map<String, String> listErros = new HashMap<>();
        Optional<List<ServicoEntity>> servicos = servicoRepository.findAllByAmbienteEntity_Id(id);
        if (servicos.isPresent()) {
            listErros.put("servicos", "Não é possível deletar o ambiente pois existem serviços associados a ele.");
        }

        Optional<List<TarefaEntity>> tarefas = tarefaRepository.findAllByAmbienteEntity_Id(id);
        if (tarefas.isPresent()) {
            listErros.put("tarefas", "Não é possível deletar o ambiente pois existem tarefas associadas a ele.");
        }

        if(!listErros.isEmpty()) {
            throw new ValidationException(listErros);
        }
    }

}