package service;

import java.util.Scanner;

public class Help {

  public static <T> boolean inputValidation(Object input, Class<T> type) {
    return type.isInstance(input);
  }

  public static int choiceValidation(Scanner scanner) {
    if (!scanner.hasNextInt()) {
      scanner.next();
      return -1;
    }
    int choice = scanner.nextInt();
    scanner.nextLine();
    return choice;
  }
}
