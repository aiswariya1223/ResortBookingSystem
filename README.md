Resort Booking System (Java Swing & JDBC)

Team Members

[A Aiswariya] – [24Ubc201]

[Niksa sabu] – [24ubc252]

🚩 Problem Definition & Objective
Problem Statement

Manual management of resort bookings often leads to calculation errors, misplaced records, and inefficient handling of customer data. Hospitality staff require a centralized and reliable system to manage bookings, automate billing based on room categories, and store booking details permanently.

Objective

To develop a Mini Real-World Java Application that integrates the following:

GUI: A user-friendly interface for entering and managing resort booking details

Logic: Automated price calculation using the formula:
Total = (Price per Day) × Number of Days

Persistence: JDBC integration with SQLite to ensure permanent data storage

Reliability: Exception handling to manage invalid inputs and runtime errors

🛠 Features & Technologies Used
Core Features

Dynamic Room Pricing

Standard Room – ₹2000 per day

Deluxe Room – ₹3500 per day

Suite Room – ₹5000 per day

Database Management

Automatic table creation

Secure record insertion using SQL and PreparedStatement

Input Validation

Error dialogs for empty customer names

Validation for non-numeric or negative number of days

Auto-Reset Feature

Clears all input fields after successful booking and database insertion

🧰 Technologies Used

Programming Language: Java (JDK 17+)

GUI Toolkit: Java Swing & AWT

Components: JFrame, JTextField, JComboBox, JButton, JLabel

Layout Manager: GridLayout

Event Handling: ActionListeners

Database: SQLite (Embedded database – no server required)

JDBC Driver: sqlite-jdbc-3.51.2.0.jar

Conclusion

The Resort Booking System demonstrates the successful integration of Java Swing for GUI and JDBC with SQLite for database connectivity. The application automates billing calculations, ensures reliability through exception handling, and maintains permanent booking records.

