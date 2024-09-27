package br.unipar.assetinsight.controllers;

import br.unipar.assetinsight.dtos.responses.UsuarioResponse;
import br.unipar.assetinsight.entities.UsuarioEntity;
import br.unipar.assetinsight.mappers.UsuarioMapper;
import br.unipar.assetinsight.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("usuario")
public class UsuarioController {
    private final UsuarioService service;

    @GetMapping("/all")
    public ResponseEntity<Page<UsuarioResponse>> getAll(Pageable pageable) {
        Page<UsuarioEntity> retorno = service.getAll(pageable);
        Page<UsuarioResponse> response = UsuarioMapper.INSTANCE.toResponsePage(retorno);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<UsuarioResponse> getById(UUID id) {
        UsuarioEntity retorno = service.getById(id);
        UsuarioResponse response = UsuarioMapper.INSTANCE.toResponse(retorno);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteById(UUID id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
