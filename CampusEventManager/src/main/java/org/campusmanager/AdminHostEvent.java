
package org.campusmanager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AdminHostEvent extends JFrame {
    AdminHostEvent() {
        JFrame frame = new JFrame("Host Event");
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the background color of the window
        frame.getContentPane().setBackground(Color.decode("#EDF2F4"));

        // Set layout to null for manual positioning
        frame.setLayout(null);

        // Define left margin (reduced by 20%)
        int leftMargin = 160;

        // Set the text color for all labels
        Color textColor = Color.decode("#2B2D42");

        // Create and configure title label
        JLabel titleLabel = new JLabel("Host Event:");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(textColor);
        titleLabel.setBounds(leftMargin, 30, 200, 30);  // Title with adjusted left margin
        frame.add(titleLabel);

        // Labels for event details
        JLabel nameLabel = new JLabel("Event Name :");
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        nameLabel.setForeground(textColor);
        nameLabel.setBounds(leftMargin, 80, 150, 30);
        frame.add(nameLabel);

        JLabel descriptionLabel = new JLabel("Event Description :");
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        descriptionLabel.setForeground(textColor);
        descriptionLabel.setBounds(leftMargin, 130, 150, 30);
        frame.add(descriptionLabel);

        JLabel locationLabel = new JLabel("Event Location :");
        locationLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        locationLabel.setForeground(textColor);
        locationLabel.setBounds(leftMargin, 230, 150, 30);
        frame.add(locationLabel);

        JLabel dateLabel = new JLabel("Event Date :");
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        dateLabel.setForeground(textColor);
        dateLabel.setBounds(leftMargin, 290, 150, 30); // Adjusted y position
        frame.add(dateLabel);

        // Input fields
        JTextField nameField = new JTextField();
        nameField.setBounds(leftMargin + 170, 80, 300, 30);
        frame.add(nameField);

        JTextArea descriptionArea = new JTextArea();
        descriptionArea.setBounds(leftMargin + 170, 130, 300, 80);  // TextArea for description
        descriptionArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        frame.add(descriptionArea);

        JTextField locationField = new JTextField();
        locationField.setBounds(leftMargin + 170, 230, 300, 30);
        frame.add(locationField);

        JTextField dateField = new JTextField();
        dateField.setBounds(leftMargin + 170, 290, 300, 30); // Adjusted y position
        frame.add(dateField);

        // Host button
        JButton hostButton = new JButton("HOST");
        hostButton.setFont(new Font("Arial", Font.BOLD, 16));
        hostButton.setBackground(Color.decode("#EF233C"));  // Background color for the button
        hostButton.setForeground(Color.decode("#EDF2F4"));  // Text color inside the button
        hostButton.setBounds(leftMargin + 220, 380, 150, 40);  // Adjusted left margin for button
        frame.add(hostButton);
        frame.setLocationRelativeTo(null);

        hostButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get values from input fields
                String eventName = nameField.getText();
                String eventDescription = descriptionArea.getText();
                String eventLocation = locationField.getText();
                String eventDate = dateField.getText();

                // Validate inputs
                if (eventName.isEmpty() || eventDescription.isEmpty() || eventLocation.isEmpty() || eventDate.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please fill in all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Insert event into the database
                try {
                    Conn conn = new Conn();
                    String query = String.format("INSERT INTO events (event_name, description, event_date, event_location) VALUES ('%s', '%s', '%s', '%s')",
                            eventName, eventDescription, eventDate, eventLocation);

                    // Execute the query
                    Statement stmt = conn.connection.createStatement();
                    int rowsAffected = stmt.executeUpdate(query);

                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(frame, "Event hosted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        // Clear fields after successful hosting
                        nameField.setText("");
                        descriptionArea.setText("");
                        locationField.setText("");
                        dateField.setText("");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Error hosting event: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Set frame visibility
        frame.setVisible(true);

    }

}

