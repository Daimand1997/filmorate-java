package ru.yandex.practicum.filmorate.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class UserServicesImpl implements UserService {

    private static Integer id = 0;
    private final ObjectMapper objectMapper;
    private List<User> users = new ArrayList<>();

    public UserServicesImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public User addUser(User user) {
        if (users.stream().anyMatch(g -> g.equals(user))) throw new ValidationException("The user was created earlier");
        user.setId(++id);
        if (user.getName() == null || user.getName().isEmpty()) {
            user.setName(user.getLogin());
        }
        users.add(user);
        return user;
    }

    @Override
    public User updateUser(User user) throws JsonProcessingException {
        if (users.stream().filter(g -> g.getId()
                .equals(user.getId())).findFirst().isEmpty())
            throw new ValidationException("Not found user from update by "
                    + objectMapper.writeValueAsString(user));
        if (user.getName() == null || user.getName().isEmpty()) user.setName(user.getLogin());
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(user.getId())) {
                users.set(i, user);
                break;
            }
        }
        return user;
    }

    @Override
    public List<User> getUsers() {
        return users != null ? users : Collections.emptyList();
    }
}
