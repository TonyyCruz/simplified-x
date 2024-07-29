package com.simplifiedx.springsecurity.service;

import com.simplifiedx.springsecurity.entities.Role;
import com.simplifiedx.springsecurity.entities.Tweet;
import com.simplifiedx.springsecurity.entities.User;
import com.simplifiedx.springsecurity.enums.RoleList;
import com.simplifiedx.springsecurity.repository.TweetRepository;
import com.simplifiedx.springsecurity.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class TweetService {
    private final TweetRepository tweetRepository;
    private final UserRepository userRepository;

    public TweetService(TweetRepository tweetRepository,
                        UserRepository userRepository) {
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Tweet save(UUID id, Tweet tweet) {
        User user = userRepository.getReferenceById(id);
        tweet.setUser(user);
        return tweetRepository.save(tweet);
    }

    @Transactional(readOnly = true)
    public Tweet findOne(UUID id) {
        return tweetRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tweet not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public List<Tweet> findAll() {
        return tweetRepository.findAll();
    }
}
