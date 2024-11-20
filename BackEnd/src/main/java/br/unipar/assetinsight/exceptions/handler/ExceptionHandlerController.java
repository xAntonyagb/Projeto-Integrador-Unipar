package br.unipar.assetinsight.exceptions.handler;

import br.unipar.assetinsight.exceptions.NotFoundException;
import br.unipar.assetinsight.exceptions.SecurityException;
import br.unipar.assetinsight.exceptions.ValidationException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlerController {

    //Exceção de Validação
    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiExceptionDTO handleIllegalArgumentException(ValidationException e) {
        return new ApiExceptionDTO(e.getListErros());
    }

    //Exeção de No Data Found
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiExceptionDTO handleApiException(NotFoundException e) {
        return new ApiExceptionDTO(e.getListErros());
    }

    //Exeção de unauthorized
    @ExceptionHandler(SecurityException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiExceptionDTO handleApiException(SecurityException e) {
        return new ApiExceptionDTO("Credenciais", e.getMessage());
    }

    //Exeção de credenciais erradas
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiExceptionDTO handleApiException(BadCredentialsException e) {
        return new ApiExceptionDTO("Credenciais", e.getMessage());
    }

    //Exeção de NoResourceFound
    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiExceptionDTO handleApiException(NoResourceFoundException e) {
        return new ApiExceptionDTO("End Point não existente", e.getMessage());
    }

    //Exeção de MissingRequestHeader
    @ExceptionHandler(MissingRequestHeaderException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiExceptionDTO handleApiException(MissingRequestHeaderException e) {
        return new ApiExceptionDTO("Header da requisição é inválido", e.getMessage());
    }

    //Exeção de PropertyReference
    @ExceptionHandler(PropertyReferenceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiExceptionDTO handleApiException(PropertyReferenceException e) {
        return new ApiExceptionDTO(e.getMessage());
    }

    //Validation Bean
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiExceptionDTO handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        Map<String, String> listErros = new HashMap<>();

        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            listErros.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return new ApiExceptionDTO(listErros);
    }

    //Excetion de erro de cast
    @ExceptionHandler(ClassCastException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiExceptionDTO handleApiException(ClassCastException e) {
        return new ApiExceptionDTO("Erro ao processar requisição", e.getMessage());
    }

    //Outras exceções
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiExceptionDTO handleException(Exception e) {
        return new ApiExceptionDTO("Erro interno", e.getMessage());
    }
}

