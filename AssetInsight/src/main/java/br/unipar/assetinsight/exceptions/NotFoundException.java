package br.unipar.assetinsight.exceptions;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Exception pra quando não encontrar nenhum registro dentro banco de dados.
 */
@Getter
public class NotFoundException extends RuntimeException{
    private final List<String> errorList;

    public NotFoundException(String message) {
        super(message);
        this.errorList = new ArrayList<>();
        this.errorList.add(message);
    }

    public NotFoundException(List<String> errorList) {
        super(errorList.toString());
        this.errorList = errorList;
    }
}
