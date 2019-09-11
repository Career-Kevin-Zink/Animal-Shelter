package Database;

import Application.Animal;

import java.sql.*;

public class Database {

    /**
     * Loads all animals from the database.
     */
    public static void loadAnimals() {
        // Database connections must be wrapped in a try/catch, because you have to catch the exceptions.
        try {
            // The Statement object is an interface that gives us methods to interact with the database.
            Connection connection = DriverManager.getConnection("jdbc:sqlite:src/database/shelter.db");
            Statement statement = connection.createStatement();

            /*  The ResultSet object is where the results of a query get placed.
                Each call of resultSet.next() moves you down one entry, or in our case, to the next
                animal.

                In order to retrieve the data from the result set, you have to call the appropriate
                get method based on what type of data is at the index provided. The index starts at 1, not 0.
                So below, resultSet.getInt(1) grabs the integer value at index 1, which in the animals table
                is CollarID.
            */
            ResultSet resultSet = statement.executeQuery("SELECT * FROM animals");
            while (resultSet.next()) {
                // @TODO replace this with a call to Animal constructor
                System.out.println(
                        "Collar ID: " + resultSet.getInt(1) + "\n" +
                        "Name: " + resultSet.getString(2) + "\n" +
                        "Species: " + resultSet.getString(3) + "\n" +
                        "Temperment: " + resultSet.getString(4) + "\n" +
                        "Age: " + resultSet.getInt(5) + " years\n" +
                        "Weight: " + resultSet.getInt(6) + " lbs"
                );
            }
        }
        catch (Exception ex) {
            System.out.println("Database Exception: Failed to load animals.\nReason: " + ex.toString());
        }
    }

    /*  Example of how to use saveAnimal

        Animal Jeff = new Animal();
        Jeff.setName("Jeff");
        Jeff.setSpecies("Gorilla");
        Jeff.setTemperment("Absolutely Bananas");
        Jeff.setAge(44);
        Jeff.setWeight(505);
        Database.saveAnimal(Jeff);
     */

    public static void saveAnimal(Animal animal) {
        try {
            // Connects to database
            Connection connection = DriverManager.getConnection("jdbc:sqlite:src/database/shelter.db");

            // build query using data from the animal object
            PreparedStatement pstmt = connection.prepareStatement(
                "INSERT INTO animals (Name, MainSpecies, Temperment, Age, Weight)" +
                    "VALUES (?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, animal.getName());
            pstmt.setString(2, animal.getSpecies());
            pstmt.setString(3, animal.getTemperment());
            pstmt.setInt(4, animal.getAge());
            pstmt.setInt(5, animal.getWeight());

            // send query
            pstmt.executeUpdate();

            // collect collar id auto generated value
            ResultSet rs = pstmt.getGeneratedKeys();

            if (rs.next()) {
                // pending implementation decision
                // animal.setId(rs.getInt(1);
                return;
            }
        }
        catch (Exception ex) {
            System.out.println("Database Exception: Failed to save animal.\nReason: " + ex.toString());
        }
    }
}