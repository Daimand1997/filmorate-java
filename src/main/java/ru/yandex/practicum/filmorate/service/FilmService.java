package ru.yandex.practicum.filmorate.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;

public interface FilmService {

    // Метод добавления фильма
    Film addFilm(Film film);

    // Метод обновления фильма
    Film updateFilm(Film film) throws JsonProcessingException;

    // Метод получения всех фильмов
    List<Film> getFilms();
}
