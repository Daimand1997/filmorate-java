package ru.yandex.practicum.filmorate.service;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.Map;

public interface FilmService {

    // Метод добавления фильма
    Film addFilm(Film film);

    // Метод обновления фильма
    Film updateFilm(Film film);

    // Метод получения всех фильмов
    Map<Integer, Film> getFilms();
}
