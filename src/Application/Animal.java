package Application;

public class Animal {

    private String name;
    private String species;
    private String temperament;
    private String color;
    private int age;
    private int weight;
    private int animalID;

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
        this.temperament = temperment;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setAnimalID(int animalID) {
        this.animalID = animalID;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public String getTemperament() {
        return temperament;
    }

    public String getColor() {
        return color;
    }

    public int getAge() {
        return age;
    }

    public int getWeight() {
        return weight;
    }

    public int getAnimalID() {
        return animalID;
    }

    //Methods
}
