package personal_training_app.data;

import java.sql.*;

public class DatabaseConnection {

    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    public DatabaseConnection(String schema) {
        connection(schema);
    }

    public Connection getConnection() {
        return connection;
    }

    private void connection(String schema) {
        String query = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/personal_trainingdb";
            String username = "cono";
            String password = "hamburger";
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}