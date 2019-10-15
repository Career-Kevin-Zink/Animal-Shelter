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
        if(currentAnimal != null) {
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
        // using getName for now since we don't store animal id yet
        String out = "Kennel ID: " + kennelId + (currentAnimal == null ? "" : " Animal ID: " + currentAnimal.getName());
        return out;
    }

    public static void loadKennels() {

    }

}
