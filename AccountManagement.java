import java.util.ArrayList;

class AccountManagement {
  static ArrayList<Compte> accounts = new ArrayList<>();

  static void addAccount(Compte account) {
    accounts.add(account);
  }

  static void showAccounts() {
    for (Compte compte : accounts) {
      System.out.println("Id: " + compte.getId() + ", Solde: " + compte.getSolde() + " MAD");
    }
  }

  static int findAccountNum(int accountNum) {
    for (int i = 0; i < accounts.size(); i++) {
      if (accounts.get(i).getNumeroCompte() == accountNum) {
        return i;
      }
    }
    return -1;
  }

  static int findAccount(int id) {
    for (int i = 0; i < accounts.size(); i++) {
      if (accounts.get(i).getId() == id) {
        return i;
      }
    }
    return -1;
  }

  static void deposit(int id, double amount) {
    int accountIndex = findAccount(id);

    if (amount > 0 && accountIndex != -1) {
      accounts.get(accountIndex).deposit(amount);
    } else {
      System.out.println("Error");
    }
  }

  static void withdraw(int id, double amount) {
    int accountIndex = findAccount(id);

    if (amount > accounts.get(accountIndex).getSolde() && accountIndex != -1) {
      accounts.get(accountIndex).withdraw(amount);
    } else {
      System.out.println("Error");
    }
  }

  static void transfer(int senderId, int accountNum, double amount) {
    int senderIndex = findAccount(senderId);
    int recieverIndex = findAccountNum(accountNum);

    if (amount > accounts.get(senderIndex).getSolde() && senderIndex != -1) {
      accounts.get(senderIndex).withdraw(amount);
      accounts.get(recieverIndex).transfer(amount);
    } else {
      System.out.println("Error");
    }
  }

}
