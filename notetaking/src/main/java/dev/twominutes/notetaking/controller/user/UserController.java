package dev.twominutes.notetaking.controller.user;

import dev.twominutes.notetaking.dto.user.UserRequestDto;
import dev.twominutes.notetaking.dto.user.UserResponseDto;

import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/auth")
public interface UserController {
    @PostMapping("/register")
    UserResponseDto registerUser(@RequestBody UserRequestDto userDto);
}
