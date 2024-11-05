package br.unipar.assetinsight.exceptions;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
    * Exception para quando ocorrer algum erro de validação.
 */
@Getter
public class ValidationException extends RuntimeException{
    private Map<String, String> listErros;

    public ValidationException(String message) {
        this.listErros = new HashMap<>();
        listErros.put("Erro Geral", message);
    }

    public ValidationException(String field, String message) {
        this.listErros = new HashMap<>();
        listErros.put(field, message);
    }

    public ValidationException(Map<String, String> listErros) {
        this.listErros = listErros;
    }
}
