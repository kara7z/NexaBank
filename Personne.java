abstract class Personne {
  protected int id;
  protected String first_name;
  protected String last_name;
  protected String email;
  protected String password;

  Personne(int id, String first_name, String last_name, String email, String password) {
    this.id = id;
    this.first_name = first_name;
    this.last_name = last_name;
    this.email = email;
    this.password = password;
  }

  public int getId() {
    return id;
  }

  String getEmail() {
    return email;
  }

  String getPassword() {
    return password;
  }
}
