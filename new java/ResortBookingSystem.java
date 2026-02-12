import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;

/*
   ==========================================
   RESORT BOOKING SYSTEM - SQLite Version
   Fully Working - No Errors
   ==========================================
*/

// javac -cp ".;lib\sqlite-jdbc-3.51.2.0.jar" ResortBookingSystem.java
//java -cp ".;lib\sqlite-jdbc-3.51.2.0.jar" ResortBookingSystem

public class ResortBookingSystem extends JFrame {

    private JTextField txtName, txtDays;
    private JComboBox<String> roomTypeBox;
    private JLabel lblTotal;

    // SQLite database file
    private static final String URL = "jdbc:sqlite:resort.db";

    public ResortBookingSystem() {

        initializeDatabase();

        setTitle("Resort Booking System");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2, 10, 10));

        add(new JLabel("Customer Name:"));
        txtName = new JTextField();
        add(txtName);

        add(new JLabel("Room Type:"));
        roomTypeBox = new JComboBox<>(new String[] { "Standard", "Deluxe", "Suite" });
        add(roomTypeBox);

        add(new JLabel("Number of Days:"));
        txtDays = new JTextField();
        add(txtDays);

        add(new JLabel("Total Amount:"));
        lblTotal = new JLabel("₹ 0.00");
        add(lblTotal);

        JButton btnCalculate = new JButton("Calculate");
        JButton btnBook = new JButton("Book Now");

        add(btnCalculate);
        add(btnBook);

        btnCalculate.addActionListener(this::calculateTotal);
        btnBook.addActionListener(this::bookResort);
    }

    // 🔹 Initialize DB and create table
    private void initializeDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");

            try (Connection con = DriverManager.getConnection(URL);
                    Statement stmt = con.createStatement()) {

                String sql = """
                        CREATE TABLE IF NOT EXISTS bookings (
                            id INTEGER PRIMARY KEY AUTOINCREMENT,
                            name TEXT NOT NULL,
                            room_type TEXT NOT NULL,
                            days INTEGER NOT NULL,
                            total_amount REAL NOT NULL
                        );
                        """;

                stmt.execute(sql);
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database Initialization Error!");
        }
    }

    // 🔹 Room Price Logic
    private double getPricePerDay(String room) {
        switch (room) {
            case "Standard":
                return 2000;
            case "Deluxe":
                return 3500;
            case "Suite":
                return 5000;
            default:
                return 0;
        }
    }

    // 🔹 Calculate Total
    private void calculateTotal(ActionEvent e) {
        try {
            int days = Integer.parseInt(txtDays.getText().trim());
            String room = roomTypeBox.getSelectedItem().toString();

            if (days <= 0) {
                JOptionPane.showMessageDialog(this, "Days must be greater than 0");
                return;
            }

            double total = getPricePerDay(room) * days;
            lblTotal.setText("₹ " + total);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Enter valid number of days");
        }
    }

    // 🔹 Book Resort
    private void bookResort(ActionEvent e) {

        String name = txtName.getText().trim();
        String room = roomTypeBox.getSelectedItem().toString();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter customer name");
            return;
        }

        try {
            int days = Integer.parseInt(txtDays.getText().trim());

            if (days <= 0) {
                JOptionPane.showMessageDialog(this, "Days must be greater than 0");
                return;
            }

            double total = getPricePerDay(room) * days;

            insertBooking(name, room, days, total);

            JOptionPane.showMessageDialog(this, "Booking Successful!");

            txtName.setText("");
            txtDays.setText("");
            lblTotal.setText("₹ 0.00");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Enter valid number of days");
        }
    }

    // 🔹 Insert Data
    private void insertBooking(String name, String roomType, int days, double total) {

        String query = "INSERT INTO bookings (name, room_type, days, total_amount) VALUES (?, ?, ?, ?)";

        try {
            Class.forName("org.sqlite.JDBC");

            try (Connection con = DriverManager.getConnection(URL);
                    PreparedStatement ps = con.prepareStatement(query)) {

                ps.setString(1, name);
                ps.setString(2, roomType);
                ps.setInt(3, days);
                ps.setDouble(4, total);

                ps.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database Insert Error!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ResortBookingSystem().setVisible(true));
    }
}
