package br.unipar.assetinsight.service;

import br.unipar.assetinsight.dtos.requests.BlocoRequest;
import br.unipar.assetinsight.dtos.responses.BlocoResponse;
import br.unipar.assetinsight.entities.BlocoEntity;
import br.unipar.assetinsight.exceptions.NotFoundException;
import br.unipar.assetinsight.mappers.BlocoMapper;
import br.unipar.assetinsight.repositories.BlocoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
public class BlocoService {
    @Autowired
    private BlocoRepository repository;


    public void deleteById(long id) {
        repository.deleteById(id);
    }


    public BlocoResponse getById(long id) throws NotFoundException {
        Optional<BlocoEntity> bloco = repository.findById(id);

        if (bloco.isEmpty()) {
            throw new NotFoundException("Nenhum de bloco foi encontrado com o id: " + id);
        }

        return BlocoMapper.INSTANCE.toResponse(bloco.get());
    }


    public List<BlocoResponse> getAll() throws NotFoundException {
        List<BlocoEntity> blocos = repository.findAll();

        if (blocos.isEmpty()) {
            throw new NotFoundException("Nenhum bloco foi encontrado");
        }

        return BlocoMapper.INSTANCE.toResponseList(blocos);
    }


    public BlocoResponse save(@Validated BlocoEntity bloco) {
        BlocoEntity retorno = repository.save(bloco);
        return BlocoMapper.INSTANCE.toResponse(retorno);
    }


    public static void validateBloco(BlocoRequest request) {
    }

}
