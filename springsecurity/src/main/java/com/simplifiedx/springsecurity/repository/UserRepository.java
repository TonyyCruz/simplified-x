package com.simplifiedx.springsecurity.repository;

import com.simplifiedx.springsecurity.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
