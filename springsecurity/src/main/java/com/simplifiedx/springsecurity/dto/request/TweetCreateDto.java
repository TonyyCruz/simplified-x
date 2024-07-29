package com.simplifiedx.springsecurity.dto.request;

import com.simplifiedx.springsecurity.entities.Tweet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TweetCreateDto {
    private String content;

    public Tweet toEntity() {
        Tweet tweet = new Tweet();
        tweet.setContent(content);
        return tweet;
    }
}
