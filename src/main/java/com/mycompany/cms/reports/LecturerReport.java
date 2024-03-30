/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cms.reports;



import com.mycompany.cms.DatabaseConnection;
import com.mycompany.cms.LecturerDAO;
import com.mycompany.cms.models.Course;
import com.mycompany.cms.models.Lecturer;
import com.mycompany.cms.models.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LecturerReport {

   public void generateReport(Users lecturerUser) {
//       System.out.print("Inside the generare report method.");
    int lecturerID = lecturerUser.getUserID(); // Assuming lecturerID is stored in userID for lecturerUser
    System.out.print(lecturerID);
    Lecturer lecturer = LecturerDAO.selectLecturer(lecturerID);
    
    if (lecturer != null) {
        System.out.println("Lecturer: " + lecturer.getName());
        List<Course> modulesTaught = fetchModulesTaughtByLecturer(lecturerID);
        if (modulesTaught != null) {
            System.out.println("Modules Taught:");
            for (Course module : modulesTaught) {
                System.out.println("- " + module.getName());
            }
        }
        Map<Integer, Integer> studentCountMap = fetchStudentCountForModules(modulesTaught);
        if (studentCountMap != null) {
            System.out.println("Number of Students:");
            for (Map.Entry<Integer, Integer> entry : studentCountMap.entrySet()) {
                System.out.println("- " + entry.getKey() + ": " + entry.getValue());
            }
        }
        List<String> expertiseList = fetchLecturerExpertise(lecturerID);
        if (expertiseList != null) {
            System.out.println("Expertise:");
            for (String expertise : expertiseList) {
                System.out.println("- " + expertise);
            }
        }
        System.out.println();
    } else {
        System.out.println("Lecturer not found.");
    }
}


    private List<Lecturer> fetchAllLecturers() {
        List<Lecturer> lecturers = new ArrayList<>();
        String sql = "SELECT * FROM Lecturers";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                lecturers.add(new Lecturer(
                        rs.getInt("lecturerID"),
                        rs.getString("name"),
                        rs.getString("role"),
                        rs.getString("expertise")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lecturers;
    }

    private List<Course> fetchModulesTaughtByLecturer(int lecturerID) {
        List<Course> modulesTaught = new ArrayList<>();
        String sql = "SELECT * FROM Courses WHERE lecturerID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, lecturerID);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    modulesTaught.add(new Course(
                            rs.getInt("courseID"),
                            rs.getString("name"),
                            rs.getString("program"),
                            rs.getInt("lecturerID"),
                            rs.getString("room")));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return modulesTaught;
    }

    private Map<Integer, Integer> fetchStudentCountForModules(List<Course> modules) {
        Map<Integer, Integer> studentCountMap = new HashMap<>();
        if (modules != null && !modules.isEmpty()) {
            String sql = "SELECT courseID, COUNT(studentID) AS studentCount FROM enrollments WHERE courseID IN (";
            StringBuilder placeholders = new StringBuilder();
            for (int i = 0; i < modules.size(); i++) {
                placeholders.append("?");
                if (i < modules.size() - 1) {
                    placeholders.append(", ");
                }
            }
            sql += placeholders.toString() + ") GROUP BY courseID";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                for (int i = 0; i < modules.size(); i++) {
                    pstmt.setInt(i + 1, modules.get(i).getCourseID());
                }
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        studentCountMap.put(rs.getInt("courseID"), rs.getInt("studentCount"));
                    }
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return studentCountMap;
    }

    private List<String> fetchLecturerExpertise(int lecturerID) {
        List<String> expertiseList = new ArrayList<>();
        String sql = "SELECT expertise FROM Lecturers WHERE lecturerID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, lecturerID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String expertise = rs.getString("expertise");
                    if (expertise != null && !expertise.isEmpty()) {
                        String[] expertiseArray = expertise.split(",");
                        for (String expertiseItem : expertiseArray) {
                            expertiseList.add(expertiseItem.trim());
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return expertiseList;
    }
}
