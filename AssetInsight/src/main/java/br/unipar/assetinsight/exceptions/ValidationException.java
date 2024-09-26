package br.unipar.assetinsight.exceptions;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
    * Exception para quando ocorrer algum erro de validação.
 */
@Getter
public class ValidationException extends RuntimeException{
    private final List<String> errorList;

    public ValidationException(String message) {
        super(message);
        this.errorList = new ArrayList<>();
        this.errorList.add(message);
    }

    public ValidationException(List<String> errorList) {
        super(errorList.toString());
        this.errorList = errorList;
    }
}
