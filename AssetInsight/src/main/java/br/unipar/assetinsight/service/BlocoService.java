package br.unipar.assetinsight.service;

import br.unipar.assetinsight.entities.AmbienteEntity;
import br.unipar.assetinsight.entities.BlocoEntity;
import br.unipar.assetinsight.exceptions.NotFoundException;
import br.unipar.assetinsight.exceptions.ValidationException;
import br.unipar.assetinsight.repositories.AmbienteRepository;
import br.unipar.assetinsight.repositories.BlocoRepository;
import br.unipar.assetinsight.service.interfaces.IService;
import br.unipar.assetinsight.utils.DataUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BlocoService implements IService<BlocoEntity>{
    private AmbienteRepository ambienteRepository;
    private BlocoRepository blocoRepository;
    private SecurityService securityService;

    @Override
    public BlocoEntity getById(long id) {
        Optional<BlocoEntity> bloco = blocoRepository.findById(id);

        if (bloco.isEmpty()) {
            throw new NotFoundException("Nenhum bloco foi encontrado com o id: " + id);
        }

        return bloco.get();
    }

    @Override
    public Page<BlocoEntity> getAll(Pageable pagable) throws NotFoundException {
        Page<BlocoEntity> blocos = blocoRepository.findAll(pagable);

        if (blocos.isEmpty()) {
            throw new NotFoundException("Nenhum bloco foi encontrado");
        }

        return blocos;
    }

    @Override
    public BlocoEntity save(BlocoEntity bloco) {
        bloco.setUsuarioEntityCriador(securityService.getUsuario());
        bloco.setDtRecord(DataUtils.getNow());

        bloco = blocoRepository.save(bloco);
        return bloco;
    }

    public void transferirBloco(long blocoId, long blocoDestinoId) {
        Optional<List<AmbienteEntity>> ambientesOpt = ambienteRepository.findByBlocoEntityId(blocoId);
        Optional<BlocoEntity> blocoDestinoOpt = blocoRepository.findById(blocoDestinoId);
        Optional<BlocoEntity> blocoOrigemOpt = blocoRepository.findById(blocoId);

        if (ambientesOpt.isEmpty()) {
            throw new NotFoundException("Não existem ambientes associados ao bloco.");
        }
        if (blocoDestinoOpt.isEmpty()) {
            throw new NotFoundException("Bloco de destino não encontrado.");
        }
        if (blocoOrigemOpt.isEmpty()) {
            throw new NotFoundException("Bloco de origem não encontrado.");
        }

        List<AmbienteEntity> ambientes = ambientesOpt.get();
        BlocoEntity blocoOrigem = blocoOrigemOpt.get();
        for (AmbienteEntity ambiente : ambientes) {
            ambiente.setBlocoEntity(blocoOrigem);
            ambiente.setUsuarioEntityCriador(securityService.getUsuario());
            ambienteRepository.save(ambiente);
        }
    }

    @Override
    public void deleteById(long id) {
        Optional<BlocoEntity> bloco = blocoRepository.findById(id);
        if (bloco.isEmpty()) {
            throw new NotFoundException("O bloco informado não existe.");
        }

        Optional<List<AmbienteEntity>> ambientes = ambienteRepository.findByBlocoEntityId(id);
        if (ambientes.isPresent()) {
            throw new ValidationException("Não é possível deletar o bloco pois existem ambientes associados a ele.");
        }

        blocoRepository.deleteById(id);
    }

}
