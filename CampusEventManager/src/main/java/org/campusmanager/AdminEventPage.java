package org.campusmanager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AdminEventPage extends JFrame {
    public AdminEventPage() {
        JFrame frame = new JFrame("Hosted Events");
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.decode("#EDF2F4"));

        // Create a panel to hold event labels and buttons
        JPanel eventPanel = new JPanel();
        eventPanel.setLayout(new GridBagLayout());
        eventPanel.setBackground(Color.decode("#EDF2F4"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        JLabel titleLabel = new JLabel("Hosted Events:");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        eventPanel.add(titleLabel, gbc);

        gbc.gridwidth = 1; // Reset gridwidth for following buttons

        try {
            Conn conn = new Conn(); // Ensure Conn is properly initialized
            String query = "SELECT event_id, event_name FROM events";
            ResultSet rs = conn.s.executeQuery(query);

            while (rs.next()) {
                String eventName = rs.getString("event_name");
                int eventId = rs.getInt("event_id");

                gbc.gridy++;
                JLabel eventLabel = new JLabel("* " + eventName);
                eventLabel.setFont(new Font("Arial", Font.PLAIN, 18));
                eventLabel.setForeground(Color.decode("#2B2D42"));

                JButton detailsButton = new JButton("Details");
                detailsButton.setFont(new Font("Arial", Font.BOLD, 16));
                detailsButton.setBackground(Color.decode("#EF233C"));
                detailsButton.setForeground(Color.decode("#EDF2F4"));
                detailsButton.setFocusPainted(false);
                detailsButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

                // Use a final variable to pass eventId to the ActionListener
                final int currentEventId = eventId;
                detailsButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new RegisteredUserPage(currentEventId); // Pass the current event ID
                    }
                });

                JButton deleteButton = new JButton("Delete");
                deleteButton.setFont(new Font("Arial", Font.BOLD, 16));
                deleteButton.setBackground(Color.decode("#EF233C"));
                deleteButton.setForeground(Color.decode("#EDF2F4"));
                deleteButton.setFocusPainted(false);
                deleteButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

                deleteButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int confirm = JOptionPane.showConfirmDialog(frame,
                                "Are you sure you want to delete the event: " + eventName + "?",
                                "Confirm Deletion", JOptionPane.YES_NO_OPTION);

                        if (confirm == JOptionPane.YES_OPTION) {
                            try {
                                String deleteQuery = "DELETE FROM events WHERE event_id = ?";
                                PreparedStatement pstmt = conn.connection.prepareStatement(deleteQuery);
                                pstmt.setInt(1, currentEventId);
                                pstmt.executeUpdate();

                                JOptionPane.showMessageDialog(frame, "Event deleted successfully!");
                                frame.dispose(); // Dispose the current frame
                                new AdminEventPage(); // Open a new instance of AdminEventPage
                            } catch (SQLException sqlEx) {
                                JOptionPane.showMessageDialog(frame, "Error deleting event: " + sqlEx.getMessage());
                            }
                        }
                    }
                });

                // Add components to the panel
                eventPanel.add(eventLabel, gbc);
                gbc.gridx = 1;
                eventPanel.add(detailsButton, gbc);
                gbc.gridx = 2;
                eventPanel.add(deleteButton, gbc);
                gbc.gridx = 0; // Reset x position for next row
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error fetching events: " + e.getMessage());
        }

        frame.setLocationRelativeTo(null);
        JScrollPane scrollPane = new JScrollPane(eventPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getViewport().setBackground(Color.decode("#EDF2F4"));

        frame.add(scrollPane);
        frame.setVisible(true);
    }
}
