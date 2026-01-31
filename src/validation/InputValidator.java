package validation;

import exception.InvalidDataException;

public class InputValidator {

    public static String validateId(String id) throws InvalidDataException {
        if (!id.matches("S\\d{3}")) {
            throw new InvalidDataException("ID must be in format S + 3 digits (e.g. S001)");
        }
        return id;
    }

    public static String validateName(String name) throws InvalidDataException {
        if (name.length() > 30) {
            throw new InvalidDataException("Name must not exceed 30 characters");
        }
        return name;
    }

    public static int validateAge(int age) throws InvalidDataException {
        if (age < 0 || age > 120) {
            throw new InvalidDataException("Age must be between 0 and 120");
        }
        return age;
    }

    public static double validateGpa(double gpa) throws InvalidDataException {
        if (gpa < 0.0 || gpa > 4.0) {
            throw new InvalidDataException("GPA must be between 0.0 and 4.0");
        }
        return gpa;
    }
}
