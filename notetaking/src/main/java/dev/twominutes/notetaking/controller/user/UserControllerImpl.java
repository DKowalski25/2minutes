package dev.twominutes.notetaking.controller.user;

import dev.twominutes.notetaking.dto.user.UserRequestDto;
import dev.twominutes.notetaking.dto.user.UserResponseDto;
import dev.twominutes.notetaking.models.User;
import dev.twominutes.notetaking.service.user.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserControllerImpl implements UserController{
    private final UserService userService;

    public UserResponseDto registerUser(@RequestBody UserRequestDto userDto) {
        User user = userService.registerUser(userDto);
        return new UserResponseDto(user.getId(), user.getUsername(), user.getEmail());
    }
}
