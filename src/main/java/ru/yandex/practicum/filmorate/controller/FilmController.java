package ru.yandex.practicum.filmorate.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;

import javax.validation.Valid;

@RestController
public class FilmController {
    @PostMapping()
    public ResponseEntity<?> addFilm(@RequestBody @Valid Film film) {

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<?> updateFilm(@RequestBody Film film) {

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFilms(@RequestParam Integer id) {

        return ResponseEntity.ok(HttpStatus.OK);
    }
}
