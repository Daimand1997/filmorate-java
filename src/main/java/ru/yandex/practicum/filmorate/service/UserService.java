package ru.yandex.practicum.filmorate.service;

import ru.yandex.practicum.filmorate.model.User;

import java.util.List;

public interface UserService {

    void addFriendById(Long idUser, Long idFriend);

    void deleteFriend(Long idUser, Long idFriend);

    List<User> getFriendsFromUserById(Long idUser);

    List<User> getCommonsFriendsByIdUser(Long idUser, Long idOtherUser);
}
