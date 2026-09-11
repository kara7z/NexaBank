package model;

import service.AccountManagement;

public class Gestionnaire extends Personne {
  public Gestionnaire(int id, String first_name, String last_name, String email, String password) {
    super(id, first_name, last_name, email, password);
  }

  public void addAccount(Compte account) {
    AccountManagement.addAccount(account);
  }
}
