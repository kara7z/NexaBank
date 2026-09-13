package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import model.Compte;
import model.Transaction;

public class ReleveService {

  public static String getPath(Compte compte) {
    return "releves/releve_C" + compte.getNumeroCompte() + ".txt";
  }

  public static void saveTransaction(Compte compte, Transaction t) {
    try {
      Path path = Paths.get(getPath(compte));
      Files.createDirectories(path.getParent());
      if (Files.notExists(path)) {
        String header = "Date Type Montant Compte Source Compte Destination";
        Files.writeString(path, header + System.lineSeparator(), StandardOpenOption.CREATE);
      }
      Files.writeString(path, t.toString() + System.lineSeparator(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    } catch (IOException e) {
      System.out.println("Releve write failed: " + e.getMessage());
    }
  }

  public static void readReleve(Compte compte) {
    try {
      Path path = Paths.get(getPath(compte));
      if (Files.notExists(path)) {
        System.out.println("No releve yet.");
        return;
      }
      List<String> lines = Files.readAllLines(path);
      for (String line : lines) {
        System.out.println(line);
      }
    } catch (IOException e) {
      System.out.println("Releve read failed: " + e.getMessage());
    }
  }
}
