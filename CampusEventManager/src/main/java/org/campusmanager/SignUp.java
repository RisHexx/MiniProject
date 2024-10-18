package org.campusmanager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.Statement;

public class SignUp extends JFrame {
    public SignUp() {
        JFrame frame = new JFrame("Campus Event Manager");
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the background color of the frame
        frame.getContentPane().setBackground(Color.decode("#EDF2F4")); // Background color of the frame

        // Use GridBagLayout for alignment and spacing
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Adjusted spacing between elements

        // Create and configure title label
        JLabel titleLabel = new JLabel("SIGNUP PAGE", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28)); // Reduced font size
        titleLabel.setForeground(Color.decode("#2B2D42")); // Title color
        gbc.gridx = 0;
        gbc.gridy = -1; // Moved up one row index to shift the title higher
        gbc.gridwidth = 2;  // Span across two columns for centering
        gbc.anchor = GridBagConstraints.CENTER;
        frame.add(titleLabel, gbc);

        // Create labels for Name, Email, Password, Gender, College Year, and Branch
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 18)); // Reduced font size
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;  // Align to left
        gbc.anchor = GridBagConstraints.EAST;
        frame.add(nameLabel, gbc);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        frame.add(emailLabel, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        frame.add(passwordLabel, gbc);

        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        frame.add(genderLabel, gbc);

        JLabel collegeYearLabel = new JLabel("College Year:");
        collegeYearLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.EAST;
        frame.add(collegeYearLabel, gbc);

        JLabel branchLabel = new JLabel("Branch:");
        branchLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.EAST;
        frame.add(branchLabel, gbc);

        // Create text fields for Name, Email, and Password
        JTextField nameField = new JTextField(15);
        nameField.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        frame.add(nameField, gbc);

        JTextField emailField = new JTextField(15);
        emailField.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        frame.add(emailField, gbc);

        JPasswordField passwordField = new JPasswordField(15);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        frame.add(passwordField, gbc);

        // Create dropdowns for Gender, College Year, and Branch
        String[] genderOptions = {"Male", "Female", "Other"};
        JComboBox<String> genderComboBox = new JComboBox<>(genderOptions);
        genderComboBox.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        frame.add(genderComboBox, gbc);

        String[] collegeYears = {"First", "Second", "Third", "Final"};
        JComboBox<String> collegeYearComboBox = new JComboBox<>(collegeYears);
        collegeYearComboBox.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        frame.add(collegeYearComboBox, gbc);

        String[] branches = {"IT", "CS", "CSE", "EXTC"};
        JComboBox<String> branchComboBox = new JComboBox<>(branches);
        branchComboBox.setFont(new Font("Arial", Font.PLAIN, 18));
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        frame.add(branchComboBox, gbc);

        // Create sign up button
        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setFont(new Font("Arial", Font.BOLD, 18));
        signUpButton.setPreferredSize(new Dimension(150, 40));
        signUpButton.setBackground(Color.decode("#EF233C"));
        signUpButton.setForeground(Color.decode("#EDF2F4"));
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        frame.add(signUpButton, gbc);

//        signUpButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                String name = nameField.getText();
//                String email = emailField.getText();
//                String password = new String(passwordField.getPassword());
//                String gender = (String) genderComboBox.getSelectedItem();
//                String collegeYear = (String) collegeYearComboBox.getSelectedItem();
//                String branch = (String) branchComboBox.getSelectedItem();
//
//                if (email.equals("admin")) {
//                    JOptionPane.showMessageDialog(frame, "This Email Is Not Allowed");
//                } else {
//                    try {
//                        Conn c = new Conn();
//                        String query = String.format(
//                                "INSERT INTO users (username, email, password, gender, college_year, branch) " +
//                                        "VALUES ('%s', '%s', '%s', '%s', '%s', '%s');",
//                                name, email, password, gender, collegeYear, branch
//                        );
//                        int rowsAffected = c.s.executeUpdate(query);
//                        if (rowsAffected > 0) {
//                            JOptionPane.showMessageDialog(frame, "Sign Up Successful");
//                            new Login();
//                            frame.dispose();
//                        } else {
//                            JOptionPane.showMessageDialog(frame, "Failed to Sign Up");
//                        }
//                    } catch (Exception ex) {
//                        JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
//                    }
//                }
//            }
//        });

        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim();
                String email = emailField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();
                String gender = (String) genderComboBox.getSelectedItem();
                String collegeYear = (String) collegeYearComboBox.getSelectedItem();
                String branch = (String) branchComboBox.getSelectedItem();

                // Check for blank fields
                if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please fill in all fields.");
                    return;
                }

                // Check for restricted email
                if (email.equals("admin")) {
                    JOptionPane.showMessageDialog(frame, "This Email Is Not Allowed");
                    return;
                }

                try {
                    Conn c = new Conn();

                    // Check if email already exists
                    String emailCheckQuery = "SELECT COUNT(*) FROM users WHERE email = '" + email + "'";
                    Statement stmt = c.s;
                    ResultSet rs = stmt.executeQuery(emailCheckQuery);
                    rs.next();
                    int count = rs.getInt(1);
                    if (count > 0) {
                        JOptionPane.showMessageDialog(frame, "Email already in use. Please use a different email.");
                        return;
                    }

                    // If the email does not exist, proceed with sign up
                    String query = String.format(
                            "INSERT INTO users (username, email, password, gender, college_year, branch) " +
                                    "VALUES ('%s', '%s', '%s', '%s', '%s', '%s');",
                            name, email, password, gender, collegeYear, branch
                    );
                    int rowsAffected = c.s.executeUpdate(query);
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(frame, "Sign Up Successful");
                        new Login();
                        frame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Failed to Sign Up");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
                }
            }
        });


        frame.setLocationRelativeTo(null);  // Center the frame on the screen

        // Set frame visibility
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new SignUp();
    }
}


