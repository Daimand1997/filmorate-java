package ru.yandex.practicum.filmorate.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
public interface FilmControllerApi {

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(schema = @Schema(implementation = Film.class))),
            @ApiResponse(responseCode = "400",
                    description = "Такой фильм уже существует",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500",
                    description = "Произошла внутренняя ошибка",
                    content = @Content(schema = @Schema()))
    })
    @Operation(description = "Добавить новый фильм")
    @Tag(name = "1. Добавление нового фильма")
    Film addFilm(@RequestBody
                 @Valid
                 @NotNull(message = "Film cannot be empty.") Film film) throws JsonProcessingException;

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(schema = @Schema(implementation = Film.class))),
            @ApiResponse(responseCode = "500",
                    description = "Произошла внутренняя ошибка",
                    content = @Content(schema = @Schema()))
    })
    @Operation(description = "Обновить существующий фильм")
    @Tag(name = "2. Обновление существующего фильма")
    Film updateFilm(@RequestBody
                    @Valid
                    @NotNull(message = "Film cannot be empty.") Film film) throws JsonProcessingException;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(schema = @Schema(implementation = Film.class))),
            @ApiResponse(responseCode = "500",
                    description = "Произошла внутренняя ошибка",
                    content = @Content(schema = @Schema()))
    })
    @Operation(description = "Получить список всех фильмов")
    @Tag(name = "3. Получение списка всех фильмов")
    List<Film> getFilms() throws JsonProcessingException;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "400",
                    description = "Произошла ошибка валидации",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404",
                    description = "Фильм не найден",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500",
                    description = "Произошла внутренняя ошибка",
                    content = @Content(schema = @Schema()))
    })
    @Operation(description = "Получить определённый фильм")
    @Tag(name = "4. Получение определённого фильма")
    Film getFilmById(@PathVariable
                     @Min(value = 1, message = "Id cannot be less 1")
                     Long id) throws JsonProcessingException;
}
