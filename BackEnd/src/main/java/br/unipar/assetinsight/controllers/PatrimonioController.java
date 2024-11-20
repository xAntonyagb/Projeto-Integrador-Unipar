package br.unipar.assetinsight.controllers;

import br.unipar.assetinsight.dtos.requests.BlocoRequest;
import br.unipar.assetinsight.dtos.requests.PatrimonioRequest;
import br.unipar.assetinsight.dtos.responses.main.BlocoResponse;
import br.unipar.assetinsight.dtos.responses.main.PatrimonioResponse;
import br.unipar.assetinsight.entities.BlocoEntity;
import br.unipar.assetinsight.entities.PatrimonioEntity;
import br.unipar.assetinsight.exceptions.handler.ApiExceptionDTO;
import br.unipar.assetinsight.mappers.BlocoMapper;
import br.unipar.assetinsight.mappers.PatrimonioMapper;
import br.unipar.assetinsight.service.BlocoService;
import br.unipar.assetinsight.service.PatrimonioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
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

@AllArgsConstructor
@RestController
@RequestMapping("patrimonio")
@Tag(name = "Patrimonios", description = "Endpoints para operações relacionadas a patrimonios.")
public class PatrimonioController {
    private final PatrimonioService service;


    @Operation(summary = "Retorna todos os patrimonios cadastrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BlocoResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "403", description = "Acesso negado - Permissões insuficientes.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "O registro não pôde ser encontrado.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) })
    })
    @Parameters({
            @Parameter(name = "page", description = "Número da página.", in = ParameterIn.QUERY, required = false, schema = @Schema(type = "integer", defaultValue = "0", example = "0")),
            @Parameter(name = "size", description = "Quantidade de registros por página.", in = ParameterIn.QUERY, required = false, schema = @Schema(type = "integer", defaultValue = "10", example = "1")),
            @Parameter(name = "sort", description = "Ordenação dos registros e exibição.", in = ParameterIn.QUERY, required = false, schema = @Schema(type = "string", example = "property,asc"))
    })
    @GetMapping("/all")
    public ResponseEntity<Page<PatrimonioResponse>> getAll(@Parameter(hidden = true) Pageable pageable) {
        Page<PatrimonioEntity> retorno = service.getAll(pageable);
        Page<PatrimonioResponse> response = PatrimonioMapper.INSTANCE.toResponsePage(retorno);

        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Retorna um patrimonio específico pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BlocoResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "403", description = "Acesso negado - Permissões insuficientes.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "O registro não pôde ser encontrado.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) })
    })
    @GetMapping
    public ResponseEntity<PatrimonioResponse> getById(@Valid @RequestParam long id) {
        PatrimonioEntity retorno = service.getById(id);
        PatrimonioResponse response = PatrimonioMapper.INSTANCE.toResponse(retorno);

        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Deleta um patrimonio específico pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Patrimonio excluído com sucesso.", content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida.", content = { @Content(mediaType = "application/json, schema = @Schema(implementation = ApiExceptionDTO.class)") }),
            @ApiResponse(responseCode = "403", description = "Acesso negado - Permissões insuficientes.", content = { @Content(mediaType = "application/json, schema = @Schema(implementation = ApiExceptionDTO.class)") }),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "O registro não pôde ser encontrado.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) })
    })
    @DeleteMapping
    public ResponseEntity<Void> deleteById(@Valid @RequestParam long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "Cadastra ou atualiza um patrimonio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = BlocoResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "403", description = "Acesso negado - Permissões insuficientes.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "O registro não pôde ser encontrado.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) })
    })
    @PostMapping
    public ResponseEntity<PatrimonioResponse> save(@RequestBody @Valid PatrimonioRequest request) {
        PatrimonioEntity retorno = service.save(
                PatrimonioMapper.INSTANCE.toEntity(request)
        );

        PatrimonioResponse response = PatrimonioMapper.INSTANCE.toResponse(retorno);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(retorno.getId()).toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uri);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

}
