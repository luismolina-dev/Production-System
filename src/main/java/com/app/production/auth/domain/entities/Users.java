package com.app.production.auth.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    private UUID id;
    private String username;
    private String password;
    private String documents_name;
    private Role role;
}
