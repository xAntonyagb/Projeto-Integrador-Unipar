package br.unipar.assetinsight.controllers;

import br.unipar.assetinsight.dtos.requests.CadastroRequest;
import br.unipar.assetinsight.dtos.requests.LoginRequest;
import br.unipar.assetinsight.dtos.responses.CadastroResponse;
import br.unipar.assetinsight.dtos.responses.LoginResponse;
import br.unipar.assetinsight.entities.UsuarioEntity;
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
@Tag(name = "Token", description = "Operações relacionadas a autentificação para utilização da API.")
@RequestMapping("auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok", content =
                    { @Content(mediaType = "application/json", schema =
                    @Schema(implementation = LoginResponse.class)) }),
            @ApiResponse(responseCode = "401", description = "Usuário não autorizado / Credenciais inválidas.",
                    content = { @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor.",
                    content = { @Content(mediaType = "application/json") })
    })
    @Operation(summary = "Gera um token de autenticação a partir dos dados de login.")

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        LoginResponse response = authenticationService.doLogin(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<CadastroResponse> cadastrarUsuario(@RequestBody @Valid CadastroRequest request) {
        UsuarioEntity retorno = authenticationService.cadastrarUsuario(request);
        CadastroResponse response = AuthenticationMapper.INSTANCE.toCadastroResponse(retorno);

        URI uri = URI.create("/usuario/" + retorno.getId());

        return ResponseEntity.created(uri).body(response);
    }

}
