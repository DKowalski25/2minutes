package dev.twominutes.notetaking.controller.user;

import dev.twominutes.notetaking.dto.user.UserRequestDto;
import dev.twominutes.notetaking.dto.user.UserResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User Registration", description = "Endpoints for user registration")
@RequestMapping("/api/auth")
public interface UserController {
    @Operation(
            summary = "Register a new user",
            description = "Registers a new user with the provided user details",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User registered successfully"),
                    @ApiResponse(responseCode = "400", description = "Bad request")
            }
    )
    @PostMapping("/register")
    UserResponseDto registerUser(@RequestBody UserRequestDto userDto);
}
