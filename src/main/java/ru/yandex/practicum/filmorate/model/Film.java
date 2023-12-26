package ru.yandex.practicum.filmorate.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import ru.yandex.practicum.filmorate.validations.DateFutureDateSerialize;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.Duration;
import java.time.LocalDate;
/**
 * Film.
 */
@Entity
@Data
@Validated
public class Film {

    @Id
    private Integer id;

    @NotBlank(message = "Название фильма не может быть пустым")
    private String name;

    @Length(max = 200, message = "Максимальная длина описания — 200 символов")
    private String description;

    @NotBlank(message = "Релизная дата формата yyyy-mm-dd не может быть пустой")
    @JsonSerialize(using = DateFutureDateSerialize.class)
    private LocalDate releaseDate;

    @Min(value = 0, message = "Продолжительность фильма должна быть положительной")
    private Duration duration;

}
