package dev.twominutes.notetaking;

import dev.twominutes.notetaking.utils.EnvLoader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NotetakingApplication {
	public static void main(String[] args) {
        // Загружаем переменные из .env в системные переменные окружения
        EnvLoader.load();

        // Запускаем Spring Boot приложение
		SpringApplication.run(NotetakingApplication.class, args);
	}

}
