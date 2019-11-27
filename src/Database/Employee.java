package Database;

import com.sun.xml.internal.bind.v2.model.core.ID;

/**
 * I'm gonna start out by making this with a physical class, but depending on how much the class
 * ends up doing, we might wing it without a class. We'll see
 */
public class Employee {

  private int id;
  private String username;
  private String name;
  private boolean isManager;

  public Employee(int id, String username, String name, boolean isManager) {
    this.id = id;
    this.username = username;
    this.name = name;
    this.isManager = isManager;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public String getName() {
    return name;
  }

  public boolean isManager() {
    return isManager;
  }

  public String toString() {
    return String.format("(%d) : %s", id, name);
  }
}

