package com.simplifiedx.springsecurity.dto.response;

import com.simplifiedx.springsecurity.entities.Tweet;

import java.time.Instant;
import java.util.UUID;

public record TweetViewDto(UUID id, String username, String content, Instant creationTimesTamp) {
    public TweetViewDto(Tweet tweet) {
        this(tweet.getId(), tweet.getUser().getUsername(), tweet.getContent(), tweet.getCreationTimesTamp());
    }
}
