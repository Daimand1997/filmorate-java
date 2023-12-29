package ru.yandex.practicum.filmorate.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exceptions.ResourceAppException;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class FilmServicesImpl implements FilmService {

    private static Integer id = 0;
    private final List<Film> films = new ArrayList<>();

    @Override
    public Film addFilm(Film film) {
        if (films.stream().anyMatch(g -> g.equals(film))) throw new ValidationException("The film was created earlier");
        film.setId(++id);
        films.add(film);
        return film;
    }

    @Override
    public Film updateFilm(Film film) {
        if (films.stream().filter(g -> g.getId().equals(film.getId())).findFirst().isEmpty())
            throw new ResourceAppException("Not found film from update by id " + film.getId());
        for (int i = 0; i < films.size(); i++) {
            if (films.get(i).getId().equals(film.getId())) {
                films.set(i, film);
                break;
            }
        }
        return film;
    }

    @Override
    public List<Film> getFilms() {
        return films;
    }

}
