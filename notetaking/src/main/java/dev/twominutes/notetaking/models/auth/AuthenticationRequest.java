package dev.twominutes.notetaking.models.auth;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String username;
    private String password;
}
