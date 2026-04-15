package com.app.production.organization.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile {
    private UUID id;
    private String fullName;
    private String position;
    private Role role;
    private byte[] signature;
}
