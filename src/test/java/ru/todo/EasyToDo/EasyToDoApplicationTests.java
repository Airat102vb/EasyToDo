package ru.todo.EasyToDo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@SpringJUnitConfig
class EasyToDoApplicationTests {

	@Test
	void contextLoads() {
		assertEquals(1, 1);
	}

}
