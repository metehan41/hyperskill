package bullscows;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[] args) {
        System.out.println("Please, enter the secret code's length:");
        String strLength = scanner.nextLine();
        int length = 0;
        try {
            length = Integer.parseInt(strLength);
        } catch (NumberFormatException e) {
            System.out.println("Error: " + strLength + " isn't a valid number.");
            System.exit(0);
        }
        System.out.println("Input the number of possible symbols in the code:");
        int possibleCharacter = scanner.nextInt();
        if (length > possibleCharacter) {
            System.out.println("Error: it's not possible to generate a code with a length of " + length + " with "+ possibleCharacter + " unique symbols.");
            System.exit(0);
        } else if (possibleCharacter > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            System.exit(0);
        } else {
            String secretCode = secretNUmber(length, possibleCharacter);
            grade(secretCode, length);
        }
    }

    public static String secretNUmber(int length, int possibleCharacter) {
        StringBuilder secretNumber = new StringBuilder("");
        if (length > 36 | length <= 0) {
            StringBuilder errorMessage = new StringBuilder("Error");
            System.out.println(errorMessage);
            System.exit(0);
            return "Error";
        } else {
            boolean flag2 = true;
            while (flag2) {
                StringBuilder str = new StringBuilder("");
                String[] strArray = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h","ı", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };
                for (int i = 0; i < length; i++) {
                    int f = random.nextInt(possibleCharacter) + 0;
                    str.append(strArray[f]);
                }
                String pseudoRandomNumericString = str.toString();
                int pseudoRandomNumberLength = pseudoRandomNumericString.length();
                for (int i = 0; i < pseudoRandomNumberLength; i++) {
                    boolean flag = true;
                    int secretNumberLength = secretNumber.length();
                    for (int j = 0; j < secretNumberLength; j++) {
                        if (pseudoRandomNumericString.charAt(i) == secretNumber.charAt(j)) {
                            flag = false;
                        }
                    }
                    if (flag && secretNumber.length() < length) {
                        secretNumber.append(pseudoRandomNumericString.charAt(i));
                    }
                }
                String secretCode = secretNumber.toString();
                if (secretCode.length() == length && secretCode.charAt(0) != '0') {
                    if (possibleCharacter <= 10) {
                        System.out.println("The secret is prepared: "+ "*".repeat(length) + " (" + "0-" + strArray[possibleCharacter - 1] + ")" + ".");
                    } else {
                        System.out.println("The secret is prepared: " + "*".repeat(length) +  " (0-9, " + "a-" + strArray[possibleCharacter - 1] + ")" + ".");
                    }
                    System.out.println("Okay, let's start a game!");
                    flag2 = false;
                    return secretCode;
                } else {
                    flag2 = true;
                }
            }
        }
        return "Error";
    }

    public static void grade(String number, int length) {
        boolean flag = true;
        int counter = 1;
        while (flag) {
            System.out.println("Turn " + counter + ":");
            String guess = scanner.next();
            int bulls = 0;
            int cows = 0;
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < length; j++) {
                    if (guess.charAt(i) == number.charAt(j)) {
                        if (i == j) {
                            bulls += 1;
                        } else {
                            cows += 1;
                        }
                    }
                }
            }
            if (bulls > 0 && cows > 0 && bulls < length) {
                if (bulls == 1 && cows == 1) {
                    System.out.printf("Grade: %d bull and %d cow.\n", bulls, cows);
                    counter++;
                } else if (bulls == 1 && cows != 1) {
                    System.out.printf("Grade: %d bull and %d cows.\n", bulls, cows);
                    counter++;
                } else if (bulls != 1 && cows == 1) {
                    System.out.printf("Grade: %d bulls and %d cow.\n", bulls, cows);
                    counter++;
                } else {
                    System.out.printf("Grade: %d bulls and %d cows.\n", bulls, cows);
                    counter++;
                }
            } else if (bulls > 0 && cows == 0 && bulls < length) {
                if (bulls == 1) {
                    System.out.printf("Grade: %d bull.\n", bulls);
                    counter++;
                } else {
                    System.out.printf("Grade: %d bulls.\n", bulls);
                    counter++;
                }
            } else if (bulls == 0 && cows > 0) {
                if (cows == 1) {
                    System.out.printf("Grade: %d cows.\n", cows);
                    counter++;
                } else {
                    System.out.printf("Grade: %d cows.\n", cows);
                    counter++;
                }
            } else if (bulls == 0 && cows == 0) {
                System.out.println("Grade: None.");
                counter++;
            } else if (bulls == length) {
                System.out.printf("Grade: %d bulls\nCongratulations! You guessed the secret code.", length);
                flag = false;
            }
        }
    }
}