import service.StudentService;
import java.util.Scanner;



public class Main {
    private static void printMenu() {
        System.out.println("\n========== STUDENT MANAGEMENT ==========");
        System.out.println("| 1. Add student                        |");
        System.out.println("| 2. Delete student by ID               |");
        System.out.println("| 3. Display students                   |");
        System.out.println("| 4. Search by name                     |");
        System.out.println("| 5. Sort by GPA (desc)                 |");
        System.out.println("| 6. Sort by name                       |");
        System.out.println("| 7. Save to file                       |");
        System.out.println("| 8. Load from file                     |");
        System.out.println("| 9. Show warning students              |");
        System.out.println("| 10. Statistics                        |");
        System.out.println("| 0. Exit                               |");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentService service = new StudentService();
        printMenu();
        while (true) {
            System.out.println("========================================");
            System.out.print("Enter your choice: ");
            String input = sc.nextLine();

            if (input.equals("0")) {
                System.out.println("Program terminated. Goodbye 👋");
                break;
            }
            try {
                int choice = Integer.parseInt(input);

                switch (choice) {
                    case 1:
                        service.add(sc);
                        break;

                    case 2:
                        System.out.print("Enter student ID to delete: ");
                        String deleteId = sc.nextLine();
                        service.deleteById(deleteId);
                        break;
                    case 3:
                        service.display();
                        break;

                    case 4:
                        System.out.print("Enter name to search: ");
                        String searchName = sc.nextLine();
                        service.searchByName(searchName);
                        break;

                    case 5:
                        service.sortByGpaDesc();
                        break;

                    case 6:
                        service.sortByName();

                        break;

                    case 7:
                        service.save();
                        break;

                    case 8:
                        System.out.print("Enter file name to load: ");
                        String fileName = sc.nextLine();
                        service.load(fileName);
                        break;

                    case 9:
                        service.showWarningStudents();
                        break;

                    case 10:
                        service.statistics();
                        break;

                    default:
                        System.out.println("Invalid choice");
                }
            } catch (Exception e) {
                System.out.println("Invalid input, try again!");
            }

        }
    }
}
