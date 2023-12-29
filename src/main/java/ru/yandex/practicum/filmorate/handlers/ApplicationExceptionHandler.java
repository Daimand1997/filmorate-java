package ru.yandex.practicum.filmorate.handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.yandex.practicum.filmorate.exceptions.ResourceAppException;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.ResponseApi;

@Slf4j
@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(ResourceAppException.class)
    public ResponseEntity<ResponseApi> handleException(Exception e) {
        String errorMessage = String.format(e.getMessage());
        log.error(errorMessage);
        return new ResponseEntity<>(new ResponseApi(errorMessage), HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler
    public ResponseEntity<ResponseApi> handleValidationException(ValidationException e) {
        String errorMessage = String.format(e.getMessage());
        log.error(errorMessage);
        return new ResponseEntity<>(new ResponseApi(errorMessage), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ResponseApi> handleVMethodArgumentNotValidException(MethodArgumentNotValidException e, BindingResult bindingResult) {
        String errorMessage = e.getMessage();
        if (bindingResult.getFieldError() != null &&
                bindingResult.getFieldError().getDefaultMessage() != null) {
            errorMessage = String.format(bindingResult.getFieldError().getDefaultMessage());
        }
        log.error(errorMessage);
        return new ResponseEntity<>(new ResponseApi(errorMessage), HttpStatus.BAD_REQUEST);
    }
}
