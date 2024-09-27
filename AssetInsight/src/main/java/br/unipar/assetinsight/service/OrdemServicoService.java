package br.unipar.assetinsight.service;

import br.unipar.assetinsight.entities.OrdemServicoEntity;
import br.unipar.assetinsight.entities.ServicoEntity;
import br.unipar.assetinsight.enums.StatusOrdemServicoEnum;
import br.unipar.assetinsight.exceptions.NotFoundException;
import br.unipar.assetinsight.repositories.OrdemServicoRepository;
import br.unipar.assetinsight.service.interfaces.IService;
import br.unipar.assetinsight.utils.DataUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrdemServicoService implements IService<OrdemServicoEntity> {
    private final OrdemServicoRepository ordemServicoRepository;
    private final ServicoService servicoService;
    private final SecurityService securityService;

    @Override
    public OrdemServicoEntity getById(long id) {
        Optional<OrdemServicoEntity> ordemServico = ordemServicoRepository.findById(id);

        return ordemServico.orElseThrow(
                () -> new NotFoundException("Nenhuma ordem de serviço foi encontrada com o id: " + id)
        );
    }

    @Override
    public Page<OrdemServicoEntity> getAll(Pageable pageable) {
        Page<OrdemServicoEntity> ordensServico = ordemServicoRepository.findAll(pageable);

        if (ordensServico.isEmpty()) {
            throw new NotFoundException("Nenhuma ordem de serviço foi encontrada.");
        }

        return ordensServico;
    }

    @Override
    public OrdemServicoEntity save(OrdemServicoEntity ordemServico) {
        boolean isDescricaoValida = ordemServico.getDescricao() != null && !ordemServico.getDescricao().isEmpty() && !ordemServico.getDescricao().isBlank();
        boolean isDataValida = ordemServico.getData() != null;
        boolean isValorTotalValido = ordemServico.getValorTotal() != 0 && !ordemServico.getListServicoEntities().isEmpty();

        if (isDataValida && isValorTotalValido && isDescricaoValida && isServicosValidos(ordemServico.getListServicoEntities())) {
            ordemServico.setStatus(StatusOrdemServicoEnum.PREENCHIDA);
        }
        else {
            ordemServico.setStatus(StatusOrdemServicoEnum.INCOMPLETA);
        }

        if (!isDescricaoValida) {
            ordemServico.setDescricao("Sem descrição");
        }

        for (int i = 0; i < ordemServico.getListServicoEntities().size(); i++) {
            servicoService.validateServico(ordemServico.getListServicoEntities().get(i)); // Throws ValidationException
            servicoService.save(ordemServico.getListServicoEntities().get(i));
        }
        ordemServico.setUsuarioEntityCriador(securityService.getUsuario());
        ordemServico.setDtRecord(DataUtils.getNow());

        return ordemServicoRepository.save(ordemServico);
    }

    @Override
    public void deleteById(long id) {
        Optional<OrdemServicoEntity> ordemServico = ordemServicoRepository.findById(id);

        if (ordemServico.isEmpty()) {
            throw new NotFoundException("Nenhuma ordem de serviço foi encontrada com o id: " + id);
        }

        ordemServicoRepository.deleteById(id);
    }


    private boolean isServicosValidos(List<ServicoEntity> listServicoEntities) {
        for (ServicoEntity servico : listServicoEntities) {
            boolean isPatrimonioValido = servico.getPatrimonio() != null && !servico.getPatrimonio().isEmpty() && !servico.getPatrimonio().isBlank();
            boolean isValorValido = servico.getValorTotal() > 0;
            boolean isCategoriaValida = servico.getCategoriaEntity() != null;
            boolean isAmbienteValido = servico.getAmbienteEntity() != null;
            boolean isValorUnitValido = servico.getValorUnit() > 0;
            boolean isQuantidadeValida = servico.getQuantidade() > 0;

            if (!isPatrimonioValido || !isValorValido || !isCategoriaValida || !isAmbienteValido || !isValorUnitValido || !isQuantidadeValida) {
                return false;
            }
        }
        return true;
    }
}
