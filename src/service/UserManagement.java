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

  public static Client findClient(int clientId) {
    for (Personne user : users) {
      if (user instanceof Client client) {
        if (client.getId() == clientId) {
          return client;
        }
      }
    }
    return null;
  }

  public static void updateClientInfo(Client client, String firstName, String lastName, String email) {
    if (client == null) {
      throw new IllegalArgumentException("Client cannot be null.");
    }
    if (firstName != null && !firstName.isEmpty()) {
      client.setFirstName(firstName);
    }
    if (lastName != null && !lastName.isEmpty()) {
      client.setLastName(lastName);
    }
    if (email != null && !email.isEmpty()) {
      client.setEmail(email);
    }
  }

  public static boolean isClient() {
    return (currentUser instanceof Client);
  }

  public static boolean isGestionnaire() {
    return (currentUser instanceof Gestionnaire);
  }
}
