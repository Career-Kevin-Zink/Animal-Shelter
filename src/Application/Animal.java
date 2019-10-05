package Application;

import java.sql.Date;

public class Animal {

    private String name;
    private String species;
    private String temperment;
    private String sex;
    private String color;
    private String breed;
    private String microchip;
    private String age;
    private String weight;
    private String adoptable;
    private String arrivalDate;
    private int animalID;

    public Animal() {
        // probably don't want a constructor with no params
    }

    public Animal(String name, String species, String temperment, String sex,
                  String color, String breed, String microchip, String age,
                  String weight, String arrivalDate, String adoptable, int animalID) {
        this.name = name;
        this.species = species;
        this.temperment = temperment;
        this.sex = sex;
        this.color = color;
        this.breed = breed;
        this.microchip = microchip;
        this.age = age;
        this.weight = weight;
        this.arrivalDate = arrivalDate;
        this.animalID = animalID;
        this.adoptable = adoptable;
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

    public void setAge(String age) {
        this.age = age;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setAdoptable(String adoptable) {
        this.adoptable = adoptable;
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

    public String getAge() {
        return age;
    }

    public String getWeight() {
        return weight;
    }

    public String getAdoptable() {
        return adoptable;
    }

    public int getAnimalID() {
        return animalID;
    }

    //Methods
}
