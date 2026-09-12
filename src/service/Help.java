package service;

import java.util.Scanner;

public class Help {

  public static <T> boolean inputValidation(Object input, Class<T> type) {
    return type.isInstance(input);
  }

  public static void clearScreen() {
    try {
      String os = System.getProperty("os.name", "").toLowerCase();
      if (os.contains("win")) {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
      } else {
        System.out.print("\033[H\033[2J");
        System.out.flush();
      }
    } catch (Exception e) {
      System.out.println("\n".repeat(50));
    }
  }

  public static void pause(Scanner scanner) {
    System.out.print("Press Enter to continue...");
    try {
      scanner.nextLine();
    } catch (Exception ignored) {
    }
  }
  public static int choiceValidation(Scanner scanner) {
    try {
      if (!scanner.hasNextInt()) {
        scanner.next();
        return -1;
      }
      int choice = scanner.nextInt();
      scanner.nextLine();
      return choice;
    } catch (java.util.NoSuchElementException | IllegalStateException e) {
      return -1;
    }
  }
}
