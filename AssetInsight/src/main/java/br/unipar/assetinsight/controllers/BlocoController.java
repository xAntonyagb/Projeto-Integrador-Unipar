package br.unipar.assetinsight.controllers;

import br.unipar.assetinsight.dtos.requests.BlocoRequest;
import br.unipar.assetinsight.dtos.responses.BlocoResponse;
import br.unipar.assetinsight.entities.BlocoEntity;
import br.unipar.assetinsight.mappers.BlocoMapper;
import br.unipar.assetinsight.service.BlocoService;
import jakarta.validation.Valid;
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
@RequestMapping("bloco")
public class BlocoController {
    private final BlocoService service;


    @GetMapping("/all")
    public ResponseEntity<Page<BlocoResponse>> getAll(Pageable pageable) {
        Page<BlocoEntity> retorno = service.getAll(pageable);
        Page<BlocoResponse> response = BlocoMapper.INSTANCE.toResponsePage(retorno);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<BlocoResponse> getById(@RequestParam long id) {
        BlocoEntity retorno = service.getById(id);
        BlocoResponse response = BlocoMapper.INSTANCE.toResponse(retorno);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteById(@RequestParam long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/transferir")
    public ResponseEntity<Void> transferirAmbientes(@RequestParam long blocoId, @RequestParam long blocoDestinoId) {
        service.transferirAmbientes(blocoId, blocoDestinoId);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<BlocoResponse> save(@RequestBody @Valid BlocoRequest request) {
        BlocoEntity retorno = service.save(
                BlocoMapper.INSTANCE.toEntity(request)
        );

        BlocoResponse response = BlocoMapper.INSTANCE.toResponse(retorno);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(retorno.getId()).toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uri);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

}
