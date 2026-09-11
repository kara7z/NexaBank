package menus;

import java.util.Scanner;

import model.Personne;
import service.Help;
import service.UserManagement;

public class ClientMenu {
  public static void Menu() {
    Scanner scanner = new Scanner(System.in);
    clientOptions();
    int choice = Help.choiceValidation(scanner);
    if (choice < 0 || choice > 5) {

    }
    switch (choice) {
      case 0 -> {
        System.out.println("Exit...");
        return;
      }
      case 1 -> {
        
      }
      case 2 -> {

      }
      case 3 -> {
          
      }
      case 4 -> {
        Personne user = UserManagement.currentUser;
         
      }
      case 5 -> {
          
      }
      default -> System.out.println("xx invalide choice xx");
    }
  }

  static void clientOptions() {
    String firstName = UserManagement.currentUser.getFirstName();
    String lastName = UserManagement.currentUser.getLastName();
    char space = ' ';
    System.out.println("Welcome " + firstName + space + lastName + " in NexaBank");
    System.out.println("1.Deposit");
    System.out.println("2.Withdraw");
    System.out.println("3.Transfer");
    System.out.println("4.See Sold");
    System.out.println("5.Logout");
    System.out.println("0.exit");
    System.out.print("Choice:");
  }
}
