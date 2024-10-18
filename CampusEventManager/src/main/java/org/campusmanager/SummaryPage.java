package org.campusmanager;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class SummaryPage extends JFrame {
    public SummaryPage(int eventId) {
        // Set up the frame
        JFrame summaryFrame = new JFrame("User Summary");
        summaryFrame.setSize(600, 700);
        summaryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        summaryFrame.getContentPane().setBackground(Color.decode("#F0F4F8"));

        JPanel summaryPanel = new JPanel();
        summaryPanel.setLayout(new GridBagLayout());
        summaryPanel.setBackground(Color.decode("#F0F4F8"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        try {
            Conn conn = new Conn();

            // Get total users count for the specific event
            String totalUsersQuery = "SELECT COUNT(DISTINCT r.user_id) AS total_users " +
                    "FROM registrations r " +
                    "WHERE r.event_id = ?";
            PreparedStatement totalUsersStmt = conn.connection.prepareStatement(totalUsersQuery);
            totalUsersStmt.setInt(1, eventId);
            ResultSet totalUsersRs = totalUsersStmt.executeQuery();
            totalUsersRs.next();
            int totalUsers = totalUsersRs.getInt("total_users");
            JLabel totalUsersLabel = new JLabel("Total Users: " + totalUsers);
            totalUsersLabel.setFont(new Font("Arial", Font.BOLD, 24));
            totalUsersLabel.setForeground(Color.decode("#333333"));
            summaryPanel.add(totalUsersLabel, gbc);

            // Add separator below Total Users
            gbc.gridy++;
            summaryPanel.add(new JSeparator(), gbc);

            // Get gender counts for the specific event
            String genderCountQuery = "SELECT u.gender, COUNT(DISTINCT r.user_id) AS count " +
                    "FROM registrations r " +
                    "JOIN users u ON r.user_id = u.user_id " +
                    "WHERE r.event_id = ? " +
                    "GROUP BY u.gender";
            PreparedStatement genderCountStmt = conn.connection.prepareStatement(genderCountQuery);
            genderCountStmt.setInt(1, eventId);
            ResultSet genderCountRs = genderCountStmt.executeQuery();
            int maleCount = 0, femaleCount = 0, otherCount = 0;

            while (genderCountRs.next()) {
                String gender = genderCountRs.getString("gender");
                int count = genderCountRs.getInt("count");
                switch (gender) {
                    case "Male":
                        maleCount = count;
                        break;
                    case "Female":
                        femaleCount = count;
                        break;
                    default:
                        otherCount = count;
                }
            }

            gbc.gridy++;
            JLabel maleCountLabel = new JLabel("Male Users: " + maleCount);
            maleCountLabel.setFont(new Font("Arial", Font.BOLD, 18));
            maleCountLabel.setForeground(Color.decode("#2B2D42"));
            summaryPanel.add(maleCountLabel, gbc);

            gbc.gridy++;
            JLabel femaleCountLabel = new JLabel("Female Users: " + femaleCount);
            femaleCountLabel.setFont(new Font("Arial", Font.BOLD, 18));
            femaleCountLabel.setForeground(Color.decode("#2B2D42"));
            summaryPanel.add(femaleCountLabel, gbc);

            gbc.gridy++;
            JLabel otherCountLabel = new JLabel("Other Users: " + otherCount);
            otherCountLabel.setFont(new Font("Arial", Font.BOLD, 18));
            otherCountLabel.setForeground(Color.decode("#2B2D42"));
            summaryPanel.add(otherCountLabel, gbc);

            // Add separator below Other Users
            gbc.gridy++;
            summaryPanel.add(new JSeparator(), gbc);

            // Get branch counts for the specific event
            String branchCountQuery = "SELECT u.branch, COUNT(DISTINCT r.user_id) AS count " +
                    "FROM registrations r " +
                    "JOIN users u ON r.user_id = u.user_id " +
                    "WHERE r.event_id = ? " +
                    "GROUP BY u.branch";
            PreparedStatement branchCountStmt = conn.connection.prepareStatement(branchCountQuery);
            branchCountStmt.setInt(1, eventId);
            ResultSet branchCountRs = branchCountStmt.executeQuery();
            int itCount = 0, csCount = 0, cseCount = 0, extcCount = 0;

            while (branchCountRs.next()) {
                String branch = branchCountRs.getString("branch");
                int count = branchCountRs.getInt("count");
                switch (branch) {
                    case "IT":
                        itCount = count;
                        break;
                    case "CS":
                        csCount = count;
                        break;
                    case "CSE":
                        cseCount = count;
                        break;
                    case "EXTC":
                        extcCount = count;
                        break;
                }
            }

            gbc.gridy++;
            JLabel itCountLabel = new JLabel("IT Students: " + itCount);
            itCountLabel.setFont(new Font("Arial", Font.BOLD, 18));
            itCountLabel.setForeground(Color.decode("#2B2D42"));
            summaryPanel.add(itCountLabel, gbc);

            gbc.gridy++;
            JLabel csCountLabel = new JLabel("CS Students: " + csCount);
            csCountLabel.setFont(new Font("Arial", Font.BOLD, 18));
            csCountLabel.setForeground(Color.decode("#2B2D42"));
            summaryPanel.add(csCountLabel, gbc);

            gbc.gridy++;
            JLabel cseCountLabel = new JLabel("CSE Students: " + cseCount);
            cseCountLabel.setFont(new Font("Arial", Font.BOLD, 18));
            cseCountLabel.setForeground(Color.decode("#2B2D42"));
            summaryPanel.add(cseCountLabel, gbc);

            gbc.gridy++;
            JLabel extcCountLabel = new JLabel("EXTC Students: " + extcCount);
            extcCountLabel.setFont(new Font("Arial", Font.BOLD, 18));
            extcCountLabel.setForeground(Color.decode("#2B2D42"));
            summaryPanel.add(extcCountLabel, gbc);

            // Add separator below EXTC Students
            gbc.gridy++;
            summaryPanel.add(new JSeparator(), gbc);

            // Get year counts for the specific event
            String yearCountQuery = "SELECT u.college_year, COUNT(DISTINCT r.user_id) AS count " +
                    "FROM registrations r " +
                    "JOIN users u ON r.user_id = u.user_id " +
                    "WHERE r.event_id = ? " +
                    "GROUP BY u.college_year";
            PreparedStatement yearCountStmt = conn.connection.prepareStatement(yearCountQuery);
            yearCountStmt.setInt(1, eventId);
            ResultSet yearCountRs = yearCountStmt.executeQuery();
            int firstYearCount = 0, secondYearCount = 0, thirdYearCount = 0, finalYearCount = 0;

            while (yearCountRs.next()) {
                String year = yearCountRs.getString("college_year");
                int count = yearCountRs.getInt("count");
                switch (year) {
                    case "First":
                        firstYearCount = count;
                        break;
                    case "Second":
                        secondYearCount = count;
                        break;
                    case "Third":
                        thirdYearCount = count;
                        break;
                    case "Final":
                        finalYearCount = count;
                        break;
                }
            }

            gbc.gridy++;
            JLabel firstYearCountLabel = new JLabel("First Year: " + firstYearCount);
            firstYearCountLabel.setFont(new Font("Arial", Font.BOLD, 18));
            firstYearCountLabel.setForeground(Color.decode("#2B2D42"));
            summaryPanel.add(firstYearCountLabel, gbc);

            gbc.gridy++;
            JLabel secondYearCountLabel = new JLabel("Second Year: " + secondYearCount);
            secondYearCountLabel.setFont(new Font("Arial", Font.BOLD, 18));
            secondYearCountLabel.setForeground(Color.decode("#2B2D42"));
            summaryPanel.add(secondYearCountLabel, gbc);

            gbc.gridy++;
            JLabel thirdYearCountLabel = new JLabel("Third Year: " + thirdYearCount);
            thirdYearCountLabel.setFont(new Font("Arial", Font.BOLD, 18));
            thirdYearCountLabel.setForeground(Color.decode("#2B2D42"));
            summaryPanel.add(thirdYearCountLabel, gbc);

            gbc.gridy++;
            JLabel finalYearCountLabel = new JLabel("Final Year: " + finalYearCount);
            finalYearCountLabel.setFont(new Font("Arial", Font.BOLD, 18));
            finalYearCountLabel.setForeground(Color.decode("#2B2D42"));
            summaryPanel.add(finalYearCountLabel, gbc);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(summaryFrame, "Error retrieving summary data: " + e.getMessage());
        }

        summaryFrame.add(summaryPanel);
        summaryFrame.setLocationRelativeTo(null);
        summaryFrame.setVisible(true);
    }
}
