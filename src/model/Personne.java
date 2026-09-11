package model;

public abstract class Personne {
  protected int id;
  protected String first_name;
  protected String last_name;
  protected String email;
  protected String password;

  public Personne(int id, String first_name, String last_name, String email, String password) {
    this.id = id;
    this.first_name = first_name;
    this.last_name = last_name;
    this.email = email;
    this.password = password;
  }

  public int getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }
}
