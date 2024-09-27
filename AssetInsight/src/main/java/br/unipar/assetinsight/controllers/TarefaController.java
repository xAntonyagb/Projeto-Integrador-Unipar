package br.unipar.assetinsight.controllers;

import br.unipar.assetinsight.dtos.responses.TarefaResponse;
import br.unipar.assetinsight.entities.TarefaEntity;
import br.unipar.assetinsight.mappers.TarefaMapper;
import br.unipar.assetinsight.service.ArquivadoService;
import br.unipar.assetinsight.service.interfaces.IService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RequestMapping("tarefa")
@AllArgsConstructor
@RestController
public class TarefaController {
    private final IService<TarefaEntity> tarefaService;
    private final ArquivadoService arquivadoService;

    @GetMapping("/all")
    public ResponseEntity<Page<TarefaResponse>> getAll(Pageable pageable) {
        Page<TarefaEntity> retorno = tarefaService.getAll(pageable);
        Page<TarefaResponse> response = TarefaMapper.INSTANCE.toResponsePage(retorno);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<TarefaResponse> getById(long id) {
        TarefaEntity retorno = tarefaService.getById(id);
        TarefaResponse response = TarefaMapper.INSTANCE.toResponse(retorno);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<TarefaResponse> save(TarefaEntity tarefa) {
        TarefaEntity retorno = tarefaService.save(tarefa);
        TarefaResponse response = TarefaMapper.INSTANCE.toResponse(retorno);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(retorno.getId()).toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uri);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @PostMapping("/arquivar")
    public ResponseEntity<Void> arquivar(@RequestParam long id) {
        arquivadoService.arquivarTarefa(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/restaurar")
    public ResponseEntity<Void> restaurar(@RequestParam long id) {
        arquivadoService.restaurarTarefa(id);
        return ResponseEntity.ok().build();
    }

}
