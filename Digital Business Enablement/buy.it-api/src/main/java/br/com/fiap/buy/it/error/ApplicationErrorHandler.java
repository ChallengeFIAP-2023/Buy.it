package br.com.fiap.buy.it.error;

import br.com.fiap.buy.it.validation.RestValidationError;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@ControllerAdvice
public class ApplicationErrorHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<RestValidationError> validationHandler(MethodArgumentNotValidException e){
        return e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> new RestValidationError(err.getField(), err.getDefaultMessage()))
                .collect(Collectors.toList());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public RestValidationError handleDataIntegrityViolation(DataIntegrityViolationException e) {
        return new RestValidationError("database", "Operação não permitida: violação de restrição de dados.");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestValidationError handleGenericException(Exception e) {
        return new RestValidationError("error", "Ocorreu um erro durante o processamento da solicitação.");
    }
}