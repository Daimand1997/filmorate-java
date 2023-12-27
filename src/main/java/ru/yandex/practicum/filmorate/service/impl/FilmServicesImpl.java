package ru.yandex.practicum.filmorate.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;

import java.util.HashMap;

@Slf4j
public class FilmServicesImpl implements FilmService {

    private HashMap<Integer, Film> films;
    private static Integer id;

    @Override
    public ResponseEntity<?> addFilm(Film film) {
        if(!films.containsValue(film)) {
            films.put(++id, film);
        }
    }

    @Override
    public ResponseEntity<?> updateFilm(Film film) {
        if(films.containsValue(film)) {
            films.put(film.getId(), film);
        }
    }

    @Override
    public Film getFilms(Integer id) {
        if(!films.containsKey(id)) {
            throw new ValidationException("Not found film by {}", id);
        }
        return films.get(id);
    }
}
