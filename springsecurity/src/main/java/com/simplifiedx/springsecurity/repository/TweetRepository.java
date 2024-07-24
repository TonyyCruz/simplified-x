package com.simplifiedx.springsecurity.repository;

import com.simplifiedx.springsecurity.entities.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TweetRepository extends JpaRepository<Tweet, UUID> {
}
