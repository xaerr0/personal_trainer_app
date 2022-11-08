package personal_training_app.services;

import personal_training_app.data.DatabaseConnection;
import personal_training_app.model.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientService {

    DatabaseConnection databaseConnection;
    Connection connection;
    ResultSet resultSet;
    DatabaseConnection close;

    public ClientService() {
        databaseConnection = new DatabaseConnection("personal_trainingdb");
        connection = databaseConnection.getConnection();
    }


    public Client getClientid(Long id) {
        String sql = "SELECT * FROM client WHERE clientid = " + id + ";";
        Client client = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            client = mapToClient(resultSet).get(0);
        } catch (SQLException e) {
            System.out.println("Exception Thrown!");
            System.out.println(e.getMessage());
        }
        return new Client();
    }

    public Client getClientLastName(String lastName) {
        String sql = "SELECT * FROM client WHERE last_name LIKE '" + lastName + "%';";
        Client client = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            client = mapToClient(resultSet).get(0);
        } catch (SQLException e) {
            System.out.println("Exception Thrown!");
            System.out.println(e.getMessage());
        }
        return new Client();
    }


    public List<Client> getAllClients() {
        String sql = "SELECT * FROM client";
        List<Client> clients = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            clients = mapToClient(resultSet);
            databaseConnection.closeConnection();
        } catch (SQLException e) {
            System.out.println("Exception Thrown!");
            System.out.println(e.getMessage());
        }
        return clients;
    }

    public Boolean updateClient(Client client) {
        String sql = "UPDATE client SET first_name = ?, last_name = ?, email = ?, WHERE id = ?";

        int rowsUpdated = 0;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, client.getFirstName());
            statement.setString(2, client.getLastName());
            statement.setString(3, client.getEmail());
            statement.setLong(4, client.getId());
            rowsUpdated = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Exception Thrown!");
            System.out.println(e.getMessage());
        }
        if (rowsUpdated > 0) {
            System.out.println("Client has been updated successfully!");
        }
        return false;
    }

    public Boolean deleteClient(Long id) {
        try {
            String sql = "DELETE FROM client WHERE id = " + id + ";";
            PreparedStatement statement = connection.prepareStatement(sql);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Client has been deleted successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Exception Thrown!");
            System.out.println(e.getMessage());
        }
        return false;
    }

    public List<Client> mapToClient(ResultSet resultSet) throws SQLException {
        List<Client> clientList = new ArrayList<>();
        while (resultSet.next()) {
            Client client = new Client();
            client.setId(resultSet.getLong("clientid"));
            client.setFirstName(resultSet.getString("first_name"));
            client.setLastName(resultSet.getString("last_name"));
            client.setEmail(resultSet.getString("email"));
            clientList.add(client);

            System.out.println(client);
        }
        return clientList;
    }


}