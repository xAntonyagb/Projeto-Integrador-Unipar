package br.unipar.assetinsight.exceptions.handler;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

/**
 * Classe que vai servir de retorno quando ocorrer uma exessão na aplicação.
 * Essa classe pode retornar tanto uma lista, quano uma mensagem única.
 */
@Getter
@Setter
public class ApiExceptionDTO {

    private List<String> errorList;

    /* Construtores */

    //Contrutor pra passar só uma mensagem
    public ApiExceptionDTO(String message) {
        errorList = Arrays.asList(message);
    }

    //Construtor pra passar mais de uma mensagem de uma vez
    public ApiExceptionDTO(List<String> errorList) {
        this.errorList = errorList;
    }
}
