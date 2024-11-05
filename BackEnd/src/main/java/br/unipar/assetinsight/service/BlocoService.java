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

        if (bloco.isPresent()) {
            BlocoEntity retorno = bloco.get();
            retorno.setQtdAmbientes(ambienteRepository.countByBlocoEntityId(id));

            return retorno;
        }
        else {
            throw new NotFoundException("bloco", "Nenhum bloco foi encontrado com o id: " + id);
        }
    }

    @Override
    public Page<BlocoEntity> getAll(Pageable pagable) throws NotFoundException {
        Page<BlocoEntity> blocos = blocoRepository.findAll(pagable);

        for (BlocoEntity bloco : blocos) {
            bloco.setQtdAmbientes(ambienteRepository.countByBlocoEntityId(bloco.getId()));
        }

        if (blocos.isEmpty()) {
            throw new NotFoundException("bloco", "Nenhum bloco foi encontrado");
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
        Optional<List<AmbienteEntity>> ambientes = ambienteRepository.findByBlocoEntityId(blocoId);
        Optional<BlocoEntity> blocoDestino = blocoRepository.findById(blocoDestinoId);
        Optional<BlocoEntity> blocoOrigem = blocoRepository.findById(blocoId);

        if (ambientes.isEmpty()) {
            throw new NotFoundException("ambiente", "Não existem ambientes associados ao bloco.");
        }
        if (blocoDestino.isEmpty()) {
            throw new NotFoundException("blocoDestino", "Bloco de destino não encontrado.");
        }
        if (blocoOrigem.isEmpty()) {
            throw new NotFoundException("blocoOrigem", "Bloco de origem não encontrado.");
        }

        List<AmbienteEntity> listAmbientes = ambientes.get();
        BlocoEntity bloco = blocoOrigem.get();
        for (AmbienteEntity ambiente : listAmbientes) {
            ambiente.setBlocoEntity(bloco);
            ambiente.setUsuarioEntityCriador(securityService.getUsuario());
            ambienteRepository.save(ambiente);
        }
    }

    @Override
    public void deleteById(long id) {
        Optional<BlocoEntity> bloco = blocoRepository.findById(id);
        if (bloco.isEmpty()) {
            throw new NotFoundException("bloco", "O bloco informado não existe.");
        }

        Optional<List<AmbienteEntity>> ambientes = ambienteRepository.findByBlocoEntityId(id);
        if (ambientes.isPresent()) {
            throw new ValidationException("bloco", "Não é possível deletar o bloco pois existem ambientes associados a ele.");
        }

        blocoRepository.deleteById(id);
    }

}
