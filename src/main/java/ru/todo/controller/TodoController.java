package ru.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.todo.dao.TodoRepository;
import ru.todo.dto.TodoDTO;
import static ru.todo.controller.Endpoint.ENTRY;
import static ru.todo.controller.Endpoint.ENTRY_USERID_TODO_ID;

@RestController
public class TodoController {

  TodoRepository todoRepository;

  @Autowired
  public TodoController(TodoRepository todoRepository) {
    this.todoRepository = todoRepository;
  }

  @GetMapping(ENTRY)
  public List<TodoDTO> getTodoEntries(@RequestParam(value = "userId") int userId) {
    return todoRepository.getTodoEntriesById(userId);
  }

  @PutMapping(ENTRY_USERID_TODO_ID)
  public void updateTodoEntries(@PathVariable(value = "userId") long userId,
                                @PathVariable(value = "todoId") long todoId,
                                @RequestBody String text
  ) {
    todoRepository.updateTodoTodoByUserIdAndTodoId(userId, todoId, text);
  }
}
