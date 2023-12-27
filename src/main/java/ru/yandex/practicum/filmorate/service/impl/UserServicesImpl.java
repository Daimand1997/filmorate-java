package ru.yandex.practicum.filmorate.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;

import java.util.List;

@Component
public class UserServicesImpl implements UserService {

    private List<User> users;
    private static Integer id;

    private final ObjectMapper objectMapper;

    public UserServicesImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public User addUser(User user) {
        if(users.contains(user)){
            throw new ValidationException("The user was created earlier");
        }
        user.setId(++id);
        users.add(user);
        return user;
    }

    @Override
    public User updateUser(User user) throws JsonProcessingException {
        if(!users.contains(user)) {
            throw new ValidationException("Not found user from update by "
                    + objectMapper.writeValueAsString(user));
        }
        // TODO ПЕРЕДЕЛАТЬ!!!
        users.add(user.getId(), user);
        return user;
    }

    @Override
    public List<User> getUsers() {
        return users;
    }
}
