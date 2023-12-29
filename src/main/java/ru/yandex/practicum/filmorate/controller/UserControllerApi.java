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
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;
import java.util.List;

@Validated
public interface UserControllerApi {

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "400",
                    description = "Такой пользователь уже существует",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500",
                    description = "Произошла внутренняя ошибка",
                    content = @Content(schema = @Schema()))
    })
    @Operation(description = "Добавить нового пользователя")
    @Tag(name = "1. Добавление нового пользователя")
    User addUser(@RequestBody @Valid User user) throws JsonProcessingException;

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "500",
                    description = "Произошла внутренняя ошибка",
                    content = @Content(schema = @Schema()))
    })
    @Operation(description = "Обновить существующего пользователя")
    @Tag(name = "2. Обновление существующего пользователя")
    User updateUser(@RequestBody @Valid User user) throws JsonProcessingException;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "500",
                    description = "Произошла внутренняя ошибка",
                    content = @Content(schema = @Schema()))
    })
    @Operation(description = "Получить список всех пользователей")
    @Tag(name = "3. Получение списка всех пользователей")
    List<User> getUsers() throws JsonProcessingException;
}
