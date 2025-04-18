public class Student {
    String name;
    int sub1, sub2, sub3;
    int total;
    double average;
    char grade;

    public Student(String name, int sub1, int sub2, int sub3) {
        this.name = name;
        this.sub1 = sub1;
        this.sub2 = sub2;
        this.sub3 = sub3;
        calculateResult();
    }

    private void calculateResult() {
        total = sub1 + sub2 + sub3;
        average = total / 3.0;

        if (average >= 75) {
            grade = 'A';
        } else if (average >= 60) {
            grade = 'B';
        } else if (average >= 50) {
            grade = 'C';
        } else if (average >= 35) {
            grade = 'D';
        } else {
            grade = 'F';
        }
    }

    public void display() {
        System.out.println("\n----- Result for " + name + " -----");
        System.out.println("Subject 1: " + sub1);
        System.out.println("Subject 2: " + sub2);
        System.out.println("Subject 3: " + sub3);
        System.out.println("Total: " + total);
        System.out.println("Average: " + String.format("%.2f", average));
        System.out.println("Grade: " + grade);
    }
}
