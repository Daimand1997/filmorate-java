package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Objects;

@Data
@Validated
public class User {

    @Id
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && email.equals(user.email) && login.equals(user.login) && Objects.equals(name, user.name) && birthday.equals(user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, login, name, birthday);
    }
}
