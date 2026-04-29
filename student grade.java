import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class Student {
    private String name;
    private double marks;

    public Student(String name, double marks) {
        this.name = name;
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public double getMarks() {
        return marks;
    }
}

public class StudentGradeTracker {

    private static ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice = 0;

        System.out.println("=================================");
        System.out.println("     STUDENT GRADE TRACKER");
        System.out.println("=================================");

        do {
            try {
                System.out.println("\n1. Add Student");
                System.out.println("2. Show Summary Report");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");

                choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        addStudent(sc);
                        break;

                    case 2:
                        showSummaryReport();
                        break;

                    case 3:
                        System.out.println("Exiting program... Thank You!");
                        break;

                    default:
                        System.out.println("Invalid choice! Please enter 1 to 3.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter numeric value only.");
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("Unexpected Error: " + e.getMessage());
            }

        } while (choice != 3);

        sc.close();
    }

    private static void addStudent(Scanner sc) {

        try {
            System.out.print("Enter Student Name: ");
            String name = sc.nextLine();

            // Name Validation
            if (name.trim().isEmpty()) {
                System.out.println("Error: Name cannot be empty.");
                return;
            }

            if (!name.matches("[a-zA-Z ]+")) {
                System.out.println("Error: Name should contain letters only.");
                return;
            }

            System.out.print("Enter Marks (0 to 100): ");
            double marks = sc.nextDouble();
            sc.nextLine();

            if (marks < 0 || marks > 100) {
                System.out.println("Error: Marks must be between 0 to 100.");
                return;
            }

            students.add(new Student(name, marks));
            System.out.println("Student added successfully!");

        } catch (InputMismatchException e) {
            System.out.println("Error: Marks must be numeric.");
            sc.nextLine();
        } catch (Exception e) {
            System.out.println("Error while adding student.");
        }
    }

    private static void showSummaryReport() {

        try {
            if (students.isEmpty()) {
                System.out.println("No student records found.");
                return;
            }

            double total = 0;
            double highest = students.get(0).getMarks();
            double lowest = students.get(0).getMarks();

            System.out.println("\n=================================");
            System.out.println("         SUMMARY REPORT");
            System.out.println("=================================");
            System.out.printf("%-20s %-10s\n", "Student Name", "Marks");
            System.out.println("---------------------------------");

            for (Student s : students) {

                System.out.printf("%-20s %-10.2f\n",
                        s.getName(), s.getMarks());

                total += s.getMarks();

                if (s.getMarks() > highest)
                    highest = s.getMarks();

                if (s.getMarks() < lowest)
                    lowest = s.getMarks();
            }

            double average = total / students.size();

            System.out.println("---------------------------------");
            System.out.println("Total Students : " + students.size());
            System.out.printf("Average Marks  : %.2f\n", average);
            System.out.printf("Highest Marks  : %.2f\n", highest);
            System.out.printf("Lowest Marks   : %.2f\n", lowest);

        } catch (Exception e) {
            System.out.println("Error while generating report.");
        }
    }
}
