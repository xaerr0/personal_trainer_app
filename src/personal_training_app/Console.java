package personal_training_app;

import java.util.Scanner;

public class Console {
    Trainer trainer = new Trainer();


    public void logIn() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter in your ID");
        while (scanner.hasNext()) {

            if (scanner.hasNextInt()) {
                System.out.println("Trainer ID found");
            } else {
                System.out.println("Unable to find Trainer ID");
            }


        }
        consoleMenu();

    }

    public void consoleMenu() {
        System.out.println("Please select an option");
        System.out.println("1) Logout");
        System.out.println("2) Search by Client");
        System.out.println("3) Search by Workout Program");
        System.out.println("4) View Clients");
        System.out.println("5) View Workout Programs");

    }

}