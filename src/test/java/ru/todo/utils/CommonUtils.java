package ru.todo.utils;

import java.util.Random;

public class CommonUtils {

  private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789 ";

  public static String getRandomString() {
    Random random = new Random();
    StringBuilder sb = new StringBuilder();
    for(int i = 0; i < 15; i++) {
      int index = random.nextInt(CHARACTERS.length());
      sb.append(CHARACTERS.charAt(index));
    }
    return sb.toString();
  }
}
