package ru.yandex.practicum.filmorate.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import ru.yandex.practicum.filmorate.validations.DateFutureDateSerialize;

import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * Film.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Film {
    @Id
    private Long id;

    @NotBlank(message = "Field 'name' cannot be empty.")
    @Size(min = 1, max = 2048, message = "Size field 'name' must be greater then 0 and less then 2048")
    private String name;

    @Size(min = 1, max = 200, message = "Size field 'description' must be greater then 0 and less then 200")
    private String description;

    @NotNull(message = "Field 'releaseDate' cannot be empty.")
    @JsonSerialize(converter = DateFutureDateSerialize.class)
    private LocalDate releaseDate;

    @Min(value = 0, message = "Field 'duration' must be greater 0")
    private Long duration;

    private long countLike;
}
