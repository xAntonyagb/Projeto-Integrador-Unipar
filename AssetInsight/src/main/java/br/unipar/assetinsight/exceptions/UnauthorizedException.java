package br.unipar.assetinsight.exceptions;

/**
 * Exception para quando o usuário não tem permissão para acessar algo.
 */
public class UnauthorizedException extends Exception{

    public UnauthorizedException(String msg) {
        super(msg);
    }
}
