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

    /**
     * Menu to login using trainer Id
     */
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
            logIn();
        }
    }

    /**
     * Main console menu
     */
    public void consoleMenu() {
        int choice;
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nPlease select an option: ");
        System.out.println("1) View All Clients");
        System.out.println("2) View All Workout Programs");
        System.out.println("3) View All Trainers");
        System.out.println("4) Search a Client");
        System.out.println("5) Search Workout Program");
        System.out.println("6) Enter New Client");
        System.out.println("7) Enter New Trainer");
        System.out.println("8) Delete Client");
        System.out.println("9) Delete Trainer");
        System.out.println("10) Logout");
        System.out.println("Choose one (q to quit): ");
        try {
            choice = scanner.nextInt();
        } catch (InputMismatchException e) {
            choice = 11;
        }
        System.out.println("\n");

        switch (choice) {
            case 1:
                listAllClients();
                consoleMenu();
                break;
            case 2:
                listAllWorkouts();
                consoleMenu();
                break;
            case 3:
                listAllTrainers();
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
                listAllClients();
                deleteClient();
                break;
            case 9:
                listAllTrainers();
                deleteTrainer();
                break;
            case 10:
                System.out.println("Logging out...");
                logIn();
                break;
            default:
                System.out.println("Selection not found.");
                consoleMenu();
        }
    }

    /**
     * Pulls list for clients from the service, iterates
     * through them and prints clients to the console
     */
    private void listAllClients() {
        System.out.println("List of Current Clients: ");
        List<Client> clientList = clientService.getAllClients();
        // iterate through list to print
        for (Client clients : clientList) {
            System.out.println(clients);
        }
    }

    /**
     * Pulls list for workouts from the service, iterates
     * through them and prints clients to the console
     */
    private void listAllWorkouts() {
        System.out.println("List of Workout Programs: ");
        List<Workout> workoutList = workoutService.getAllWorkouts();

        for (Workout workouts : workoutList) {
            System.out.println(workouts);
        }
    }

    /**
     * Pulls list for trainers from the service, iterates
     * through them and prints clients to the console
     */
    private void listAllTrainers() {
        System.out.println("List of Current Trainers: ");
        List<Trainer> trainerList = trainerService.getAllTrainers();
        // iterate through list to print
        for (Trainer trainers : trainerList) {
            System.out.println(trainers);
        }
    }

    /**
     * Menu to choose an option to search through clients
     * by Id or Last Name
     */
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
                // if 1, 2, or 3 is not selected, repeat request
                if (choice != 1 && choice != 2 && choice != 3) {
                    System.out.println("Selection not found. Please select either 1) or 2)");
                    // to search by Client ID
                } else if (choice == 1) {
                    System.out.println("Please Enter Client ID");
                    clientId = scanner.nextLong();
                    Client client = clientService.getClient(clientId);
                    System.out.println(client);
                }
                // to search by last name
                if (choice == 2) {
                    System.out.println("Please Enter Last Name");
                    lastName = scanner.next();
                    List<Client> clients = clientService.getClients(lastName);
                    for (Client clientList : clients)
                        System.out.println(clientList);
                }
                // go back to menu
                if (choice == 3) {
                    consoleMenu();
                }
            }
        } while (choice != 1 && choice != 2);
        consoleMenu();
    }

    /**
     * Menu to choose an option to search through workouts
     * by length or type
     */
    public void searchWorkout() {
        int choice = 0;

        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Search by: ");
            System.out.println("1) Length of workout");
            System.out.println("2) Workout type");
            System.out.println("3) Go Back to Menu");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                // if neither 1,2 or 3 chosen, repeat request
                if (choice != 1 && choice != 2 && choice != 3) {
                    System.out.println("Selection not found. Please select either 1) or 2)");
                    // search for workout by length
                } else if (choice == 1) {
                    workoutLength();
                }
                // search workout by type
                if (choice == 2) {
                    workoutType();
                }
                // go back to menu
                if (choice == 3) {
                    consoleMenu();
                }
            }
        } while (choice != 1 && choice != 2);
    }

    /**
     * Enter a new client into the database
     */
    private void newClient() {

        Scanner scanner = new Scanner(System.in);
        Client client = new Client();

        // enter first name
        System.out.println("Please Enter Client's First Name");
        client.setFirstName(scanner.nextLine());
        // enter last name
        System.out.println("Please Enter Client's Last Name");
        client.setLastName(scanner.nextLine());
        // enter email address
        System.out.println("Please Enter Client's Email");
        client.setEmail(scanner.nextLine());
        // verify information input is correct
        System.out.println(client);
        System.out.println("Is this correct? y/n?");
        String choice = scanner.next();
        if (choice.equalsIgnoreCase("y")) {
            // save to database
            Client savedClient = clientService.saveClient(client);
            System.out.println(savedClient);
            System.out.println(client.getFirstName() + " " + client.getLastName() +
                               " has been saved to the database");
            // otherwise back to menu
        } else {
            System.out.println("Back to Menu");
            consoleMenu();
        }
    }

    /**
     * Enter a new trainer into the database
     */
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
        // ignore lower case inputs
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

    /**
     * Delete a client from the database
     */
    private void deleteClient() {
        System.out.println("Please enter the Client ID you would like to remove.");
        Scanner scanner = new Scanner(System.in);
        Long id = scanner.nextLong();
        System.out.println("Are you sure you want to delete " + clientService.getClient(id) + "? y/n?");
        String choice = scanner.next();
        if (choice.equalsIgnoreCase("y")) {
            System.out.println(clientService.getClient(id) + " has been successfully deleted.");
            clientService.deleteClient(id);
            consoleMenu();

        } else {
            System.out.println("Back to Menu");
            consoleMenu();
        }
    }

    /**
     * Delete a trainer from the database
     */
    private void deleteTrainer() {
        System.out.println("Please enter the Trainer ID you would like to remove.");
        Scanner scanner = new Scanner(System.in);
        Long id = scanner.nextLong();
        System.out.println("Are you sure you want to delete " + trainerService.getTrainer(id) + "? y/n?");
        String choice = scanner.next();
        if (choice.equalsIgnoreCase("y")) {
            System.out.println(trainerService.getTrainer(id) + " has been successfully deleted.");
            trainerService.deleteTrainer(id);
            consoleMenu();

        } else {
            System.out.println("Back to Menu");
            consoleMenu();
        }
    }

    /**
     * Menu to find workout by length
     */
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

    /**
     * Menu to find workouts by type
     */
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
}