package ru.yandex.practicum.filmorate.validations;

import com.fasterxml.jackson.databind.util.StdConverter;
import ru.yandex.practicum.filmorate.exceptions.ResourceAppException;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFutureDateSerialize extends StdConverter<Object, String> {

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public String convert(Object value) {
        if (value instanceof LocalDate) {
            if (LocalDate.parse("1895-12-28").isAfter((LocalDate) value)) {
                throw new ResourceAppException("Дата не может быть раньше 28.12.1895");
            }
            return dateTimeFormatter.format((LocalDate) value);
        }
        throw new ValidationException("Дата должна быть формата yyyy-mm-dd");
    }
}