package ru.todo.EasyToDo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.todo.utils.CommonUtils.getRandomString;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.todo.EasyToDo.config.TestDbConfig;
import ru.todo.dao.TodoRepository;
import ru.todo.dto.Todo;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes={TestDbConfig.class})
class EasyToDoApplicationTests {

	private static final int USER_ID = 1;

	@Autowired
	public TodoRepository todoRepository;

	@Test
	void getTest() {
		List<Todo> todos = todoRepository.getTodoEntriesById(USER_ID);
		assertEquals("Test text", todos.get(0).todo());
	}

	@Test
	@DirtiesContext
	void updateTest() {
		String randomEntryText = getRandomString();
		List<Todo> todos = todoRepository.getTodoEntriesById(USER_ID);

		todoRepository.updateTodoTodoByUserIdAndTodoId(USER_ID, Integer.parseInt(todos.get(0).id()), randomEntryText);
		todos = todoRepository.getTodoEntriesById(USER_ID);
		assertEquals(randomEntryText, todos.get(0).todo());
	}
}
