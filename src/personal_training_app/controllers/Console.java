package personal_training_app.controllers;

import personal_training_app.model.Trainer;
import personal_training_app.services.ClientService;

import java.io.IOException;
import java.util.Scanner;

public class Console {
    Trainer trainer = new Trainer();
    ClientService clientService = new ClientService();

    // Main menu
    public void logIn() {
        Scanner scanner = new Scanner(System.in);

        //
        System.out.println("Please enter in your ID:");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid option. Please enter in your ID:");
            // TODO add option to quit here
            System.out.println("(q to quit)");
            scanner.next();
        }
        if (scanner.hasNextInt()) {
            System.out.println("Trainer ID found.. Welcome " + trainer.getFirstName() + "!");
        }
        try {
            consoleMenu();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void consoleMenu() throws IOException {
        int choice;
        System.out.println("Please select an option");
        System.out.println("\n");

        System.out.println("1) View Clients");
        System.out.println("2) View Workout Programs");
        System.out.println("3) Search Client");
        System.out.println("4) Search Workout Program");
        System.out.println("5) Logout");
        System.out.println("Choose one (q to quit): ");
        choice = System.in.read();

        System.out.println("\n");

        switch (choice) {
            case '1':
                System.out.println("List of current clients: ");
                // TODO getAllClients
            case '2':
                System.out.println("List of Workout Programs: ");
                // TODO getAllWorkouts
                break;
            case '3':
                System.out.println("Please enter Client ID");
                // getClient();
                break;
            case '4':
                searchWorkout();
                break;
            case '5':
                System.out.println("Logging out...");
                logIn();
                break;
            case 'q':
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
                    // logic for different workout types
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

                // if neither 1, 2, nor 3 are entered
                if (choice != 1 && choice != 2 && choice != 3) {
                    System.out.println("Selection not found. Please select either 1), 2) or 3)");
                } else if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    if (choice == 1) {
                        System.out.println("List of 30 min workouts...");
                    }
                    if (choice == 2) {
                        System.out.println("List of 45 min workouts...");
                    }
                    if (choice == 3) {
                        System.out.println("List of 60 min workouts...");
                    }

                    // loop while 1 - 3 are not entered
                    // TODO after wrong choice, 1 - 3 has to be entered twice

                }
            }
        } while (choice != 1 && choice != 2 && choice != 3);

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
                if (choice != 1 && choice != 2 && choice != 3) {
                    System.out.println("Selection not found. Please select either 1), 2) or 3)");
                } else if (choice == 1) {
                    System.out.println("List of Strength Workouts...");
                }
                if (choice == 2) {
                    System.out.println("List of Cardio Workouts...");
                }
                if (choice == 3) {
                    System.out.println("List of Hypertrophy Workouts...");
                }
            }

        } while (choice != 1 && choice != 2 && choice != 3);
    }

}


