package br.unipar.assetinsight.enums;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "Permissões de usuários")
public enum PermissoesEnum {
    @Schema(description = "Permissão para todos os acessos do sistema.")
    SUPER(1, "Super"),

    @Schema(description = "Permissão para administrar o sistema e cadastrar usuarios novos.")
    ADMINISTRADOR(2, "Administrador"),

    @Schema(description = "Permissão para operar o sistema (sem cadastro de novos usuarios).")
    OPERADOR(3, "Operador"),;

    private final long id;
    private final String descricao;

}
