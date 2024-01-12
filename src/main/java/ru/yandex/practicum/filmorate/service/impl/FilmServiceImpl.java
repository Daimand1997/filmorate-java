package ru.yandex.practicum.filmorate.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.FilmService;
import ru.yandex.practicum.filmorate.storage.impl.InMemoryFilmStorageImpl;
import ru.yandex.practicum.filmorate.storage.impl.InMemoryUserStorageImpl;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FilmServiceImpl implements FilmService {

    private final InMemoryFilmStorageImpl inMemoryFilmStorage;
    private final InMemoryUserStorageImpl inMemoryUserStorage;

    @Value("${app.defaultCountTopLikeFilms}")
    private String defaultCountTopLikeFilms;

    @Autowired
    public FilmServiceImpl(InMemoryFilmStorageImpl inMemoryFilmStorage, InMemoryUserStorageImpl inMemoryUserStorage) {
        this.inMemoryFilmStorage = inMemoryFilmStorage;
        this.inMemoryUserStorage = inMemoryUserStorage;
    }

    @Override
    public void addLikeFromFilm(Long idFilm, Long idUser) {
        Film film = inMemoryFilmStorage.getFilmById(idFilm);
        User user = inMemoryUserStorage.getUserById(idUser);
        if(Objects.nonNull(user.getIdLikeFilms())
                && user.getIdLikeFilms().contains(idFilm)) {
            throw new ValidationException(String.format("The film with id %s has already been liked user with id %s",
                    idFilm, idUser));
        }
        film.setCountLike(film.getCountLike() + 1);
        user.getIdLikeFilms().add(idFilm);
    }

    @Override
    public void deleteLikeFromFilm(Long idFilm, Long idUser) {
        Film film = inMemoryFilmStorage.getFilmById(idFilm);
        User user = inMemoryUserStorage.getUserById(idUser);
        if(!user.getIdLikeFilms().contains(idFilm)) {
            throw new ValidationException(String.format("The film with Id %s was not liked by user with id %s",
                    idFilm, idUser));
        }
        film.setCountLike(film.getCountLike() - 1);
        user.getIdLikeFilms().remove(idFilm);
    }

    @Override
    public List<Film> getTopFilmsByLike(Long countTopFilms) {
        return getTopFilmsByCount(Objects.requireNonNullElse(countTopFilms, Long.parseLong(defaultCountTopLikeFilms)));
    }

    private List<Film> getTopFilmsByCount(Long countTopFilms) {
        return inMemoryFilmStorage.getFilms().values().stream()
                .sorted(Comparator.comparing(Film::getCountLike).reversed())
                .limit(countTopFilms)
                .collect(Collectors.toList());
    }
}
