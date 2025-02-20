package dev.twominutes.notetaking.dto.user;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDto {
    private String username;
    private String email;
    private String password;
}
