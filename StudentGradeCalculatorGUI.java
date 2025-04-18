import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StudentGradeCalculatorGUI {
    JFrame frame;
    JTextField nameField;
    JPanel subjectsPanel;
    JTextArea resultArea;
    ArrayList<Student> studentList = new ArrayList<>();
    ArrayList<JTextField> subjectFields = new ArrayList<>();

    public StudentGradeCalculatorGUI() {
        frame = new JFrame("🎓 Student Grade Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);  
        frame.setMinimumSize(new Dimension(800, 600)); 
        frame.setLocationRelativeTo(null); // Centering the frame on screen
        frame.setLayout(new GridBagLayout());

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 149, 237), 2),
                BorderFactory.createEmptyBorder(20, 30, 20, 30)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        JLabel title = new JLabel("Student Grade Calculator", SwingConstants.CENTER);  
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(new Color(25, 25, 112));
        mainPanel.add(title, gbc);

        // Student name
        gbc.gridy++;
        gbc.gridwidth = 1;
        mainPanel.add(new JLabel("Student Name:"), gbc);

        gbc.gridx = 1;
        nameField = new JTextField(20);
        mainPanel.add(nameField, gbc);

        // Panel for subject fields
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        subjectsPanel = new JPanel(new GridBagLayout());
        subjectsPanel.setBackground(Color.WHITE);
        JScrollPane subjectScroll = new JScrollPane(subjectsPanel);
        subjectScroll.setPreferredSize(new Dimension(350, 150));
        mainPanel.add(subjectScroll, gbc);

        addSubjectField(); // Add initial subject field
        addSubjectField(); // Add second subject
        addSubjectField(); // Add third subject

        // Add Subject Button
        gbc.gridy++;
        JButton addSubjectBtn = new JButton("➕ Add Subject");
        addSubjectBtn.setBackground(new Color(70, 130, 180));
        addSubjectBtn.setForeground(Color.WHITE);
        addSubjectBtn.setFocusPainted(false);
        mainPanel.add(addSubjectBtn, gbc);

        addSubjectBtn.addActionListener(e -> addSubjectField());

        // Add Student Button
        gbc.gridy++;
        JButton calcButton = new JButton("✅ Add Student");  
        calcButton.setBackground(new Color(60, 179, 113));
        calcButton.setForeground(Color.WHITE);
        calcButton.setFocusPainted(false);
        mainPanel.add(calcButton, gbc);

        // Results
        gbc.gridy++;
        resultArea = new JTextArea(10, 30);
        resultArea.setFont(new Font("Consolas", Font.PLAIN, 13));
        resultArea.setBackground(new Color(255, 250, 205));
        resultArea.setBorder(BorderFactory.createLineBorder(new Color(100, 149, 237)));
        resultArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(resultArea);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        mainPanel.add(scrollPane, gbc);

        frame.add(mainPanel, new GridBagConstraints());
        frame.setVisible(true);

        calcButton.addActionListener(e -> addStudent());
    }

    private void addSubjectField() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = subjectFields.size();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel label = new JLabel("Subject " + (subjectFields.size() + 1) + " Marks:");
        JTextField field = new JTextField(15);

        subjectFields.add(field);
        subjectsPanel.add(label, gbc);

        gbc.gridx = 1;
        subjectsPanel.add(field, gbc);

        subjectsPanel.revalidate();
        subjectsPanel.repaint();
    }

    private void addStudent() {
        try {
            String name = nameField.getText().trim();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "❗ Student name cannot be empty.");
                return;
            }

            int total = 0;
            int validSubjects = 0;

            // Collecting marks from the subject fields
            for (JTextField field : subjectFields) {
                String text = field.getText().trim();
                if (!text.isEmpty()) {
                    int mark = Integer.parseInt(text);
                    if (mark < 0 || mark > 100) {
                        JOptionPane.showMessageDialog(frame, "❗ Marks must be between 0 and 100.");
                        return;
                    }
                    total += mark;
                    validSubjects++;
                }
            }

            // If no subjects were entered
            if (validSubjects == 0) {
                JOptionPane.showMessageDialog(frame, "❗ Please enter marks for at least one subject.");
                return;
            }

            // Calculate average
            double average = (double) total / validSubjects;
            String grade = getGrade(average);

            // Creating student object with the modified constructor
            Student s = new Student(name, total, average, Student.Grade.valueOf(grade));

            studentList.add(s);

            // Displaying the result
            resultArea.setText(""); 
            resultArea.append(String.format("%-15s %-15s %-10s %-10s\n", "Name", "Total Marks", "Average", "Grade"));
            resultArea.append("------------------------------------------------------------\n");

            // Display all student results
            for (Student student : studentList) {
                resultArea.append(String.format("%-15s %-15d %-10.2f %-10s\n", student.getName(), student.getTotal(), student.getAverage(), student.getGrade()));
            }

            clearFields();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "⚠️ Please enter valid numbers for marks.");
        }
    }

    private String getGrade(double avg) {
        if (avg >= 75) return "A";
        else if (avg >= 60) return "B";
        else if (avg >= 50) return "C";
        else if (avg >= 35) return "D";
        else return "F";
    }

    private void clearFields() {
        nameField.setText("");
        for (JTextField field : subjectFields) {
            field.setText("");
        }
    }

    public static void main(String[] args) {
        new StudentGradeCalculatorGUI();
    }
}
