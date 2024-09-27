package br.unipar.assetinsight.controllers;

import br.unipar.assetinsight.dtos.responses.ArquivadoResponse;
import br.unipar.assetinsight.entities.ArquivadoEntity;
import br.unipar.assetinsight.enums.TipoArquivadoEnum;
import br.unipar.assetinsight.mappers.ArquivadosMapper;
import br.unipar.assetinsight.service.ArquivadoService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequestMapping("arquivados")
@RestController
@AllArgsConstructor
public class ArquivadoController {
    private final ArquivadoService arquivadoService;

    @GetMapping("/all")
    public ResponseEntity<Page<ArquivadoResponse>> getAll(Pageable pageable) {
        Page<ArquivadoEntity> retorno = arquivadoService.getAll(pageable);
        Page<ArquivadoResponse> response = ArquivadosMapper.INSTANCE.toResponsePage(retorno);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ArquivadoResponse> getById(long id) {
        ArquivadoEntity retorno = arquivadoService.getById(id);
        ArquivadoResponse response = ArquivadosMapper.INSTANCE.toResponse(retorno);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/restaurar")
    public ResponseEntity<ArquivadoResponse> restaurar(long id) {
        ArquivadoEntity arquivado = arquivadoService.restaurarById(id);
        ArquivadoResponse response = ArquivadosMapper.INSTANCE.toResponse(arquivado);

        URI uri;
        if (arquivado.getTipo() == TipoArquivadoEnum.TAREFA) {
            uri = URI.create("/tarefa/" + arquivado.getTarefaEntity().getId());
        }
        else {
            uri = URI.create("/ordem-servico/" + arquivado.getOrdemServicoEntity().getId());
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uri);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteById(long id) {
        arquivadoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
