package com.app.production.auth.application.dtos;

import com.app.production.auth.domain.entities.Role;
import lombok.Data;

@Data
public class UserDto {
    private String username;
    private String password;
    private String documents_name;
    private Role role;
}
