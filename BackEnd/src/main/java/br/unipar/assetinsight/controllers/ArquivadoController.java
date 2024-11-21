package br.unipar.assetinsight.controllers;

import br.unipar.assetinsight.dtos.responses.principal.ArquivadoResponse;
import br.unipar.assetinsight.entities.ArquivadoEntity;
import br.unipar.assetinsight.enums.TipoArquivadoEnum;
import br.unipar.assetinsight.exceptions.handler.ApiExceptionDTO;
import br.unipar.assetinsight.mappers.ArquivadosMapper;
import br.unipar.assetinsight.service.ArquivadoService;
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

import java.net.URI;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("arquivados")
@RestController
@AllArgsConstructor
@Tag(name = "Arquivados", description = "Operações relacionadas a registros que foram arquivados.")
public class ArquivadoController {
    private final ArquivadoService arquivadoService;


    @Operation(summary = "Retorna todos os arquivados cadastrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ArquivadoResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "403", description = "Acesso negado - Permissões insuficientes.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "O registro não pôde ser encontrado.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) })
    })
    @Parameters({
            @Parameter(name = "page", description = "Número da página.", in = ParameterIn.QUERY, required = false, schema = @Schema(type = "integer", defaultValue = "0", example = "0")),
            @Parameter(name = "size", description = "Quantidade de registros por página.", in = ParameterIn.QUERY, required = false, schema = @Schema(type = "integer", defaultValue = "10", example = "1")),
            @Parameter(name = "sort", description = "Ordenação dos registros e exibição.", in = ParameterIn.QUERY, required = false, schema = @Schema(type = "string", example = "property,asc")),
            @Parameter(name = "tipo", description = "Filtro para informar o tipo do arquivado.", in = ParameterIn.QUERY, required = false, schema = @Schema(type = "string", example = "TAREFA")),
            @Parameter(name = "dtArquivado", description = "Filtro para informar a data de arquivamento.", in = ParameterIn.QUERY, required = false, schema = @Schema(type = "string", example = "2021-09-01")),
            @Parameter(name = "dtExcluir", description = "Filtro para informar a data de exclusão.", in = ParameterIn.QUERY, required = false, schema = @Schema(type = "string", example = "2021-09-01")),
            @Parameter(name = "arquivadoBy", description = "Filtro para informar o nome do usuário que arquivou.", in = ParameterIn.QUERY, required = false, schema = @Schema(type = "string", example = "UNIPAR")),
            @Parameter(name = "ordemServico", description = "Filtro para informar o ID da ordem de serviço.", in = ParameterIn.QUERY, required = false, schema = @Schema(type = "integer", example = "1")),
            @Parameter(name = "tarefa", description = "Filtro para informar o ID da tarefa.", in = ParameterIn.QUERY, required = false, schema = @Schema(type = "integer", example = "1"))
    })
    @GetMapping("/all")
    public ResponseEntity<Page<ArquivadoResponse>> getAll(
            @RequestParam(required = false) TipoArquivadoEnum tipo,
            @RequestParam(required = false) Timestamp dtArquivado,
            @RequestParam(required = false) Time dtExcluir,
            @RequestParam(required = false) String arquivadoBy,
            @RequestParam(required = false) Long ordemServico,
            @RequestParam(required = false) Long tarefa,
            @Parameter(hidden = true) Pageable pageable
    ) {
        Map<String, String> filtros = new HashMap<>();
        if (tipo != null) {
            filtros.put("tipo", tipo.toString());
        }
        if (dtArquivado != null) {
            filtros.put("dtArquivado", dtArquivado.toString());
        }
        if (dtExcluir != null) {
            filtros.put("dtExcluir", dtExcluir.toString());
        }
        if (arquivadoBy != null) {
            filtros.put("arquivadoBy", arquivadoBy);
        }
        if (ordemServico != null) {
            filtros.put("ordemServico", ordemServico.toString());
        }
        if (tarefa != null) {
            filtros.put("tarefa", tarefa.toString());
        }

        Page<ArquivadoEntity> retorno = arquivadoService.getAll(pageable, filtros);
        Page<ArquivadoResponse> response = ArquivadosMapper.INSTANCE.toResponsePage(retorno);

        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Retorna um arquivada específico pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ArquivadoResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "403", description = "Acesso negado - Permissões insuficientes.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "O registro não pôde ser encontrado.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) })
    })
    @GetMapping
    public ResponseEntity<ArquivadoResponse> getById(@Valid @RequestParam long id) {
        ArquivadoEntity retorno = arquivadoService.getById(id);
        ArquivadoResponse response = ArquivadosMapper.INSTANCE.toResponse(retorno);

        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Restaura um arquivada pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ArquivadoResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "403", description = "Acesso negado - Permissões insuficientes.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "O registro não pôde ser encontrado.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) })
    })
    @PostMapping("/restaurar")
    public ResponseEntity<ArquivadoResponse> restaurar(@Valid @RequestParam long id) {
        ArquivadoEntity arquivado = arquivadoService.restaurarById(id);
        ArquivadoResponse response = ArquivadosMapper.INSTANCE.toResponse(arquivado);

        URI uri;
        if (arquivado.getTipo() == TipoArquivadoEnum.TAREFA) {
            uri = URI.create("/tarefa/" + arquivado.getTarefaEntity().getId());
        }
        else {
            uri = URI.create("/ordem-servico/" + arquivado.getOrdemServicoEntity().getId());
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uri);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }


    @Operation(summary = "Excluí por definitivo de um arquivada pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deletado com sucesso.", content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "403", description = "Acesso negado - Permissões insuficientes.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "O registro não pôde ser encontrado.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) })
    })
    @DeleteMapping
    public ResponseEntity<Void> deleteById(@Valid @RequestParam long id) {
        arquivadoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
