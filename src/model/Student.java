package model;

public class Student {
    private String id;
    private String name;
    private int age;
    private double gpa;

    public Student(String id, String name, int age, double gpa) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gpa = gpa;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public double getGpa() { return gpa; }

    @Override
    public String toString() {
        return id + "," + name + "," + age + "," + gpa;
    }

    public String display() {
        return String.format("ID: %s | Name: %s | Age: %d | GPA: %.2f",
                id, name, age, gpa);
    }
}
