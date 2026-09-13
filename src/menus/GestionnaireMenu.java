package menus;

import java.util.Scanner;
import model.Client;
import model.Compte;
import model.Gestionnaire;
import model.Personne;
import service.Help;
import service.UserManagement;

public class GestionnaireMenu {
  public static boolean Menu(Scanner scanner) {
    Personne user = UserManagement.currentUser;
    if (!(user instanceof Gestionnaire gestionnaire)) {
      System.out.println("Cannot open gestionnaire menu: not a gestionnaire account.");
      return false;
    }

    Help.clearScreen();
    while (true) {
      Help.clearScreen();
      gestionnaireOptions(gestionnaire);
      int choice = Help.choiceValidation(scanner);
      try {
        switch (choice) {
          case 0 -> {
            System.out.println("Exit...");
            return true;
          }
          case 1 -> {
            showClients();
            int clientId = readClientId(scanner);
            Client client = UserManagement.findClient(clientId);
            if (client == null) {
              System.out.println("Client not found: id=" + clientId);
              Help.pause(scanner);
              break;
            }
            String type = readType(scanner);
            double solde = readSolde(scanner);
            gestionnaire.createAccount(client, type, solde);
            Help.pause(scanner);
          }
          case 2 -> {
            showClients();
            int clientId = readClientId(scanner);
            Client client = UserManagement.findClient(clientId);
            if (client == null) {
              System.out.println("Client not found: id=" + clientId);
              Help.pause(scanner);
              break;
            }
            showClientAccounts(client);
            int accountId = readAccountId(scanner);
            gestionnaire.closeAccount(client, accountId);
            Help.pause(scanner);
          }
          case 3 -> {
            showClients();
            int clientId = readClientId(scanner);
            Client client = UserManagement.findClient(clientId);
            if (client == null) {
              System.out.println("Client not found: id=" + clientId);
              Help.pause(scanner);
              break;
            }
            String firstName = readName(scanner, "New first name (Enter to keep): ");
            String lastName = readName(scanner, "New last name (Enter to keep): ");
            String email = readName(scanner, "New email (Enter to keep): ");
            gestionnaire.updateClient(client, firstName, lastName, email);
            Help.pause(scanner);
          }
          case 4 -> {
            showClients();
            int clientId = readClientId(scanner);
            Client client = UserManagement.findClient(clientId);
            if (client == null) {
              System.out.println("Client not found: id=" + clientId);
              Help.pause(scanner);
              break;
            }
            showClientAccounts(client);
            Help.pause(scanner);
          }
          case 5 -> {
            System.out.println("logout..");
            UserManagement.currentUser = null;
            return false;
          }
          case 6 -> {
            showClients();
            int clientId = readClientId(scanner);
            Client client = UserManagement.findClient(clientId);
            if (client == null) {
              System.out.println("Client not found: id=" + clientId);
              Help.pause(scanner);
              break;
            }
            showClientAccounts(client);
            int accountId = readAccountId(scanner);
            Compte compte = null;
            for (Compte c : client.accounts) {
              if (c.getId() == accountId) {
                compte = c;
              }
            }
            if (compte == null) {
              System.out.println("Account not found: id=" + accountId);
              Help.pause(scanner);
              break;
            }
            service.AccountManagement.showTransactions(compte);
            Help.pause(scanner);
          }
          default -> {
            System.out.println("xx invalide choice xx");
            Help.pause(scanner);
          }
        }
      } catch (IllegalArgumentException | IllegalStateException e) {
        System.out.println("Operation failed: " + e.getMessage());
        Help.pause(scanner);
      }
    }
  }

  static void gestionnaireOptions(Gestionnaire gestionnaire) {
    System.out.println("Welcome " + gestionnaire.getFirstName() + " " + gestionnaire.getLastName() + " in NexaBank");
    System.out.println("1.Create account");
    System.out.println("2.Close account");
    System.out.println("3.Update client info");
    System.out.println("4.Show client accounts");
    System.out.println("6.Show history");
    System.out.println("5.Logout");
    System.out.println("0.exit");
    System.out.print("Choice:");
  }

  static void showClients() {
    System.out.println("Clients list:");
    for (Personne p : UserManagement.users) {
      if (p instanceof Client client) {
        System.out.println("Id: " + client.getId()
            + ", Name: " + client.getFirstName() + " " + client.getLastName()
            + ", Email: " + client.getEmail());
      }
    }
  }

  static void showClientAccounts(Client client) {
    if (client.accounts.isEmpty()) {
      System.out.println("No accounts for client id=" + client.getId());
      return;
    }
    for (Compte c : client.accounts) {
      System.out.println("Id: " + c.getId()
          + ", N°: " + c.getNumeroCompte()
          + ", Solde: " + c.getSolde() + " MAD");
    }
  }

  static int readClientId(Scanner scanner) {
    System.out.print("Client id: ");
    String line = scanner.nextLine();
    try {
      return Integer.parseInt(line.trim());
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Invalid client id.");
    }
  }

  static int readAccountId(Scanner scanner) {
    System.out.print("Account id: ");
    String line = scanner.nextLine();
    try {
      return Integer.parseInt(line.trim());
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Invalid account id.");
    }
  }

  static String readName(Scanner scanner, String prompt) {
    System.out.print(prompt);
    String line = scanner.nextLine();
    return line.trim();
  }

  static String readType(Scanner scanner) {
    System.out.print("Type (1=Courant, 2=Epargne): ");
    String line = scanner.nextLine();
    if (line.trim().equals("2")) {
      return "Epargne";
    }
    if (line.trim().equals("1")) {
      return "Courant";
    }
    throw new IllegalArgumentException("Invalid type, choose 1 or 2.");
  }

  static double readSolde(Scanner scanner) {
    System.out.print("First solde: ");
    String line = scanner.nextLine();
    try {
      double solde = Double.parseDouble(line.trim());
      if (solde < 0) {
        throw new IllegalArgumentException("Solde cannot be negative.");
      }
      return solde;
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Invalid solde, enter a number.");
    }
  }
}
