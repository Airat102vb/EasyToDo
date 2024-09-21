package ru.todo.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;

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
}
