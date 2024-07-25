package com.simplifiedx.springsecurity.controller;

import com.simplifiedx.springsecurity.dto.response.TokenDto.LoginResponseTokenDto;
import com.simplifiedx.springsecurity.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseTokenDto> login(Authentication authentication) {
        String token = authenticationService.authenticate(authentication);
        return ResponseEntity.ok(new LoginResponseTokenDto(token));
    }
}
