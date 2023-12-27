package ru.yandex.practicum.filmorate.service;

import org.springframework.http.ResponseEntity;
import ru.yandex.practicum.filmorate.model.Film;

public interface FilmService {

    ResponseEntity<?> addFilm(Film film);

    ResponseEntity<?> updateFilm(Film film);

    Film getFilms(Integer id);
}
