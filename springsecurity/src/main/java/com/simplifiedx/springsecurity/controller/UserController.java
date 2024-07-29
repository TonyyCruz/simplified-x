package com.simplifiedx.springsecurity.controller;

import com.simplifiedx.springsecurity.dto.request.UserCreateDto;
import com.simplifiedx.springsecurity.dto.response.UserViewDto;
import com.simplifiedx.springsecurity.entities.User;
import com.simplifiedx.springsecurity.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public ResponseEntity<List<UserViewDto>> listAll() {
        List<UserViewDto> users = userService.findAll().stream().map(UserViewDto::new).toList();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public ResponseEntity<UserViewDto> findById(@PathVariable("id")UUID id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(new UserViewDto(user));
    }
}
