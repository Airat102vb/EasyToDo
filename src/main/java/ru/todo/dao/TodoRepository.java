package ru.todo.dao;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.todo.dto.Todo;

@Component
public class TodoRepository {

  private static final Logger LOGGER = LoggerFactory.getLogger("TodoRepository");
  private DataSource dataSource;

//  private JdbcTemplate jdbcTemplate;

  @Autowired
  public TodoRepository (DataSource dataSource) {
    this.dataSource = dataSource;
  }

  public String simpleQuery() {
    try {
      Connection connection = dataSource.getConnection();
      Statement statement = connection.createStatement();
      String sql = "select * from users";
      ResultSet resultSet = statement.executeQuery(sql);

      if (resultSet.next()) {
        return resultSet.getString(2);
      } else {
        return "nothing";
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public List<Todo> getTodoEntriesById(int userId) {
    try {
      Connection connection = dataSource.getConnection();
      Statement statement = connection.createStatement();
      String sql = """
                      SELECT t.id, u.full_name, u.login, t.entry from todo t
                      JOIN users u on t.user_id = u.id
                      WHERE user_id = %d
                   """.formatted(userId);
      LOGGER.info("Выполняется запрос: '{};'", sql);
      ResultSet resultSet = statement.executeQuery(sql);

      List<Todo> entries = new LinkedList();
      while (resultSet.next()) {
        entries.add(
            new Todo(
                resultSet.getString("id"),
                resultSet.getString("full_name"),
                resultSet.getString("login"),
                resultSet.getString("entry")
            )
        );
      }
      return entries;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void updateTodoTodoByUserIdAndTodoId(long userId, long todoId, String text) {
    try {
      Connection connection = dataSource.getConnection();
      Statement statement = connection.createStatement();
      String sql = """
                      UPDATE todo
                      SET entry = '%s'
                      WHERE user_id = %d
                      AND id = %d;
                   """.formatted(text, userId, todoId);
      LOGGER.info("Выполняется запрос: '{};'", sql);
      statement.executeUpdate(sql);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
