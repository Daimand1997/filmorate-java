package ru.yandex.practicum.filmorate.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exceptions.ResourceNotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;
import ru.yandex.practicum.filmorate.storage.impl.InMemoryUserStorageImpl;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final InMemoryUserStorageImpl inMemoryUserStorage;

    @Override
    public void addFriendById(Long idUser, Long idFriend) {
        User user = inMemoryUserStorage.getUserById(idUser);
        User friend = inMemoryUserStorage.getUserById(idFriend);
        // Добавляю user друга
        user.getIdFriends().add(friend.getId());
        // Добавляю другу user
        friend.getIdFriends().add(user.getId());
    }

    @Override
    public void deleteFriend(Long idUser, Long idFriend) {
        User user = inMemoryUserStorage.getUserById(idUser);
        if (!user.getIdFriends().contains(idFriend))
            throw new ResourceNotFoundException("Not found friend with id " +
                    idFriend + " from user with id " + idUser);
        user.getIdFriends().remove(idFriend);
    }

    @Override
    public List<User> getFriendsFromUserById(Long idUser) {
        User user = inMemoryUserStorage.getUserById(idUser);
        List<Long> idFriends = user.getIdFriends();
        return inMemoryUserStorage.getUsersById(idFriends);
    }

    @Override
    public List<User> getCommonsFriendsByIdUser(Long idUser, Long idOtherUser) {
        User user = inMemoryUserStorage.getUserById(idUser);
        User otherUser = inMemoryUserStorage.getUserById(idOtherUser);

        if (Objects.nonNull(user.getIdFriends()) && Objects.nonNull(otherUser.getIdFriends())) {
            List<Long> commonsFriends = user.getIdFriends().stream()
                    .filter(otherUser.getIdFriends()::contains)
                    .collect(Collectors.toList());
            return inMemoryUserStorage.getUsersById(commonsFriends);
        }
        return Collections.emptyList();
    }
}
