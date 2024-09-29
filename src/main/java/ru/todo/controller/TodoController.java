package ru.todo.controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.todo.dao.TodoRepository;
import ru.todo.dto.Greeting;
import ru.todo.dto.Todo;

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

  @GetMapping("/entry")
  public List<Todo> getTodoEntries(@RequestParam(value = "userId") int userId) {
    return todoRepository.getTodoEntriesById(userId);
  }

  @PutMapping("/entry/{userId}/todo/{todoId}")
  public void updateTodoEntries(@PathVariable(value = "userId") long userId,
                                @PathVariable(value = "todoId") long todoId,
                                @RequestBody String text
  ) {
    todoRepository.updateTodoTodoByUserIdAndTodoId(userId, todoId, text);
  }
}
