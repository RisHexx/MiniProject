package org.campusmanager;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class RegisteredUserPage extends JFrame {
    public RegisteredUserPage(int eventId) {
        JFrame registeredUsersFrame = new JFrame("Registered Users");
        registeredUsersFrame.setSize(800, 500);
        registeredUsersFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        registeredUsersFrame.getContentPane().setBackground(Color.decode("#EDF2F4"));

        // Create a main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.decode("#EDF2F4"));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding around the main panel

        // Create a panel for displaying users
        JPanel userPanel = new JPanel();
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS)); // Use BoxLayout for vertical stacking
        userPanel.setBackground(Color.decode("#EDF2F4"));

        // Title label
        JLabel titleLabel = new JLabel("Registered Users:");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.decode("#2B2D42"));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the title
        userPanel.add(titleLabel);
        userPanel.add(Box.createVerticalStrut(20)); // Add spacing between title and users list

        try {
            Conn conn = new Conn();
            // Updated query to reflect the new database schema
            String query = "SELECT Distinct u.username, u.branch, u.college_year " +
                    "FROM registrations r " +
                    "JOIN users u ON r.user_id = u.user_id " +
                    "WHERE r.event_id = ?";
            PreparedStatement pstmt = conn.connection.prepareStatement(query);
            pstmt.setInt(1, eventId);
            ResultSet rs = pstmt.executeQuery();

            boolean hasUsers = false; // Flag to check if users are found

            while (rs.next()) {
                hasUsers = true; // Set flag if users are found
                String username = rs.getString("username");
                String branch = rs.getString("branch");
                String collegeYear = rs.getString("college_year");

                // Create a label for each registered user
                JLabel userLabel = new JLabel("* " + username + " | Branch: " + branch + " | Year: " + collegeYear);
                userLabel.setFont(new Font("Arial", Font.PLAIN, 18));
                userLabel.setForeground(Color.decode("#2B2D42"));
                userLabel.setAlignmentX(Component.LEFT_ALIGNMENT); // Align labels to the left
                userPanel.add(userLabel);
                userPanel.add(Box.createVerticalStrut(10)); // Add spacing between user entries
            }

            // If no users found, display a message
            if (!hasUsers) {
                JLabel noUsersLabel = new JLabel("No users registered for this event.");
                noUsersLabel.setFont(new Font("Arial", Font.PLAIN, 18));
                noUsersLabel.setForeground(Color.decode("#2B2D42"));
                noUsersLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the message
                userPanel.add(noUsersLabel);
                userPanel.add(Box.createVerticalStrut(20)); // Add spacing below the message
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(registeredUsersFrame, "Error retrieving registered users: " + e.getMessage());
        }

        // Add the userPanel to the mainPanel (at the center)
        JScrollPane scrollPane = new JScrollPane(userPanel);
        scrollPane.setBorder(null); // Remove border from scroll pane
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Add Summary button
        JButton summaryButton = new JButton("Summary");
        summaryButton.setFont(new Font("Arial", Font.BOLD, 18));
        summaryButton.setBackground(Color.decode("#EF233C"));
        summaryButton.setForeground(Color.decode("#EDF2F4"));
        summaryButton.setFocusPainted(false);
        summaryButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Modify the action listener to pass the eventId to SummaryPage
        summaryButton.addActionListener(e -> {
            new SummaryPage(eventId); // Pass the eventId to SummaryPage constructor

        });

        // Create a button panel and align the button in the center at the bottom
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.decode("#EDF2F4"));
        buttonPanel.add(summaryButton);

        // Add the button panel at the bottom of the main panel
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add the main panel to the frame
        registeredUsersFrame.add(mainPanel);
        registeredUsersFrame.setLocationRelativeTo(null);
        registeredUsersFrame.setVisible(true);
    }

}

