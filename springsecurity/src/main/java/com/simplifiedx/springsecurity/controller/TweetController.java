package com.simplifiedx.springsecurity.controller;

import com.simplifiedx.springsecurity.dto.request.TweetCreateDto;
import com.simplifiedx.springsecurity.dto.response.TweetViewDto;
import com.simplifiedx.springsecurity.entities.Tweet;
import com.simplifiedx.springsecurity.enums.RoleList;
import com.simplifiedx.springsecurity.service.TweetService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tweets")
public class TweetController {
    private final TweetService tweetService;

    public TweetController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @PostMapping
    public ResponseEntity<TweetViewDto> create(@RequestBody @Valid TweetCreateDto tweetDto) {
        Jwt jwtUser = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Tweet tweet = tweetService.save(UUID.fromString(jwtUser.getSubject()), tweetDto.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED).body(new TweetViewDto(tweet));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public ResponseEntity<List<TweetViewDto>> listAll() {
        List<TweetViewDto> tweets = tweetService.findAll().stream().map(TweetViewDto::new).toList();
        return ResponseEntity.ok(tweets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TweetViewDto> findById(@PathVariable("id") UUID id) {
        Tweet tweet = tweetService.findById(id);
        return ResponseEntity.ok(new TweetViewDto(tweet));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") UUID id) {
        if (isAdmin()) {
            tweetService.delete(id);
        } else {
            Jwt jwtUser = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            tweetService.delete(UUID.fromString(jwtUser.getSubject()), id);
        }
        return ResponseEntity.ok().body("Tweet deleted with id: " + id);
    }

    private boolean isAdmin() {
        return SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().contains(RoleList.ROLE_ADMIN.name()));
    }
}
