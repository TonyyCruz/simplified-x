package com.simplifiedx.springsecurity.service;

import com.simplifiedx.springsecurity.entities.Role;
import com.simplifiedx.springsecurity.enums.RoleList;
import com.simplifiedx.springsecurity.repository.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional(readOnly = true)
    public Role findByRoleName(RoleList role) {
        return roleRepository.findByRoleName(role)
                .orElseThrow(() -> new EntityNotFoundException("Role not found with name: " + role));
    }
}
