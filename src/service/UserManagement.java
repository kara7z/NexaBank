package service;

import java.util.ArrayList;
import model.Client;
import model.Gestionnaire;
import model.Personne;

public class UserManagement {
  public static ArrayList<Personne> users = new ArrayList<>();
  public static Personne currentUser;

  public static void addUser(Personne user) {
    users.add(user);
  }

  public static void addUser(Personne... userx) {
    for (Personne user : userx) {
      users.add(user);
    }
  }

  public static Personne findUser(int userId) {
    for (Personne user : users) {
      if (userId == user.getId()) {
        return user;
      }
    }
    return null;
  }

  public static Personne login(String email, String password) {
    for (Personne user : users) {
      if (user.getEmail().trim().equals(email) && user.getPassword().equals(password)) {
        return user;
      }
    }
    return null;
  }

  public static boolean isClient() {
    return (currentUser instanceof Client);
  }

  public static boolean isGestionnaire() {
    return (currentUser instanceof Gestionnaire);
  }
}
