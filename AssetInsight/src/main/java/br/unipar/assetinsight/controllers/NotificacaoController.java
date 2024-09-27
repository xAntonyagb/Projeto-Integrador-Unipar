package br.unipar.assetinsight.controllers;

import br.unipar.assetinsight.dtos.responses.AmbienteResponse;
import br.unipar.assetinsight.dtos.responses.NotificacaoResponse;
import br.unipar.assetinsight.entities.NotificacaoEntity;
import br.unipar.assetinsight.exceptions.handler.ApiExceptionDTO;
import br.unipar.assetinsight.mappers.NotificacaoMapper;
import br.unipar.assetinsight.service.NotificacaoService;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("notificacao")
@Tag(name = "Notificações", description = "Endpoints para operações relacionadas a notificações.")
public class NotificacaoController {
    private final NotificacaoService notificacaoService;


    @Operation(summary = "Retorna todas as notificações do usuario autenticado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = NotificacaoResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "403", description = "Acesso negado - Permissões insuficientes.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "O registro não pôde ser encontrado.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) })
    })
    @GetMapping("/all")
    public ResponseEntity<Page<NotificacaoResponse>> getAll(Pageable pageable) {
        Page<NotificacaoEntity> retorno = notificacaoService.getAll(pageable);
        Page<NotificacaoResponse> response = NotificacaoMapper.INSTANCE.toResponsePage(retorno);

        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Retorna uma notificação específica pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = NotificacaoResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "403", description = "Acesso negado - Permissões insuficientes.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "O registro não pôde ser encontrado.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) })
    })
    @GetMapping
    public ResponseEntity<NotificacaoResponse> getById(@Valid @RequestParam long id) {
        NotificacaoEntity retorno = notificacaoService.getById(id);
        NotificacaoResponse response = NotificacaoMapper.INSTANCE.toResponse(retorno);

        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Marca uma notificação como lida para o usuario autenticado. O id da notificação deve ser informado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Notificação marcada como lida.", content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "403", description = "Acesso negado - Permissões insuficientes.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "O registro não pôde ser encontrado.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) })
    })
    @PostMapping("/read")
    public ResponseEntity<Void> read(@Valid @RequestParam long id) {
        notificacaoService.lerNotificao(id);
        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "Marca todas as notificações do usuario autenticado como lida.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Notificações marcadas como lidas.", content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "403", description = "Acesso negado - Permissões insuficientes.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "O registro não pôde ser encontrado.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) })
    })
    @PostMapping("/read/all")
    public ResponseEntity<Void> readAll() {
        notificacaoService.lerTodasNotificacoes();
        return ResponseEntity.noContent().build();
    }

}
