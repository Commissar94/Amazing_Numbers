package numbers;

public class PropertiesOfNumber {

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
        return newString.length() > 2 && ((number % fpl == 0));
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

    public static boolean isItSquare(long number) {
        double sqrt = Math.sqrt(number);
        sqrt = Math.round(sqrt);
        return sqrt * sqrt == number;
    }

    public static boolean isItSunny(long number) {
        return isItSquare(number + 1);
    }

    public static boolean isItJumping(long number) {
        String num = Long.toString(number);
        StringBuilder sb = new StringBuilder(num);
        long lastDigit = sb.charAt(0);

        for (int i = 1; i < sb.length(); i++) {
            if (sb.charAt(i) == lastDigit + 1 || sb.charAt(i) == lastDigit - 1) {
                lastDigit = sb.charAt(i);
            } else {
                return false;
            }
        }
        return true;
    }

    public static boolean isItHappy(long number) {

        while (number != 1 && number != 4) {
            number = isHappyNumber(number);
        }
        return number == 1;
    }

    public static long isHappyNumber(long number) {
        long rem, sum = 0;

        while (number > 0) {
            rem = number % 10;
            sum = sum + (rem * rem);
            number = number / 10;
        }
        return sum;
    }
}