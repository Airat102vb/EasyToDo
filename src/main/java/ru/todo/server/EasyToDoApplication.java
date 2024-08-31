package ru.todo.server;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import ru.todo.config.TodoConfig;

import java.util.Arrays;

@SpringBootApplication
@Import(TodoConfig.class)
public class EasyToDoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyToDoApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner commandLineRunner(ApplicationContext context) {
//		return args -> {
//			System.out.println("Let's inspect the beans provided by Spring Boot:");
//			Arrays.stream(context.getBeanDefinitionNames())
//					.sorted()
//					.forEach(System.out::println);
//		};
//	}

}
