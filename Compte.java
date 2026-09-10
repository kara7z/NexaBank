import java.util.HashSet;

abstract class Compte {
  private int id;
  private int clientId;
  private int numeroCompte;
  private double solde;
  private HashSet<Transaction> historiqueTransactions;

  Compte(int id, int clientId, int numeroCompte, double solde) {
    this.id = id;
    this.clientId = clientId;
    this.numeroCompte = numeroCompte;
    this.solde = solde;
  };

  int getId() {
    return id;
  }

  int getNumeroCompte() {
    return numeroCompte;
  }

  public double getSolde() {
    return solde;
  }
  int getClientId(){
    return clientId;
  }

  void withdraw(double amount) {
    solde -= amount;
  }

  void deposit(double amount) {
    solde += amount;
  }

  void transfer(double amount) {
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
