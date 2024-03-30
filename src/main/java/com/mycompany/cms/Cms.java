package com.mycompany.cms;

import com.mycompany.cms.models.Users;
import com.mycompany.cms.models.Lecturer;
import com.mycompany.cms.reports.LecturerReport;
import com.mycompany.cms.reports.StudentReportGenerator;
import com.mycompany.cms.reports.CourseReportGenerator;
import com.mycompany.cms.CourseDAO;
import com.mycompany.cms.EnrollmentDAO;
import java.sql.*;
import java.io.IOException;
import java.io.FileWriter;

import java.sql.SQLException;
import java.util.Scanner;

public class Cms {

    private static final Scanner scanner = new Scanner(System.in);
    private static final UsersDAO usersDAO = new UsersDAO();
    private static final LecturerDAO lecturerDAO = new LecturerDAO();
    private static final CourseDAO courseDAO = new CourseDAO();
    private static final EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
    public static void main(String[] args) {
        login();
    }

    private static void login() {
        System.out.println("Welcome to CMS.");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        try {
            Users user = usersDAO.getUserByUsernameAndPassword(username, password);
            if (user != null) {
                System.out.println("Login successful.");
                if (user.getRole().equals("Admin")) {
                    adminMenu();
                } else if (user.getRole().equals("Lecturer")) {
                    lecturerPanel(user);
                }else if(user.getRole().equals("Office")){ 
                    officePanel();
                } else {
                    System.out.println("You don't have admin or lecturer or privileges.");
                }
            } else {
                System.out.println("Invalid username or password.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

       private static void adminMenu() {
        while (true) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Add User");
            System.out.println("2. Update User");
            System.out.println("3. Delete User");
            System.out.println("4. Change Password");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addUser();
                    break;
                case 2:
                    updateUser();
                    break;
                case 3:
                    deleteUser();
                    break;
                case 4:
                    changePassword();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
    }

    private static void addUser() {
        System.out.println("\nAdd User:");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter role (Admin, Office, Lecturer): ");
        String role = scanner.nextLine();

        Users newUser = new Users(username, password, role);
        try {Connection conn = DatabaseConnection.getConnection();
            boolean success = usersDAO.insertUser(newUser);
            if (success) {
                System.out.println("User added successfully.");
            } else {
                System.out.println("Failed to add user.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void updateUser() {
        System.out.println("\nUpdate User:");
        System.out.print("Enter userID of user to update: ");
        int userID = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter new username: ");
        String newUsername = scanner.nextLine();
        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();
        System.out.print("Enter new role (Admin, Office, Lecturer): ");
        String newRole = scanner.nextLine();

        Users updatedUser = new Users(userID, newUsername, newPassword, newRole);
        try {Connection conn = DatabaseConnection.getConnection();
            boolean success = usersDAO.updateUser(updatedUser);
            if (success) {
                System.out.println("User updated successfully.");
            } else {
                System.out.println("Failed to update user.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void deleteUser() {
        System.out.println("\nDelete User:");
        System.out.print("Enter userID of user to delete: ");
        int userID = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        try {Connection conn = DatabaseConnection.getConnection();
            boolean success = usersDAO.deleteUser(userID);
            if (success) {
                System.out.println("User deleted successfully.");
            } else {
                System.out.println("Failed to delete user.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void changePassword() {
        // Implement change password functionality here
    }

    private static void lecturerPanel(Users lecturerUser) {
        while (true) {
            System.out.println("\nLecturer Panel:");
            System.out.println("1. Generate Lecturer Report");
            System.out.println("2. Change Username and Password");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    generateLecturerReport(lecturerUser);
                    break;
                case 2:
                    changeLecturerCredentials(lecturerUser);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
            }
        }
    }

    private static void generateLecturerReport(Users lecturerUser) {
        LecturerReport lecturerReport = new LecturerReport();
        lecturerReport.generateReport(lecturerUser);
    }

    private static void changeLecturerCredentials(Users lecturerUser) {

        System.out.println("\nChange Username and Password:");
        System.out.print("Enter new username: ");
        String newUsername = scanner.nextLine();
        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();
        
        // Ensure that the logged-in lecturer can only change their own username and password
        if (lecturerUser.getUsername().equals(lecturerUser.getUsername())) {
            try {Connection conn = DatabaseConnection.getConnection();
                lecturerUser.setUsername(newUsername);
                lecturerUser.setPassword(newPassword);

                 boolean success = usersDAO.updateUser(lecturerUser);

//                boolean success = usersDAO.updateUser(lecturerUser.getUserID(), newUsername, newPassword);
                if (success) {
                    System.out.println("Username and password updated successfully.");
                } else {
                    System.out.println("Failed to update username and password.");
                }
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("You don't have permission to change other users' credentials.");
        }
    }
    private static void officePanel() {
    while (true) {
        System.out.println("\nOffice Panel:");
        System.out.println("1. Generate Student Report");
        System.out.println("2. Generate Course Report");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");

        String option = scanner.nextLine();

        switch (option) {
            case "1":
                System.out.print("Enter Student ID: ");
                int studentID = scanner.nextInt();
                scanner.nextLine();  // consume newline
                String studentReport = StudentReportGenerator.generateStudentReport(studentID);
                if (studentReport != null && !studentReport.isEmpty()) {
                    exportReport(studentReport);
                }
                break;
            case "2":
                CourseReportGenerator crg = new CourseReportGenerator(courseDAO, lecturerDAO, enrollmentDAO);
                String courseReport = crg.generateReport();
                if (courseReport != null && !courseReport.isEmpty()) {
                    exportReport(courseReport);
                }
                break;
            case "3":
                System.out.println("Exiting Office Panel.");
                return;
            default:
                System.out.println("Invalid option. Please try again.");
                break;
        }
    }
}
    private static void exportReport(String report) {
        System.out.println("1. Export as Text");
        System.out.println("2. Export as CSV");
        System.out.print("Choose an option: ");
        String exportOption = scanner.nextLine();

        switch (exportOption) {
            case "1":
                exportAsText(report);
                break;
            case "2":
                exportAsCSV(report);
                break;
            default:
                System.out.println("Invalid option. Report not exported.");
                break;
        }
    }

    private static void exportAsText(String report) {
        try (FileWriter writer = new FileWriter("report.txt")) {
            writer.write(report);
            System.out.println("Report exported as text successfully.");
        } catch (IOException e) {
            System.out.println("Error exporting report: " + e.getMessage());
        }
    }

    private static void exportAsCSV(String report) {
 

    try {
        // Define the file name for the CSV export
        String fileName = "report.csv";

        // Create a FileWriter object to write to the CSV file
        FileWriter csvWriter = new FileWriter(fileName);

        // Parse the report and format it as CSV
        String formattedCSV = formatReportAsCSV(report);

        // Write the formatted CSV data to the CSV file
        csvWriter.write(formattedCSV);

        // Close the FileWriter
        csvWriter.flush();
        csvWriter.close();

        // Print a message to indicate that the export was successful
        System.out.println("Report exported as CSV successfully. File name: " + fileName);
    } catch (IOException e) {
        // Handle any IOException that occurs during file writing
        System.out.println("Error exporting report as CSV: " + e.getMessage());
    }

    }
    private static String formatReportAsCSV(String report) {
    StringBuilder csvBuilder = new StringBuilder();

    // Split the report into lines
    String[] lines = report.split("\n");

    // Iterate over each line of the report
    for (String line : lines) {
        // Split the line into fields based on the delimiter (e.g., tab or comma)
        String[] fields = line.split("\t"); // Change "\t" to the appropriate delimiter

        // Append each field to the CSV string, separated by commas
        for (int i = 0; i < fields.length; i++) {
            csvBuilder.append(fields[i]);

            // Add a comma after each field except for the last one
            if (i < fields.length - 1) {
                csvBuilder.append(",");
            }
        }

        // Add a newline character after each line
        csvBuilder.append("\n");
    }

    return csvBuilder.toString();
}


}























//package com.mycompany.cms;
//
//import com.mycompany.cms.models.Users;
//import com.mycompany.cms.models.Lecturer;
//import com.mycompany.cms.reports.LecturerReport;
//import com.mycompany.cms.reports.StudentReportGenerator;
//import com.mycompany.cms.reports.CourseReportGenerator;
//import com.mycompany.cms.CourseDAO;
//import com.mycompany.cms.EnrollmentDAO;
//import java.sql.*;
//import java.sql.SQLException;
//import java.util.Scanner;
//
//public class Cms {
//
//    private static final Scanner scanner = new Scanner(System.in);
//    private static final UsersDAO usersDAO = new UsersDAO();
//    private static final LecturerDAO lecturerDAO = new LecturerDAO();
//    private static final CourseDAO courseDAO = new CourseDAO();
//    private static final EnrollmentDAO enrollmentDAO = new EnrollmentDAO();
//    public static void main(String[] args) {
//        login();
//    }
//
//    private static void login() {
//        System.out.println("Welcome to CMS.");
//        System.out.print("Enter username: ");
//        String username = scanner.nextLine();
//        System.out.print("Enter password: ");
//        String password = scanner.nextLine();
//
//        try {
//            Users user = usersDAO.getUserByUsernameAndPassword(username, password);
//            if (user != null) {
//                System.out.println("Login successful.");
//                if (user.getRole().equals("Admin")) {
//                    adminMenu();
//                } else if (user.getRole().equals("Lecturer")) {
//                    lecturerPanel(user);
//                }else if(user.getRole().equals("Office")){ 
//                    officePanel();
//                } else {
//                    System.out.println("You don't have admin or lecturer or privileges.");
//                }
//            } else {
//                System.out.println("Invalid username or password.");
//            }
//        } catch (SQLException e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//    }
//
//       private static void adminMenu() {
//        while (true) {
//            System.out.println("\nAdmin Menu:");
//            System.out.println("1. Add User");
//            System.out.println("2. Update User");
//            System.out.println("3. Delete User");
//            System.out.println("4. Change Password");
//            System.out.println("5. Exit");
//            System.out.print("Enter your choice: ");
//            int choice = scanner.nextInt();
//            scanner.nextLine(); // Consume newline
//
//            switch (choice) {
//                case 1:
//                    addUser();
//                    break;
//                case 2:
//                    updateUser();
//                    break;
//                case 3:
//                    deleteUser();
//                    break;
//                case 4:
//                    changePassword();
//                    break;
//                case 5:
//                    System.out.println("Exiting...");
//                    return;
//                default:
//                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
//            }
//        }
//    }
//
//    private static void addUser() {
//        System.out.println("\nAdd User:");
//        System.out.print("Enter username: ");
//        String username = scanner.nextLine();
//        System.out.print("Enter password: ");
//        String password = scanner.nextLine();
//        System.out.print("Enter role (Admin, Office, Lecturer): ");
//        String role = scanner.nextLine();
//
//        Users newUser = new Users(username, password, role);
//        try {Connection conn = DatabaseConnection.getConnection();
//            boolean success = usersDAO.insertUser(newUser);
//            if (success) {
//                System.out.println("User added successfully.");
//            } else {
//                System.out.println("Failed to add user.");
//            }
//        } catch (SQLException e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//    }
//
//    private static void updateUser() {
//        System.out.println("\nUpdate User:");
//        System.out.print("Enter userID of user to update: ");
//        int userID = scanner.nextInt();
//        scanner.nextLine(); // Consume newline
//        System.out.print("Enter new username: ");
//        String newUsername = scanner.nextLine();
//        System.out.print("Enter new password: ");
//        String newPassword = scanner.nextLine();
//        System.out.print("Enter new role (Admin, Office, Lecturer): ");
//        String newRole = scanner.nextLine();
//
//        Users updatedUser = new Users(userID, newUsername, newPassword, newRole);
//        try {Connection conn = DatabaseConnection.getConnection();
//            boolean success = usersDAO.updateUser(updatedUser);
//            if (success) {
//                System.out.println("User updated successfully.");
//            } else {
//                System.out.println("Failed to update user.");
//            }
//        } catch (SQLException e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//    }
//
//    private static void deleteUser() {
//        System.out.println("\nDelete User:");
//        System.out.print("Enter userID of user to delete: ");
//        int userID = scanner.nextInt();
//        scanner.nextLine(); // Consume newline
//
//        try {Connection conn = DatabaseConnection.getConnection();
//            boolean success = usersDAO.deleteUser(userID);
//            if (success) {
//                System.out.println("User deleted successfully.");
//            } else {
//                System.out.println("Failed to delete user.");
//            }
//        } catch (SQLException e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//    }
//
//    private static void changePassword() {
//        // Implement change password functionality here
//    }
//
//    private static void lecturerPanel(Users lecturerUser) {
//        while (true) {
//            System.out.println("\nLecturer Panel:");
//            System.out.println("1. Generate Lecturer Report");
//            System.out.println("2. Change Username and Password");
//            System.out.println("3. Exit");
//            System.out.print("Enter your choice: ");
//            int choice = scanner.nextInt();
//            scanner.nextLine(); // Consume newline
//
//            switch (choice) {
//                case 1:
//                    generateLecturerReport(lecturerUser);
//                    break;
//                case 2:
//                    changeLecturerCredentials(lecturerUser);
//                    break;
//                case 3:
//                    System.out.println("Exiting...");
//                    return;
//                default:
//                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
//            }
//        }
//    }
//
//    private static void generateLecturerReport(Users lecturerUser) {
//        LecturerReport lecturerReport = new LecturerReport();
//        lecturerReport.generateReport(lecturerUser);
//    }
//
//    private static void changeLecturerCredentials(Users lecturerUser) {
//
//        System.out.println("\nChange Username and Password:");
//        System.out.print("Enter new username: ");
//        String newUsername = scanner.nextLine();
//        System.out.print("Enter new password: ");
//        String newPassword = scanner.nextLine();
//        
//        // Ensure that the logged-in lecturer can only change their own username and password
//        if (lecturerUser.getUsername().equals(lecturerUser.getUsername())) {
//            try {Connection conn = DatabaseConnection.getConnection();
//                lecturerUser.setUsername(newUsername);
//                lecturerUser.setPassword(newPassword);
//
//                 boolean success = usersDAO.updateUser(lecturerUser);
//
////                boolean success = usersDAO.updateUser(lecturerUser.getUserID(), newUsername, newPassword);
//                if (success) {
//                    System.out.println("Username and password updated successfully.");
//                } else {
//                    System.out.println("Failed to update username and password.");
//                }
//            } catch (SQLException e) {
//                System.out.println("Error: " + e.getMessage());
//            }
//        } else {
//            System.out.println("You don't have permission to change other users' credentials.");
//        }
//    }
//    private static void officePanel() {
//        while (true) {
//            System.out.println("\nOffice Panel:");
//            System.out.println("1. Generate Student Report");
//            System.out.println("2. Generate Course Report");
//            System.out.println("3. Exit");
//            System.out.print("Choose an option: ");
//
//            String option = scanner.nextLine();
//
//            switch (option) {
//                case "1":
//                    System.out.print("Enter Student ID: ");
//                    int studentID = scanner.nextInt();
//                    scanner.nextLine();  // consume newline
//                    StudentReportGenerator srg = new StudentReportGenerator();
//                    srg.generateStudentReport(studentID);
//                    break;
//                case "2":
//
//                    CourseReportGenerator crg = new CourseReportGenerator(courseDAO,lecturerDAO,enrollmentDAO);
//                    crg.generateReport();
//                    break;
//                case "3":
//                    System.out.println("Exiting Office Panel.");
//                    return;
//                default:
//                    System.out.println("Invalid option. Please try again.");
//                    break;
//            }
//        }
//    }
//}
//
