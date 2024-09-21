package ru.todo.config;

import javax.sql.DataSource;

import org.postgresql.Driver;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@Configuration
@ComponentScan("ru.todo")
public class Config {

  @Bean
//  @ConfigurationProperties(prefix = "app.datasource")
//  @ConfigurationProperties(prefix = "spring.datasource")
  public DataSource dataSource() {
    return new SimpleDriverDataSource(
        new Driver(), "jdbc:postgresql://localhost:5432/tododb", "postgres", "postgres"
    );
  }
}
