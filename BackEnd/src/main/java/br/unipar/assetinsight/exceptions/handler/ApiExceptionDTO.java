package br.unipar.assetinsight.exceptions.handler;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe que vai servir de retorno quando ocorrer uma exessão na aplicação.
 * Essa classe pode retornar tanto uma lista, quano uma mensagem única.
 */
@Getter
@Setter
@Schema(description = "DTO para retorno de exceções.")
public class ApiExceptionDTO {

    @Schema(description = "Lista de mensagens de erro.")
    private Map<String, String> listErros;

    public ApiExceptionDTO(String message) {
        this.listErros = new HashMap<>();
        listErros.put("Erro Geral", message);
    }

    public ApiExceptionDTO(String field, String message) {
        this.listErros = new HashMap<>();
        listErros.put(field, message);
    }

    public ApiExceptionDTO(Map<String, String> listErros) {
        this.listErros = listErros;
    }

}
