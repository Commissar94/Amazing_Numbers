package numbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static numbers.PropertiesOfNumber.*;

public class Requests {

    static boolean even, odd, buzz, duck, palindromic, gapful, spy, square, sunny, jumping, happy, sad;

    public static void SimpleRequest(long number) {

        if (isItNatural(number)) {
            System.out.println("The first parameter should be a natural number or zero.");
            System.out.println();
        } else {
            SetProperties(number);

            System.out.print(showResult(number, even, odd, buzz, duck, palindromic, gapful, spy, square, sunny, jumping, happy, sad, false));
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
                SetProperties(number1 + i);
                System.out.print(showResult(number1 + i, even, odd, buzz, duck, palindromic, gapful, spy, square, sunny, jumping, happy, sad, true));
            }
        }
    }


    public static void NumberWithProperty(long number1, long number2, String prop) {
        String property = prop.toLowerCase();

        boolean minusProperty = isMinusProperty(property);
        boolean goodProperty = isGoodProperty(property);

        if (goodProperty) {
            while (number2 > 0) {
                for (int i = 0; ; i++) {
                    SetProperties(number1 + i);
                    String lookForProp = showResult(number1 + i, even, odd, buzz, duck, palindromic, gapful, spy, square, sunny, jumping, happy, sad, true);

                    if (lookForProp.contains(property)) {
                        System.out.print(lookForProp);
                        number2--;
                    }

                    if (number2 == 0) {
                        break;
                    }
                }
            }
        } else if (minusProperty) {
            while (number2 > 0) {
                for (int i = 0; ; i++) {
                    SetProperties(number1 + i);
                    String lookForProp = showResult(number1 + i, even, odd, buzz, duck, palindromic, gapful, spy, square, sunny, jumping, happy, sad, true);

                    if (!lookForProp.contains(property.substring(1))) {
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
                    "Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD]%n", prop);
        }
    }

    public static void NumberWithMultiProperties(String[] inputs) {

        long number1 = Long.parseLong(inputs[0]);
        long number2 = Long.parseLong(inputs[1]);
        List<String> listOfProperties = new LinkedList<>(Arrays.asList(inputs));
        listOfProperties.remove(0);
        listOfProperties.remove(0);

        List<String> badProperties = new ArrayList<>();
        List<String> goodProperties = new ArrayList<>();
        List<String> minusProperties = new ArrayList<>(); //свойства с минусом, чтобы исключать из поиска

        for (String listOfProperty : listOfProperties) {
            String property = listOfProperty.toLowerCase();
            boolean goodProperty = isGoodProperty(property);
            boolean minusProperty = isMinusProperty(property);
            if (goodProperty) {
                goodProperties.add(property);
            } else if (minusProperty) {
                minusProperties.add(property);
            } else {
                badProperties.add(property);
            }
        }


        if (badProperties.isEmpty()) {
            if (goodProperties.contains("even") && goodProperties.contains("odd") ||
                    minusProperties.contains("-even") && minusProperties.contains("-odd") ||
                    goodProperties.contains("even") && minusProperties.contains("-even") ||
                    goodProperties.contains("odd") && minusProperties.contains("-odd")) {

                System.out.printf("The request contains mutually exclusive properties:[EVEN, ODD]%n" +
                        "Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, SAD, HAPPY]%n");

            } else if (goodProperties.contains("square") && goodProperties.contains("sunny") ||
                    minusProperties.contains("-square") && minusProperties.contains("-sunny") ||
                    goodProperties.contains("square") && minusProperties.contains("-square") ||
                    goodProperties.contains("sunny") && minusProperties.contains("-sunny")) {

                System.out.printf("The request contains mutually exclusive properties:[SQUARE, SUNNY]%n" +
                        "Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, SAD, HAPPY]%n");

            } else if (goodProperties.contains("spy") && goodProperties.contains("duck") ||
                    minusProperties.contains("-spy") && minusProperties.contains("-duck") ||
                    goodProperties.contains("spy") && minusProperties.contains("-spy") ||
                    goodProperties.contains("duck") && minusProperties.contains("-duck")) {

                System.out.printf("The request contains mutually exclusive properties:[SPY, DUCK]%n" +
                        "Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, SAD, HAPPY]%n");

            } else if (goodProperties.contains("happy") && goodProperties.contains("sad") ||
                    minusProperties.contains("-happy") && minusProperties.contains("-sad") ||
                    goodProperties.contains("happy") && minusProperties.contains("-happy") ||
                    goodProperties.contains("sad") && minusProperties.contains("-sad")) {
                System.out.printf("The request contains mutually exclusive properties:[HAPPY, SAD]%n" +
                        "Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, SAD, HAPPY]%n");
            } else {
                while (number2 > 0) {
                    int properties;
                    int noProperties;
                    for (int i = 0; ; i++) {
                        SetProperties(number1 + i);
                        String lookForProp = showResult(number1 + i, even, odd, buzz, duck, palindromic, gapful, spy, square, sunny, jumping, happy, sad, true);
                        properties = 0;
                        for (String property : goodProperties) {
                            if (lookForProp.contains(property)) {
                                properties = properties + 1;
                            }
                        }
                        noProperties = 0;
                        for (String property : minusProperties) {
                            if (!lookForProp.contains(property.substring(1))) {
                                noProperties = noProperties + 1;
                            }
                        }

                        if (properties == goodProperties.size() && noProperties == minusProperties.size()) {
                            System.out.print(lookForProp);
                            number2--;
                        }
                        if (number2 == 0) {
                            break;
                        }
                    }
                }
            }

        }
        if (!badProperties.isEmpty()) {
            if (badProperties.size() == 1) {
                System.out.println("The property " + badProperties + " is wrong." +
                        "Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD]%n");
            } else {
                System.out.println("The properties " + badProperties + " are wrong." +
                        "Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD]%n");
            }
        }
    }

    public static String showResult(long number, boolean even, boolean odd, boolean buzz,
                                    boolean duck, boolean palindromic, boolean gapful,
                                    boolean spy, boolean square, boolean sunny,
                                    boolean jumping, boolean happy, boolean sad, boolean multi) {

        if (multi) {
            return String.format("%d is %s%s%s%s%s%s%s%s%s%s%s%s%n", number,
                    even ? "even" : "",
                    odd ? "odd" : "",
                    buzz ? ", buzz" : "",
                    duck ? ", duck" : "",
                    palindromic ? ", palindromic" : "",
                    gapful ? ", gapful" : "",
                    spy ? ", spy" : "",
                    square ? ", square" : "",
                    sunny ? ", sunny" : "",
                    jumping ? ", jumping" : "",
                    happy ? ", happy" : "",
                    sad ? ", sad" : "");


        } else {
            return String.format("Properties of %d%n" +
                    "even: %b%n" +
                    "odd: %b%n" +
                    "buzz: %b%n" +
                    "duck: %b%n" +
                    "palindromic: %b%n" +
                    "gapful: %b%n" +
                    "spy: %b%n" +
                    "square: %b%n" +
                    "sunny: %b%n" +
                    "jumping: %b%n" +
                    "happy: %b%n" +
                    "sad: %b%n%n", number, even, odd, buzz, duck, palindromic, gapful, spy, square, sunny, jumping, happy, sad);
        }
    }

    public static void SetProperties(long number) {
        buzz = isItBuzz(number);
        even = isItEven(number);
        odd = !even;
        duck = isItDuck(number);
        palindromic = isItPalindromic(number);
        gapful = isItGapful(number);
        spy = isItSpy(number);
        square = isItSquare(number);
        sunny = isItSunny(number);
        jumping = isItJumping(number);
        happy = isItHappy(number);
        sad = !happy;
    }

    private static boolean isMinusProperty(String property) {
        boolean minusProperty;
        minusProperty = property.equals("-even") || property.equals("-odd") ||
                property.equals("-buzz") || property.equals("-duck") ||
                property.equals("-palindromic") || property.equals("-gapful") ||
                property.equals("-spy") || property.equals("-square")
                || property.equals("-sunny") || property.equals("-jumping")
                || property.equals("-happy") || property.equals("-sad");
        return minusProperty;
    }

    private static boolean isGoodProperty(String property) {
        boolean goodProperty;
        goodProperty = property.equals("even") || property.equals("odd") ||
                property.equals("buzz") || property.equals("duck") ||
                property.equals("palindromic") || property.equals("gapful") ||
                property.equals("spy") || property.equals("square")
                || property.equals("sunny") || property.equals("jumping")
                || property.equals("happy") || property.equals("sad");
        return goodProperty;
    }
}