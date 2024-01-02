package ru.yandex.practicum.filmorate.storage.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exceptions.ResourceAppException;
import ru.yandex.practicum.filmorate.exceptions.ResourceNotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Component
public class InMemoryUserStorageImpl implements UserStorage {

    private static Long id = 1L;
    private final Map<Long, User> users = new LinkedHashMap<>();

    @Autowired
    public InMemoryUserStorageImpl() {

    }

    @Override
    public User addUser(User user) {
        user.setId(++id);
        if (user.getName() == null || user.getName().isEmpty())
            user.setName(user.getLogin());
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public User updateUser(User user) {
        if (!users.containsKey(user.getId()))
            throw new ResourceAppException("Not found user from update by id " + user.getId());
        if (user.getName() == null || user.getName().isEmpty()) user.setName(user.getLogin());
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public Map<Long, User> getUsers() {
        return users;
    }

    @Override
    public User getUserById(Long id) {
        if(!users.containsKey(id)) {
            throw new ResourceNotFoundException("Not found user by id " + id);
        }
        return users.get(id);
    }
}