package ru.yandex.practicum.filmorate.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

    private Integer id;

    @Email(message = "Поле 'email' не должна быть пустой и должна содержать @")
    private String email;

    @NotNull(message = "Поле 'login' не может быть пустым или состоять только из пробелов")
    private String login;

    private String name;

    @NotNull(message = "Поле 'birthday' должно быть формата yyyy-mm-dd")
    @Past(message = "Поле 'birthday' не может быть в будущем")
    private LocalDate birthday;

}
