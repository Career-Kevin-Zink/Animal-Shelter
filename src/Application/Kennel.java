package Application;

public class Kennel {

    private int kennelId;
    private Animal currentAnimal;

    public Kennel() {
        currentAnimal = null;
    }

    public Kennel(Animal animal) {
        currentAnimal = animal;
    }

    public void addAnimal(Animal animal) {

    }

    public void removeAnimal() {

    }

    @Override
    public String toString() {
        // using getName for now since we don't store animal id yet
        String out = "Kennel ID: " + kennelId + " Animal ID: " + currentAnimal.getName();
        return out;
    }

    public static void loadKennels() {

    }

}
