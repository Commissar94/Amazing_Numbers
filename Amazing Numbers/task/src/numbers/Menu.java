package numbers;

import java.util.Scanner;

import static numbers.Requests.*;

public class Menu {

    public static Scanner sc = new Scanner(System.in);
    static boolean exit = false;

    public Menu() {
        System.out.printf("Welcome to Amazing Numbers!%n%n" +
                "Supported requests: %n" +
                "- enter a natural number to know its properties; %n" +
                "- enter two natural numbers to obtain the properties of the list: %n" +
                "  * the first parameter represents a starting number; %n" +
                "  * the second parameters show how many consecutive numbers are to be processed; %n" +
                "- two natural numbers and properties to search for; %n" +
                "- a property preceded by minus must not be present in numbers; %n" +
                "- separate the parameters with one space; %n" +
                "- enter 0 to exit.%n%n");
    }

    public void InputHandler() {
        do {
            System.out.println("Enter a request: ");
            String input = sc.nextLine();

            String[] inputs = input.split(" ");

            if (inputs.length == 1 && Long.parseLong(inputs[0]) == 0) {
                exit = true;
            } else if (inputs.length == 1) {
                Requests.SimpleRequest(Long.parseLong(inputs[0]));
            } else if (inputs.length == 2) {
                MultiRequest(Long.parseLong(inputs[0]), Long.parseLong(inputs[1]));
                System.out.println();
            } else if (inputs.length == 3) {
                NumberWithProperty(Long.parseLong(inputs[0]), Long.parseLong(inputs[1]), inputs[2]);
                System.out.println();
            } else if (inputs.length >= 4) {
                NumberWithMultiProperties(inputs);
                System.out.println();
            }

        } while (!exit);
    }
}