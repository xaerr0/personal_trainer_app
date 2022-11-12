package personal_training_app.controllers;

import personal_training_app.model.Client;
import personal_training_app.model.Trainer;
import personal_training_app.model.Workout;
import personal_training_app.services.ClientService;
import personal_training_app.services.TrainerService;
import personal_training_app.services.WorkoutService;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Console {

    ClientService clientService = new ClientService();
    WorkoutService workoutService = new WorkoutService();
    TrainerService trainerService = new TrainerService();


    // Main menu
    public void logIn() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Please enter in your ID:");
            if (scanner.hasNextLong()) {
                Trainer trainer;
                Long id = scanner.nextLong();
                trainer = trainerService.getTrainer(id);
                System.out.println("Trainer ID found.. Welcome " + trainer.getFirstName() + "!");
                consoleMenu();
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            System.out.println("Trainer ID  not found. Please try again.");
            consoleMenu();
        }
    }

    public void consoleMenu() {
        int choice;
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nPlease select an option: ");
        System.out.println("1) View All Clients"); //done
        System.out.println("2) View All Workout Programs"); //done
        System.out.println("3) View All Trainers");
        System.out.println("4) Search a Client"); //done
        System.out.println("5) Search Workout Program");
        System.out.println("6) Enter New Client");
        System.out.println("7) Enter New Trainer");
        System.out.println("8) Logout");
        System.out.println("Choose one (q to quit): ");
        try {
            choice = scanner.nextInt();
        } catch (InputMismatchException e) {
            choice = 9;
        }
        System.out.println("\n");


        switch (choice) {
            case 1:
                System.out.println("List of Current Clients: ");
                List<Client> clientList = clientService.getAllClients();
                // iterate through list to print
                for (Client clients : clientList) {
                    System.out.println(clients);
                }
                consoleMenu();
                break;
            case 2:
                System.out.println("List of Workout Programs: ");
                List<Workout> workoutList = workoutService.getAllWorkouts();
                // iterate through list to print
                for (Workout workouts : workoutList) {
                    System.out.println(workouts);
                }
                consoleMenu();
                break;
            case 3:
                System.out.println("List of Current Trainers: ");
                List<Trainer> trainerList = trainerService.getAllTrainers();
                // iterate through list to print
                for (Trainer trainers : trainerList) {
                    System.out.println(trainers);
                }
                consoleMenu();

            case 4:
                searchClient();
                break;
            case 5:
                searchWorkout();
                break;
            case 6:
                newClient();
                consoleMenu();
                break;
            case 7:
                newTrainer();
                consoleMenu();
                break;
            case 8:
                System.out.println("Logging out...");
                logIn();
                break;
            case 9:
                break;
            default:
                System.out.println("Selection not found.");
                consoleMenu();
        }
    }


    public void searchWorkout() {
        int choice = 0;

        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Search by: ");
            System.out.println("1) Length of workout");
            System.out.println("2) Workout type");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice != 1 && choice != 2) {
                    System.out.println("Selection not found. Please select either 1) or 2)");
                } else if (choice == 1) {
                    workoutLength();

                }
                if (choice == 2) {

                    workoutType();
                }
            }
        } while (choice != 1 && choice != 2);
    }


    public void workoutLength() {
        int choice = 0;

        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("1) 30 min workouts");
            System.out.println("2) 45 min workouts");
            System.out.println("3) 60 min workouts");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                List<Workout> workoutList = new ArrayList<>();

                // if neither 1, 2, nor 3 are entered
                if (choice != 1 && choice != 2 && choice != 3) {
                    System.out.println("Selection not found. Please select either 1), 2) or 3)");
                } else if (choice == 1) {
                    System.out.println("List of 30 min workouts...");
                    workoutList = workoutService.getWorkouts(30);
                }
                if (choice == 2) {
                    System.out.println("List of 45 min workouts...");
                    workoutList = workoutService.getWorkouts(45);
                }
                if (choice == 3) {
                    System.out.println("List of 60 min workouts...");
                    workoutList = workoutService.getWorkouts(60);
                }
                // iterate through list to print
                for (Workout workout : workoutList) {
                    System.out.println(workout);
                }
            }
        } while (choice != 1 && choice != 2 && choice != 3);
        consoleMenu();
    }


    public void workoutType() {
        int choice = 0;

        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("1) Strength Workouts");
            System.out.println("2) Cardio Workouts");
            System.out.println("3) Hypertrophy Workouts");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                List<Workout> workoutList = new ArrayList<>();
                if (choice != 1 && choice != 2 && choice != 3) {
                    System.out.println("Selection not found. Please select either 1), 2) or 3)");
                } else if (choice == 1) {
                    System.out.println("List of Strength Workouts...");
                    workoutList = workoutService.getWorkouts("strength");
                }
                if (choice == 2) {
                    System.out.println("List of Cardio Workouts...");
                    workoutList = workoutService.getWorkouts("cardio");
                }
                if (choice == 3) {
                    System.out.println("List of Hypertrophy Workouts...");
                    workoutList = workoutService.getWorkouts("hypertrophy");
                }
                for (Workout workout : workoutList) {
                    System.out.println(workout);
                }
            }

        } while (choice != 1 && choice != 2 && choice != 3);
        consoleMenu();
    }

    public void searchClient() {
        int choice = 0;
        Long clientId = null;
        String lastName = null;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Search by: ");
            System.out.println("1) Client ID");
            System.out.println("2) Client Last Name");
            System.out.println("3) Go Back to Menu");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice != 1 && choice != 2 && choice != 3) {
                    System.out.println("Selection not found. Please select either 1) or 2)");
                } else if (choice == 1) {
                    System.out.println("Please Enter Client ID");
                    clientId = scanner.nextLong();
                    Client client = clientService.getClient(clientId);
                    System.out.println(client);
                }
                if (choice == 2) {
                    System.out.println("Please Enter Last Name");
                    lastName = scanner.next();
                    List<Client> clients = clientService.getClients(lastName);
                    for (Client clientList : clients)
                        System.out.println(clientList);
                }
                if (choice == 3) {
                    consoleMenu();
                }
            }
        } while (choice != 1 && choice != 2);
        consoleMenu();
    }

