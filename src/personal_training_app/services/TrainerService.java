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

    public Trainer saveTrainer(Trainer trainer) {
        String sql = "INSERT INTO trainer (first_name, last_name, email) VALUES (?, ?, ?)";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, trainer.getFirstName());
            statement.setString(2, trainer.getLastName());
            statement.setString(3, trainer.getEmail());
            statement.executeUpdate();
            statement.close();
            trainer = getTrainer(trainer.getEmail());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return trainer;
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
        return trainer;
    }

    public Trainer getTrainer(String email) {
        String sql = "SELECT * FROM trainer WHERE email = ?";
        Trainer trainer = null;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            trainer = mapToTrainer(resultSet).get(0);
        } catch (SQLException e) {
            System.out.println("Exception Thrown!");
            System.out.println(e.getMessage());
        }
        return trainer;
    }

    public List<Trainer> getTrainers(String lastName) {
        String sql = "SELECT * FROM trainer WHERE last_name LIKE '" + lastName + "%';";
        List<Trainer> trainerList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            trainerList.addAll(mapToTrainer(resultSet));
        } catch (SQLException e) {
            System.out.println("Exception Thrown!");
            System.out.println(e.getMessage());
        }
        return trainerList;
    }

    public List<Trainer> getAllTrainers() {
        String sql = "SELECT * FROM trainer";
        List<Trainer> trainers = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            trainers = mapToTrainer(resultSet);
//            databaseConnection.closeConnection();
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
            System.out.println("Trainer has been updated successfully!");
        }
        return false;
    }

    public Boolean deleteTrainer(Long id) {
        try {
            String sql = "DELETE FROM trainer WHERE trainerid = " + id + ";";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Exception Thrown!");
            System.out.println(e.getMessage());
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
        }
        return trainerList;
    }
}