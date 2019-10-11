package Database;

import Application.Animal;
import Application.Kennel;

import java.sql.*;
import java.util.HashMap;

public class Database {

    public static HashMap<Integer, Animal> animals = new HashMap<Integer, Animal>();
    public static HashMap<Integer, Kennel> kennels = new HashMap<Integer, Kennel>();

    public static void saveNewPatient(String name, String species, String temperment, String sex,
                                      String color, String breed, String microchip, String age,
                                      String weight, String arrivalDate, String adoptable) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:src/database/shelter.db");

            // Ensure the connection exists
            if (connection != null) {
                // Prepare the query statement.
                PreparedStatement pstmt = connection.prepareStatement("INSERT INTO animals " +
                        "(Name,MainSpecies,Temperment,Sex,Color,Breed,Microchip,Age,Weight,ArrivalDate,Adoptable) " +
                        "VALUES (?,?,?,?,?,?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, name);
                pstmt.setString(2, species);
                pstmt.setString(3, temperment);
                pstmt.setString(4, sex);
                pstmt.setString(5, color);
                pstmt.setString(6, breed);
                pstmt.setString(7, microchip);
                pstmt.setString(8, age);
                pstmt.setString(9, weight);
                pstmt.setString(10, arrivalDate);
                pstmt.setString(11, adoptable);

                // Execute the query & catch generated key.
                pstmt.executeUpdate();
                ResultSet rs = pstmt.getGeneratedKeys();

                // Create an animal object using the generated key & store it in our hashmap.
                while (rs.next()) {
                    animals.put(rs.getInt(1), new Animal(name, species, temperment, sex, color, breed,
                            microchip, age, weight, arrivalDate, adoptable, rs.getInt(1)));
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    /**
     * Loads Animals and Kennels from the database.
     */
    public static void loadAll() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:src/database/shelter.db");

            if (connection != null) {
                Statement statement = connection.createStatement();

                // Load animals and place them into the HashMap
                ResultSet resultSet = statement.executeQuery("SELECT * FROM animals");
                while (resultSet.next()) {
                    animals.put(resultSet.getInt("CollarID"), new Animal(
                            resultSet.getString("Name"),
                            resultSet.getString("MainSpecies"),
                            resultSet.getString("Temperment"),
                            resultSet.getString("Sex"),
                            resultSet.getString("Color"),
                            resultSet.getString("Breed"),
                            resultSet.getString("Microchip"),
                            resultSet.getString("Age"),
                            resultSet.getString("Weight"),
                            resultSet.getString("ArrivalDate"),
                            resultSet.getString("Adoptable"),
                            resultSet.getInt("CollarID")));
                }

                // Load kennels and place them into the HashMap
                resultSet = statement.executeQuery("SELECT * FROM kennels");
                while (resultSet.next()) {
                    int kennelID = resultSet.getInt("KennelID");
                    int currentAnimal = resultSet.getInt("CurrentAnimal");

                    Kennel kennel;
                    if (currentAnimal == -1) {
                        // No animal, so empty constructor.
                        kennel = new Kennel();
                    } else {
                        // get animal info from animals hashmap where key = currentAnimal id
                        kennel = new Kennel(animals.get(currentAnimal));
                    }
                    kennels.put(kennelID, kennel);
                }

                // Close all connections.
                resultSet.close();
                statement.close();
                connection.close();
            }
        } catch (Exception ex) {
            System.out.println("Database Exception: Failed to loadAll.\nReason: " + ex.toString() + "\n\n\n");
            ex.printStackTrace();
        }
    }
}