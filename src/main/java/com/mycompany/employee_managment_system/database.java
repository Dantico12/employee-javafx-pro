package com.mycompany.employee_managment_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class database {

    // Non-static method to connect to the database
    public Connection ConnectDB() {

        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/employee", "postgres", "Karenjudk");

            return conn;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
