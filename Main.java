class Main {
  public static void main(String[] args) {
    Gestionnaire gestionnaire = new Gestionnaire(1, "ja", "tst1",
        "kara@gmail.co", "j", 23);
    Client client1 = new Client(1, "ja", "tst1", "kara@gmail.co", "test");
    Compte compte1 = new CompteEpargne(1, 325252, 500);
    gestionnaire.addAccount(compte1);

    accountManagement.showAccounts();
  }

}
