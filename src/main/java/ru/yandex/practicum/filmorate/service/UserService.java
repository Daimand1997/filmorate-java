package ru.yandex.practicum.filmorate.service;

import org.springframework.http.ResponseEntity;
import ru.yandex.practicum.filmorate.model.User;

public interface UserService {
    ResponseEntity<?> addUser(User user);

    ResponseEntity<?> updateUser(User user);

    ResponseEntity<?> getUsers(String id);
}
