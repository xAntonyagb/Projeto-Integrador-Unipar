package br.unipar.assetinsight.controllers;

import br.unipar.assetinsight.dtos.requests.AmbienteRequest;
import br.unipar.assetinsight.dtos.responses.main.AmbienteResponse;
import br.unipar.assetinsight.entities.AmbienteEntity;
import br.unipar.assetinsight.mappers.AmbienteMapper;
import br.unipar.assetinsight.service.AmbienteService;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

@AllArgsConstructor
@RestController
@RequestMapping("ambiente")
@Tag(name = "Ambientes", description = "Endpoints para manipulação de ambientes.")
public class AmbienteController {
    private final AmbienteService service;


    @Operation(summary = "Retorna todos os ambientes cadastrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = AmbienteResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida.", content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "403", description = "Acesso negado - Permissões insuficientes.", content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas.", content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "O registro não pôde ser encontrado.", content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor.", content = { @Content(mediaType = "application/json") })
    })
    @Parameters({
            @Parameter(name = "page", description = "Número da página.", in = ParameterIn.QUERY, required = false, schema = @Schema(type = "integer", example = "0")),
            @Parameter(name = "size", description = "Quantidade de registros por página.", in = ParameterIn.QUERY, required = false, schema = @Schema(type = "integer", example = "1")),
            @Parameter(name = "sort", description = "Ordenação dos registros e exibição.", in = ParameterIn.QUERY, required = false, schema = @Schema(type = "string", example = "atributo,asc"))
    })
    @GetMapping("/all")
    public ResponseEntity<Page<AmbienteResponse>> getAll(@Parameter(hidden = true) Pageable pageable) {
        Page<AmbienteEntity> retorno = service.getAll(pageable);
        Page<AmbienteResponse> response = AmbienteMapper.INSTANCE.toResponsePage(retorno);


        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Retorna um ambiente específico pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = AmbienteResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida.", content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "403", description = "Acesso negado - Permissões insuficientes.", content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas.", content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "O registro não pôde ser encontrado.", content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor.", content = { @Content(mediaType = "application/json") })
    })
    @GetMapping
    public ResponseEntity<AmbienteResponse> getById(@Valid @RequestParam long id) {
        AmbienteEntity retorno = service.getById(id);
        AmbienteResponse response = AmbienteMapper.INSTANCE.toResponse(retorno);

        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Deleta um ambiente específico pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Ambiente excluído com sucesso.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = AmbienteResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida.", content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "403", description = "Acesso negado - Permissões insuficientes.", content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas.", content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "O registro não pôde ser encontrado.", content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor.", content = { @Content(mediaType = "application/json") })
    })
    @DeleteMapping
    public ResponseEntity<Void> deleteById(@Valid @RequestParam long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "Transfere todos os registros vinculados a um ambiente para outro informado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Requisição aceita e processada.", content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida.", content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "403", description = "Acesso negado - Permissões insuficientes.", content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas.", content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "O registro não pôde ser encontrado.", content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor.", content = { @Content(mediaType = "application/json") })
    })
    @PutMapping("/transferir")
    public ResponseEntity<Void> transferirAmbientes(@Valid @RequestParam long ambienteId, @Valid @RequestParam long ambienteDestinoId) {
        service.transferirAmbientes(ambienteId, ambienteDestinoId);
        return ResponseEntity.accepted().build();
    }


    @Operation(summary = "Cadastra ou atualiza um ambiente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = AmbienteResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida.", content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "403", description = "Acesso negado - Permissões insuficientes.", content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas.", content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "O registro não pôde ser encontrado.", content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor.", content = { @Content(mediaType = "application/json") })
    })
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
