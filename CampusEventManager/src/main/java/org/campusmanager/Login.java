package org.campusmanager;

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame {
    public Login() {

        JFrame frame = new JFrame("Campus Event Manager");
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.decode("#EDF2F4"));

        // Use GridBagLayout for alignment and spacing
        frame.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 10, 20, 10);

        // Create and configure title label
        JLabel titleLabel = new JLabel("LOGIN PAGE", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(Color.decode("#2B2D42"));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        frame.add(titleLabel, gbc);

        // Create labels for Email and Password
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        frame.add(emailLabel, gbc);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        frame.add(passwordLabel, gbc);

        // Create text fields for Email and Password
        JTextField emailField = new JTextField(15);
        emailField.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        frame.add(emailField, gbc);

        JPasswordField passwordField = new JPasswordField(15);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        frame.add(passwordField, gbc);

        // Create login button
        JButton loginButton = new JButton("LOGIN");
        loginButton.setFont(new Font("Arial", Font.BOLD, 20));
        loginButton.setPreferredSize(new Dimension(150, 50));
        loginButton.setBackground(Color.decode("#EF233C"));
        loginButton.setForeground(Color.decode("#EDF2F4"));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        frame.add(loginButton, gbc);
        frame.setLocationRelativeTo(null);

        // Add action listener for the login button
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());


                try {
                    Conn conn = new Conn();
                    String query = "SELECT user_id, username FROM users WHERE email = '" + email + "' AND password = '" + password + "'";
                    Statement stmt = conn.s;
                    ResultSet rs = stmt.executeQuery(query);

                    if (email.equals("admin") && password.equals("admin")) {

                        new AdminHome();
                    } else if (rs.next()) {

                        JOptionPane.showMessageDialog(frame, "Login Successful!");
                        int userId = rs.getInt("user_id");
                        String username = rs.getString("username");
                        new UserHomePage(userId, username);
                        frame.dispose();
                    } else {

                        JOptionPane.showMessageDialog(frame, "Invalid Credentials");
                    }


                    rs.close();
                    conn.s.close();
                    conn.connection.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
                }
            }
        });


        frame.setVisible(true);
    }
}
