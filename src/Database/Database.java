package Database;

import java.sql.*;

public class Database {

    public static ResultSet getAllOfSpecies(String species) {
        ResultSet rs = null;

        rs = executeQuery("SELECT * FROM animals WHERE MainSpecies = '" + species + "'",
                null, false);
        return rs;
    }

    public static ResultSet saveNewPatient(String name, String species, String sex, String color,
                                           String breed, int age, String microchip) {

        ResultSet rs = executeQuery("INSERT INTO animals (Name,MainSpecies,Sex,Color,Breed,Age,Microchip) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)", new Object[] {name, species, "male", color, breed, age, microchip},
                true);

        try {
            while (rs.next())
                System.out.println(rs.getInt(1));
        }
        catch (Exception ex) {
            System.out.println(ex.toString());
        }

        return null;
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


            // Close connection
            resultSet.close();
            statement.close();
            connection.close();
        }
        catch (Exception ex) {
            System.out.println("Database Exception: Failed to load animals.\nReason: " + ex.toString());
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

                // Depending on whether or not the query will return a result, or simply succeed/fail, you have to
                // call executeQuery, or executeUpdate. For our uses, I think we'll only need to care about SELECT.
                if (query.contains("SELECT")) {
                    if (fetchKey) {
                        // if fetchKey is true, we are most likely using an INSERT statement, so we're not looking for
                        // a row, but rather just the generated key from the new row, so we only need to return that.
                        pstmt.executeQuery();
                        return pstmt.getGeneratedKeys();
                    }

                    // but if fetchKey is false, we're most likely searching the database for something, so we want to
                    // return the entire table of results, so that the user can search through the results themselves.
                    return pstmt.executeQuery();
                }
                else {
                    if (fetchKey) {
                        // INSERT doesn't usually return anything, but it can return generated keys if wanted.
                        pstmt.executeUpdate();
                        return pstmt.getGeneratedKeys();
                    }

                    // Since other commands like INSERT and UPDATE don't return anything when executed, return null.
                    pstmt.executeUpdate();
                    return null;
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
                        throw new IllegalArgumentException("Query Params: Unknown data type - " +
                                queryParams[i].getClass().getTypeName());
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