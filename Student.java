public class Student {
    private String name;
    private int[] subjects;
    private int total;
    private double average;
    private Grade grade;

    // Enum for grades
    public enum Grade {
        A, B, C, D, F
    }

    // Constructor for Student class
    public Student(String name, int[] subjects) {
        this.name = name;
        this.subjects = subjects;
        calculateResult();
    }

    // Constructor with total, average, and grade 
    public Student(String name, int total, double average, Grade grade) {
        this.name = name;
        this.total = total;
        this.average = average;
        this.grade = grade;
    }

    private void calculateResult() {
        total = 0;
        for (int sub : subjects) {
            total += sub;
        }

        average = total / (double) subjects.length;

        if (average >= 75) {
            grade = Grade.A;
        } else if (average >= 60) {
            grade = Grade.B;
        } else if (average >= 50) {
            grade = Grade.C;
        } else if (average >= 35) {
            grade = Grade.D;
        } else {
            grade = Grade.F;
        }
    }

    public void display() {
        System.out.println("\n----- Result for " + name + " -----");
        for (int i = 0; i < subjects.length; i++) {
            System.out.println("Subject " + (i + 1) + ": " + subjects[i]);
        }
        System.out.println("Total: " + total);
        System.out.println("Average: " + String.format("%.2f", average));
        System.out.println("Grade: " + grade);
    }

    public String getName() {
        return name;
    }

    public int[] getSubjects() {
        return subjects;
    }

    public int getTotal() {
        return total;
    }

    public double getAverage() {
        return average;
    }

    public Grade getGrade() {
        return grade;
    }
}
