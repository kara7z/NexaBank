import java.util.ArrayList;

class Client extends Personne {
  ArrayList<Compte> accounts = new ArrayList<>();

  Client(int id, String first_name, String last_name, String email, String password) {
    super(id, first_name, last_name, email, password);
  }

  void deposit(int amount) {
    AccountManagement.deposit(id, amount);
  }

}
