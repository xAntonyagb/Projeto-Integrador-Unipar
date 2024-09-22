package br.unipar.assetinsight.exceptions;

/**
 * Exception pra quando não encontrar nenhum registro dentro banco de dados.
 */
public class NotFoundException extends Exception{
    public NotFoundException(String message) {
        super(message);
    }
}
