package org.campusmanager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class UserHomePage extends JFrame {
    private int userId; // Store the current user's ID

    public UserHomePage(int userId, String username) {
        this.userId = userId; // Initialize user ID
        // Set up the frame
        JFrame frame = new JFrame("User Home Page");
        frame.setSize(900, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.decode("#EDF2F4"));
        frame.setLayout(new BorderLayout()); // Set the layout to BorderLayout

        // Create a panel for the header
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.decode("#EDF2F4"));

        // Title label greeting the user
        JLabel titleLabel = new JLabel("Hello, " + username + "!", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerPanel.add(titleLabel, BorderLayout.CENTER); // Add the title label to the header panel

        // Create a button for registered events
        JButton registeredEventsButton = new JButton("Registered Events");
        registeredEventsButton.setFont(new Font("Arial", Font.BOLD, 14));
        registeredEventsButton.setBackground(Color.decode("#EF233C"));
        registeredEventsButton.setForeground(Color.decode("#EDF2F4"));
        registeredEventsButton.setFocusPainted(false);
        registeredEventsButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        registeredEventsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UserRegisteredEvents(userId); // Open UserRegisteredEvents page for the specific user
            }
        });

        headerPanel.add(registeredEventsButton, BorderLayout.EAST); // Add the button to the header panel

        // Create a panel to hold event labels and buttons
        JPanel eventPanel = new JPanel();
        eventPanel.setLayout(new GridBagLayout());
        eventPanel.setBackground(Color.decode("#EDF2F4"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;

        // Fetch events from the database
        try {
            Conn conn = new Conn();
            String query = "SELECT event_id, event_name, description, event_date FROM events"; // Include description
            ResultSet rs = conn.s.executeQuery(query);

            while (rs.next()) {
                String eventName = rs.getString("event_name");
                int eventId = rs.getInt("event_id");
                String eventDescription = rs.getString("description"); // Fetch the event description
                Date eventDate = rs.getDate("event_date"); // Fetch the event date

                gbc.gridy++;
                JLabel eventLabel = new JLabel("* " + eventName); // Add asterisk before event name
                eventLabel.setFont(new Font("Arial", Font.BOLD, 18)); // Set font to bold
                eventLabel.setForeground(Color.decode("#2B2D42")); // Set text color for event name

                // Create a label for the event date with a faded tone
                JLabel dateLabel = new JLabel("Date: " + eventDate.toString());
                dateLabel.setFont(new Font("Arial", Font.ITALIC, 14)); // Italic font for date
                dateLabel.setForeground(Color.decode("#888888")); // Faded tone color

                // Create register button for the event
                JButton registerButton = new JButton("Register");
                registerButton.setFont(new Font("Arial", Font.BOLD, 16));
                registerButton.setBackground(Color.decode("#EF233C")); // Button background color
                registerButton.setForeground(Color.decode("#EDF2F4")); // Button text color
                registerButton.setFocusPainted(false); // Remove the square focus effect
                registerButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Add padding to button

                // Add action listener to the button
                registerButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int response = JOptionPane.showConfirmDialog(frame,
                                "Do you want to register for " + eventName + "?",
                                "Confirm Registration",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.QUESTION_MESSAGE);

                        if (response == JOptionPane.YES_OPTION) {
                            registerUserForEvent(userId, eventId, eventName);
                        }
                    }
                });

                // Add event label, date label, and register button to the eventPanel
                gbc.gridx = 0; // Reset gridx for the event label
                eventPanel.add(eventLabel, gbc);
                gbc.gridx++; // Move to next column for the register button
                eventPanel.add(registerButton, gbc); // Register button to the right of event label
                gbc.gridx = 0; // Reset gridx for the next row

                // Add date label below the event name and button
                gbc.gridy++;
                eventPanel.add(dateLabel, gbc);

                // Add a JSeparator to create a line effect
                gbc.gridy++;
                JSeparator separator = new JSeparator();
                separator.setPreferredSize(new Dimension(700, 1)); // Set width and height
                eventPanel.add(separator, gbc);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Error fetching events: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        // Wrap the eventPanel in a JScrollPane
        JScrollPane scrollPane = new JScrollPane(eventPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getViewport().setBackground(Color.decode("#EDF2F4"));

        // Add the header panel and the scroll pane to the frame
        frame.add(headerPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void registerUserForEvent(int userId, int eventId, String eventName) {
        try {
            Conn conn = new Conn();
            String insertQuery = "INSERT INTO registrations (user_id, event_id) VALUES (?, ?)";
            PreparedStatement pstmt = conn.connection.prepareStatement(insertQuery);
            pstmt.setInt(1, userId);
            pstmt.setInt(2, eventId);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Successfully registered for " + eventName);
            } else {
                JOptionPane.showMessageDialog(this, "Registration failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error during registration: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

}
