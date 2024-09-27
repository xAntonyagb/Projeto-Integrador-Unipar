package br.unipar.assetinsight.controllers;

import br.unipar.assetinsight.dtos.requests.OrdemServicoRequest;
import br.unipar.assetinsight.dtos.responses.ArquivadoResponse;
import br.unipar.assetinsight.dtos.responses.OrdemServicoResponse;
import br.unipar.assetinsight.entities.OrdemServicoEntity;
import br.unipar.assetinsight.exceptions.handler.ApiExceptionDTO;
import br.unipar.assetinsight.mappers.OrdemServicoMapper;
import br.unipar.assetinsight.service.ArquivadoService;
import br.unipar.assetinsight.service.interfaces.IService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@RequestMapping("ordem-servico")
@RestController
@AllArgsConstructor
@Tag(name = "Ordens de Serviço", description = "Endpoints para operações relacionadas a Ordens de Serviço.")
public class OrdemServicoController {
    private final IService<OrdemServicoEntity> ordemServicoService;
    private final ArquivadoService arquivadoService;


    @Operation(summary = "Retorna todas as ordens de serviço cadastradas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = OrdemServicoResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "403", description = "Acesso negado - Permissões insuficientes.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "O registro não pôde ser encontrado.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) })
    })
    @GetMapping("/all")
    public ResponseEntity<Page<OrdemServicoResponse>> getAll(Pageable pageable) {
        Page<OrdemServicoEntity> retorno = ordemServicoService.getAll(pageable);
        Page<OrdemServicoResponse> response = OrdemServicoMapper.INSTANCE.toResponsePage(retorno);

        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Retorna uma ordem de serviço específica.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = OrdemServicoResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "403", description = "Acesso negado - Permissões insuficientes.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "O registro não pôde ser encontrado.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) })
    })
    @GetMapping
    public ResponseEntity<OrdemServicoResponse> getById(@Valid @RequestParam long id) {
        OrdemServicoEntity retorno = ordemServicoService.getById(id);
        OrdemServicoResponse response = OrdemServicoMapper.INSTANCE.toResponse(retorno);

        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Cadastra ou atualiza uma ordem de serviço.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = OrdemServicoResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "403", description = "Acesso negado - Permissões insuficientes.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "O registro não pôde ser encontrado.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) })
    })
    @PostMapping
    public ResponseEntity<OrdemServicoResponse> save(@Valid @RequestBody OrdemServicoRequest ordemServico) {
        OrdemServicoEntity retorno = ordemServicoService.save(OrdemServicoMapper.INSTANCE.toEntity(ordemServico));
        OrdemServicoResponse response = OrdemServicoMapper.INSTANCE.toResponse(retorno);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(retorno.getId()).toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uri);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }


    @Operation(summary = "Arquiva uma ordem de serviço.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Ordem de Serviço arquivada com sucesso.", content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "403", description = "Acesso negado - Permissões insuficientes.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "O registro não pôde ser encontrado.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) })
    })
    @PostMapping("/arquivar")
    public ResponseEntity<Void> arquivar(@Valid @RequestParam long id) {
        arquivadoService.arquivarOS(id);
        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "Restaura uma ordem de serviço que havia sido arquivada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Ordem de Serviço restaurada com sucesso.", content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "403", description = "Acesso negado - Permissões insuficientes.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "O registro não pôde ser encontrado.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) })
    })
    @PostMapping("/restaurar")
    public ResponseEntity<Void> restaurar(@Valid @RequestParam long id) {
        arquivadoService.restaurarOS(id);
        return ResponseEntity.noContent().build();
    }

}
