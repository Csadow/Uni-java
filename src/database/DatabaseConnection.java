package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {
    private static final String URL="jdbc:postgresql://localhost:5432/clothing_db";
    private static final String USER="SADOW";
    private static final String PASSWORD="SADOW";


    public static Connection getConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to Database");
        } catch (SQLException e) {
            System.out.println("Failed to connect to database");
            System.out.println("Error" + e.getMessage());
        }

        return connection;
    }

    public static void closeConnection(Connection connection){
        if(connection !=null){
            try {
                connection.close();
                System.out.println("Connection closed");
            }
            catch (SQLException e){
                System.out.println("Failed to close connection");
                e.printStackTrace();
            }
        }
    }
}
