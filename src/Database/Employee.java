package Database;

/**
 * I'm gonna start out by making this with a physical class, but depending on how much the class ends up doing, we might
 * wing it without a class. We'll see
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

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }

    public String toString() {
        return "Employee ID " + id + ": " + name + ".\nUsername: " + username + "\nManager: " + (isManager ? "Yes" : "No");
    }
}
