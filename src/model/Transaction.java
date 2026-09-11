package model;

import java.time.LocalDateTime;

public class Transaction {
  private int id;
  private String type;
  private double montant;
  private LocalDateTime date;
  private Compte destinationAccount;

  public Transaction(int id, String type, double montant, LocalDateTime date, Compte compte) {
    this.id = id;
    this.type = type;
    this.montant = montant;
    this.date = date;
    this.destinationAccount = compte;
  }
}
