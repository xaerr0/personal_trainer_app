package personal_training_app.services;

import personal_training_app.data.DatabaseConnection;
import personal_training_app.model.Trainer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrainerService {

    DatabaseConnection databaseConnection;
    Connection connection;
    ResultSet resultSet;
    DatabaseConnection close;

    public TrainerService() {
        databaseConnection = new DatabaseConnection("personal_trainingdb");
        connection = databaseConnection.getConnection();
    }

    public Trainer getTrainer(Long id) {
        String sql = "SELECT * FROM trainer WHERE trainerid = " + id + ";";
        Trainer trainer = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            trainer = mapToTrainer(resultSet).get(0);
        } catch (SQLException e) {
            System.out.println("Exception Thrown!");
            System.out.println(e.getMessage());
        }
        return new Trainer();
    }

    public Trainer getTrainerLastName(String lastName) {
        String sql = "SELECT * FROM trainer WHERE last_name LIKE '" + lastName + "%';";
        Trainer trainer = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            trainer = mapToTrainer(resultSet).get(0);
        } catch (SQLException e) {
            System.out.println("Exception Thrown!");
            System.out.println(e.getMessage());
        }
        return new Trainer();
    }

    public List<Trainer> getAllTrainers() {
        String sql = "SELECT * FROM trainer";
        List<Trainer> trainers = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            trainers = mapToTrainer(resultSet);
            databaseConnection.closeConnection();
        } catch (SQLException e) {
            System.out.println("Exception Thrown!");
            System.out.println(e.getMessage());
        }
        return trainers;
    }

    public Boolean updateTrainer(Trainer trainer) {
        String sql = "UPDATE trainer SET first_name = ?, last_name = ?, email = ?, WHERE id = ?";
        int rowsUpdated = 0;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, trainer.getFirstName());
            statement.setString(2, trainer.getLastName());
            statement.setString(3, trainer.getEmail());
            statement.setLong(4, trainer.getId());
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

    private List<Trainer> mapToTrainer(ResultSet resultSet) throws SQLException {
        List<Trainer> trainerList = new ArrayList<>();
        while (resultSet.next()) {
            Trainer trainer = new Trainer();
            trainer.setId(resultSet.getLong("trainerid"));
            trainer.setFirstName(resultSet.getString("first_name"));
            trainer.setLastName(resultSet.getString("last_name"));
            trainer.setEmail(resultSet.getString("email"));
            trainerList.add(trainer);
            System.out.println(trainer);
        }
        return trainerList;
    }

}