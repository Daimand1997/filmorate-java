package ru.yandex.practicum.filmorate.handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.ResponseApi;

@Slf4j
@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ResponseApi handleException(Exception e) {
        String errorMessage = String.format("Ошибка работы приложения: %s", e.getMessage());
        log.error(errorMessage);
        return new ResponseApi(errorMessage);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseApi handleValidationException(ValidationException e) {
        String errorMessage = String.format("Ошибка валидации: %s", e.getMessage());
        log.error(errorMessage);
        return new ResponseApi(errorMessage);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseApi handleVMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String errorMessage = String.format("Ошибка валидации на уровне Spring: %s", e);
        log.error(errorMessage);
        return new ResponseApi(errorMessage);
    }
}
