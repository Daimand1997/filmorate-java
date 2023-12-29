package ru.yandex.practicum.filmorate.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exceptions.ResourceAppException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Service
public class FilmServicesImpl implements FilmService {

    private static Integer id = 0;
    private final ObjectMapper objectMapper;
    private final Map<Integer, Film> films = new LinkedHashMap<>();

    public FilmServicesImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Film addFilm(Film film) {
        film.setId(++id);
        films.put(id, film);
        return film;
    }

    @Override
    public Film updateFilm(Film film) {
        if (!films.containsKey(id))
            throw new ResourceAppException("Not found film from update by id: "
                    + film.getId());
        films.put(id, film);
        return film;
    }

    @Override
    public Map<Integer, Film> getFilms() {
        return films;
    }

}
