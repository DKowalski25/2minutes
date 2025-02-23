package dev.twominutes.notetaking.service.user;

import dev.twominutes.notetaking.dto.user.UserRequestDto;
import dev.twominutes.notetaking.models.User;
import dev.twominutes.notetaking.repository.user.UserRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void testRegisterUser() {
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);

        UserRequestDto userDto = UserRequestDto.builder()
                .username("testuser")
                .email("test@example.com")
                .password("test_password")
                .build();

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setUsername("testuser");
        savedUser.setEmail("test@example.com");
        savedUser.setPassword("encoded_password");

        when(passwordEncoder.encode("test_password")).thenReturn("encoded_password");
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        userService.registerUser(userDto);

        verify(userRepository).save(userCaptor.capture());
        User capturedUser = userCaptor.getValue();

        assertNotNull(capturedUser);
        assertEquals("testuser", capturedUser.getUsername());
        assertEquals("test@example.com", capturedUser.getEmail());
        assertEquals("encoded_password", capturedUser.getPassword()); // Проверка закодированного пароля

        verify(passwordEncoder, times(1)).encode("test_password");
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testRegisterUser_UsernameAlreadyExists() {
        UserRequestDto userDto = new UserRequestDto();
        userDto.setUsername("testuser");
        userDto.setEmail("test@example.com");
        userDto.setPassword("test_password");

        User existingUser = new User(1L, "existingUser", "test@example.com", "encoded_password");

        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(existingUser));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.registerUser(userDto);
        });

        assertEquals("User already exists", exception.getMessage());

        verify(userRepository, never()).save(any(User.class));
    }
}
