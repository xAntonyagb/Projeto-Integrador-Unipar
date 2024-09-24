package br.unipar.assetinsight.controllers;

import br.unipar.assetinsight.dtos.requests.CadastroRequest;
import br.unipar.assetinsight.dtos.requests.LoginRequest;
import br.unipar.assetinsight.dtos.responses.CadastroResponse;
import br.unipar.assetinsight.dtos.responses.LoginResponse;
import br.unipar.assetinsight.entities.UsuarioEntity;
import br.unipar.assetinsight.infra.security.TokenException;
import br.unipar.assetinsight.exceptions.ValidationException;
import br.unipar.assetinsight.mappers.RoleMapper;
import br.unipar.assetinsight.infra.security.TokenService;
import br.unipar.assetinsight.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Token", description = "Operações relacionadas a autentificação para utilização da API.")
@RequestMapping("auth")
@AllArgsConstructor
public class AuthenticationController {
    private AuthenticationManager authenticationManager; //Faz o encode da senha e compara com hash do banco
    private AuthenticationService authenticationService;
    private TokenService tokenService;
    private RoleMapper roleMapper;

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
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) throws TokenException {
        var usuarioSenha = new UsernamePasswordAuthenticationToken(request.username(), request.password());
        var auth = authenticationManager.authenticate(usuarioSenha); // 401 se der ruim

        var usuarioAutenticado = (UsuarioEntity) auth.getPrincipal();

        var token = tokenService.generateToken(usuarioAutenticado); //Gera token a partir do usuario que já foi autenticado
        var tokenDecoded = tokenService.decodeToken(token);

        LoginResponse loginResponse = new LoginResponse(
                token,
                tokenDecoded.getExpiresAt(),
                tokenDecoded.getIssuedAt(),
                roleMapper.toPermissoesEnumList(usuarioAutenticado.getListRoles())
        );


        return ResponseEntity.ok(loginResponse);
    }


    @PostMapping("/cadastrar")
    public ResponseEntity<CadastroResponse> cadastrarUsuario(@RequestBody @Valid CadastroRequest request) throws ValidationException {
        CadastroResponse retorno = authenticationService.cadastrarUsuario(request);
        return ResponseEntity.ok(retorno);
    }

}
