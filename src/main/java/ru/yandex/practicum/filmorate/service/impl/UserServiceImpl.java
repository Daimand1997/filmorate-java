package ru.yandex.practicum.filmorate.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exceptions.ResourceNotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;
import ru.yandex.practicum.filmorate.storage.impl.InMemoryUserStorageImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final InMemoryUserStorageImpl inMemoryUserStorage;

    @Autowired
    public UserServiceImpl(InMemoryUserStorageImpl inMemoryUserStorage) {
        this.inMemoryUserStorage = inMemoryUserStorage;
    }

    @Override
    public void addFriendById(Long idUser, Long idFriend)
    {
        User user = inMemoryUserStorage.getUserById(idUser);
        User friend = inMemoryUserStorage.getUserById(idFriend);
        user.getFriends().add(friend.getId());
    }

    @Override
    public void deleteFriend(Long idUser, Long idFriend) {
        User user = inMemoryUserStorage.getUserById(idUser);
        if(!user.getFriends().contains(idFriend)) {
            throw new ResourceNotFoundException("Not found friend with id " +
                    idFriend + " from user with id " + idUser);
        }
        user.getFriends().remove(idFriend);
    }

    @Override
    public List<User> friendsFromUserById(Long idUser) {
        User user = inMemoryUserStorage.getUserById(idUser);
        Set<Long> idFriends = user.getFriends();
        return new ArrayList<>(inMemoryUserStorage.getUsersById(idFriends));
    }

    @Override
    public List<User> commonsFriendsByIdUser(Long idUser, Long idOtherUser) {
        User user = inMemoryUserStorage.getUserById(idUser);
        User otherUser = inMemoryUserStorage.getUserById(idOtherUser);

        Set<Long> commonsFriends = user.getFriends().stream()
                .filter(otherUser.getFriends()::contains)
                .collect(Collectors.toSet());

        return new ArrayList<>(inMemoryUserStorage.getUsersById(commonsFriends));
    }
}
