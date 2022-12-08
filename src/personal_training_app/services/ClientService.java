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

    public Client saveClient(Client client) {
        String sql = "INSERT INTO client (first_name, last_name, email) VALUES (?, ?, ?);";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, client.getFirstName());
            statement.setString(2, client.getLastName());
            statement.setString(3, client.getEmail());
            statement.executeUpdate();
            statement.close();
            client = getClient(client.getEmail());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return client;
    }

    public Client getClient(Long id) {
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
        return client;
    }

    /**
     * Gets client from database by email address
     * @param email of client to return
     * @return a client
     */
    public Client getClient(String email) {
        String sql = "SELECT * FROM client WHERE email = ?" ;
        Client client = null;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            client = mapToClient(resultSet).get(0);
        } catch (SQLException e) {
            System.out.println("Exception Thrown!");
            System.out.println(e.getMessage());
        }
        return client;
    }

    /**
     * Gets client(s) from database by last name
     * @param lastName of client(s)
     * @return list of client(s)
     */
    public List<Client> getClients(String lastName) {
        String sql = "SELECT * FROM client WHERE last_name LIKE '" + lastName + "%';";
        List<Client> clientList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            clientList.addAll(mapToClient(resultSet));
        } catch (SQLException e) {
            System.out.println("Exception Thrown!");
            System.out.println(e.getMessage());
        }
        return clientList;
    }
    /**
     * Gets all clients from database
     * @return list of all clients
     */
    public List<Client> getAllClients() {
        String sql = "SELECT * FROM client";
        List<Client> clients = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            clients = mapToClient(resultSet);
//            databaseConnection.closeConnection();
        } catch (SQLException e) {
            System.out.println("Exception Thrown!");
            System.out.println(e.getMessage());
        }
        return clients;
    }

    /**
     * Updates client from database
     * @param client to be updated
     * @return true if successfully updated
     */
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
    /**
     * Deletes client from database
     * @param id of client to be deleted
     * @return true if successfully deleted
     */
    public Boolean deleteClient(Long id) {
        try {
            String sql = "DELETE FROM client WHERE clientid = " + id + ";";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Exception Thrown!");
            System.out.println(e.getMessage());
            return false;
        }
        return false;
    }

    /**
     * Maps records from resultSet to Client objects
     * @param resultSet contains records from database
     * @return lists of clients
     * @throws SQLException when reading resultSet
     */
    public List<Client> mapToClient(ResultSet resultSet) throws SQLException {
        List<Client> clientList = new ArrayList<>();
        while (resultSet.next()) {
            Client client = new Client();
            client.setId(resultSet.getLong("clientid"));
            client.setFirstName(resultSet.getString("first_name"));
            client.setLastName(resultSet.getString("last_name"));
            client.setEmail(resultSet.getString("email"));
            clientList.add(client);
        }
        return clientList;
    }
}