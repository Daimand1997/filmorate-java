package ru.yandex.practicum.filmorate.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.models.auth.In;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;
import java.util.Map;

public interface FilmService {

    // Метод добавления фильма
    Film addFilm(Film film);

    // Метод обновления фильма
    Film updateFilm(Film film) throws JsonProcessingException;

    // Метод получения всех фильмов
    Map<Integer, Film> getFilms();
}
