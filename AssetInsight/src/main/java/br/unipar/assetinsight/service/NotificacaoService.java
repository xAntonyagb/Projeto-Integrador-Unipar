package br.unipar.assetinsight.service;

import br.unipar.assetinsight.entities.NotificacaoEntity;
import br.unipar.assetinsight.entities.OrdemServicoEntity;
import br.unipar.assetinsight.entities.TarefaEntity;
import br.unipar.assetinsight.enums.NotificacaoEnum;
import br.unipar.assetinsight.enums.StatusTarefaEnum;
import br.unipar.assetinsight.exceptions.NotFoundException;
import br.unipar.assetinsight.repositories.NotificacaoRepository;
import br.unipar.assetinsight.repositories.OrdemServicoRepository;
import br.unipar.assetinsight.repositories.TarefaRepository;
import br.unipar.assetinsight.service.interfaces.IService;
import br.unipar.assetinsight.utils.CalculoUtils;
import br.unipar.assetinsight.utils.DataUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NotificacaoService implements IService<NotificacaoEntity> {
    private final TarefaRepository tarefaRepository;
    private final NotificacaoRepository notificacaoRepository;
    private final OrdemServicoRepository ordemServicoRepository;

    @Override
    public NotificacaoEntity getById(long id) {
        Optional<NotificacaoEntity> notificacao = notificacaoRepository.findById(id);

        return notificacao.orElseThrow(
                () -> new NotFoundException("Nenhuma notificação foi encontrada com o id: " + id)
        );
    }

    @Override
    public Page<NotificacaoEntity> getAll(Pageable pageable) {
        Page<NotificacaoEntity> notificacoes = notificacaoRepository.findAll(pageable);

        if (notificacoes.isEmpty()) {
            throw new NotFoundException("Nenhuma notificação foi encontrada");
        }

        return notificacoes;
    }

    @Override
    public NotificacaoEntity save(NotificacaoEntity entity) {
        return notificacaoRepository.save(entity);
    }

    @Override
    public void deleteById(long id) {
        Optional<NotificacaoEntity> notificacao = notificacaoRepository.findById(id);

        if (notificacao.isEmpty()) {
            throw new NotFoundException("Nenhuma notificação foi encontrada com o id: " + id);
        }

        notificacaoRepository.deleteById(id);
    }

    @Scheduled(cron = "0 0 0 * * *") // todo dia a meia noite exeucta automatico
    public void gerarNotificacaoTarefa() {
        Optional<List<TarefaEntity>> tarefasAtrasadas = tarefaRepository.findAllByDtPrevisaoBefore(DataUtils.getNow());

        tarefasAtrasadas.ifPresent(tarefaEntities -> tarefaEntities.forEach(tarefa -> {
            tarefa.setStatus(StatusTarefaEnum.ATRASADA);
            tarefaRepository.save(tarefa);

            NotificacaoEntity notificacaoEntity = new NotificacaoEntity();
            notificacaoEntity.setTipo(NotificacaoEnum.TAREFA);
            notificacaoEntity.setTitulo("Tarefa atrasada");
            notificacaoEntity.setDescricao("A sua tarefa " + tarefa.getTitulo() + " está atrasada (" + DataUtils.formatarData(tarefa.getDtPrevisao()) + ").");
            notificacaoEntity.setDtEnvio(DataUtils.getNow());
            notificacaoEntity.setLida(false);
            notificacaoRepository.save(notificacaoEntity);
        }));
    }

    @Scheduled(cron = "0 0 0 1 * *") // todo dia 1 de cada mês exeucta automatico
    public void gerarNotificacaoOrdemServico() {
        NotificacaoEntity notificacaoEntity = new NotificacaoEntity();
        notificacaoEntity.setTipo(NotificacaoEnum.ORDEM_SERVICO);
        notificacaoEntity.setTitulo("Ordem de serviço");
        notificacaoEntity.setDtEnvio(DataUtils.getNow());
        notificacaoEntity.setLida(false);

        Month mesPassado = DataUtils.getMonth().minus(1);
        Month mesRetrasado = DataUtils.getMonth().minus(2);

        List<OrdemServicoEntity> osMesPassado = ordemServicoRepository.findAllByMonth(mesPassado);
        List<OrdemServicoEntity> osMesRetrasado = ordemServicoRepository.findAllByMonth(mesRetrasado);

        double vlTotalMesRetrasado = 0;
        if (!osMesRetrasado.isEmpty()) {
            vlTotalMesRetrasado = osMesRetrasado.stream().mapToDouble(OrdemServicoEntity::getValorTotal).sum();
        }

        double vlTotalMesPassado = 0;
        if (!osMesPassado.isEmpty()) {
            vlTotalMesPassado = osMesPassado.stream().mapToDouble(OrdemServicoEntity::getValorTotal).sum();
        }

        if (vlTotalMesPassado == vlTotalMesRetrasado) {
            notificacaoEntity.setDescricao("Os gastos do mês de "+ mesPassado.name().toLowerCase() +"foram iguais ao mês anterior.");
            notificacaoRepository.save(notificacaoEntity);
            return;
        }

        double porcentagem;
        if (vlTotalMesPassado > vlTotalMesRetrasado) {
            porcentagem = ((vlTotalMesPassado - vlTotalMesRetrasado) / vlTotalMesRetrasado) * 100;
            porcentagem = CalculoUtils.arredondar(porcentagem, 2);

            notificacaoEntity.setDescricao("Os gastos do mês de "+ mesPassado.name().toLowerCase() +"foram "+ porcentagem +"% acima comparado ao mês anterior.");
        }
        else {
            porcentagem = ((vlTotalMesRetrasado - vlTotalMesPassado) / vlTotalMesPassado) * 100;
            porcentagem = CalculoUtils.arredondar(porcentagem, 2);

            notificacaoEntity.setDescricao("Os gastos do mês de "+ mesPassado.name().toLowerCase() +"foram "+ porcentagem +"% abaixo comparado ao mês anterior.");
        }

        notificacaoRepository.save(notificacaoEntity);
    }
}
