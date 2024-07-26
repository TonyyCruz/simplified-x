package com.simplifiedx.springsecurity.dto.request;

import com.simplifiedx.springsecurity.config.validate.UniqueUsername;
import com.simplifiedx.springsecurity.entities.User;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDto {
    @UniqueUsername(message = "This username is already in use.")
    private String username;
    @Min(value = 6, message = "The password must be at least 6 characters long.")
    private String password;

    public User toEntity() {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return user;
    }
}
