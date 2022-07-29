/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author sunilbohara
 */
public class DB {

    private static final String URL = "jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6508697";
    private static final String USER = "sql6508697";
    private static final String PASSWORD = "VQ5wLQFNyS";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
