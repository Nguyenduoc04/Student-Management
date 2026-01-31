package service;

import exception.InvalidDataException;
import model.Student;
import util.FileUtil;
import validation.InputValidator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class StudentService {
    private final List<Student> students;
    private String FILE = "students.csc";

    public StudentService() {
        students = new ArrayList<>();
    }

    private String getLastName(Student s) {
        String name = s.getName().trim();
        return name.substring(name.lastIndexOf(" ") + 1).toLowerCase();
    }

    public boolean existsById(String id) {
        return students.stream()
                .anyMatch(s -> s.getId().equalsIgnoreCase(id));
    }

    public void add(Scanner sc) {
        String id;
        String name;
        int age;
        double gpa;

        // ID
        while (true) {
            try {
                System.out.print("ID (Sxxx): ");
                id = InputValidator.validateId(sc.nextLine());

                if (existsById(id)) {
                    System.out.println("ID already exists!");
                    continue;
                }
                break;
            } catch (InvalidDataException e) {
                System.out.println(e.getMessage());
            }
        }

        // Name
        while (true) {
            try {
                System.out.print("Name: ");
                name = InputValidator.validateName(sc.nextLine());
                break;
            } catch (InvalidDataException e) {
                System.out.println(e.getMessage());
            }
        }

        // Age
        while (true) {
            try {
                System.out.print("Age: ");
                age = InputValidator.validateAge(
                        Integer.parseInt(sc.nextLine())
                );
                break;
            } catch (NumberFormatException e) {
                System.out.println("Age must be a number");
            } catch (InvalidDataException e) {
                System.out.println(e.getMessage());
            }
        }

        // GPA
        while (true) {
            try {
                System.out.print("GPA: ");
                gpa = InputValidator.validateGpa(
                        Double.parseDouble(sc.nextLine())
                );
                break;
            } catch (NumberFormatException e) {
                System.out.println("GPA must be a number");
            } catch (InvalidDataException e) {
                System.out.println(e.getMessage());
            }
        }

        students.add(new Student(id, name, age, gpa));
        System.out.println("Student added successfully!");
    }

    public void deleteById(String id) {
        if (students.isEmpty()) {
            System.out.println("No students available");
            return;
        }

        boolean removed = students.removeIf(
                s -> s.getId().equalsIgnoreCase(id)
        );

        if (removed) {
            System.out.println("Student deleted successfully");
        } else {
            System.out.println("No student found with ID: " + id);
        }
    }

    public void display() {
        if (students.isEmpty()) {
            System.out.println("No students available");
            return;
        }

        System.out.println("+------+------------------------------+-----+------+");
        System.out.printf("| %-4s | %-28s | %-3s | %-4s |\n",
                "ID", "Name", "Age", "GPA");
        System.out.println("+------+------------------------------+-----+------+");

        for (Student s : students) {
            System.out.printf("| %-4s | %-28s | %3d | %4.2f |\n",
                    s.getId(), s.getName(), s.getAge(), s.getGpa());
        }

        System.out.println("+------+------------------------------+-----+------+");
    }


    public void searchByName(String keyword) {
        if (students.isEmpty()) {
            System.out.println("No students available");
            return;
        }

        boolean found = false;
        String key = keyword.toLowerCase();

        for (Student s : students) {
            if (s.getName().toLowerCase().contains(key)) {
                if (!found) {
                    System.out.println("Student(s) found:");
                }
                System.out.println(s.display());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No student found with keyword: " + keyword);
        }
    }

    public void sortByName() {
        students.sort(Comparator.comparing(this::getLastName));
        System.out.println("Sorted by name (A → Z)");
    }

    public void sortByGpaDesc() {
        students.sort(Comparator.comparing(Student::getGpa).reversed());
        System.out.println("Sorted by GPA descending");
    }

    public void save() {
        FileUtil.writeToFile(FILE, students);
        System.out.println("Saved to file");
    }
    public void load(String FILE) {
        this.FILE = FILE;
        students.clear();
        students.addAll(FileUtil.readFromFile(FILE));
        System.out.println("Loaded data from file: " + FILE);
    }

}
