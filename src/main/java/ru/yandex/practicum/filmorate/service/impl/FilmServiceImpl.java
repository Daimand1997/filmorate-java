package ru.yandex.practicum.filmorate.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;
import ru.yandex.practicum.filmorate.storage.impl.InMemoryFilmStorageImpl;

@Service
@Slf4j
public class FilmServiceImpl implements FilmService {

    private final InMemoryFilmStorageImpl inMemoryFilmStorage;

    @Autowired
    public FilmServiceImpl(InMemoryFilmStorageImpl inMemoryFilmStorage) {
        this.inMemoryFilmStorage = inMemoryFilmStorage;
    }


    @Override
    public Film addLike(Film film) {
        return null;
    }

    @Override
    public Film deleteLike(Film film) {
        return null;
    }

    @Override
    public Film topFilmsByLike(Film film) {
        return null;
    }
}
