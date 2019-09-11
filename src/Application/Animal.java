package Application;

public class Animal {

    private String name;
    private String species;
    private String temperment;
    private int age;
    private int weight;

    public Animal() {
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public void setTemperment(String temperment) {
        this.temperment = temperment;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public String getTemperment() {
        return temperment;
    }

    public int getAge() {
        return age;
    }

    public int getWeight() {
        return weight;
    }

    //Methods
}
