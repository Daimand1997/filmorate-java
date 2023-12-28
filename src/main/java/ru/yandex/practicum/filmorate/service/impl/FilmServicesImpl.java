package ru.yandex.practicum.filmorate.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Data
@Component
public class FilmServicesImpl implements FilmService {

    private List<Film> films = new ArrayList<>();
    private static Integer id = 0;
    private final ObjectMapper objectMapper;

    public FilmServicesImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Film addFilm(Film film) {
        if(films.stream().anyMatch(g-> g.equals(film))) throw new ValidationException("The film was created earlier");
        film.setId(++id);
        films.add(film);
        return film;
    }

    @Override
    public Film updateFilm(Film film) throws JsonProcessingException {
        if(films.stream().filter(g-> g.getId().equals(film.getId())).findFirst().isEmpty())
            throw new ValidationException("Not found film from update by "
                    + objectMapper.writeValueAsString(film));
        for(int i = 0; i < films.size(); i++) {
            if (films.get(i).getId().equals(film.getId())) {
                films.set(i, film);
                break;
            }
        }
        return film;
    }

    @Override
    public List<Film> getFilms() {
        return films != null ? films : Collections.emptyList();
    }

}
