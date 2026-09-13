import java.util.Scanner;
import menus.GuestMenu;
import model.Client;
import model.CompteCourant;
import model.CompteEpargne;
import model.Gestionnaire;
import service.UserManagement;

public class Main {
  public static void main(String[] args) {
    Client user1 = new Client(1, "kara", "oussama", "kara@gmail.com", "password");
    Client user2 = new Client(2, "kara", "oussama", "kara.oussama@gmail.com", "password");
    Client user4 = new Client(4, "ch", "sa", "ka@gmail.com", "password");
    Gestionnaire user3 = new Gestionnaire(3, "Gestionnaire", "j", "gest@gmail.com", "password");
    UserManagement.addUser(user1, user2, user3,user4);

    user3.addAccount(new CompteCourant(101, 1, 1001, 1500.0));
    user3.addAccount(new CompteEpargne(102, 1, 1002, 2500.75));
    user3.addAccount(new CompteCourant(107, 2, 1007, 900.0));
    user3.addAccount(new CompteEpargne(108, 2, 1008, 4500.0));
    user3.addAccount(new CompteCourant(109, 2, 1009, 120.0));
    user3.addAccount(new CompteCourant(110, 4, 1010, 220.0));

    int size = UserManagement.users.size();
    System.out.println("They are " + size + " users");
    Scanner scanner = new Scanner(System.in);
    try {
      GuestMenu.start(scanner);
    } finally {
      scanner.close();
    }

  }

}
