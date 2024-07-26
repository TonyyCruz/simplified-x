package com.simplifiedx.springsecurity.controller;

import com.simplifiedx.springsecurity.dto.request.UserCreateDto;
import com.simplifiedx.springsecurity.dto.response.UserViewDto;
import com.simplifiedx.springsecurity.entities.User;
import com.simplifiedx.springsecurity.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/create")
    public ResponseEntity<UserViewDto> create(@RequestBody @Valid UserCreateDto userDto) {
        User user = userService.create(userDto.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED).body(new UserViewDto(user));
    }
}