// TODO Enter New Client

    private void newClient() {

        Scanner scanner = new Scanner(System.in);
        Client client = new Client();

        System.out.println("Please Enter Client's First Name");
        client.setFirstName(scanner.nextLine());
        System.out.println("Please Enter Client's Last Name");
        client.setLastName(scanner.nextLine());
        System.out.println("Please Enter Client's Email");
        client.setEmail(scanner.nextLine());
        System.out.println(client);
        System.out.println("Is this correct? y/n?");
        String choice = scanner.next();
        if (choice.equalsIgnoreCase("y")) {
            Client savedClient = clientService.saveClient(client);
            System.out.println(savedClient);
            System.out.println(client.getFirstName() + " " + client.getLastName() +
                               " has been saved to the database");
        } else {
            System.out.println("Back to Menu");
            consoleMenu();
        }
    }

    private void newTrainer() {

        Scanner scanner = new Scanner(System.in);
        Trainer trainer = new Trainer();

        System.out.println("Please Enter Trainer's First Name");
        trainer.setFirstName(scanner.nextLine());
        System.out.println("Please Enter Trainer's Last Name");
        trainer.setLastName(scanner.nextLine());
        System.out.println("Please Enter Trainer's Email");
        trainer.setEmail(scanner.nextLine());
        System.out.println(trainer);
        System.out.println("Is this correct? y/n?");
        String choice = scanner.next();
        if (choice.equalsIgnoreCase("y")) {
            Trainer savedTrainer = trainerService.saveTrainer(trainer);
            System.out.println(savedTrainer);
            System.out.println(trainer.getFirstName() + " " + trainer.getLastName() +
                               " has been saved to the database");
        } else {
            System.out.println("Back to Menu");
            consoleMenu();
        }
    }
}