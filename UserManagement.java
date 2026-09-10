import java.util.ArrayList;

class UserManagement {
  static ArrayList<Personne> users = new ArrayList<>();

  static void addUser(Personne user) {
    users.add(user);
  }

  static Personne findUser(int userId) {
    for (Personne user : users) {
      if (userId == user.getId()) {
        return user;
      }
    }
    return null;
  }

  static boolean login(String email, String password) {
    boolean isAvailable = false;
    for (Personne user : users) {
      if (user.getEmail().trim().equals(email) && user.getPassword().equals(password)) {
        isAvailable = true;
      }
    }
    return isAvailable;
  }
}
