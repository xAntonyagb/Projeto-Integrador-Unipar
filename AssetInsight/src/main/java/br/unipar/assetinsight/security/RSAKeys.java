package br.unipar.assetinsight.security;

import lombok.*;
import org.springframework.stereotype.Component;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;
import java.util.logging.Logger;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Component
public class RSAKeys {
    private static final Logger LOGGER = Logger.getLogger(RSAKeys.class.getName());

    private RSAPublicKey publicKey;
    private RSAPrivateKey privateKey;

    public RSAPublicKey getPublicKey() {
        if (publicKey == null) {
            gerarChavesRSA();
            return publicKey;
        }
        return publicKey;
    }

    public RSAPrivateKey getPrivateKey() {
        if (privateKey == null) {
            gerarChavesRSA();
            return privateKey;
        }
        return privateKey;
    }

    private void gerarChavesRSA() {
        try {
            LOGGER.info("Gerando chaves RSA...");
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048);
            KeyPair pair = keyGen.generateKeyPair();

            this.publicKey = (RSAPublicKey) pair.getPublic();
            this.privateKey = (RSAPrivateKey) pair.getPrivate();
            LOGGER.info("Chaves geradas com sucesso!");
        }
        catch (NoSuchAlgorithmException ex) {
            LOGGER.severe("Erro ao gerar chaves RSA. Algoritmo não encontrado.");
            LOGGER.severe(Arrays.toString(ex.getStackTrace()));
            System.exit(1);
        }
    }

}
