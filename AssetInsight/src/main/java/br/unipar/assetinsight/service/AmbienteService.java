package br.unipar.assetinsight.service;

import br.unipar.assetinsight.dtos.requests.AmbienteRequest;
import br.unipar.assetinsight.dtos.responses.AmbienteResponse;
import br.unipar.assetinsight.entities.AmbienteEntity;
import br.unipar.assetinsight.entities.BlocoEntity;
import br.unipar.assetinsight.exceptions.NotFoundException;
import br.unipar.assetinsight.exceptions.ValidationException;
import br.unipar.assetinsight.mappers.AmbienteMapper;
import br.unipar.assetinsight.repositories.AmbienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AmbienteService {
    private AmbienteRepository ambienteRepository;
    private SecurityService securityService;
    private BlocoService blocoService;

    public void deleteById(long id) throws ValidationException {
        // Implemente a lógica de exclusão aqui
    }

    public AmbienteResponse getById(long id) throws NotFoundException {
        Optional<AmbienteEntity> ambiente = ambienteRepository.findById(id);

        if (ambiente.isEmpty()) {
            throw new NotFoundException("Nenhum ambiente foi encontrado com o id: " + id);
        }

        return AmbienteMapper.INSTANCE.toResponse(ambiente.get());
    }

    public List<AmbienteResponse> getAll() throws NotFoundException {
        List<AmbienteEntity> ambientes = ambienteRepository.findAll();

        if (ambientes.isEmpty()) {
            throw new NotFoundException("Nenhum ambiente foi encontrado");
        }

        return AmbienteMapper.INSTANCE.toResponseList(ambientes);
    }

    public AmbienteResponse save(AmbienteRequest request) throws NotFoundException {
        AmbienteEntity ambiente = AmbienteMapper.INSTANCE.toEntity(request);
        Optional<AmbienteEntity> consulta = ambienteRepository.findById(ambiente.getId());

        if (consulta.isPresent()) {
            BlocoEntity bloco = blocoService.getById(request.bloco().id());
            ambiente = AmbienteMapper.INSTANCE.updateEntity(request, consulta.get());
            ambiente.setBlocoEntity(bloco);
        }

        ambiente.setUsuarioEntityCriador(securityService.getUsuario());
        ambiente = ambienteRepository.save(ambiente);

        return AmbienteMapper.INSTANCE.toResponse(ambiente);
    }
}