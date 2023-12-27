package ru.yandex.practicum.filmorate.service.impl;

import org.springframework.http.ResponseEntity;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;

import java.util.List;

public class UserServicesImpl implements UserService {

    List<User> users;

    @Override
    public ResponseEntity<?> addUser(User user) {
        return null;
    }

    @Override
    public ResponseEntity<?> updateUser(User user) {
        return null;
    }

    @Override
    public ResponseEntity<?> getUsers(String id) {
        return null;
    }
}
