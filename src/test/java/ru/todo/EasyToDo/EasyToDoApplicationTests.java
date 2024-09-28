package ru.todo.EasyToDo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.todo.EasyToDo.config.TestDbConfig;
import ru.todo.dao.TodoRepository;
import ru.todo.dto.Todo;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes={TestDbConfig.class})
class EasyToDoApplicationTests {

	@Autowired
	public TodoRepository todoRepository;

	@Test
	void test() {
		List<Todo> todos = todoRepository.getTodoEntriesById(0);
		assertEquals("Test text", todos.get(0).todo());
	}

}
