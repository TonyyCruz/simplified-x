package com.simplifiedx.springsecurity.service;

import com.simplifiedx.springsecurity.entities.Role;
import com.simplifiedx.springsecurity.entities.User;
import com.simplifiedx.springsecurity.enums.RoleList;
import com.simplifiedx.springsecurity.repository.RoleRepository;
import com.simplifiedx.springsecurity.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User create(User user) {
        Role initialRole = roleService.findByRoleName(RoleList.ROLE_USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.addRole(initialRole);
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
    }
}
