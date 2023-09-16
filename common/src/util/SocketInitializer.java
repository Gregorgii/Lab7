package util;

import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

public final class SocketInitializer {
    private SocketInitializer() {
    }
    private final static int MAX_PORT = 65535;

    public static boolean acceptAnswer(Scanner scanner) throws IllegalArgumentException {
        boolean answer = true;
        try {
            String stringAnswer = scanner.nextLine().trim().toLowerCase(Locale.ROOT);
            answer = new Validator<Boolean>(stringAnswer)
                    .withCheckingNull(false)
                    .withCheckingFunction(CheckAns::checkAns, "Answer must be y/n")
                    .getValue();
        } catch (NoSuchElementException e) {
            System.out.println("Unsuitable element");
            System.exit(1);
        }
        return answer;
    }
    public static boolean ask(String question, Scanner scanner){
        System.out.println(question);
        boolean isRunning = true;
        boolean answer = true;
        while (isRunning) {
            try {
                answer = acceptAnswer(scanner);
                isRunning = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return answer;
    }

    public static String askForAddress(Scanner scanner){
        String question = "Do u want use default server address? y/n ";
        boolean answer = ask(question, scanner);
        boolean isRunning = true;
        String address = null;
        isRunning = true;
        while (isRunning) {
            try {
                address = inputAddress(answer, scanner);
                isRunning = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return address;
    }

    private static String inputAddress(Boolean answer, Scanner scanner){
        String address = null;
        try {
            if (!answer) {
                System.out.println(("Print server address "));
                address = scanner.nextLine();
            }
        } catch (NoSuchElementException e) {
            System.out.println("Unsuitable element");
            System.exit(1);
        }
        return address;
    }

    public static Integer askForPort(Scanner scanner){
        String question = "Do u want to use default port? y/n ";
        boolean answer = ask(question, scanner);
        boolean isRunning = true;
        Integer port = null;
        while (isRunning) {
            try {
                port = inputPort(answer, scanner);
                isRunning = false;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        return port;
    }

    private static Integer inputPort(boolean answer, Scanner scanner) throws IllegalArgumentException {
        Integer portInt = null;
        try {
            if (!answer) {
                System.out.println("Choose hosts port (1-65535)");
                String port = scanner.nextLine();
                portInt = new Validator<Integer>(port)
                        .withCheckingNull(false)
                        .withCheckingFunction(Integer::parseInt, "Port must be integer")
                        .withCheckingPredicate((arg) -> (int) arg > 0 && (int) arg <= MAX_PORT, "Port must be [0, 65535]")
                        .getValue();

            }
        } catch (NoSuchElementException e) {
            System.out.println("Unsuitable element");
            System.exit(1);
        }
        return portInt;
    }
}

