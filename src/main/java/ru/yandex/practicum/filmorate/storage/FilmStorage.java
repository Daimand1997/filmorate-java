package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.Map;

public interface FilmStorage {

    // Метод добавления фильма
    Film addFilm(Film film);

    // Метод обновления фильма
    Film updateFilm(Film film);

    // Метод получения всех фильмов
    Map<Long, Film> getFilms();

    // Метод получения фильма по Id
    Film getFilmById(Long id);
}
