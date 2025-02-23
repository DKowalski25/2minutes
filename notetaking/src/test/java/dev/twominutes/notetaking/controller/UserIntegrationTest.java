package dev.twominutes.notetaking.controller;

import dev.twominutes.notetaking.dto.user.UserRequestDto;
import dev.twominutes.notetaking.dto.user.UserResponseDto;
import dev.twominutes.notetaking.utils.EnvLoader;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class UserIntegrationTest {

    @Container // Аннотация для контейнера
    public static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("testdb")
            .withUsername("testuser")
            .withPassword("testpassword");

    @BeforeAll
    static void setup() {
        EnvLoader.load();

        System.setProperty("spring.datasource.url", postgres.getJdbcUrl());
        System.setProperty("spring.datasource.username", postgres.getUsername());
        System.setProperty("spring.datasource.password", postgres.getPassword());
    }

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void registerUser_shouldReturnUserResponseDto() {
        UserRequestDto requestDto = new UserRequestDto("testuser", "test@example.com", "password");

        ResponseEntity<UserResponseDto> response = restTemplate.postForEntity(
                "/api/auth/register",
                requestDto,
                UserResponseDto.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        UserResponseDto responseDto = response.getBody();
        assertEquals("testuser", responseDto.getUsername());
        assertEquals("test@example.com", responseDto.getEmail());
    }
}