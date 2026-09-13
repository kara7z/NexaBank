package menus;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import model.Personne;
import model.Client;
import model.Compte;
import service.AccountManagement;
import service.Help;
import service.UserManagement;

public class ClientMenu {
  public static boolean Menu(Scanner scanner) {
    Personne user = UserManagement.currentUser;
    if (!(user instanceof Client client)) {
      System.out.println("Cannot open client menu: not a client account.");
      return false;
    }

    if (client.accounts.isEmpty()) {
      System.out.println("No accounts found for " + client.getFirstName() + ". Contact your gestionnaire.");
      return false;
    }

    Compte selected = null;
    if (client.accounts.size() == 1) {
      selected = client.accounts.get(0);
      System.out.println("Account N°" + selected.getNumeroCompte() + " selected.");
    }

    Help.clearScreen();
    while (true) {
      while (selected == null) {
        Help.clearScreen();
        showAccountSelection(client);
        int sel = Help.choiceValidation(scanner);
        if (sel == 0) {
          System.out.println("Exit...");
          return true;
        }
        if (sel == 5) {
          System.out.println("logout..");
          UserManagement.currentUser = null;
          return false;
        }
        if (sel >= 1 && sel <= client.accounts.size()) {
          selected = client.accounts.get(sel - 1);
          System.out.println(
              "Account N°" + selected.getNumeroCompte() + " selected.");
          Help.pause(scanner);
        } else {
          System.out.println("xx invalide choice xx");
          Help.pause(scanner);
        }
      }

      if (UserManagement.currentUser == null) {
        System.out.println("Session expired, back to login.");
        return false;
      }
      Help.clearScreen();
      clientOptions(selected);
      int choice = Help.choiceValidation(scanner);
      try {
        switch (choice) {
          case 0 -> {
            System.out.println("Exit...");
            return true;
          }
          case 1 -> {
            double amount = readAmount(scanner, "Deposit amount:");
            AccountManagement.deposit(client, selected.getId(), amount);
            System.out.println("New sold: " + selected.getSolde() + " MAD");
            Help.pause(scanner);
          }
          case 2 -> {
            double amount = readAmount(scanner, "Withdraw amount:");
            AccountManagement.withdraw(client, selected.getId(), amount);
            System.out.println("New sold: " + selected.getSolde() + " MAD");
            Help.pause(scanner);
          }
          case 3 -> {
            int receiverNumero = readAccountNumero(scanner);
            double amount = readAmount(scanner, "Transfer amount:");
            AccountManagement.transfer(client, selected.getId(), receiverNumero, amount);
            System.out.println("New sold: " + selected.getSolde() + " MAD");
            Help.pause(scanner);
          }
          case 4 -> {
            System.out.println(
                "N° " + selected.getNumeroCompte() + " Sold: " + selected.getSolde() + " MAD");
            Help.pause(scanner);
          }
          case 5 -> {
            System.out.println("logout..");
            UserManagement.currentUser = null;
            return false;
          }
          case 6 -> {
            if (client.accounts.size() <= 1) {
              System.out.println("xx invalide choice xx");
              Help.pause(scanner);
            } else {
              selected = null;
            }
          }
          case 7 -> {
            AccountManagement.showTransactions(selected);
            Help.pause(scanner);
          }
          default -> {
            System.out.println("xx invalide choice xx");
            Help.pause(scanner);
            break;
          }
        }
      } catch (IllegalArgumentException | IllegalStateException | NoSuchElementException e) {
        System.out.println("Operation failed: " + e.getMessage());
        Help.pause(scanner);
      }
    }
  }

  static ArrayList<Compte> getAccounts() {
    Personne user = UserManagement.currentUser;
    if (user instanceof Client client) {
      return client.accounts;
    }
    return new ArrayList<>();
  }

  static void showAccountSelection(Client client) {
    System.out.println(
        "Welcome " + client.getFirstName() + " " + client.getLastName() + " in NexaBank");
    System.out.println("Select an account:");
    for (int i = 0; i < client.accounts.size(); i++) {
      Compte c = client.accounts.get(i);
      System.out.println(
          (i + 1) + ". N°" + c.getNumeroCompte() + " - Sold: " + c.getSolde() + " MAD");
    }
    System.out.println("5.Logout");
    System.out.println("0.exit");
    System.out.print("Choice:");
  }

  static void accountsSelection() {
    Personne user = UserManagement.currentUser;
    if (user instanceof Client client) {
      showAccountSelection(client);
    } else {
      System.out.println("Not a client account.");
    }
  }

  static void clientOptions(Compte selected) {
    String firstName = UserManagement.currentUser.getFirstName();
    String lastName = UserManagement.currentUser.getLastName();
    char space = ' ';
    System.out.println("Welcome " + firstName + space + lastName + " in NexaBank");
    System.out.println("[Account N°" + selected.getNumeroCompte() + " - Sold: " + selected.getSolde() + " MAD]");
    System.out.println("1.Deposit");
    System.out.println("2.Withdraw");
    System.out.println("3.Transfer");
    System.out.println("4.See Sold");
    System.out.println("7.History");
    if (getAccounts().size() > 1) {
      System.out.println("6.Change account");
    }
    System.out.println("5.Logout");
    System.out.println("0.exit");
    System.out.print("Choice:");
  }

  static void clientOptions() {
    Personne user = UserManagement.currentUser;
    if (user instanceof Client client && !client.accounts.isEmpty()) {
      clientOptions(client.accounts.get(0));
      return;
    }
    String firstName = UserManagement.currentUser.getFirstName();
    String lastName = UserManagement.currentUser.getLastName();
    char space = ' ';
    System.out.println("Welcome " + firstName + space + lastName + " in NexaBank");
    System.out.println("1.Deposit");
    System.out.println("2.Withdraw");
    System.out.println("3.Transfer");
    System.out.println("4.See Sold");
    System.out.println("7.History");
    System.out.println("5.Logout");
    System.out.println("0.exit");
    System.out.print("Choice:");
  }

  static double readAmount(Scanner scanner, String prompt) {
    System.out.print(prompt + " ");
    String line;
    try {
      line = scanner.nextLine();
    } catch (java.util.NoSuchElementException | IllegalStateException e) {
      throw new IllegalArgumentException("Input unavailable.");
    }
    try {
      double amount = Double.parseDouble(line.trim());
      if (amount <= 0) {
        throw new IllegalArgumentException("Amount must be > 0.");
      }
      return amount;
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Invalid amount, enter a number.");
    }
  }

  static int readAccountNumero(Scanner scanner) {
    return readAccountNumero(scanner, "Receiver account N°: ");
  }

  static int readAccountNumero(Scanner scanner, String prompt) {
    System.out.print(prompt);
    String line;
    try {
      line = scanner.nextLine();
    } catch (java.util.NoSuchElementException | IllegalStateException e) {
      throw new IllegalArgumentException("Input unavailable.");
    }
    try {
      return Integer.parseInt(line.trim());
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Invalid account number.");
    }
  }
}
