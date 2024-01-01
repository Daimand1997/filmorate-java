package ru.yandex.practicum.filmorate.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.Id;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

    @Id
    private Integer id;

    @Email(message = "Поле 'email' не должна быть пустой и должна содержать @")
    @Size(min = 5, max = 128, message = "Некорректный размер email")
    private String email;

    @NotBlank(message = "Поле 'login' не может быть пустым или состоять только из пробелов")
    @Size(min = 5, max = 32, message = "Некорректный размер логина")
    private String login;

    private String name;

    @NotNull(message = "Поле 'birthday' должно быть формата yyyy-mm-dd")
    @Past(message = "Поле 'birthday' не может быть в будущем")
    private LocalDate birthday;

}
