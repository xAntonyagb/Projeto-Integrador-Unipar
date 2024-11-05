package br.unipar.assetinsight.service;

import br.unipar.assetinsight.entities.ArquivadoEntity;
import br.unipar.assetinsight.entities.OrdemServicoEntity;
import br.unipar.assetinsight.entities.TarefaEntity;
import br.unipar.assetinsight.enums.TipoArquivadoEnum;
import br.unipar.assetinsight.exceptions.NotFoundException;
import br.unipar.assetinsight.exceptions.ValidationException;
import br.unipar.assetinsight.repositories.ArquivadoRepository;
import br.unipar.assetinsight.repositories.OrdemServicoRepository;
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
public class ArquivadoService implements IService<ArquivadoEntity> {
    private final ArquivadoRepository arquivadoRepository;
    private final OrdemServicoRepository ordemServicoRepository;
    private final TarefaRepository tarefaRepository;
    private final OrdemServicoService ordemServicoService;
    private final TarefaService tarefaService;
    private final SecurityService securityService;
    private final ValidateUtilsService validateUtilsService;

    @Override
    public ArquivadoEntity getById(long id) {
        Optional<ArquivadoEntity> arquivado = arquivadoRepository.findById(id);

        return arquivado.orElseThrow(
                () -> new NotFoundException("arquivado","Nenhum arquivadao foi encontrado com o id: " + id)
        );
    }

    @Override
    public Page<ArquivadoEntity> getAll(Pageable pageable) {
        Page<ArquivadoEntity> arquivados = arquivadoRepository.findAll(pageable);

        if (arquivados.isEmpty()) {
            throw new NotFoundException("arquivado", "Nenhum arquivado foi encontrado.");
        }

        return arquivados;
    }

    @Override
    public ArquivadoEntity save(ArquivadoEntity entity) {
        Map<String, String> listErros = new HashMap<>();
        OrdemServicoEntity ordemServico = null;
        TarefaEntity tarefa = null;

        if (entity.getOrdemServicoEntity() == null && entity.getTarefaEntity() == null) {
            listErros.put("ordemServico/tarefa", "Ao menos uma ordem de serviço ou tarefa deve ser informada.");
        }
        else if (entity.getOrdemServicoEntity() != null && entity.getTarefaEntity() != null) {
            listErros.put("ordemServico/tarefa", "Apenas uma ordem de serviço ou tarefa deve ser informada.");
        }
        else {
            if (entity.getOrdemServicoEntity() != null) {
                ordemServico = validateUtilsService.validarOrdemServico(entity.getOrdemServicoEntity());
            }
            if (entity.getTarefaEntity() != null) {
                tarefa = validateUtilsService.validarTarefa(entity.getTarefaEntity());
            }
        }

        if(entity.getTipo() == TipoArquivadoEnum.ORDEM_SERVICO && (ordemServico == null || tarefa != null)) {
            listErros.put("tipo", "Tipo Ordem de serviço mas o conteúdo foi informado indevidamente.");
        }
        else if(entity.getTipo() == TipoArquivadoEnum.TAREFA && (tarefa == null || ordemServico != null)) {
            listErros.put("tipo", "Tipo Tarefa mas o conteúdo foi informado indevidamente.");
        }

        if (!listErros.isEmpty()) {
            throw new ValidationException(listErros);
        }

        entity.setTarefaEntity(tarefa);
        entity.setOrdemServicoEntity(ordemServico);

        return arquivadoRepository.save(entity);
    }

    @Override
    public void deleteById(long id) {
        Optional<ArquivadoEntity> retorno = arquivadoRepository.findById(id);
        ArquivadoEntity arquivado;

        if (retorno.isEmpty()) {
            throw new NotFoundException("arquivado", "Nenhum arquivado foi encontrado com o id: " + id);
        } else {
            arquivado = retorno.get();
        }

        if(arquivado.getTipo() == TipoArquivadoEnum.ORDEM_SERVICO) {
            ordemServicoService.deleteById(arquivado.getOrdemServicoEntity().getId());
        }
        else if(arquivado.getTipo() == TipoArquivadoEnum.TAREFA) {
            tarefaService.deleteById(arquivado.getTarefaEntity().getId());
        }

        arquivadoRepository.deleteById(id);
    }

