package model;

import service.AccountManagement;
import service.UserManagement;

public class Gestionnaire extends Personne {
  public Gestionnaire(int id, String first_name, String last_name, String email, String password) {
    super(id, first_name, last_name, email, password);
  }

  public void addAccount(Compte account) {
    AccountManagement.addAccount(account);
    System.out.println("Account N°" + account.getNumeroCompte() + " added.");
  }

  public void createAccount(Client client, String type, double solde) {
    if (client == null) {
      throw new IllegalArgumentException("Client cannot be null.");
    }
    if (solde < 0) {
      throw new IllegalArgumentException("Solde cannot be negative.");
    }
    int newId = AccountManagement.getNextAccountId();
    int newNumero = AccountManagement.getNextNumeroCompte();
    Compte account;
    if (type.equals("Epargne")) {
      account = new CompteEpargne(newId, client.getId(), newNumero, solde);
    } else {
      account = new CompteCourant(newId, client.getId(), newNumero, solde);
    }
    AccountManagement.addAccount(account);
    System.out.println("Account N°" + account.getNumeroCompte() + " added.");
  }

  public void closeAccount(Client client, int accountId) {
    AccountManagement.removeAccount(client, accountId);
    System.out.println("Account id=" + accountId + " closed.");
  }

  public void updateClient(Client client, String firstName, String lastName, String email) {
    UserManagement.updateClientInfo(client, firstName, lastName, email);
    System.out.println("Client id=" + client.getId() + " updated.");
  }
}
