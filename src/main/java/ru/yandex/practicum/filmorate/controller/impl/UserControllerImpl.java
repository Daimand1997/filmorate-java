package ru.yandex.practicum.filmorate.controller.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.controller.UserControllerApi;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.impl.UserServicesImpl;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController("/users")
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@Data
@Validated
public class UserControllerImpl implements UserControllerApi {
    private final UserServicesImpl userServices;
    private final ObjectMapper objectMapper;

    @Autowired
    public UserControllerImpl(UserServicesImpl userServices, ObjectMapper objectMapper) {
        this.userServices = userServices;
        this.objectMapper = objectMapper;
    }

    @Override
    public User addUser(User user) throws JsonProcessingException {
        log.info("Start create user. " + objectMapper.writeValueAsString(user));
        User responseUser = userServices.addUser(user);
        log.info("Finish create user. " + objectMapper.writeValueAsString(user));
        return responseUser;
    }

    @Override
    public User updateUser(@RequestBody @Valid @NotNull User user) throws JsonProcessingException {
        log.info("Start update user. " + objectMapper.writeValueAsString(user));
        User responseUser = userServices.updateUser(user);
        log.info("Finish update user. " + objectMapper.writeValueAsString(user));
        return responseUser;
    }

    @Override
    public List<User> getUsers() throws JsonProcessingException {
        log.info("Start get users.");
        List<User> responseUsers = userServices.getUsers();
        log.info("Finish get users: " + objectMapper.writeValueAsString(responseUsers));
        return responseUsers;
    }
}
