package ru.yandex.practicum.filmorate.service;

import ru.yandex.practicum.filmorate.model.User;

import java.util.Map;

public interface UserService {

    // Метод добавления пользователя
    User addUser(User user);

    // Метод изменения пользователя
    User updateUser(User user);

    // Метод получения всех пользователей
    Map<Integer, User> getUsers();
}
