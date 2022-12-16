package personal_training_app.services;

import personal_training_app.data.DatabaseConnection;
import personal_training_app.model.Client;
import personal_training_app.model.Workout;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkoutService {

    DatabaseConnection databaseConnection;
    Connection connection;
    ResultSet resultSet;
    DatabaseConnection close;

    public WorkoutService() {
        databaseConnection = new DatabaseConnection("personal_trainingdb");
        connection = databaseConnection.getConnection();
    }

    public Workout getWorkoutId(Long id) {
        String sql = "SELECT * FROM client WHERE workoutid = " + id + ";";

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            Workout workout = mapToWorkout(resultSet).get(0);
        } catch (SQLException e) {
            System.out.println("Exception Thrown!");
            System.out.println(e.getMessage());
        }
        return new Workout();
    }
    /**
     * Gets all workouts from database
     * @return list of all workouts
     */
    public List<Workout> getAllWorkouts() {
        String sql = "SELECT * FROM workout;";
        List<Workout> workouts = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            workouts = mapToWorkout(resultSet);

        } catch (SQLException e) {
            System.out.println("Exception Thrown!");
            System.out.println(e.getMessage());
        }
        return workouts;
    }

    /**
     * Gets list of workouts from database by length
     * @param length of workouts
     * @return list of workouts
     */
    public List<Workout> getWorkouts(int length) {
        String sql = "SELECT * FROM workout WHERE length = " + length + ";";
        List<Workout> workouts = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            workouts = mapToWorkout(resultSet);

        } catch (SQLException e) {
            System.out.println("Exception Thrown!");
            System.out.println(e.getMessage());
        }
        return workouts;
    }

    /**
     * Gets list of workouts from database by workout type
     * @param type of workouts
     * @return list of workouts
     */
    public List<Workout> getWorkouts(String type) {
        String sql = "SELECT * FROM workout WHERE type = '" + type + "';";
        List<Workout> workouts = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            workouts = mapToWorkout(resultSet);

        } catch (SQLException e) {
            System.out.println("Exception Thrown!");
            System.out.println(e.getMessage());
        }
        return workouts;
    }

    /**
     * Maps records from resultSet to Workout objects
     * @param resultSet contains records from database
     * @return lists of workouts
     * @throws SQLException when reading resultSet
     */
    private List<Workout> mapToWorkout(ResultSet resultSet) throws SQLException {
        List <Workout>  workoutList = new ArrayList<>();
        while (resultSet.next()) {
            Workout workout = new Workout();
            workout.setId(resultSet.getLong("workoutid"));
            workout.setName(resultSet.getString("name"));
            workout.setType(resultSet.getString("type"));
            workout.setLength(resultSet.getInt("length"));
            workoutList.add(workout);
        }
        return workoutList;
    }
}