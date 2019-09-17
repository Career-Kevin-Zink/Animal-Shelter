package Application;

public class Animal {

    private String name;
    private String species;
    private String temperment;
    private String sex;
    private String color;
    private String breed;
    private String microchip;
    private int age;
    private int weight;
    private int animalID;

    public Animal() {
        // probably don't want a constructor with no params
    }

    public Animal(String name, String species, String temperment, String sex,
                  String color, String breed, String microchip, int age,
                  int weight, int animalID) {
        this.name = name;
        this.species = species;
        this.temperment = temperment;
        this.sex = sex;
        this.color = color;
        this.breed = breed;
        this.microchip = microchip;
        this.age = age;
        this.weight = weight;
        this.animalID = animalID;
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

    public void setSex(String sex) { this.sex = sex; }

    public void setColor(String color) {
        this.color = color;
    }

    public void setBreed(String breed) { this.breed = breed; }

    public void setMicrochip(String microchip) { this.microchip = microchip; }

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

    public String getTemperment() {
        return temperment;
    }

    public String getSex() { return sex; }

    public String getColor() {
        return color;
    }

    public String getBreed() { return breed; }

    public String getMicrochip() { return microchip; }

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
