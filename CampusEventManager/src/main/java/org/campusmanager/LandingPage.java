package org.campusmanager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LandingPage {
    public static void main(String[] args) {
        // Create frame
        JFrame frame = new JFrame("Campus Event Manager");
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Use a layout manager for better performance and alignment
        frame.setLayout(new GridBagLayout());
        frame.getContentPane().setBackground(Color.decode("#EDF2F4"));
        // Create and configure title label
        JLabel titleLabel = new JLabel("Campus Event Manager", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(Color.decode("#2B2D42"));
        // Create constraints for the title label
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 50, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        frame.add(titleLabel, gbc);


        // Create buttons
        JButton button1 = new JButton("Login");
        JButton button2 = new JButton("SignUp");

        // Customize button appearance with rounded corners
        customizeButton(button1, Color.decode("#EF233C"), Color.decode("#EDF2F4"));
        customizeButton(button2, Color.decode("#EF233C"), Color.decode("#EDF2F4"));

        // Add action listeners to buttons to make them clickable
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Login();
//
            }
        });

        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new SignUp();
//                frame.dispose();
            }
        });

        // Add buttons to the frame
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(20, 100, 0, 20);  // Adjust spacing
        frame.add(button1, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(20, 20, 0, 100);  // Adjust spacing
        frame.add(button2, gbc);
        frame.setLocationRelativeTo(null);

        // Set frame visibility
        frame.setVisible(true);
    }

    // Method to customize button appearance with background, text color, and rounded corners
    private static void customizeButton(JButton button, Color backgroundColor, Color textColor) {
        button.setBackground(backgroundColor);
        button.setForeground(textColor);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setFocusPainted(false);

        // Add rounded border
        button.setBorder(BorderFactory.createLineBorder(backgroundColor));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));
    }
}
