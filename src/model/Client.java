package model;

import java.util.ArrayList;
import service.AccountManagement;

public class Client extends Personne {
  public ArrayList<Compte> accounts = new ArrayList<>();

  public Client(int id, String first_name, String last_name, String email, String password) {
    super(id, first_name, last_name, email, password);
  }

  public void deposit(int amount) {
    AccountManagement.deposit(id, amount);
  }

}
