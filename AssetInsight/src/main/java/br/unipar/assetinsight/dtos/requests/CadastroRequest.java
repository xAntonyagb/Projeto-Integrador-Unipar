package br.unipar.assetinsight.dtos.requests;

import br.unipar.assetinsight.enums.PermissoesEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record CadastroRequest(
        @NotNull @NotEmpty @NotBlank String username,
        @NotNull @NotEmpty @NotBlank String password,
        @NotNull @Size(min = 1) List<PermissoesEnum> permissoes) {
}
