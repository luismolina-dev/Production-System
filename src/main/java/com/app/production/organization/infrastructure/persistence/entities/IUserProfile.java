package com.app.production.organization.infrastructure.persistence.entities;

import com.app.production.organization.domain.entities.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "users_profiles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IUserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, unique = true)
    private IUser user;

    @Column(name = "full_name")
    private String fullName;

    private String cargo;

    @Enumerated(EnumType.STRING)
    private Role role;

    private byte[] signature;
}
