package br.unipar.assetinsight.service;

import br.unipar.assetinsight.entities.AmbienteEntity;
import br.unipar.assetinsight.entities.PatrimonioEntity;
import br.unipar.assetinsight.exceptions.NotFoundException;
import br.unipar.assetinsight.repositories.AmbienteRepository;
import br.unipar.assetinsight.repositories.PatrimonioRepository;
import br.unipar.assetinsight.service.interfaces.IService;
import br.unipar.assetinsight.utils.DataUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PatrimonioService implements IService<PatrimonioEntity>{
    private final PatrimonioRepository patrimonioRepository;
    private final AmbienteRepository ambienteRepository;
    private SecurityService securityService;

    @Override
    public PatrimonioEntity getById(long id) {
        Optional<PatrimonioEntity> patrimonio = patrimonioRepository.findById(id);

        return patrimonio.orElseThrow(() ->
                new NotFoundException("patrimonio", "Nenhum patrimonio foi encontrado com o id: " + id));
    }

    @Override
    public Page<PatrimonioEntity> getAll(Pageable pagable, Map<String, String> filtros) throws NotFoundException {
        Page<PatrimonioEntity> patrimonios = patrimonioRepository.findAllWithFilters(pagable, filtros);

        if (patrimonios.isEmpty()) {
            throw new NotFoundException("patrimonio", "Nenhum patrimonio foi encontrado");
        }

        return patrimonios;
    }

    @Override
    public PatrimonioEntity save(PatrimonioEntity patrimonio) {
        if (patrimonio.getAmbienteEntity() != null) {
            long id = patrimonio.getAmbienteEntity().getId();
            AmbienteEntity ambiente = ambienteRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("ambiente", "Nenhum ambiente foi encontrado com o id: " + id));
        }

        patrimonio.setUsuarioEntityCriador(securityService.getUsuario());
        patrimonio.setDtRecord(DataUtils.getNow());

        return patrimonioRepository.save(patrimonio);
    }

    @Override
    public void deleteById(long id) {
        Optional<PatrimonioEntity> patrimonio = patrimonioRepository.findById(id);
        if (patrimonio.isEmpty()) {
            throw new NotFoundException("patrimonio", "O patrimonio informado não existe.");
        }

        patrimonioRepository.deleteById(id);
    }

}
