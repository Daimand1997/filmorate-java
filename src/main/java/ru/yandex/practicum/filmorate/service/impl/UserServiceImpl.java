package ru.yandex.practicum.filmorate.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;
import ru.yandex.practicum.filmorate.storage.impl.InMemoryUserStorageImpl;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final InMemoryUserStorageImpl inMemoryUserStorage;

    @Autowired
    public UserServiceImpl(InMemoryUserStorageImpl inMemoryUserStorage) {
        this.inMemoryUserStorage = inMemoryUserStorage;
    }


    @Override
    public User addFriend(User user) {
        return null;
    }

    @Override
    public User deleteFriend(User user) {
        return null;
    }

    @Override
    public User mutualFriends(User user) {
        return null;
    }
}
