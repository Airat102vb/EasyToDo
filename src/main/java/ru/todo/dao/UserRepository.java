package ru.todo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.todo.dto.UserDTO;

@Repository
public class UserRepository {

  private static final Logger LOGGER = LoggerFactory.getLogger("TodoRepository");
  private DataSource dataSource;

  @Autowired
  public UserRepository(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  public List<UserDTO> getAllUsers() {
    List<UserDTO> users = new LinkedList<>();
    try(Connection connection = dataSource.getConnection()) {
      Statement statement = connection.createStatement();
      String sql = "SELECT * FROM users";
      LOGGER.info("Выполняется запрос: \n{}", sql);
      ResultSet resultSet = statement.executeQuery(sql);

      while (resultSet.next()) {
        users.add(
            new UserDTO(
                resultSet.getString("id"),
                resultSet.getString("full_name"),
                resultSet.getString("login")
            )
        );
      }
      return users;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public UserDTO getUserById(long id) {
    try(Connection connection = dataSource.getConnection()) {
      Statement statement = connection.createStatement();
      String sql = "SELECT * FROM users WHERE id = %d".formatted(id);
      LOGGER.info("Выполняется запрос: \n{}", sql);
      ResultSet resultSet = statement.executeQuery(sql);

      if (resultSet.next()) {
        return new UserDTO(
            resultSet.getString("id"),
            resultSet.getString("full_name"),
            resultSet.getString("login")
        );
      } else {
        return null;
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
