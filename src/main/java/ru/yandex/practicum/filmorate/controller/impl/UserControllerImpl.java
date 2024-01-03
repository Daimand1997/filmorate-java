package ru.yandex.practicum.filmorate.controller.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.controller.UserControllerApi;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.impl.UserServiceImpl;
import ru.yandex.practicum.filmorate.storage.impl.InMemoryUserStorageImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController("/users")
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@Data
@Validated
public class UserControllerImpl implements UserControllerApi {
    private final InMemoryUserStorageImpl userStorage;
    private final UserServiceImpl userService;
    private final ObjectMapper objectMapper;

    @Autowired
    public UserControllerImpl(InMemoryUserStorageImpl userStorage, UserServiceImpl userService, ObjectMapper objectMapper) {
        this.userStorage = userStorage;
        this.userService = userService;
        this.objectMapper = objectMapper;
    }

    @Override
    public User addUser(User user) throws JsonProcessingException {
        log.info("Start create user. " + objectMapper.writeValueAsString(user));
        User responseUser = userStorage.addUser(user);
        log.info("Finish create user. " + objectMapper.writeValueAsString(user));
        return responseUser;
    }

    @Override
    public User updateUser(User user) throws JsonProcessingException {
        log.info("Start update user. " + objectMapper.writeValueAsString(user));
        User responseUser = userStorage.updateUser(user);
        log.info("Finish update user. " + objectMapper.writeValueAsString(user));
        return responseUser;
    }

    @Override
    public List<User> getUsers() throws JsonProcessingException {
        log.info("Start get users.");
        Map<Long, User> responseUsers = userStorage.getUsers();
        log.info("Finish get users: " + objectMapper.writeValueAsString(responseUsers));
        return new ArrayList<>(responseUsers.values());
    }

    @Override
    public User getUserById(Long id) throws JsonProcessingException {
        log.info("Start get user by id {}", id);
        User responseUser = userStorage.getUserById(id);
        log.info("Finish get user by id {}: {} ", id, objectMapper.writeValueAsString(responseUser));
        return responseUser;
    }

    @Override
    public void addFriendById(Long idUser, Long idFriend) {
        log.info("Start add friend with id {} from user with id {}", idFriend, idUser);
        userService.addFriendById(idUser, idFriend);
        log.info("Successful add friend with id {} from user with id {}", idFriend, idUser);
    }
}
