import menus.GuestMenu;
import model.Client;
import model.Gestionnaire;
import service.UserManagement;

public class Main {
  public static void main(String[] args) {
    GuestMenu menu = new GuestMenu();
    Client user1 = new Client(1, "kara", "oussama", "kara@gmail.com", "password");
    Client user2 = new Client(2, "kara", "oussama", "kara.oussama@gmail.com", "password");
    Gestionnaire user3 = new Gestionnaire(3, "Gestionnaire", "j", "gest@gmail.com", "password");
    UserManagement.addUser(user1, user2, user3);

    int size = UserManagement.users.size();
    System.out.println("They are " + size + " users");
    menu.start();

  }

}
