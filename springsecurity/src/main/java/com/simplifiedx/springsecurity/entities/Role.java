package com.simplifiedx.springsecurity.entities;

import com.simplifiedx.springsecurity.enums.RoleList;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_ROLES")
public class Role implements GrantedAuthority {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Enumerated(EnumType.STRING)
    @Column(unique = true)
    private RoleList roleName;

    @Override
    public String getAuthority() {
        return roleName.name();
    }
}
