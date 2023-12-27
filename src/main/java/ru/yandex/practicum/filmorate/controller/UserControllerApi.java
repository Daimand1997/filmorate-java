package ru.yandex.practicum.filmorate.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import java.util.List;

public interface UserControllerApi {

    User addUser(@RequestBody @Valid User user) throws JsonProcessingException;

    User updateUser(@RequestBody @Valid User user) throws JsonProcessingException;

    List<User> getUsers() throws JsonProcessingException;
}
