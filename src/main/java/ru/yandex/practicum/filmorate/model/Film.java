package ru.yandex.practicum.filmorate.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import ru.yandex.practicum.filmorate.validations.DateFutureDateSerialize;

import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Film.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Film {
    @Id
    private Integer id;

    @NotBlank(message = "Поле 'name' не может быть пустым")
    private String name;

    @Length(max = 200, message = "Максимальная длина описания — 200 символов")
    private String description;

    @NotNull(message = "Поле 'releaseDate' не может быть пустой")
    @JsonSerialize(converter = DateFutureDateSerialize.class)
    private LocalDate releaseDate;

    @Min(value = 0, message = "Продолжительность фильма должна быть положительной")
    private Integer duration;

}
