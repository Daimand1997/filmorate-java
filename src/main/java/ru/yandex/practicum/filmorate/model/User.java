package ru.yandex.practicum.filmorate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.Id;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

    @Id
    private Long id;

    @Email(message = "Field 'email' cannot be empty and must be present @")
    @Size(min = 5, max = 128, message = "Size field 'email' must be greater then 4 and less then 128")
    private String email;

    @NotBlank(message = "Field 'login' cannot be empty")
    @Size(min = 5, max = 32, message = "Size field 'login' must be greater then 4 and less then 32")
    private String login;

    private String name;

    @NotNull(message = "Field 'birthday' must be format yyyy-mm-dd")
    @Past(message = "Field 'birthday' must be in past")
    private LocalDate birthday;

    @JsonIgnore
    private List<Long> idFriends;

    @JsonIgnore
    private List<Long> idLikeFilms;
}
