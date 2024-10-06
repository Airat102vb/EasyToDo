package ru.todo.controller;

public class Endpoint {
  public static final String ROOT = "/";

  //Users
  public static final String USERS = "/users";
  public static final String USER_BY_ID = "/users/{userId}";

  //Todos
  public static final String ENTRY = "/entry";
  public static final String ENTRY_USERID_TODO_ID = "/entry/{userId}/todo/{todoId}";
}
