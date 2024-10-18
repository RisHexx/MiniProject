package org.campusmanager;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class UserRegisteredEvents extends JFrame {
    private int userId;

    public UserRegisteredEvents(int userId) {
        this.userId = userId;

        // Set up the frame
        JFrame registeredEventsFrame = new JFrame("Registered Events");
        registeredEventsFrame.setSize(900, 500);
        registeredEventsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        registeredEventsFrame.getContentPane().setBackground(Color.decode("#EDF2F4"));

        JPanel eventsPanel = new JPanel();
        eventsPanel.setLayout(new GridBagLayout());
        eventsPanel.setBackground(Color.decode("#EDF2F4"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;

        try {
            Conn conn = new Conn(); // Create a new instance of Conn
            String query = "SELECT DISTINCT e.event_id, e.event_name, e.description, e.event_date, e.event_location " +
                    "FROM registrations r " +
                    "JOIN events e ON r.event_id = e.event_id " +
                    "WHERE r.user_id = ?";

            PreparedStatement pstmt = conn.connection.prepareStatement(query);
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            boolean hasEvents = false; // Flag to check if events are found

            while (rs.next()) {
                hasEvents = true; // Set flag if events are found
                String eventName = rs.getString("event_name");
                String description = rs.getString("description");
                Date eventDate = rs.getDate("event_date");
                String eventLocation = rs.getString("event_location");

                gbc.gridy++;
                JLabel eventLabel = new JLabel("* " + eventName);
                eventLabel.setFont(new Font("Arial", Font.BOLD, 18));
                eventLabel.setForeground(Color.decode("#2B2D42"));
                eventsPanel.add(eventLabel, gbc);

                gbc.gridy++;
                JLabel descLabel = new JLabel("Description: " + description);
                descLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                eventsPanel.add(descLabel, gbc);

                gbc.gridy++;
                JLabel dateLabel = new JLabel("Date: " + eventDate.toString());
                dateLabel.setFont(new Font("Arial", Font.ITALIC, 14));
                eventsPanel.add(dateLabel, gbc);

                gbc.gridy++;
                JLabel locationLabel = new JLabel("Location: " + eventLocation);
                locationLabel.setFont(new Font("Arial", Font.ITALIC, 14));
                eventsPanel.add(locationLabel, gbc);

                // Add a horizontal separator
                gbc.gridy++;
                JSeparator separator = new JSeparator();
                separator.setPreferredSize(new Dimension(700, 1));
                eventsPanel.add(separator, gbc);
            }

            // If no events found, display a message
            if (!hasEvents) {
                JLabel noEventsLabel = new JLabel("No registered events found.");
                noEventsLabel.setFont(new Font("Arial", Font.PLAIN, 18));
                noEventsLabel.setForeground(Color.decode("#2B2D42"));
                eventsPanel.add(noEventsLabel, gbc);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(registeredEventsFrame, "Error retrieving registered events: " + e.getMessage());
        }

        registeredEventsFrame.add(new JScrollPane(eventsPanel));
        registeredEventsFrame.setLocationRelativeTo(null);
        registeredEventsFrame.setVisible(true);
    }
}
