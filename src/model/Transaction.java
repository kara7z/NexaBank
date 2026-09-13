package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
  private int id;
  private String type;
  private double montant;
  private LocalDateTime date;
  private Compte compteSource;
  private Compte compteDestination;
  private static int nextId = 1;

  public Transaction(String type, double montant, Compte source, Compte destination) {
    this.id = nextId;
    nextId++;
    this.type = type;
    this.montant = montant;
    this.date = LocalDateTime.now();
    this.compteSource = source;
    this.compteDestination = destination;
  }

  public int getId() {
    return id;
  }

  public String getType() {
    return type;
  }

  public double getMontant() {
    return montant;
  }

  public LocalDateTime getDate() {
    return date;
  }

  public Compte getCompteSource() {
    return compteSource;
  }

  public Compte getCompteDestination() {
    return compteDestination;
  }

  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Transaction t)) {
      return false;
    }
    return this.id == t.id;
  }

  public int hashCode() {
    return id;
  }

  public String toString() {
    String source = "null";
    String dest = "null";
    if (compteSource != null) {
      source = "C" + compteSource.getNumeroCompte();
    }
    if (compteDestination != null) {
      dest = "C" + compteDestination.getNumeroCompte();
    }
    return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + " " + type + " " + montant + " " + source + " " + dest;
  }
}
