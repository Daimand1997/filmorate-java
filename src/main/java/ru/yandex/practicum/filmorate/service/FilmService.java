package ru.yandex.practicum.filmorate.service;

import ru.yandex.practicum.filmorate.model.Film;

import java.util.List;

public interface FilmService {

    void addLikeFromFilm(Long idFilm, Long idUser);

    void deleteLikeFromFilm(Long idFilm, Long idUser);

    List<Film> getTopFilmsByLike(Long countTopFilms);
}
