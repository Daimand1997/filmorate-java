package ru.yandex.practicum.filmorate.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.impl.FilmServicesImpl;

import javax.validation.Valid;

@RestController
@Slf4j
public class FilmController {

    private final FilmServicesImpl filmServices;
    private final ObjectMapper objectMapper;

    public FilmController(FilmServicesImpl filmServices, ObjectMapper objectMapper) {
        this.filmServices = filmServices;
        this.objectMapper = objectMapper;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> addFilm(@RequestBody @Valid Film film) throws JsonProcessingException {
        log.info("Start create film. " + objectMapper.writeValueAsString(film));
        filmServices.addFilm(film);
        log.info("Finish create film. " + objectMapper.writeValueAsString(film));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public HttpStatus updateFilm(@RequestBody Film film) throws JsonProcessingException {
        log.info("Start update film. " + objectMapper.writeValueAsString(film));
        filmServices.updateFilm(film);
        log.info("Finish update film. " + objectMapper.writeValueAsString(film));
        return HttpStatus.OK;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Film getFilms(@RequestParam Integer id) throws JsonProcessingException {
        log.info("Start get film. ID = {}", id);
        Film film = filmServices.getFilms(id);
        log.info("Finish get film. Response: " + objectMapper.writeValueAsString(film));
        return film;
    }
}
