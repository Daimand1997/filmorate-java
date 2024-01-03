package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.User;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface UserStorage {

    // Метод добавления пользователя
    User addUser(User user);

    // Метод изменения пользователя
    User updateUser(User user);

    // Метод получения всех пользователей
    Map<Long, User> getUsers();

    // Метод получения пользователя по id
    User getUserById(Long id);

    // Метод получения списка пользователей по их Id
    Set<User> getUsersById(Set<Long> idFriends);
}
