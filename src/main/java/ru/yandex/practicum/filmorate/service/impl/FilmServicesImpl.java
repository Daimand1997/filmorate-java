package ru.yandex.practicum.filmorate.service.impl;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;

import java.util.HashMap;

@Slf4j
@Data
@Component
public class FilmServicesImpl implements FilmService {

    private HashMap<Integer, Film> films;
    private static Integer id;

    @Override
    public Film addFilm(Film film) {
        if(!films.containsValue(film)) {
            films.put(++id, film);
        }
        return null;
    }

    @Override
    public Film updateFilm(Film film) {
        if(!films.containsValue(film)) {
            throw new ValidationException("Not found film from update by");
        }
        films.put(film.getId(), film);
        return films.get(film.getId());

    }

    @Override
    public Film getFilms(Integer id) {
        if(!films.containsKey(id)) {
            throw new ValidationException("Not found film by {}" + id);
        }
        return films.get(id);
    }
}
