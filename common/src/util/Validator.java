package util;

import java.time.format.DateTimeParseException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Function;
import java.util.function.Predicate;

public class Validator<T> {
    private String string;
    private T value;

    public Validator(String string) {
        if ("".equals(string)) {
            this.string = "null";
        } else {
            this.string = string;
        }
    }

    public Validator(Scanner scanner) {
        try {
            this.string = scanner.nextLine();
            if ("".equals(string)) {
                string = "null";
            }
        } catch (NoSuchElementException e) {
            System.out.println("Unsuitable element");
            System.exit(1);
        }

    }

    public Validator<T> withCheckingNull(boolean nullable) throws IllegalArgumentException {
        if ("null".equals(string)) {
            if (nullable) {
                value = null;
            } else {
                throw new IllegalArgumentException("Value can't be null");
            }
        }
        return this;
    }

    public Validator<T> withCheckingFunction(Function<String, T> function, String description) {
        if (!"null".equals(string)) {
            try {
                value = function.apply(string);
            } catch (IllegalArgumentException | DateTimeParseException e) {
                throw new IllegalArgumentException("Error in checking func, " + description);
            }
        }
        return this;
    }

    public Validator<T> withCheckingPredicate(Predicate<Object> predicate, String error) {
        if (!"null".equals(string)) {
            if (!predicate.test(value)) {
                throw new IllegalArgumentException(error);
            }
        }
        return this;
    }

    public T getValue() {
        if (value == null && !"null".equals(string)) {
            // if (value.getClass().equals(String.class) && !"".equals(string)) {
            value = (T) string;
        }
        return value;
    }

    public static void validateQuantityOfArgs(String[] args, int amountOfArgs) throws IllegalArgumentException {
        if (args.length != amountOfArgs) {
            throw new IllegalArgumentException("Wrong count of args, this command wants " + amountOfArgs + " args");
        }
    }
}