    public ArquivadoEntity restaurarById(long id) {
        Optional<ArquivadoEntity> retorno = arquivadoRepository.findById(id);
        ArquivadoEntity arquivado;

        if (retorno.isEmpty()) {
            throw new NotFoundException("arquivado", "Nenhum arquivado foi encontrado com o id: " + id);
        } else {
            arquivado = retorno.get();
        }

        if(arquivado.getTipo() == TipoArquivadoEnum.ORDEM_SERVICO) {
            restaurarOS(arquivado.getOrdemServicoEntity().getId());
        }
        else if(arquivado.getTipo() == TipoArquivadoEnum.TAREFA) {
            restaurarTarefa(arquivado.getTarefaEntity().getId());
        }

        arquivadoRepository.deleteById(id);
        return arquivado;
    }


    public void arquivarTarefa(long id) {
        TarefaEntity arquivar = tarefaRepository.findById(id).orElseThrow(
                () -> new NotFoundException("tarefa", "Nenhuma tarefa foi encontrada com o id: " + id)
        );

        arquivar.setUsuarioEntityCriador(securityService.getUsuario());
        arquivar.setDtRecord(DataUtils.getNow());
        arquivar.setArquivado(true);
        tarefaRepository.save(arquivar);


        ArquivadoEntity arquivadoEntity = new ArquivadoEntity();
        arquivadoEntity.setTarefaEntity(arquivar);
        arquivadoEntity.setTipo(TipoArquivadoEnum.TAREFA);
        arquivadoEntity.setUsuarioEntityResponsavel(securityService.getUsuario());
        arquivadoEntity.setDtArquivado(DataUtils.getNow());
        save(arquivadoEntity);
    }

    public void arquivarOS(long id) {
        OrdemServicoEntity arquivar = ordemServicoRepository.findById(id).orElseThrow(
                () -> new NotFoundException("ordemServico", "Nenhuma ordem de serviço foi encontrada com o id: " + id)
        );

        arquivar.setUsuarioEntityCriador(securityService.getUsuario());
        arquivar.setDtRecord(DataUtils.getNow());
        arquivar.setArquivado(true);
        ordemServicoRepository.save(arquivar);


        ArquivadoEntity arquivadoEntity = new ArquivadoEntity();
        arquivadoEntity.setOrdemServicoEntity(arquivar);
        arquivadoEntity.setTipo(TipoArquivadoEnum.ORDEM_SERVICO);
        arquivadoEntity.setUsuarioEntityResponsavel(securityService.getUsuario());
        arquivadoEntity.setDtArquivado(DataUtils.getNow());
        save(arquivadoEntity);
    }

    public void restaurarTarefa(long id) {
        Optional<TarefaEntity> tarefa = tarefaRepository.findById(id);

        if (tarefa.isEmpty()) {
            throw new NotFoundException("tarefa", "Nenhuma tarefa foi encontrada com o id: " + id);
        }
        TarefaEntity restaurar = tarefa.get();

        restaurar.setUsuarioEntityCriador(securityService.getUsuario());
        restaurar.setDtRecord(DataUtils.getNow());
        restaurar.setArquivado(false);

        tarefaRepository.save(tarefa.get());
    }

    public void restaurarOS(long id) {
        Optional<OrdemServicoEntity> ordemServico = ordemServicoRepository.findById(id);

        if (ordemServico.isEmpty()) {
            throw new NotFoundException("ordemServico","Nenhuma ordem de serviço foi encontrada com o id: " + id);
        }
        OrdemServicoEntity restaurar = ordemServicoRepository.findById(id).get();

        restaurar.setUsuarioEntityCriador(securityService.getUsuario());
        restaurar.setDtRecord(DataUtils.getNow());
        restaurar.setArquivado(false);

        ordemServicoRepository.save(ordemServico.get());
    }

}
