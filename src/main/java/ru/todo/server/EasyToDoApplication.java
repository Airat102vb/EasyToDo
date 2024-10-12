package ru.todo.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;
import ru.todo.config.Config;
import ru.todo.config.DataSourceConfigurationProperties;

import java.util.Arrays;

@SpringBootApplication
@Import(Config.class)
@EnableConfigurationProperties(DataSourceConfigurationProperties.class)
public class EasyToDoApplication {

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);

		SpringApplication.run(EasyToDoApplication.class, args);
	}
}
