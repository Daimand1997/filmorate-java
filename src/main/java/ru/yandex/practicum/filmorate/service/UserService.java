package ru.yandex.practicum.filmorate.service;

import ru.yandex.practicum.filmorate.model.User;

import java.util.List;

public interface UserService {

    void addFriendById(Long idUser, Long idFriend);

    void deleteFriend(Long idUser, Long idFriend);

    List<User> friendsFromUserById(Long idUser);

    List<User> commonsFriendsByIdUser(Long idUser, Long idOtherUser);
}
