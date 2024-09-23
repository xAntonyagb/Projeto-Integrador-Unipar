package br.unipar.assetinsight.exceptions.handler;

import br.unipar.assetinsight.exceptions.NotFoundException;
import br.unipar.assetinsight.exceptions.UnauthorizedException;
import br.unipar.assetinsight.exceptions.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionHandlerController {

    //Exceção de Validação
    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiExceptionDTO handleIllegalArgumentException(ValidationException e) {
        return new ApiExceptionDTO(e.getMessage());
    }

    //Exeção de No Data Found
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiExceptionDTO handleApiException(NotFoundException e) {
        return new ApiExceptionDTO(e.getMessage());
    }

    //Exeção de unauthorized
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiExceptionDTO handleApiException(UnauthorizedException e) {
        return new ApiExceptionDTO(e.getMessage());
    }

    //Validation Bean
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiExceptionDTO handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {

        List<String> errors = new ArrayList<>();

        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            errors.add(fieldError.getField() + ": " +
                    fieldError.getDefaultMessage());
        }

        return new ApiExceptionDTO(errors);
    }

    //Outras exceções
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiExceptionDTO handleException(Exception e) {
        return new ApiExceptionDTO(e.getMessage());
    }
}

