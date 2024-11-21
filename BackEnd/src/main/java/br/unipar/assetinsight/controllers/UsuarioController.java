package br.unipar.assetinsight.controllers;

import br.unipar.assetinsight.dtos.responses.principal.UsuarioResponse;
import br.unipar.assetinsight.entities.UsuarioEntity;
import br.unipar.assetinsight.enums.PermissoesEnum;
import br.unipar.assetinsight.exceptions.handler.ApiExceptionDTO;
import br.unipar.assetinsight.mappers.UsuarioMapper;
import br.unipar.assetinsight.service.UsuarioService;
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

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("usuario")
@Tag(name = "Usuarios", description = "Endpoints para operações relacionadas a usuários. Apenas ")
public class UsuarioController {
    private final UsuarioService service;


    @Operation(summary = "Retorna todos os usuários cadastrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponse.class)) }),
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
            @Parameter(name = "username", description = "Filtro para informar o username do usuário.", in = ParameterIn.QUERY, required = false, schema = @Schema(type = "string", example = "UNIPAR")),
            @Parameter(name = "dtCriacao", description = "Filtro para informar a data de criação do usuário.", in = ParameterIn.QUERY, required = false, schema = @Schema(type = "string", example = "2021-09-01")),
            @Parameter(name = "lastLogin", description = "Filtro para informar a última data de login do usuário.", in = ParameterIn.QUERY, required = false, schema = @Schema(type = "string", example = "2021-09-01")),
            @Parameter(name = "permissao", description = "Filtro para informar a permissão do usuário.", in = ParameterIn.QUERY, required = false, schema = @Schema(type = "string", example = "ADMINISTRADOR"))
    })
    @GetMapping("/all")
    public ResponseEntity<Page<UsuarioResponse>> getAll(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) Timestamp dtCriacao,
            @RequestParam(required = false) Timestamp lastLogin,
            @RequestParam(required = false) PermissoesEnum permissao,
            @Parameter(hidden = true) Pageable pageable
    ) {
        Map<String, String> filtros = new HashMap<>();
        if (username != null ) {
            filtros.put("username", username);
        }
        if (dtCriacao != null ) {
            filtros.put("dtCriacao", dtCriacao.toString());
        }
        if (lastLogin != null ) {
            filtros.put("lastLogin", lastLogin.toString());
        }
        if (permissao != null ) {
            filtros.put("permissao", permissao.toString());
        }

        Page<UsuarioEntity> retorno = service.getAll(pageable, filtros);
        Page<UsuarioResponse> response = UsuarioMapper.INSTANCE.toResponsePage(retorno);

        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Retorna um usuário específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = UsuarioResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "403", description = "Acesso negado - Permissões insuficientes.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "O registro não pôde ser encontrado.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) })
    })
    @GetMapping
    public ResponseEntity<UsuarioResponse> getById(@Valid @RequestParam UUID id) {
        UsuarioEntity retorno = service.getById(id);
        UsuarioResponse response = UsuarioMapper.INSTANCE.toResponse(retorno);

        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Deleta um usuário específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuario excluído com sucesso.", content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "403", description = "Acesso negado - Permissões insuficientes.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "O registro não pôde ser encontrado.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) })
    })
    @DeleteMapping
    public ResponseEntity<Void> deleteById(@Valid @RequestParam UUID id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
