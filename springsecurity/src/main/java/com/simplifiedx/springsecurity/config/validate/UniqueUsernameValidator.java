package com.simplifiedx.springsecurity.config.validate;

import com.simplifiedx.springsecurity.entities.User;
import com.simplifiedx.springsecurity.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void initialize(UniqueUsername constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) return true;
        if (SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken) return false;
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal.getId().equals(user.get().getId());
    }
}
