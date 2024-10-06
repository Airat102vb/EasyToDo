package ru.todo.controller;

import static ru.todo.controller.Endpoint.USERS;
import static ru.todo.controller.Endpoint.USER_BY_ID;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.todo.dao.UserRepository;
import ru.todo.dto.UserDTO;

@RestController
public class UserController {

  private UserRepository userRepository;

  @Autowired
  public UserController(UserRepository dataSource) {
    this.userRepository = dataSource;
  }

  @GetMapping(USERS)
  public List<UserDTO> getAllUsers() {
    return userRepository.getAllUsers();
  }

  @GetMapping(USER_BY_ID)
  public String getUserById(@PathVariable(value = "userId") long userId) {
    UserDTO user = userRepository.getUserById(userId);
    return user == null ? "There is not user with required id" : user.toString();
  }
}
