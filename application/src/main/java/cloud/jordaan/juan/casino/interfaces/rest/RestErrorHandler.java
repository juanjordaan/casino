package cloud.jordaan.juan.casino.interfaces.rest;

import cloud.jordaan.juan.casino.error.ClientApplicationException;
import cloud.jordaan.juan.casino.error.ErrorDto;
import cloud.jordaan.juan.casino.error.InternalApplicationException;
import cloud.jordaan.juan.casino.error.TeapotApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestErrorHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    @ExceptionHandler(TeapotApplicationException.class)
    public ResponseEntity<ErrorDto> handleTeapotApplicationException(TeapotApplicationException e) {
        return new ResponseEntity<>(ErrorDto.INSTANCE(e.getMessage()), HttpStatus.I_AM_A_TEAPOT);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ClientApplicationException.class)
    public ResponseEntity<ErrorDto> handleClientApplicationException(ClientApplicationException e) {
        return new ResponseEntity<>(ErrorDto.INSTANCE(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InternalApplicationException.class)
    public ResponseEntity<ErrorDto> handleInternalApplicationException(InternalApplicationException e) {
        return new ResponseEntity<>(ErrorDto.INSTANCE(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}