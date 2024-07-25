package com.simplifiedx.springsecurity.dto.response;

import com.simplifiedx.springsecurity.entities.Role;
import com.simplifiedx.springsecurity.entities.User;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public record UserViewDto(UUID id, String username, Set<String> roles) {
    public UserViewDto(User user) {
        this(user.getId(), user.getUsername(), getRoleString(user.getRoles()));
    }

    public static Set<String> getRoleString(Set<Role> roleSet) {
        return roleSet.stream().map(r -> r.getRoleName().name()).collect(Collectors.toSet());
    }
}
