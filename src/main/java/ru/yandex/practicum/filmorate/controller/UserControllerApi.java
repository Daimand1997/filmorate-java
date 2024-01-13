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
import javax.validation.constraints.Min;
import java.util.List;

@Validated
public interface UserControllerApi {

    @PostMapping()
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
    User addUser(@RequestBody
                 @Valid
                 User user) throws JsonProcessingException;

    @PutMapping()
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
    User updateUser(@RequestBody
                    @Valid
                    User user) throws JsonProcessingException;

    @GetMapping()
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

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "400",
                    description = "Произошла ошибка валидации",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404",
                    description = "Пользователь не найден",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500",
                    description = "Произошла внутренняя ошибка",
                    content = @Content(schema = @Schema()))
    })
    @Operation(description = "Получить определённого пользователя")
    @Tag(name = "4. Получение определённого пользователя")
    User getUserById(@PathVariable
                    @Min(value = 1, message = "Id cannot be less 1")
                    Long id) throws JsonProcessingException;

    @PutMapping("/{id}/friends/{friendId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "400",
                    description = "Произошла ошибка валидации",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404",
                    description = "Пользователь не найден",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500",
                    description = "Произошла внутренняя ошибка",
                    content = @Content(schema = @Schema()))
    })
    @Operation(description = "Добавить пользователя в друзья")
    @Tag(name = "5. добавление пользователя в друзья")
    void addFriendById(@PathVariable("id")
            @Min(value = 1, message = "Id cannot be less 1")
            Long idUser,
            @PathVariable("friendId")
            @Min(value = 1, message = "friendId cannot be less 1")
            Long idFriend);

    @DeleteMapping("/{id}/friends/{friendId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "400",
                    description = "Произошла ошибка валидации",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404",
                    description = "Пользователь не найден",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500",
                    description = "Произошла внутренняя ошибка",
                    content = @Content(schema = @Schema()))
    })
    @Operation(description = "Удалить пользователя из друзей")
    @Tag(name = "6. Удаление пользователя из друзей")
    void deleteFriendById(@PathVariable("id")
                       @Min(value = 1, message = "Id cannot be less 1")
                       Long idUser,
                       @PathVariable("friendId")
                       @Min(value = 1, message = "friendId cannot be less 1")
                       Long idFriend);

    @GetMapping("/{id}/friends")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "400",
                    description = "Произошла ошибка валидации",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404",
                    description = "Пользователь не найден",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500",
                    description = "Произошла внутренняя ошибка",
                    content = @Content(schema = @Schema()))
    })
    @Operation(description = "Получить список друзей пользователя")
    @Tag(name = "7. Получение списка друзей пользователя")
    List<User> getFriendsFromUserById(@PathVariable("id")
                          @Min(value = 1, message = "Id cannot be less 1")
                          Long idUser);

    @GetMapping("/{id}/friends/common/{otherId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "400",
                    description = "Произошла ошибка валидации",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404",
                    description = "Пользователь не найден",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "500",
                    description = "Произошла внутренняя ошибка",
                    content = @Content(schema = @Schema()))
    })
    @Operation(description = "Удалить пользователя из друзей")
    @Tag(name = "8. Удаление пользователя из друзей")
    List<User> getCommonsFriendsByIdUser(@PathVariable("id")
                          @Min(value = 1, message = "Id cannot be less 1")
                          Long idUser,
                          @PathVariable("otherId")
                          @Min(value = 1, message = "otherId cannot be less 1")
                          Long idFriend);
}