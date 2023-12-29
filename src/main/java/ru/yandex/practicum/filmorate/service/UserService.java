package ru.yandex.practicum.filmorate.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.yandex.practicum.filmorate.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    // Метод добавления пользователя
    User addUser(User user);

    // Метод изменения пользователя
    User updateUser(User user) throws JsonProcessingException;

    // Метод получения всех пользователей
    Map<Integer, User> getUsers();
}
