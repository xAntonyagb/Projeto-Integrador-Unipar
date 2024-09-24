package br.unipar.assetinsight.infra.security;

/**
 * Exception para quando peculiaridades ocorrerem durante o processo de gerar um token.
 */
public class TokenException extends RuntimeException{

    public TokenException(String msg) {
        super(msg);
    }
}
