package ru.yandex.practicum.filmorate.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

    private Integer id;

    @Email(message = "Электронная почта не должна быть пустой и должна содержать @")
    @Valid
    @NotNull
    @NotEmpty
    private String email;

    @NotBlank(message = "Логин не может быть пустым или состоять только из пробелов")
    private String login;

    private String name;

    @NotNull(message = "Некорректная дата рождения формата yyyy-mm-dd")
    @Past(message = "Дата рождения не может быть в будущем")
    private LocalDate birthday;

}
