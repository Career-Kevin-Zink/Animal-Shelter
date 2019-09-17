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
