package br.unipar.assetinsight.controllers;

import br.unipar.assetinsight.dtos.requests.TarefaRequest;
import br.unipar.assetinsight.dtos.responses.main.TarefaResponse;
import br.unipar.assetinsight.entities.TarefaEntity;
import br.unipar.assetinsight.exceptions.handler.ApiExceptionDTO;
import br.unipar.assetinsight.mappers.TarefaMapper;
import br.unipar.assetinsight.service.ArquivadoService;
import br.unipar.assetinsight.service.interfaces.IService;
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

@RequestMapping("tarefa")
@AllArgsConstructor
@RestController
@Tag(name = "Tarefas", description = "Endpoints para operações relacionadas a tarefas.")
public class TarefaController {
    private final IService<TarefaEntity> tarefaService;
    private final ArquivadoService arquivadoService;


    @Operation(summary = "Retorna todas as tarefas cadastradas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = TarefaResponse.class)) }),
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
    public ResponseEntity<Page<TarefaResponse>> getAll(@Parameter(hidden = true) Pageable pageable) {
        Page<TarefaEntity> retorno = tarefaService.getAll(pageable);
        Page<TarefaResponse> response = TarefaMapper.INSTANCE.toResponsePage(retorno);

        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Retorna uma tarefa específica.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = TarefaResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "403", description = "Acesso negado - Permissões insuficientes.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "O registro não pôde ser encontrado.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) })
    })
    @GetMapping
    public ResponseEntity<TarefaResponse> getById(@Valid @RequestParam long id) {
        TarefaEntity retorno = tarefaService.getById(id);
        TarefaResponse response = TarefaMapper.INSTANCE.toResponse(retorno);

        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Cadastra ou salva uma tarefa.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = TarefaResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "403", description = "Acesso negado - Permissões insuficientes.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "O registro não pôde ser encontrado.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) })
    })
    @PostMapping
    public ResponseEntity<TarefaResponse> save(@Valid @RequestBody TarefaRequest tarefa) {
        TarefaEntity retorno = tarefaService.save(TarefaMapper.INSTANCE.toEntity(tarefa));
        TarefaResponse response = TarefaMapper.INSTANCE.toResponse(retorno);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(retorno.getId()).toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uri);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }


    @Operation(summary = "Arquiva uma tarefa.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Tarefa arquivada com sucesso.", content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "403", description = "Acesso negado - Permissões insuficientes.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "O registro não pôde ser encontrado.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) })
    })
    @PostMapping("/arquivar")
    public ResponseEntity<Void> arquivar(@Valid @RequestParam long id) {
        arquivadoService.arquivarTarefa(id);
        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "Restaura uma tarefa.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Tarefa restaurada com sucesso.", content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "403", description = "Acesso negado - Permissões insuficientes.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "O registro não pôde ser encontrado.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) })
    })
    @PostMapping("/restaurar")
    public ResponseEntity<Void> restaurar(@Valid @RequestParam long id) {
        arquivadoService.restaurarTarefa(id);
        return ResponseEntity.noContent().build();
    }

}
