package util;

import model.Student;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    public static List<Student> readFromFile(String fileName) {
        List<Student> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                list.add(new Student(
                        p[0],
                        p[1],
                        Integer.parseInt(p[2]),
                        Double.parseDouble(p[3])
                ));
            }
        } catch (IOException e) {
            System.out.println("File not found, creating new file...");
        }
        return list;
    }

    public static void writeToFile(String fileName, List<Student> students) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Student s : students) {
                bw.write(s.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing file");
        }
    }
}
