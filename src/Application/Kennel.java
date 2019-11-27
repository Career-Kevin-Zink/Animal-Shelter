package Application;

import Database.Database;

public class Kennel {

    private int kennelId;
    private Animal currentAnimal;

    public Kennel(int id) {
        kennelId = id;
        currentAnimal = null;
    }

    public Kennel(int id, Animal animal) {
        kennelId = id;
        currentAnimal = animal;
    }

    public int getKennelID() {
        return kennelId;
    }

    public Animal getCurrentAnimal() {
        return currentAnimal;
    }

    public void addAnimal(Animal animal) {
        if (currentAnimal != null) {
            System.out.println("ERROR: Kennel not empty!");
            return;
        }

        currentAnimal = animal;
        Database.updateKennel(this);
    }

    public void removeAnimal() {
        if (currentAnimal == null) {
            System.out.println("ERROR: Kennel is already empty!");
            return;
        }

        currentAnimal = null;
        Database.updateKennel(this);
    }

    @Override
    public String toString() {
        String out = "Kennel ID: " + kennelId + (currentAnimal == null ? ": Empty" : ": " + currentAnimal.getName());
        return out;
    }
}
