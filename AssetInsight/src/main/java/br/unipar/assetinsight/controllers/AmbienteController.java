package br.unipar.assetinsight.controllers;

import br.unipar.assetinsight.dtos.requests.AmbienteRequest;
import br.unipar.assetinsight.dtos.responses.AmbienteResponse;
import br.unipar.assetinsight.entities.AmbienteEntity;
import br.unipar.assetinsight.mappers.AmbienteMapper;
import br.unipar.assetinsight.service.AmbienteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

@AllArgsConstructor
@RestController
@RequestMapping("ambiente")
public class AmbienteController {
    private final AmbienteService service;

    @GetMapping("/all")
    public ResponseEntity<Page<AmbienteResponse>> getAll(Pageable pageable) {
        Page<AmbienteEntity> retorno = service.getAll(pageable);
        Page<AmbienteResponse> response = AmbienteMapper.INSTANCE.toResponsePage(retorno);


        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<AmbienteResponse> getById(@RequestParam long id) {
        AmbienteEntity retorno = service.getById(id);
        AmbienteResponse response = AmbienteMapper.INSTANCE.toResponse(retorno);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteById(@RequestParam long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/transferir")
    public ResponseEntity<Void> transferirAmbientes(@RequestParam long ambienteId, @RequestParam long ambienteDestinoId) {
        service.transferirAmbientes(ambienteId, ambienteDestinoId);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<AmbienteResponse> save(@RequestBody @Valid AmbienteRequest request) {
        AmbienteEntity retorno = service.save(
                AmbienteMapper.INSTANCE.toEntity(request)
        );

        AmbienteResponse response = AmbienteMapper.INSTANCE.toResponse(retorno);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(retorno.getId()).toUri();

        return ResponseEntity.created(uri).body(response);
    }

}
