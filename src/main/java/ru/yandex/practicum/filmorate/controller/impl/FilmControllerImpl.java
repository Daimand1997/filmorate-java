package ru.yandex.practicum.filmorate.controller.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.controller.FilmControllerApi;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.impl.FilmServicesImpl;

import java.util.List;
import java.util.Map;

@RestController("/films")
@RequestMapping(value = "/films", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@Data
public class FilmControllerImpl implements FilmControllerApi {

    private final FilmServicesImpl filmServices;
    private final ObjectMapper objectMapper;

    public FilmControllerImpl(FilmServicesImpl filmServices, ObjectMapper objectMapper) {
        this.filmServices = filmServices;
        this.objectMapper = objectMapper;
    }

    @Override
    public Film addFilm(Film film) throws JsonProcessingException {
        log.info("Start create film. " + objectMapper.writeValueAsString(film));
        Film responseFilm = filmServices.addFilm(film);
        log.info("Finish create film. " + objectMapper.writeValueAsString(film));
        return responseFilm;
    }

    @Override
    public Film updateFilm(Film film) throws JsonProcessingException {
        log.info("Start update film. " + objectMapper.writeValueAsString(film));
        Film responseFilm = filmServices.updateFilm(film);
        log.info("Finish update film by " + objectMapper.writeValueAsString(responseFilm));
        return responseFilm;
    }

    @Override
    public Map<Integer, Film> getFilms() throws JsonProcessingException {
        log.info("Start get films");
        Map<Integer, Film> responseFilm = filmServices.getFilms();
        log.info("Finish get film. Response: " + objectMapper.writeValueAsString(responseFilm));
        return responseFilm;
    }
}
