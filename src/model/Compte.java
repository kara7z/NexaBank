package model;

import java.util.HashSet;

public abstract class Compte {
  private int id;
  private int clientId;
  private int numeroCompte;
  private double solde;
  private HashSet<Transaction> historiqueTransactions;

  public Compte(int id, int clientId, int numeroCompte, double solde) {
    this.id = id;
    this.clientId = clientId;
    this.numeroCompte = numeroCompte;
    this.solde = solde;
  };

  public int getId() {
    return id;
  }

  public int getNumeroCompte() {
    return numeroCompte;
  }

  public double getSolde() {
    return solde;
  }

  public int getClientId() {
    return clientId;
  }

  public void withdraw(double amount) {
    solde -= amount;
  }

  public void deposit(double amount) {
    solde += amount;
  }

  public void transfer(double amount) {
    solde += amount;
  }

  public String toString() {
    return "Compte{" +
        "id=" + id +
        ", numero='" + numeroCompte + '\'' +
        ", solde=" + solde +
        '}';
  }
}
