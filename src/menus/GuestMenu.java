package menus;

import java.util.Scanner;
import java.io.Console;
import model.Personne;
import service.UserManagement;
import service.Help;

public class GuestMenu {
  public static void start(Scanner scanner) {
    boolean isRunning = true;
    while (isRunning) {
      Help.clearScreen();
      guestOptions();

      int choice = Help.choiceValidation(scanner);
      switch (choice) {
        case 0 -> {
          exit();
          isRunning = false;
          return;
        }
        case 1 -> {
          boolean exitRequested = login(scanner);
          if (exitRequested) {
            return;
          }
        }
        default -> {
          System.out.println("xx invalide choice xx");
          Help.pause(scanner);
        }
      }
    }

  }

  static void exit() {
    System.out.println("Exit...");
  }

  static boolean login(Scanner scanner) {
    Help.clearScreen();
    System.out.println("=== LoginPage ===");
    System.out.print("Email:");
    String email = scanner.nextLine();
    String password;
    try {
      Console console = System.console();
      if (console == null) {
        throw new IllegalStateException("No console available.");
      }
      char[] consolePassword = console.readPassword("Password:");
      if (consolePassword == null) {
        throw new IllegalStateException("No password entered.");
      }
      password = new String(consolePassword);
    } catch (IllegalStateException | NullPointerException e) {
      System.out.print("Password (fallback):");
      password = scanner.nextLine();
    }

    Personne user = UserManagement.login(email, password);
    if (user != null) {
      System.out.println("valide informations");
      UserManagement.currentUser = user;
      System.out.println(UserManagement.currentUser.getEmail());
      if (UserManagement.isClient()) {
        boolean exitRequested = ClientMenu.Menu(scanner);
        if (exitRequested) {
          return true;
        }
      } else {
        boolean exitRequested = GestionnaireMenu.Menu(scanner);
        if (exitRequested) {
          return true;
        }
      }
    } else {
      System.out.println("xx invalide informations xx");
      Help.pause(scanner);
    }
    return false;
  }

  static void guestOptions() {
    System.out.println("Welcome in NexaBank");
    System.out.println("1.login");
    System.out.println("0.exit");
    System.out.print("Choice:");
  }

  static void loginMenu() {
    System.out.println("=== LoginPage ===");
    System.out.println("Email:");
    System.out.println("1.login");
    System.out.println("0.exit");
    System.out.print("Choice:");
  }

}
