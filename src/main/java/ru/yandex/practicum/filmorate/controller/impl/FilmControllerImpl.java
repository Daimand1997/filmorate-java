package ru.yandex.practicum.filmorate.controller.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.controller.FilmControllerApi;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.impl.FilmServicesImpl;

import javax.validation.Valid;
import java.util.List;

@RestController("/films")
@RequestMapping(value = "/films", produces = MediaType.APPLICATION_JSON_VALUE)
@Component
@Slf4j
public class FilmControllerImpl implements FilmControllerApi {

    private final FilmServicesImpl filmServices;
    private final ObjectMapper objectMapper;

    public FilmControllerImpl(FilmServicesImpl filmServices, ObjectMapper objectMapper) {
        this.filmServices = filmServices;
        this.objectMapper = objectMapper;
    }


    public Film addFilm(@RequestBody @Valid Film film) throws JsonProcessingException {
        log.info("Start create film. " + objectMapper.writeValueAsString(film));
        Film responseFilm = filmServices.addFilm(film);
        log.info("Finish create film. " + objectMapper.writeValueAsString(film));
        return responseFilm;
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public Film updateFilm(@RequestBody Film film) throws JsonProcessingException {
        log.info("Start update film. " + objectMapper.writeValueAsString(film));
        Film responseFilm = filmServices.updateFilm(film);
        log.info("Finish update film by " + objectMapper.writeValueAsString(responseFilm));
        return responseFilm;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Film> getFilms() throws JsonProcessingException {
        log.info("Start get films");
        List<Film> responseFilm = filmServices.getFilms();
        log.info("Finish get film. Response: " + objectMapper.writeValueAsString(responseFilm));
        return responseFilm;
    }
}
