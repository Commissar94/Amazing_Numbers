package numbers;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static boolean even;
    static boolean odd;
    static boolean buzz;
    static boolean duck;
    static boolean palindromic;
    static boolean gapful;
    static boolean spy;
    static boolean exit = false;

    public static boolean isItBuzz(long number) {
        return (number % 10 == 7 || (number % 7 == 0));
    }

    public static boolean isItEven(long number) {
        return number % 2 == 0;
    }

    public static boolean isItNatural(long number) {
        return number < 1;
    }

    public static boolean isItDuck(long number) {

        return String.valueOf(number).substring(1).contains("0");
    }

    public static boolean isItPalindromic(long number) {

        String str = String.valueOf(number);
        String reverseStr = new StringBuilder(str).reverse().toString();
        return (str.equals(reverseStr));

    }

    public static boolean isItGapful(long number) {

        String newString = "" + number;
        char firstChar = newString.charAt(0);
        char lastChar = newString.charAt(newString.length() - 1);

        int fpl = Integer.parseInt(firstChar + "" + lastChar);
        if (newString.length() > 2 && ((number % fpl == 0))) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isItSpy(long number) {

        String num = Long.toString(number);
        StringBuilder sb = new StringBuilder(num);
        long sum = 0;
        long multiplication = 1;


        for (int i = 0; i < sb.length(); i++) {
            sum = sum + Character.getNumericValue(sb.charAt(i));
            multiplication = multiplication * Character.getNumericValue(sb.charAt(i));
        }
        return sum == multiplication;
    }

    public static void showMenu() {
        System.out.printf("Welcome to Amazing Numbers!%n%n" +
                "Supported requests: %n" +
                "- enter a natural number to know its properties; %n" +
                "- enter two natural numbers to obtain the properties of the list: %n" +
                "  * the first parameter represents a starting number; %n" +
                "  * the second parameters show how many consecutive numbers are to be processed; %n" +
                "- two natural numbers and a property to search for; %n" +
                "- separate the parameters with one space; %n" +
                "- enter 0 to exit.%n%n");
    }

    public static String showResult(long number, boolean even, boolean odd, boolean buzz, boolean duck, boolean palindromic, boolean gapful, boolean spy, boolean multi) {

        if (multi) {
            return String.format("%d is %s%s%s%s%s%s%s%n", number, even ? "even" : "",
                    odd ? "odd" : "",
                    buzz ? ", buzz" : "",
                    duck ? ", duck" : "",
                    palindromic ? ", palindromic" : "",
                    gapful ? ", gapful" : "",
                    spy ? ", spy" : "");


        } else {
            return String.format("Properties of %d%n" +
                    "even: %b%n" +
                    "odd: %b%n" +
                    "buzz: %b%n" +
                    "duck: %b%n" +
                    "palindromic: %b%n" +
                    "gapful: %b%n" +
                    "spy: %b%n%n", number, even, odd, buzz, duck, palindromic, gapful, spy);
        }

    }

    public static void SimpleRequest(long number) {

        if (isItNatural(number)) {
            System.out.println("The first parameter should be a natural number or zero.");
            System.out.println();
        } else {
            buzz = isItBuzz(number);
            even = isItEven(number);
            odd = !even;
            duck = isItDuck(number);
            palindromic = isItPalindromic(number);
            gapful = isItGapful(number);
            spy = isItSpy(number);

            System.out.print(showResult(number, even, odd, buzz, duck, palindromic, gapful, spy, false));
        }
    }

    public static void MultiRequest(long number1, long number2) {

        if (isItNatural(number1)) {
            System.out.println("The first parameter should be a natural number or zero.");
            System.out.println();
        } else if (isItNatural(number2)) {
            System.out.println("The second parameter should be a natural number.");
            System.out.println();
        } else {
            for (int i = 0; i < number2; i++) {
                buzz = isItBuzz(number1 + i);
                even = isItEven(number1 + i);
                odd = !even;
                duck = isItDuck(number1 + i);
                palindromic = isItPalindromic(number1 + i);
                gapful = isItGapful(number1 + i);
                spy = isItSpy(number1 + i);
                System.out.print(showResult(number1 + i, even, odd, buzz, duck, palindromic, gapful, spy, true));
            }
        }
    }

    public static void NumberWithProperty(long number1, long number2, String prop) {
        String property = prop.toLowerCase();

        if (property.equals("even") || property.equals("odd") ||
                property.equals("buzz") || property.equals("duck") ||
                property.equals("palindromic") || property.equals("gapful") ||
                property.equals("spy")) {
            while (number2 > 0) {
                for (int i = 0; ; i++) {
                    buzz = isItBuzz(number1 + i);
                    even = isItEven(number1 + i);
                    odd = !even;
                    duck = isItDuck(number1 + i);
                    palindromic = isItPalindromic(number1 + i);
                    gapful = isItGapful(number1 + i);
                    spy = isItSpy(number1 + i);
                    String lookForProp = showResult(number1 + i, even, odd, buzz, duck, palindromic, gapful, spy, true);
                    if (lookForProp.contains(property)) {
                        System.out.print(lookForProp);
                        number2--;
                    }
                    if (number2 == 0) {
                        break;
                    }
                }
            }
        } else {
            System.out.printf("The property [%s] is wrong.%n" +
                    "Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY]%n", prop);
        }
    }

    public static void InputHandler() {
        do {
            System.out.println("Enter a request: ");
            String input = sc.nextLine();
            String[] inputs = input.split(" ");

            if (inputs.length == 1 && Long.parseLong(inputs[0]) == 0) {
                exit = true;
            } else if (inputs.length == 1) {
                SimpleRequest(Long.parseLong(inputs[0]));
            } else if (inputs.length == 2) {
                MultiRequest(Long.parseLong(inputs[0]), Long.parseLong(inputs[1]));
                System.out.println();
            } else if (inputs.length == 3) {
                NumberWithProperty(Long.parseLong(inputs[0]), Long.parseLong(inputs[1]), inputs[2]);
                System.out.println();
            }

        } while (!exit);
    }

    public static void main(String[] args) {

        showMenu();
        InputHandler();
    }
}