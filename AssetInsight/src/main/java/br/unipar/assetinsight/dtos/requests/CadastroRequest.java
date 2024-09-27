package br.unipar.assetinsight.dtos.requests;

import br.unipar.assetinsight.enums.PermissoesEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record CadastroRequest(
        @Schema(description = "Nome que o usuário utilizara para entrar no sistema.", example = "Usuario", required = true)
        @NotNull @NotEmpty @NotBlank String username,

        @Schema(description = "Senha que o usuário utilizara para entrar no sistema.", example = "123456", required = true)
        @NotNull @NotEmpty @NotBlank String password,

        @Schema(description = "Lista de permissões que o usuário terá no sistema.", example = "[\"ADMIN\", \"USER\"]", required = true)
        @NotNull @Size(min = 1) List<PermissoesEnum> permissoes) {
}
