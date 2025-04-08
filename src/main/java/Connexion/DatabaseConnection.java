package main.java.Connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.io.IOException;

public class DatabaseConnection {
 
    public static Connection getConnexion() throws IOException {
        Connection connexion = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_s2_ETU003085", "ETU003085", "yHZLupRD");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connexion;
    }

    public static Statement getStatement() {
        try {
            return getConnexion().createStatement();
        } catch (Exception e) {
            return null;
        }
    }

    // public static void closeConnexion() {
    //     try {
    //         if (getConnexion() != null && !getConnexion().isClosed()) {
    //             getConnexion().close();
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    // }
}
