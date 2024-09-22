package br.unipar.assetinsight.exceptions;

/**
    * Exception para quando ocorrer algum erro de validação.
 */
public class ValidationException extends Exception{
    public ValidationException(String message) {
        super(message);
    }
}
