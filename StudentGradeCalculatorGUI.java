import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class StudentGradeCalculatorGUI {
    JFrame frame;
    JTextField nameField, sub1Field, sub2Field, sub3Field;
    JTextArea resultArea;
    ArrayList<Student> studentList = new ArrayList<>();

    public StudentGradeCalculatorGUI() {
        frame = new JFrame("🎓 Student Grade Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setMinimumSize(new Dimension(600, 500));
        frame.setLocationRelativeTo(null); 
        frame.setLayout(new GridBagLayout()); // Main layout to center the panel

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(255, 255, 255));
        mainPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 149, 237), 2), // Cornflower Blue
                BorderFactory.createEmptyBorder(20, 30, 20, 30) // Padding
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8); // spacing
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        JLabel title = new JLabel("Student Grade Calculator", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(new Color(25, 25, 112));
        mainPanel.add(title, gbc);

        // Name
        gbc.gridy++;
        gbc.gridwidth = 1;
        mainPanel.add(new JLabel("Student Name:"), gbc);

        gbc.gridx = 1;
        nameField = new JTextField(20);
        mainPanel.add(nameField, gbc);

        // Subject 1
        gbc.gridy++;
        gbc.gridx = 0;
        mainPanel.add(new JLabel("Subject 1 Marks:"), gbc);
        gbc.gridx = 1;
        sub1Field = new JTextField(20);
        mainPanel.add(sub1Field, gbc);

        // Subject 2
        gbc.gridy++;
        gbc.gridx = 0;
        mainPanel.add(new JLabel("Subject 2 Marks:"), gbc);
        gbc.gridx = 1;
        sub2Field = new JTextField(20);
        mainPanel.add(sub2Field, gbc);

        // Subject 3
        gbc.gridy++;
        gbc.gridx = 0;
        mainPanel.add(new JLabel("Subject 3 Marks:"), gbc);
        gbc.gridx = 1;
        sub3Field = new JTextField(20);
        mainPanel.add(sub3Field, gbc);

        // Button
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        JButton calcButton = new JButton("Add Student");
        calcButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        calcButton.setBackground(new Color(60, 179, 113));
        calcButton.setForeground(Color.WHITE);
        calcButton.setFocusPainted(false);
        mainPanel.add(calcButton, gbc);

        // Results
        gbc.gridy++;
        resultArea = new JTextArea(10, 30);
        resultArea.setFont(new Font("Consolas", Font.PLAIN, 13));
        resultArea.setBackground(new Color(255, 250, 205));
        resultArea.setForeground(Color.BLACK);
        resultArea.setBorder(BorderFactory.createLineBorder(new Color(100, 149, 237)));
        resultArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(resultArea);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        mainPanel.add(scrollPane, gbc);

        // Add main panel to frame (centered)
        frame.add(mainPanel, new GridBagConstraints());

        // Button functionality
        calcButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        frame.setVisible(true);
    }

    private void addStudent() {
        try {
            String name = nameField.getText().trim();
            int sub1 = Integer.parseInt(sub1Field.getText().trim());
            int sub2 = Integer.parseInt(sub2Field.getText().trim());
            int sub3 = Integer.parseInt(sub3Field.getText().trim());

            if (isValid(sub1) && isValid(sub2) && isValid(sub3)) {
                Student s = new Student(name, sub1, sub2, sub3);
                studentList.add(s);
                resultArea.append("🎯 " + name + " | Total: " + s.total + " | Avg: " +
                        String.format("%.2f", s.average) + " | Grade: " + s.grade + "\n");
                clearFields();
            } else {
                JOptionPane.showMessageDialog(frame, "❗ Marks must be between 0 and 100.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "⚠️ Please enter valid numbers for marks.");
        }
    }

    private boolean isValid(int mark) {
        return mark >= 0 && mark <= 100;
    }

    private void clearFields() {
        nameField.setText("");
        sub1Field.setText("");
        sub2Field.setText("");
        sub3Field.setText("");
    }

    public static void main(String[] args) {
        new StudentGradeCalculatorGUI();
    }
}
