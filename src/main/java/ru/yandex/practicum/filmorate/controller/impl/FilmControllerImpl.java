package ru.yandex.practicum.filmorate.controller.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.filmorate.controller.FilmControllerApi;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.impl.InMemoryFilmStorageImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController("/films")
@RequestMapping(value = "/films", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
@Data
public class FilmControllerImpl implements FilmControllerApi {

    private final InMemoryFilmStorageImpl filmServices;
    private final ObjectMapper objectMapper;

    @Autowired
    public FilmControllerImpl(InMemoryFilmStorageImpl filmServices, ObjectMapper objectMapper) {
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
    public List<Film> getFilms() throws JsonProcessingException {
        log.info("Start get films");
        Map<Long, Film> responseFilm = filmServices.getFilms();
        log.info("Finish get film. Response: " + objectMapper.writeValueAsString(responseFilm));
        return new ArrayList<>(responseFilm.values());
    }

    @Override
    public Film getFilmById(Long id) throws JsonProcessingException {
        log.info("Start get film by id: {}", id);
        Film responseFilm = filmServices.getFilmById(id);
        log.info("Finish get film by id {}. Response: {}", id, objectMapper.writeValueAsString(responseFilm));
        return responseFilm;
    }
}
