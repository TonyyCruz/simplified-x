package com.simplifiedx.springsecurity.repository;

import com.simplifiedx.springsecurity.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
}
