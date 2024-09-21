package ru.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.todo.dao.TodoRepository;
import ru.todo.dto.Greeting;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class TodoController {

  private static final String template = "Hello, %s!";
  private final AtomicLong counter = new AtomicLong();
  TodoRepository todoRepository;

  @Autowired
  public TodoController(TodoRepository todoRepository) {
    this.todoRepository = todoRepository;
  }

  @GetMapping("/greeting")
  public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
    return new Greeting(counter.incrementAndGet(), String.format(template, name));
  }

  @GetMapping("/simple")
  public String simple() {
    return todoRepository.simpleQuery();
  }
}
