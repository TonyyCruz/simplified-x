package com.simplifiedx.springsecurity.service;

import com.simplifiedx.springsecurity.entities.Tweet;
import com.simplifiedx.springsecurity.entities.User;
import com.simplifiedx.springsecurity.exceptions.UnauthorizedException;
import com.simplifiedx.springsecurity.repository.TweetRepository;
import com.simplifiedx.springsecurity.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Tweet findById(UUID id) {
        return tweetRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tweet not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public Page<Tweet> findAll(Pageable pageable) {
        return tweetRepository.findAll(pageable);
    }

    @Transactional
    public void delete(UUID userId, UUID tweetId) {
        Tweet tweet = findById(tweetId);
        if (!tweet.getUser().getId().equals(userId)) {
            throw new UnauthorizedException("You have no authorization to delete this tweet.");
        }
        tweetRepository.deleteById(tweetId);
    }

    @Transactional
    public void delete(UUID id) {
        findById(id);
        tweetRepository.deleteById(id);
    }
}
