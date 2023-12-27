package ru.yandex.practicum.filmorate.service.impl;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Data
@Component
public class FilmServicesImpl implements FilmService {

    private List<Film> films;
    private static Integer id;

    @Override
    public Film addFilm(Film film) {
        if(films.contains(film)){
            throw new ValidationException("The film was created earlier");
        }
        film.setId(++id);
        films.add(film);
        return film;
    }

    @Override
    public Film updateFilm(Film film) {
        if(!films.contains(film)) {
            throw new ValidationException("Not found film from update by");
        }
        // TODO переделать!!!
        films.add(film.getId(), film);
        return film;
    }

    @Override
    public List<Film> getFilms() {
        return films;
    }

}
