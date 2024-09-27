package br.unipar.assetinsight.controllers;

import br.unipar.assetinsight.dtos.responses.OrdemServicoResponse;
import br.unipar.assetinsight.entities.OrdemServicoEntity;
import br.unipar.assetinsight.mappers.OrdemServicoMapper;
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

@RequestMapping("ordem-servico")
@RestController
@AllArgsConstructor
public class OrdemServicoController {
    private final IService<OrdemServicoEntity> ordemServicoService;
    private final ArquivadoService arquivadoService;

    @GetMapping("/all")
    public ResponseEntity<Page<OrdemServicoResponse>> getAll(Pageable pageable) {
        Page<OrdemServicoEntity> retorno = ordemServicoService.getAll(pageable);
        Page<OrdemServicoResponse> response = OrdemServicoMapper.INSTANCE.toResponsePage(retorno);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<OrdemServicoResponse> getById(long id) {
        OrdemServicoEntity retorno = ordemServicoService.getById(id);
        OrdemServicoResponse response = OrdemServicoMapper.INSTANCE.toResponse(retorno);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<OrdemServicoResponse> save(OrdemServicoEntity ordemServico) {
        OrdemServicoEntity retorno = ordemServicoService.save(ordemServico);
        OrdemServicoResponse response = OrdemServicoMapper.INSTANCE.toResponse(retorno);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(retorno.getId()).toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uri);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @PostMapping("/arquivar")
    public ResponseEntity<Void> arquivar(@RequestParam long id) {
        arquivadoService.arquivarOS(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/restaurar")
    public ResponseEntity<Void> restaurar(@RequestParam long id) {
        arquivadoService.restaurarOS(id);
        return ResponseEntity.ok().build();
    }

}
