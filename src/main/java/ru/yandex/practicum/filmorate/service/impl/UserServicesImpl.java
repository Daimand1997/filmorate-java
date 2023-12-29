package ru.yandex.practicum.filmorate.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exceptions.ResourceAppException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
@Slf4j
public class UserServicesImpl implements UserService {

    private static Integer id = 0;
    private final ObjectMapper objectMapper;
    private final Map<Integer, User> users = new LinkedHashMap<>();

    public UserServicesImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public User addUser(User user) {
        user.setId(++id);
        if (user.getName() == null || user.getName().isEmpty()) {
            user.setName(user.getLogin());
        }
        users.put(id, user);
        return user;
    }

    @Override
    public User updateUser(User user) {
        if (!users.containsKey(user.getId()))
            throw new ResourceAppException("Not found user from update by id: "
                    + user.getId());
        if (user.getName() == null || user.getName().isEmpty()) user.setName(user.getLogin());
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public Map<Integer, User> getUsers() {
        return users;
    }
}
