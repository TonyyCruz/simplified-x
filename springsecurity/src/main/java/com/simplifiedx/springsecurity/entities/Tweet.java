package com.simplifiedx.springsecurity.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_TWEET")
public class Tweet {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @OneToMany
    @JoinColumn(name = "user_id")
    private User user;
    private String content;
    @CreationTimestamp
    private Instant creationTimesTamp;

}
