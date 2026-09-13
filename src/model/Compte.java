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
    this.historiqueTransactions = new HashSet<>();
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

  public HashSet<Transaction> getHistorique() {
    return historiqueTransactions;
  }

  public void addTransaction(Transaction t) {
    historiqueTransactions.add(t);
  }

  public String getType() {
    return "Compte";
  }

  public boolean canTransfer() {
    return true;
  }

  public void withdraw(double amount) {
    if (amount <= 0) {
      throw new IllegalArgumentException("Amount must be > 0.");
    }
    if (amount > solde) {
      throw new IllegalStateException(
          "Insufficient funds: solde=" + solde + ", amount=" + amount);
    }
    solde -= amount;
  }

  public void deposit(double amount) {
    if (amount <= 0) {
      throw new IllegalArgumentException("Amount must be > 0.");
    }
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
