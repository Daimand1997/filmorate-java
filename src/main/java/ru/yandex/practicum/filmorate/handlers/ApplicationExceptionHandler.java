package ru.yandex.practicum.filmorate.handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.yandex.practicum.filmorate.exceptions.ResourceAppException;
import ru.yandex.practicum.filmorate.exceptions.ResourceNotFoundException;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.ResponseApi;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

@Slf4j
@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ResponseApi> handleResourceAppException(ResourceAppException e) {
        String errorMessage = String.format(e.getMessage());
        log.error(errorMessage);
        return new ResponseEntity<>(new ResponseApi(errorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<ResponseApi> handleValidationException(ValidationException e) {
        String errorMessage = String.format(e.getMessage());
        log.error(errorMessage);
        return new ResponseEntity<>(new ResponseApi(errorMessage), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ResponseApi> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, BindingResult bindingResult) {
        String errorMessage = e.getMessage();
        if (bindingResult.getFieldError() != null &&
                bindingResult.getFieldError().getDefaultMessage() != null) {
            errorMessage = String.format(bindingResult.getFieldError().getDefaultMessage());
        }
        log.error(errorMessage);
        return new ResponseEntity<>(new ResponseApi(errorMessage), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ResponseApi> handleConstraintViolationException(ConstraintViolationException e) {
        // Формирую красивый errorMessage
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        StringBuilder errorMessage = new StringBuilder();
        for (ConstraintViolation<?> violation : violations) {
            String interpolatedMessage = violation.getMessage();
            errorMessage.append(interpolatedMessage).append("; ");
        }

        // Удаляю последнюю точку с запятой и пробел из сообщения об ошибке
        if (errorMessage.length() > 0) errorMessage.delete(errorMessage.length() - 2, errorMessage.length());

        log.error(String.valueOf(errorMessage));
        return new ResponseEntity<>(new ResponseApi(String.valueOf(errorMessage)), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ResponseApi> handleResourceNotFoundException(ResourceNotFoundException e) {
        String errorMessage = String.format(e.getMessage());
        log.error(errorMessage);
        return new ResponseEntity<>(new ResponseApi(errorMessage), HttpStatus.NOT_FOUND);
    }
}
