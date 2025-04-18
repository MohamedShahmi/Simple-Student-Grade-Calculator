import java.util.ArrayList;
import java.util.Scanner;

public class StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Student> studentList = new ArrayList<>();

        System.out.println("📘 Student Grade Calculator");

        while (true) {
            System.out.print("\nEnter student name: ");
            String name = input.nextLine();

            int sub1 = getValidMark(input, "Subject 1");
            int sub2 = getValidMark(input, "Subject 2");
            int sub3 = getValidMark(input, "Subject 3");

            studentList.add(new Student(name, sub1, sub2, sub3));

            System.out.print("Do you want to add another student? (yes/no): ");
            String choice = input.nextLine().trim().toLowerCase();

            if (!choice.equals("yes")) {
                break;
            }
        }

        System.out.println("\n🎓 All Student Results:");
        for (Student s : studentList) {
            s.display();
        }

        input.close();
    }

    private static int getValidMark(Scanner input, String subject) {
        int mark;
        while (true) {
            System.out.print("Enter marks for " + subject + " (0-100): ");
            if (input.hasNextInt()) {
                mark = input.nextInt();
                input.nextLine(); // consume newline
                if (mark >= 0 && mark <= 100) {
                    return mark;
                } else {
                    System.out.println("❌ Marks must be between 0 and 100. Try again.");
                }
            } else {
                System.out.println("❌ Invalid input. Please enter a number.");
                input.nextLine(); // clear invalid input
            }
        }
    }
}
