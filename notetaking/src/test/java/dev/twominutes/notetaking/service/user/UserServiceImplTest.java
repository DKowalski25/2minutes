package dev.twominutes.notetaking.service.user;

import dev.twominutes.notetaking.dto.user.UserRequestDto;
import dev.twominutes.notetaking.models.User;
import dev.twominutes.notetaking.repository.user.UserRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
    public void testRegisterUser() {
        // 1. Подготовка данных
        UserRequestDto userDto = new UserRequestDto();
        userDto.setUsername("testuser");
        userDto.setEmail("test@example.com");
        userDto.setPassword("test_password");

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setUsername("testuser");
        savedUser.setEmail("test@example.com");
        savedUser.setPassword("encoded_password");

        // 2. Мокирование зависимостей
        when(passwordEncoder.encode("test_password")).thenReturn("encoded_password");
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        // 3. Вызов метода
        User result = userService.registerUser(userDto);

        // 4. Проверка результатов
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("testuser", result.getUsername());
        assertEquals("test@example.com", result.getEmail());
        assertEquals("encoded_password", result.getPassword()); // Проверка закодированного пароля

        // 5. Проверка вызовов
        verify(passwordEncoder, times(1)).encode("test_password");
        verify(userRepository, times(1)).save(any(User.class));
    }
}
