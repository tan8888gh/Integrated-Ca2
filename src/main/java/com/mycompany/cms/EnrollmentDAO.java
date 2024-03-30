/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cms;



import com.mycompany.cms.models.Enrollment;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAO {
   
  
    public int getEnrolledStudents(int courseID) {
        String sql = "SELECT COUNT(*) AS total FROM Enrollments WHERE courseID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, courseID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0; // Return 0 if there's an error or no students are enrolled
    }

    public boolean insertEnrollment(Enrollment enrollment) {
        String sql = "INSERT INTO Enrollments(studentID, courseID, grade, status) VALUES(?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, enrollment.getStudentID());
            pstmt.setInt(2, enrollment.getCourseID());
            pstmt.setString(3, enrollment.getGrade());
            pstmt.setString(4, enrollment.getStatus());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Enrollment selectEnrollment(int enrollmentID) {
        String sql = "SELECT * FROM Enrollments WHERE enrollmentID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, enrollmentID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Enrollment(
                        rs.getInt("enrollmentID"),
                        rs.getInt("studentID"),
                        rs.getInt("courseID"),
                        rs.getString("grade"),
                        rs.getString("status"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Enrollment> selectAllEnrollments() {
        List<Enrollment> enrollments = new ArrayList<>();
        String sql = "SELECT * FROM Enrollments";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                enrollments.add(new Enrollment(
                        rs.getInt("enrollmentID"),
                        rs.getInt("studentID"),
                        rs.getInt("courseID"),
                        rs.getString("grade"),
                        rs.getString("status")));
            }
            return enrollments;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean updateEnrollment(Enrollment enrollment) {
        String sql = "UPDATE Enrollments SET studentID = ?, courseID = ?, grade = ?, status = ? WHERE enrollmentID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, enrollment.getStudentID());
            pstmt.setInt(2, enrollment.getCourseID());
            pstmt.setString(3, enrollment.getGrade());
            pstmt.setString(4, enrollment.getStatus());
            pstmt.setInt(5, enrollment.getEnrollmentID());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean deleteEnrollment(int enrollmentID) {
        String sql = "DELETE FROM Enrollments WHERE enrollmentID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, enrollmentID);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
