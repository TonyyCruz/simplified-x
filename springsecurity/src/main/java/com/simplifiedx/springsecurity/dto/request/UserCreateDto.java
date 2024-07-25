package com.simplifiedx.springsecurity.dto.request;

import com.simplifiedx.springsecurity.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDto {
    private String username;
    private String password;

    public User toEntity() {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return user;
    }
}
