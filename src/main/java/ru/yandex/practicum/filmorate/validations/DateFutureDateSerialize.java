package ru.yandex.practicum.filmorate.validations;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;

import java.io.IOException;
import java.time.LocalDate;

public class DateFutureDateSerialize extends JsonSerializer<LocalDate> {
    @Override
    public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if(LocalDate.parse("1895-12-28").isAfter(value)) {
            throw new ValidationException("Дата не может быть раньше 28.12.1895");
        }
    }
}