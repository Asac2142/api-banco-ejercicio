package banco.pichincha.web.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        logger.warn("BAD REQUEST", ex.getMessage());

        var response = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Validacion Fallida",
                "Data de entrada invalido");

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            response.getDetails().put(error.getField(), error.getDefaultMessage());
        }

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex) {
        logger.warn("CONFLICT", ex.getMessage());

        var response = new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                "Violacion de regla de negocio",
                ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<ErrorResponse> handleHandlerMethodValidationException(HandlerMethodValidationException ex) {
        logger.warn("BAD REQUEST: Validation failure - {}", ex.getMessage());

        var response = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Validacion Fallida",
                "Data de entrada invalido");

        ex.getParameterValidationResults().forEach(result -> {
            if (result.getResolvableErrors() != null) {
                result.getResolvableErrors().forEach(error -> {
                    if (error instanceof ConstraintViolation<?> violation) {
                        String field = violation.getPropertyPath().toString();
                        String message = violation.getMessage();
                        response.getDetails().put(field, message);
                    } else {
                        response.getDetails().put("unknown", error.getDefaultMessage());
                    }
                });
            }
        });

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex) {
        logger.info("NOT FOUND", ex.getMessage());
        var response = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "No Encontrado",
                "Dato no encontrado");

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        logger.error("INTERNAL SERVICE ERROR", ex.getMessage());
        var response = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                "An unexpected error occurred");

        ex.printStackTrace();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
        logger.info("Resource not found: {}", ex.getMessage());

        ErrorResponse response = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "Recurso no encontrado",
                ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}