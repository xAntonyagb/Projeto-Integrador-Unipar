package br.unipar.assetinsight.service;

import br.unipar.assetinsight.entities.*;
import br.unipar.assetinsight.exceptions.NotFoundException;
import br.unipar.assetinsight.exceptions.ValidationException;
import br.unipar.assetinsight.repositories.*;
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
    private final PatrimonioRepository patrimonioRepository;
    private final PatrimonioService patrimonioService;
    private AmbienteRepository ambienteRepository;
    private SecurityService securityService;
    private ServicoRepository servicoRepository;

    @Override
    public AmbienteEntity getById(long id) {
        Optional<AmbienteEntity> ambiente = ambienteRepository.findById(id);

        if (ambiente.isPresent()) {
            AmbienteEntity ambienteEntity = ambiente.get();

            List<PatrimonioEntity> patrimonios = patrimonioRepository.findAllByAmbienteEntity_Id(ambienteEntity.getId()).orElse(new ArrayList<>());
            ambienteEntity.setQtdPatrimonios(patrimonios.size());
            ambienteEntity.setListPatrimonioEntities(patrimonios);

            BlocoEntity bloco = ambienteEntity.getBlocoEntity();
            bloco.setQtdAmbientes(ambienteRepository.countByBlocoEntityId(bloco.getId()));
            ambienteEntity.setBlocoEntity(bloco);

            return ambienteEntity;
        }
        else {
            throw new NotFoundException("ambiente","Nenhum ambiente foi encontrado com o id: " + id);
        }
    }

    @Override
    public Page<AmbienteEntity> getAll(Pageable pagable, Map<String, String> filtros) {
        Page<AmbienteEntity> ambientes = ambienteRepository.findAllWithFilters(pagable, filtros);

        if (ambientes.isEmpty()) {
            throw new NotFoundException("ambiente","Nenhum ambiente foi encontrado");
        }

        ambientes.forEach(ambiente -> {
            List<PatrimonioEntity> patrimonios = patrimonioRepository.findAllByAmbienteEntity_Id(ambiente.getId()).orElse(new ArrayList<>());
            ambiente.setQtdPatrimonios(patrimonios.size());
            ambiente.setListPatrimonioEntities(patrimonios);

            BlocoEntity bloco = ambiente.getBlocoEntity();
            bloco.setQtdAmbientes(ambienteRepository.countByBlocoEntityId(bloco.getId()));
            ambiente.setBlocoEntity(bloco);
        });

        return ambientes;
    }

    @Override
    public AmbienteEntity save(AmbienteEntity ambiente) {
        Map<String, String> listErros = new HashMap<>();

        Optional<BlocoEntity> bloco = blocoRepository.findById(ambiente.getBlocoEntity().getId());
        if (bloco.isEmpty()) {
            listErros.put("bloco","Nenhum bloco foi encontrado com o id: " + ambiente.getBlocoEntity().getId());
        }

        List<PatrimonioEntity> patrimoniosToSave = new ArrayList<>();
        if (ambiente.getListPatrimonioEntities() != null) {
            for (PatrimonioEntity patrimonio : ambiente.getListPatrimonioEntities()) {
                Optional<PatrimonioEntity> patrimonioEntity = patrimonioRepository.findById(patrimonio.getId());

                if (patrimonioEntity.isEmpty()) {
                    listErros.put("patrimonio/"+patrimonio.getId(),"Nenhum patrimônio foi encontrado com o id: " + patrimonio.getId());
                }
                if (patrimonioEntity.isPresent()
                        && patrimonioEntity.get().getAmbienteEntity() != null
                        && patrimonioEntity.get().getAmbienteEntity().getId() != 0
                        && patrimonioEntity.get().getAmbienteEntity().getId() != ambiente.getId())
                {
                    listErros.put("patrimonio/"+patrimonio.getId(),"Este patrimônio já está associado ao ambiente:" + patrimonioEntity.get().getAmbienteEntity().getDescricao());
                }

                if (!listErros.isEmpty()) {
                    throw new ValidationException(listErros);
                } else {
                    patrimoniosToSave.add(patrimonioEntity.get());
                }
            }
        }

        if (!listErros.isEmpty()) {
            throw new ValidationException(listErros);
        }

        ambiente.setBlocoEntity(bloco.get());
        ambiente.setUsuarioEntityCriador(securityService.getUsuario());
        ambiente.setDtRecord(DataUtils.getNow());

        ambiente = ambienteRepository.save(ambiente);
        for (PatrimonioEntity patrimonio : patrimoniosToSave) {
            patrimonio.setAmbienteEntity(ambiente);
            patrimonioService.save(patrimonio);
        }

        BlocoEntity blocoEntity = ambiente.getBlocoEntity();
        blocoEntity.setQtdAmbientes(ambienteRepository.countByBlocoEntityId(blocoEntity.getId()));
        ambiente.setBlocoEntity(blocoEntity);

        List<PatrimonioEntity> patrimonios = patrimonioRepository.findAllByAmbienteEntity_Id(ambiente.getId()).orElse(new ArrayList<>());
        ambiente.setQtdPatrimonios(patrimonios.size());
        ambiente.setListPatrimonioEntities(patrimonios);

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
        if (ambienteOrigem.get().getId() == ambienteDestino.get().getId()) {
            listErros.put("ambienteOrigem/ambienteDestino","O ambiente de origem e de destino não podem ser o mesmo.");
        }
        if(!listErros.isEmpty()) {
            throw new ValidationException(listErros);
        }



        List<TarefaEntity> tarefas = tarefaRepository.findAllByAmbienteEntity_Id(ambienteOrigemId).orElse(new ArrayList<>());
        for (TarefaEntity tarefa : tarefas) {
            tarefa.setAmbienteEntity(ambienteDestino.get());
            tarefaRepository.save(tarefa);
        }

        List<PatrimonioEntity> patrimonios = patrimonioRepository.findAllByAmbienteEntity_Id(ambienteOrigemId).orElse(new ArrayList<>());
        for (PatrimonioEntity patrimonio : patrimonios) {
            patrimonio.setAmbienteEntity(ambienteDestino.get());
            patrimonioRepository.save(patrimonio);
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

        Optional<List<PatrimonioEntity>> patrimonios = patrimonioRepository.findAllByAmbienteEntity_Id(id);
        if (patrimonios.isPresent()) {
            listErros.put("patrimonios", "Não é possível deletar o ambiente pois existem patrimônios associados a ele.");
        }

        if(!listErros.isEmpty()) {
            throw new ValidationException(listErros);
        }
    }

}