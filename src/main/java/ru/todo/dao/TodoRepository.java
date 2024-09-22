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

  DataSource dataSource;
  private static final Logger logger = LoggerFactory.getLogger("config");

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
                   SELECT * from USERS u
                   JOIN todo t on u.id = t.user_id
                   WHERE user_id = %d
                   """.formatted(userId);
      ResultSet resultSet = statement.executeQuery(sql);

      List<Todo> entries = new LinkedList();
      while (resultSet.next()) {
        entries.add(
            new Todo(
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
}
