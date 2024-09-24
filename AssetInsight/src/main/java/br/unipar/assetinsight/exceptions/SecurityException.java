package br.unipar.assetinsight.exceptions;

/**
 * Exception pra quando ocorrerem desvios do fluxo feliz do spring security
 */
public class SecurityException extends Exception{
    public SecurityException(String message) {
        super(message);
    }
}
