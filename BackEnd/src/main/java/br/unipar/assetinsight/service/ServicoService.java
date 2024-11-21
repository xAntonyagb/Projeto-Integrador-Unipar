package br.unipar.assetinsight.service;

import br.unipar.assetinsight.entities.AmbienteEntity;
import br.unipar.assetinsight.entities.CategoriaEntity;
import br.unipar.assetinsight.entities.ServicoEntity;
import br.unipar.assetinsight.exceptions.NotFoundException;
import br.unipar.assetinsight.exceptions.ValidationException;
import br.unipar.assetinsight.repositories.AmbienteRepository;
import br.unipar.assetinsight.repositories.CategoriaRepository;
import br.unipar.assetinsight.repositories.OrdemServicoRepository;
import br.unipar.assetinsight.repositories.ServicoRepository;
import br.unipar.assetinsight.service.interfaces.IService;
import br.unipar.assetinsight.utils.DataUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class ServicoService {
    private final ServicoRepository servicoRepository;
    private final AmbienteRepository ambienteRepository;
    private final CategoriaRepository categoriaRepository;
    private final SecurityService securityService;
    private final OrdemServicoRepository ordemServicoRepository;

    public ServicoEntity getById(long id) {
        Optional<ServicoEntity> servico = servicoRepository.findById(id);
        return servico.orElseThrow(
                () -> new NotFoundException("servico", "Nenhum serviço foi encontrado com o id: " + id)
        );
    }

    public Page<ServicoEntity> getAll(Pageable pageable) {
        Page<ServicoEntity> servicos = servicoRepository.findAll(pageable);

        if (servicos.isEmpty()) {
            throw new NotFoundException("servico", "Nenhum serviço foi encontrado.");
        }

        return servicos;
    }

    public Page<ServicoEntity> getAllByOSId(long id, Pageable pageable) {
        Page<ServicoEntity> servicos = ordemServicoRepository.findAllById(id, pageable);

        if (servicos.isEmpty()) {
            throw new NotFoundException("servico", "Nenhum serviço foi encontrado.");
        }

        return servicos;
    }


    public ServicoEntity save(ServicoEntity servicoEntity) {
        servicoEntity = validateServico(servicoEntity);

        servicoEntity = servicoRepository.save(servicoEntity);
        return servicoEntity;
    }


    public void deleteById(long id) {
        if (!servicoRepository.existsById(id)) {
            throw new NotFoundException("servico", "Nenhum serviço foi encontrado com o id: " + id);
        }
        servicoRepository.deleteById(id);
    }


    // ----------------------------------- Método pra validação de save ----------------------------------- //

    public ServicoEntity validateServico(ServicoEntity servicoEntity) {
        boolean isCategoriaPreenchida = servicoEntity.getCategoriaEntity() != null;
        boolean isAmbientePreenchido = servicoEntity.getAmbienteEntity() != null;
        boolean isPatrimonioPreenchido = servicoEntity.getPatrimonioEntity() != null;

        Map<String, String> listErros = new HashMap<>();
        if (isCategoriaPreenchida) {
            Optional<CategoriaEntity> categoria = categoriaRepository.findById(servicoEntity.getCategoriaEntity().getId());
            if (categoria.isEmpty()) {
                listErros.put("categoria", "Nenhuma categoria foi encontrada com o id: " + servicoEntity.getCategoriaEntity().getId());
            }
        }

        if (isAmbientePreenchido) {
            Optional<AmbienteEntity> ambiente = ambienteRepository.findById(servicoEntity.getAmbienteEntity().getId());
            if (ambiente.isEmpty()) {
                listErros.put("ambiente", "Nenhum ambiente foi encontrado com o id: " + servicoEntity.getAmbienteEntity().getId());
            }
        }

        if (isPatrimonioPreenchido) {
            if (servicoEntity.getPatrimonioEntity().getId() <= 0) {
                long id = servicoEntity.getPatrimonioEntity().getId();
                listErros.put("patrimonio"+id, "Nenhum patrimônio foi encontrado com o id: " + id);
            }
        }

        if(!listErros.isEmpty()){
            throw new ValidationException(listErros);
        }

        servicoEntity.setValorTotal(servicoEntity.getValorUnit() * servicoEntity.getQuantidade());
        servicoEntity.setCategoriaEntity(servicoEntity.getCategoriaEntity());
        servicoEntity.setAmbienteEntity(servicoEntity.getAmbienteEntity());
        servicoEntity.setUsuarioEntityCriador(securityService.getUsuario());
        servicoEntity.setDtRecord(DataUtils.getNow());

        return servicoEntity;
    }
}
