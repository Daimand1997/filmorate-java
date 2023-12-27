package ru.yandex.practicum.filmorate.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.Valid;

public interface FilmControllerApi {

    Film addFilm(@RequestBody @Valid Film film) throws JsonProcessingException;

    Film updateFilm(@RequestBody Film film) throws JsonProcessingException;

    Film getFilms(@PathVariable Integer id) throws JsonProcessingException;


}
