package com.simplifiedx.springsecurity.service;

import com.simplifiedx.springsecurity.entities.Role;
import com.simplifiedx.springsecurity.entities.User;
import com.simplifiedx.springsecurity.enums.RoleList;
import com.simplifiedx.springsecurity.repository.RoleRepository;
import com.simplifiedx.springsecurity.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findByRoleName(RoleList role) {
        return roleRepository.findByRoleName(role)
                .orElseThrow(() -> new EntityNotFoundException("Role not found with name: " + role));
    }
}
