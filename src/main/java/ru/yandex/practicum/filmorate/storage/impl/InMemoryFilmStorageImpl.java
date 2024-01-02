package ru.yandex.practicum.filmorate.storage.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exceptions.ResourceAppException;
import ru.yandex.practicum.filmorate.exceptions.ResourceNotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.FilmStorage;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Component
public class InMemoryFilmStorageImpl implements FilmStorage {

    private static Long id = 1L;
    private final Map<Long, Film> films = new LinkedHashMap<>();

    @Override
    public Film addFilm(Film film) {
        film.setId(++id);
        films.put(id, film);
        return film;
    }

    @Override
    public Film updateFilm(Film film) {
        if (!films.containsKey(film.getId()))
            throw new ResourceAppException("Not found film from update by id " + film.getId());
        films.put(id, film);
        return film;
    }

    @Override
    public Map<Long, Film> getFilms() {
        return films;
    }

    @Override
    public Film getFilmById(Long id) {
        if(!films.containsKey(id)){
           throw new ResourceNotFoundException("Not found film by id " + id);
        }
        return films.get(id);
    }

}
