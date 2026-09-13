package service;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import model.Client;
import model.Compte;
import model.Personne;

public class AccountManagement {

  public static void addAccount(Compte account) {
    if (account == null) {
      throw new IllegalArgumentException("Account cannot be null.");
    }
    Personne owner = UserManagement.findUser(account.getClientId());
    if (!(owner instanceof Client client)) {
      throw new IllegalStateException(
          "Client not found for clientId=" + account.getClientId());
    }
    for (Compte existing : client.accounts) {
      if (existing.getId() == account.getId()
          || existing.getNumeroCompte() == account.getNumeroCompte()) {
        throw new IllegalArgumentException(
            "Account already exists (id=" + account.getId() + ").");
      }
    }
    client.accounts.add(account);
  }

  public static void removeAccount(Client client, int accountId) {
    if (client == null) {
      throw new IllegalArgumentException("Client cannot be null.");
    }
    for (int i = 0; i < client.accounts.size(); i++) {
      Compte c = client.accounts.get(i);
      if (c.getId() == accountId) {
        client.accounts.remove(i);
        return;
      }
    }
    throw new IllegalArgumentException("Account not found: id=" + accountId);
  }

  public static int getNextAccountId() {
    int maxId = 110;
    for (Personne p : UserManagement.users) {
      if (p instanceof Client client) {
        for (Compte c : client.accounts) {
          if (c.getId() > maxId) {
            maxId = c.getId();
          }
        }
      }
    }
    return maxId + 1;
  }

  public static int getNextNumeroCompte() {
    int maxNumero = 1010;
    for (Personne p : UserManagement.users) {
      if (p instanceof Client client) {
        for (Compte c : client.accounts) {
          if (c.getNumeroCompte() > maxNumero) {
            maxNumero = c.getNumeroCompte();
          }
        }
      }
    }
    return maxNumero + 1;
  }

  public static void showAccounts(Client client) {
    if (client == null) {
      throw new IllegalArgumentException("Client cannot be null.");
    }
    if (client.accounts.isEmpty()) {
      throw new IllegalStateException("No accounts found.");
    }
    for (Compte compte : client.accounts) {
      System.out.println(
          "Id: " + compte.getId()
              + ", N°: " + compte.getNumeroCompte()
              + ", Solde: " + compte.getSolde() + " MAD");
    }
  }

  public static Compte findAccount(Client client, int id) {
    if (client == null) {
      throw new IllegalArgumentException("Client cannot be null.");
    }
    for (Compte c : client.accounts) {
      if (c.getId() == id) {
        return c;
      }
    }
    return null;
  }

  public static Compte findAccountByNumero(int numeroCompte) {
    for (Personne p : UserManagement.users) {
      if (p instanceof Client client) {
        for (Compte c : client.accounts) {
          if (c.getNumeroCompte() == numeroCompte) {
            return c;
          }
        }
      }
    }
    return null;
  }

  public static ArrayList<Compte> getClientAccounts(Client client) {
    if (client == null) {
      throw new IllegalArgumentException("Client cannot be null.");
    }
    return client.accounts;
  }

  public static void deposit(Client client, int accountId, double amount) {
    if (client == null) {
      throw new IllegalArgumentException("Client cannot be null.");
    }
    Compte c = findAccount(client, accountId);
    if (c == null) {
      throw new NoSuchElementException("Account not found: id=" + accountId);
    }
    c.deposit(amount);
  }

  public static void withdraw(Client client, int accountId, double amount) {
    if (client == null) {
      throw new IllegalArgumentException("Client cannot be null.");
    }
    Compte c = findAccount(client, accountId);
    if (c == null) {
      throw new NoSuchElementException("Account not found: id=" + accountId);
    }
    c.withdraw(amount);
  }

  public static void transfer(Client sender, int senderAccountId, int receiverNumero, double amount) {
    if (sender == null) {
      throw new IllegalArgumentException("Sender cannot be null.");
    }
    Compte s = findAccount(sender, senderAccountId);
    if (s == null) {
      throw new NoSuchElementException("Sender account not found: id=" + senderAccountId);
    }
    Compte r = findAccountByNumero(receiverNumero);
    if (r == null) {
      throw new NoSuchElementException("Receiver account not found: N°=" + receiverNumero);
    }
    if (amount <= 0) {
      throw new IllegalArgumentException("Amount must be > 0.");
    }
    if (amount > s.getSolde()) {
      throw new IllegalStateException(
          "Insufficient funds: solde=" + s.getSolde() + ", amount=" + amount);
    }
    s.withdraw(amount);
    r.deposit(amount);
  }

}
