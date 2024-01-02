package ru.yandex.practicum.filmorate.service;

import ru.yandex.practicum.filmorate.model.User;

public interface UserService {

    User addFriend(User user);

    User deleteFriend(User user);

    User mutualFriends(User user);
}
