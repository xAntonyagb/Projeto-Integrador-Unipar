package br.unipar.assetinsight.controllers;

import br.unipar.assetinsight.dtos.requests.CadastroRequest;
import br.unipar.assetinsight.dtos.requests.LoginRequest;
import br.unipar.assetinsight.dtos.responses.principal.CadastroResponse;
import br.unipar.assetinsight.dtos.responses.principal.LoginResponse;
import br.unipar.assetinsight.entities.UsuarioEntity;
import br.unipar.assetinsight.exceptions.handler.ApiExceptionDTO;
import br.unipar.assetinsight.mappers.AuthenticationMapper;
import br.unipar.assetinsight.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@Tag(name = "Login", description = "Operações relacionadas a autentificação para utilização da API.")
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @Operation(summary = "Utilizado para receber o Token de acesso.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) })
    })
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        LoginResponse response = authenticationService.doLogin(request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Utilizado para receber o Token de acesso através de um RefreshToken.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Refresh Token inválido.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) })
    })
    @PostMapping("/refresh")
    public ResponseEntity<LoginResponse> refresh(@Valid @RequestHeader("Authorization") String refreshToken) {
        LoginResponse response = authenticationService.doLogin(refreshToken);
        return ResponseEntity.ok(response);
    }


    @Operation(summary = "Utilizado para cadastrar um novo usuário. Necessário permissões de administrador para realizar a operação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CadastroResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Requisição inválida.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "403", description = "Acesso negado - Permissões insuficientes.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "O registro não pôde ser encontrado.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor.", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ApiExceptionDTO.class)) })
    })
    @PostMapping("/cadastrar")
    public ResponseEntity<CadastroResponse> cadastrarUsuario(@RequestBody @Valid CadastroRequest request) {
        UsuarioEntity retorno = authenticationService.cadastrarUsuario(request);
        CadastroResponse response = AuthenticationMapper.INSTANCE.toCadastroResponse(retorno);

        URI uri = URI.create("/usuario/" + retorno.getId());

        return ResponseEntity.created(uri).body(response);
    }

}
