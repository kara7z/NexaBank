package menus;

import java.util.Scanner;
import java.io.Console;
import model.Personne;
import service.UserManagement;
import service.Help;

public class GuestMenu {
  public void start() {
    Scanner scanner = new Scanner(System.in);
    boolean isRunning = true;
    while (isRunning) {
      guestOptions();

      int choice = Help.choiceValidation(scanner);
      switch (choice) {
        case 0 -> {
          System.out.println("Exit...");
          isRunning = false;
          return;
        }
        case 1 -> {
          System.out.println();
          System.out.println("=== LoginPage ===");
          System.out.print("Email:");
          String email = scanner.nextLine();
          Console console = System.console();
          char[] consolePassword = console.readPassword("Password:");
          String password = new String(consolePassword);

          Personne user = UserManagement.login(email, password);
          if (user != null) {
            System.out.println("valide informations");
            UserManagement.currentUser = user;
            System.out.println(UserManagement.currentUser.getEmail());
            if (UserManagement.isClient()) {
              ClientMenu.Menu();
              return;
            } else {
              System.out.println("Gestionnaire");
            }
          } else {
            System.out.println("xx invalide informations xx");
          }
        }
        default -> System.out.println("xx invalide choice xx");
      }
    }
    scanner.close();
  }

  void guestOptions() {
    System.out.println("Welcome in NexaBank");
    System.out.println("1.login");
    System.out.println("0.exit");
    System.out.print("Choice:");
  }

  void loginMenu() {
    System.out.println("===LoginPage===");
    System.out.println("Email:");
    System.out.println("1.login");
    System.out.println("0.exit");
    System.out.print("Choice:");
  }

  void option1() {

  }

  void option2() {

  }

  void option3() {

  }

  void option4() {

  }
}
