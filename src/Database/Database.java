package Database;

import Application.Animal;

import java.sql.*;

public class Database {

    public static ResultSet getAllOfSpecies(String species) {
        ResultSet rs = null;

        rs = executeQuery("SELECT * FROM animals WHERE MainSpecies = '" + species + "'", null, false);
        return rs;
    }

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
                        "Temperament: " + resultSet.getString(4) + "\n" +
                        "Age: " + resultSet.getInt(5) + " years\n" +
                        "Weight: " + resultSet.getInt(6) + " lbs"
                );
            }


            // Close connection & cleanup
            resultSet.close();
            resultSet = null;
            statement.close();
            statement = null;
            connection.close();
            connection = null;
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
            pstmt.setString(3, animal.getTemperament());
            pstmt.setInt(4, animal.getAge());
            pstmt.setInt(5, animal.getWeight());

            // send query
            pstmt.executeUpdate();

            // collect collar id auto generated value
            ResultSet resultSet = pstmt.getGeneratedKeys();

            if (resultSet.next()) {
                // pending implementation decision
                // animal.setId(rs.getInt(1));
                return;
            }

            // Close connection & cleanup
            resultSet.close();
            resultSet = null;
            pstmt.close();
            pstmt = null;
            connection.close();
            connection = null;
        }
        catch (Exception ex) {
            System.out.println("Database Exception: Failed to save animal.\nReason: " + ex.toString());
        }
    }

    // this method is kinda wild right now, I will simplify it more as we develop the program more, but for now
    // it's a general work horse for querying the database
    /**
     * Used to execute custom queries to the database.
     * @param query The query code to execute. (eg. INSERT INTO animals (Name, MainSpecies) VALUES (?,?)
     * @param queryParams Array of objects that match the query code. (eg. new Object[] {"John", "Canine"}
     * @return Returns the generated key fetched from the query, if applicable. Returns -1 if no generated keys are
     * retrieved.
     */
    public static ResultSet executeQuery(String query, Object[] queryParams, boolean fetchKey) {
        try{
            Connection connection = DriverManager.getConnection("jdbc:sqlite:src/database/shelter.db");

            if (connection != null) {

                // build query using method
                PreparedStatement pstmt = buildQuery(connection, query, queryParams);

                // send query & return results
                if (fetchKey) {
                    // if fetchKey is true, we are most likely using an INSERT statement, so we're not looking for
                    // a row, but rather just the generated key from the new row, so we only need to return that.
                    return pstmt.getGeneratedKeys();
                }
                else {
                    // but if fetchKey is false, we're most likely searching the database for something, so we want to
                    // return the entire table of results, so that the user can search through the results themselves.

                    // Depending on whether or not the query will return a result, or simply succeed/fail, you have to
                    // call executeQuery, or executeUpdate. For our uses, I think we'll only need to care about SELECT.
                    if (query.contains("SELECT")) {
                        return pstmt.executeQuery();
                    }
                    else {
                        // Since other commands like INSERT and UPDATE don't return anything when executed, return null.
                        pstmt.executeUpdate();
                        return null;
                    }
                }
            }
            else {
                System.out.println("Database Exception: Failed to execute query.\nReason: Connection invalid.");
            }
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Database Exception: Failed to execute query \"" + query + "\"\n" +
                    "Reason: Array Index Out Of Bounds. Ensure the number of ?'s match the number of params.");
        }
        catch (Exception ex) {
            System.out.println("Database Exception: Failed to execute query \"" + query + "\"\n" +
                    "Reason: " + ex.toString());
        }

        // If we reach this return, the query failed and the console should have output an error for us.
        return null;
    }

    private static PreparedStatement buildQuery(Connection connection, String query, Object[] queryParams) {
        try {
            // We use a PreparedStatement rather than a regular statement so that we can add our variables below
            PreparedStatement pstmt = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

            if (queryParams != null && queryParams.length > 0) {

                // build the query by iterating through all queryParams passed to this method.
                // for each parameter passed to the query, replace a '?' with the queryParam object at that index
                for (int i = 0; i < queryParams.length; i++) {
                    if (queryParams[i] instanceof String) {
                        pstmt.setString(i + 1, (String) queryParams[i]);
                    } else if (queryParams[i] instanceof Integer) {
                        pstmt.setInt(i + 1, (int) queryParams[i]);
                    } else if (queryParams[i] instanceof Double) {
                        pstmt.setDouble(i + 1, (double) queryParams[i]);
                    } else if (queryParams[i] instanceof Float) {
                        pstmt.setFloat(i + 1, (float) queryParams[i]);
                    } else {
                        System.out.println("UNKNOWN TYPE: " + queryParams[i].getClass().getTypeName());
                    }
                }
            }

            return pstmt;
        }
        catch (Exception ex) {
            System.out.println("Database Exception: Error building query \"" + query + "\"\nReason: " + ex.toString());
        }

        // if we reach this return, an error occurred, and the console should output an exception.
        return null;
    }
}