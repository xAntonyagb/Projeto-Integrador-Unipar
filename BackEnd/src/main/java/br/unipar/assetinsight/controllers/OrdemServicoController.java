package br.unipar.assetinsight.controllers;

import br.unipar.assetinsight.dtos.requests.OrdemServicoRequest;
import br.unipar.assetinsight.dtos.responses.principal.OrdemServicoResponse;
import br.unipar.assetinsight.entities.OrdemServicoEntity;
import br.unipar.assetinsight.enums.StatusOrdemServicoEnum;
import br.unipar.assetinsight.exceptions.handler.ApiExceptionDTO;
import br.unipar.assetinsight.mappers.OrdemServicoMapper;
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
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

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
    @Parameters({
            @Parameter(name = "page", description = "Número da página.", in = ParameterIn.QUERY, required = false, schema = @Schema(type = "integer", defaultValue = "0", example = "0")),
            @Parameter(name = "size", description = "Quantidade de registros por página.", in = ParameterIn.QUERY, required = false, schema = @Schema(type = "integer", defaultValue = "10", example = "1")),
            @Parameter(name = "sort", description = "Ordenação dos registros e exibição.", in = ParameterIn.QUERY, required = false, schema = @Schema(type = "string", example = "property,asc")),
            @Parameter(name = "descricao", description = "Filtro para informar a descrição da ordem de serviço.", in = ParameterIn.QUERY, required = false, schema = @Schema(type = "string", example = "Manutenção do Ar Condicionado")),
            @Parameter(name = "data", description = "Filtro para informar a data da ordem de serviço.", in = ParameterIn.QUERY, required = false, schema = @Schema(type = "string", example = "2021-10-10")),
            @Parameter(name = "servico", description = "Filtro para informar o ID do serviço.", in = ParameterIn.QUERY, required = false, schema = @Schema(type = "integer", example = "1")),
            @Parameter(name = "valorTotal", description = "Filtro para informar o valor total da ordem de serviço.", in = ParameterIn.QUERY, required = false, schema = @Schema(type = "double", example = "100.00")),
            @Parameter(name = "status", description = "Filtro para informar o status da ordem de serviço.", in = ParameterIn.QUERY, required = false, schema = @Schema(type = "string", example = "ABERTA")),
            @Parameter(name = "arquivada", description = "Filtro para informar se a ordem de serviço está arquivada.", in = ParameterIn.QUERY, required = false, schema = @Schema(type = "boolean", example = "false"))
    })
    @GetMapping("/all")
    public ResponseEntity<Page<OrdemServicoResponse>> getAll(
            @RequestParam(required = false) String descricao,
            @RequestParam(required = false) Timestamp data,
            @RequestParam(required = false) Long servico,
            @RequestParam(required = false) Double valorTotal,
            @RequestParam(required = false) StatusOrdemServicoEnum status,
            @RequestParam(required = false, defaultValue = "false") Boolean arquivada,
            @Parameter(hidden = true) Pageable pageable
    ) {
        Map<String, String> filtros = new HashMap<>();
        if (descricao != null ) {
            filtros.put("descricao", descricao);
        }
        if (data != null ) {
            filtros.put("data", data.toString());
        }
        if (servico != null ) {
            filtros.put("servico", servico.toString());
        }
        if (valorTotal != null ) {
            filtros.put("valorTotal", valorTotal.toString());
        }
        if (status != null ) {
            filtros.put("status", status.toString());
        }
        filtros.put("arquivada", arquivada.toString());

        Page<OrdemServicoEntity> retorno = ordemServicoService.getAll(pageable, filtros);
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
