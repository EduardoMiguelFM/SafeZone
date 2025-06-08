package br.com.fiap.SafeZone.exception;

import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PropertyReferenceException.class)
    public ResponseEntity<String> handleInvalidSortField(PropertyReferenceException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Campo de ordenação inválido: " + ex.getPropertyName());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erro interno: " + ex.getMessage());
    }
}
