package ru.yandex.practicum.filmorate.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;

import javax.validation.Valid;

@RestController
public class UserController {

    @PostMapping()
    public ResponseEntity<?> addUser(@RequestBody @Valid User user) {
        if(user.getName() == null || user.getName().isEmpty()) {
            user.setName(user.getLogin());
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<?> updateUser(@RequestBody @Valid User user) {
        if(user.getName() == null || user.getName().isEmpty()) {
            user.setName(user.getLogin());
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
