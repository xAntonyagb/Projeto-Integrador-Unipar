package br.unipar.assetinsight.exceptions;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * Exception pra quando não encontrar nenhum registro dentro banco de dados.
 */
@Getter
public class NotFoundException extends RuntimeException{
    private Map<String, String> listErros;

    public NotFoundException(String message) {
        this.listErros = new HashMap<>();
        listErros.put("Erro Geral", message);
    }

    public NotFoundException(String field, String message) {
        this.listErros = new HashMap<>();
        listErros.put(field, message);
    }

    public NotFoundException(Map<String, String> listErros) {
        this.listErros = listErros;
    }
}
