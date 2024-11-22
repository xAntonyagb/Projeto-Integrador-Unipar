package br.unipar.assetinsight.service;

import br.unipar.assetinsight.entities.AmbienteEntity;
import br.unipar.assetinsight.entities.CategoriaEntity;
import br.unipar.assetinsight.entities.PatrimonioEntity;
import br.unipar.assetinsight.entities.ServicoEntity;
import br.unipar.assetinsight.exceptions.NotFoundException;
import br.unipar.assetinsight.exceptions.ValidationException;
import br.unipar.assetinsight.repositories.*;
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
    private final PatrimonioService patrimonioService;
    private final PatrimonioRepository patrimonioRepository;

    public ServicoEntity getById(long id) {
        Optional<ServicoEntity> servico = servicoRepository.findById(id);
        return servico.orElseThrow(
                () -> new NotFoundException("servico/"+ id, "Nenhum serviço foi encontrado com o id: " + id)
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

        Optional<CategoriaEntity> categoria = Optional.empty();
        if (isCategoriaPreenchida) {
            categoria = categoriaRepository.findById(servicoEntity.getCategoriaEntity().getId());
            if (categoria.isEmpty()) {
                long id = servicoEntity.getCategoriaEntity().getId();
                listErros.put("categoria/"+ id, "Nenhuma categoria foi encontrada com o id: " + id);
            }
        }

        Optional<AmbienteEntity> ambiente = Optional.empty();
        if (isAmbientePreenchido) {
            ambiente = ambienteRepository.findById(servicoEntity.getAmbienteEntity().getId());
            if (ambiente.isEmpty()) {
                long id = servicoEntity.getAmbienteEntity().getId();
                listErros.put("ambiente/"+id, "Nenhum ambiente foi encontrado com o id: " + id);
            }
        }

        Optional<PatrimonioEntity> patrimonio = Optional.empty();
        if (isPatrimonioPreenchido) {
            patrimonio = patrimonioRepository.findById(servicoEntity.getPatrimonioEntity().getId());
            if (patrimonio.isEmpty()) {
                long id = servicoEntity.getPatrimonioEntity().getId();
                listErros.put("patrimonio/"+id, "Nenhum patrimônio foi encontrado com o id: " + id);
            }
        }

        if(!listErros.isEmpty()){
            throw new ValidationException(listErros);
        }

        servicoEntity.setValorTotal(servicoEntity.getValorUnit() * servicoEntity.getQuantidade());
        servicoEntity.setCategoriaEntity(categoria.orElse(servicoEntity.getCategoriaEntity()));
        servicoEntity.setAmbienteEntity(ambiente.orElse(servicoEntity.getAmbienteEntity()));
        servicoEntity.setPatrimonioEntity(patrimonio.orElse(servicoEntity.getPatrimonioEntity()));
        servicoEntity.setUsuarioEntityCriador(securityService.getUsuario());
        servicoEntity.setDtRecord(DataUtils.getNow());

        return servicoEntity;
    }
}
