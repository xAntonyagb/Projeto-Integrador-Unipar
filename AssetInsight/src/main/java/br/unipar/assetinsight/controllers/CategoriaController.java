package br.unipar.assetinsight.controllers;

import br.unipar.assetinsight.dtos.requests.CategoriaRequest;
import br.unipar.assetinsight.dtos.responses.CategoriaResponse;
import br.unipar.assetinsight.entities.CategoriaEntity;
import br.unipar.assetinsight.mappers.CategoriaMapper;
import br.unipar.assetinsight.service.CategoriaService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@AllArgsConstructor
@RestController
@RequestMapping("categoria")
public class CategoriaController {
    private final CategoriaService service;

    @GetMapping("/all")
    public ResponseEntity<Page<CategoriaResponse>> getAll(Pageable pageable) {
        Page<CategoriaEntity> retorno = service.getAll(pageable);
        Page<CategoriaResponse> response = CategoriaMapper.INSTANCE.toResponsePage(retorno);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<CategoriaResponse> getById(@RequestParam long id) {
        CategoriaEntity retorno = service.getById(id);
        CategoriaResponse response = CategoriaMapper.INSTANCE.toResponse(retorno);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CategoriaResponse> save(@RequestBody CategoriaRequest request) {
        CategoriaEntity entity = CategoriaMapper.INSTANCE.toEntity(request);
        CategoriaEntity retorno = service.save(entity);
        CategoriaResponse response = CategoriaMapper.INSTANCE.toResponse(retorno);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(retorno.getId()).toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uri);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteById(@RequestParam long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/transferir")
    public ResponseEntity<CategoriaResponse> update(@RequestBody long idOrigem, long idDestino) {
        service.transferirCategoria(idOrigem, idDestino);
        return ResponseEntity.accepted().build();
    }

}
