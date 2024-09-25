package br.unipar.assetinsight.service;

import br.unipar.assetinsight.dtos.requests.BlocoRequest;
import br.unipar.assetinsight.dtos.responses.BlocoResponse;
import br.unipar.assetinsight.entities.AmbienteEntity;
import br.unipar.assetinsight.entities.BlocoEntity;
import br.unipar.assetinsight.exceptions.NotFoundException;
import br.unipar.assetinsight.exceptions.ValidationException;
import br.unipar.assetinsight.mappers.BlocoMapper;
import br.unipar.assetinsight.repositories.AmbienteRepository;
import br.unipar.assetinsight.repositories.BlocoRepository;
import br.unipar.assetinsight.utils.DataUtils;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BlocoService {
    private AmbienteRepository ambienteRepository;
    private BlocoRepository blocoRepository;
    private SecurityService securityService;


    public void deleteById(long id) throws ValidationException {
        Optional<List<AmbienteEntity>> ambientes = ambienteRepository.findByBlocoEntityId(id);

        if (ambientes.isPresent()) {
            throw new ValidationException("Não é possível deletar o bloco pois existem ambientes associados a ele.");
        }

        blocoRepository.deleteById(id);
    }

    public void transferirAmbientes(long blocoId, long blocoDestinoId) throws ValidationException {
        Optional<List<AmbienteEntity>> ambientesOpt = ambienteRepository.findByBlocoEntityId(blocoId);
        Optional<BlocoEntity> blocoDestinoOpt = blocoRepository.findById(blocoDestinoId);
        Optional<BlocoEntity> blocoOrigemOpt = blocoRepository.findById(blocoId);

        if (ambientesOpt.isEmpty()) {
            throw new ValidationException("Não existem ambientes associados ao bloco.");
        }
        if (blocoDestinoOpt.isEmpty()) {
            throw new ValidationException("Bloco de destino não encontrado.");
        }
        if (blocoOrigemOpt.isEmpty()) {
            throw new ValidationException("Bloco de origem não encontrado.");
        }

        List<AmbienteEntity> ambientes = ambientesOpt.get();
        BlocoEntity blocoOrigem = blocoOrigemOpt.get();
        for (AmbienteEntity ambiente : ambientes) {
            ambiente.setBlocoEntity(blocoOrigem);
            ambiente.setUsuarioEntityCriador(securityService.getUsuario());
            ambienteRepository.save(ambiente);
        }
    }

    public BlocoEntity getById(long id) throws NotFoundException {
        Optional<BlocoEntity> bloco = blocoRepository.findById(id);

        if (bloco.isEmpty()) {
            throw new NotFoundException("Nenhum bloco foi encontrado com o id: " + id);
        }

        return bloco.get();
    }


    public Page<BlocoEntity> getAll(Pageable pagable) throws NotFoundException {
        Page<BlocoEntity> blocos = blocoRepository.findAll(pagable);

        if (blocos.isEmpty()) {
            throw new NotFoundException("Nenhum bloco foi encontrado");
        }

        return blocos;
    }


    public BlocoEntity save(BlocoRequest request) {
        BlocoEntity bloco = BlocoMapper.INSTANCE.toEntity(request);
        bloco.setUsuarioEntityCriador(securityService.getUsuario());
        bloco.setDtRecord(DataUtils.getNow());

        bloco = blocoRepository.save(bloco);
        return bloco;
    }

}
