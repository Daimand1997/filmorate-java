package ru.yandex.practicum.filmorate.service;

import ru.yandex.practicum.filmorate.model.Film;

public interface FilmService {

    Film addLike(Film film);

    Film deleteLike(Film film);

    Film topFilmsByLike(Film film);
}
