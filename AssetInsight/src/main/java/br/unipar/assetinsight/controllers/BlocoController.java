package br.unipar.assetinsight.controllers;

import br.unipar.assetinsight.dtos.responses.BlocoResponse;
import br.unipar.assetinsight.exceptions.NotFoundException;
import br.unipar.assetinsight.repositories.UsuarioRepository;
import br.unipar.assetinsight.service.BlocoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BlocoController {
    @Autowired
    private UsuarioRepository userRepo;
    @Autowired
    private BlocoService service;

    @GetMapping("/all")
    public ResponseEntity<List<BlocoResponse>> getAll() throws NotFoundException {
        return ResponseEntity.ok(service.getAll());
    }


}
