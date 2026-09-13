package model;

public class CompteEpargne extends Compte {

  public CompteEpargne(int id, int clientId, int numeroCompte, double solde) {
    super(id, clientId, numeroCompte, solde);
  }

  public String getType() {
    return "Epargne";
  }

  public boolean canTransfer() {
    return false;
  }
}
