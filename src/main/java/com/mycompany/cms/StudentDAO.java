/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cms;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.cms.models.Student;
import com.mycompany.cms.models.Course;

public class StudentDAO {

    public List<Course> findEnrolledModules(int studentID) {
        List<Course> enrolledModules = new ArrayList<>();
        String sql = "SELECT c.* FROM courses c JOIN enrollments e  WHERE e.studentID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, studentID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                enrolledModules.add(new Course(
                        rs.getInt("courseID"),
                        rs.getString("name"),
                        rs.getString("program"),
                        rs.getInt("lecturerID"),
                        rs.getString("room")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enrolledModules;
    }

    public List<Course> findCompletedModules(int studentID) {
        List<Course> completedModules = new ArrayList<>();
        // Modified to use the enrollments table for grade
        String sql = "SELECT c.* FROM courses c JOIN enrollments e ON c.courseID = e.courseID WHERE e.studentID = ? AND e.status = 'completed'";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, studentID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                completedModules.add(new Course(
                        rs.getInt("courseID"),
                        rs.getString("name"),
                        rs.getString("program"),
                        rs.getInt("lecturerID"),
                        rs.getString("room")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return completedModules;
    }

    // Assuming that repeating modules means a grade that requires improvement
    public List<Course> findRepeatModules(int studentID) {
        List<Course> repeatModules = new ArrayList<>();
        // Modified to use the enrollments table and its grade attribute
        String sql = "SELECT c.* FROM courses c JOIN enrollments e ON c.courseID = e.courseID WHERE e.studentID = ? AND e.status= 'repeating'"; // Assuming grade < 50 needs repeat
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, studentID);
//            stmt.setInt(2, 50); // Example threshold for repeat
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                repeatModules.add(new Course(
                        rs.getInt("courseID"),
                        rs.getString("name"),
                        rs.getString("program"),
                        rs.getInt("lecturerID"),
                        rs.getString("room")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return repeatModules;
    }
    public boolean insertStudent(Student student) {
        String sql = "INSERT INTO students(studentID, name, program) VALUES(?,?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, student.getId());
            pstmt.setString(2, student.getName());

            pstmt.setString(4, student.getProgram());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Student selectStudent(int id) {
        String sql = "SELECT * FROM students WHERE studentID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Student(
                        rs.getInt("studentID"),
                        rs.getString("name"),
                        rs.getString("program"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Student> selectAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)) {
            while (rs.next()) {
                students.add(new Student(
                        rs.getInt("studentID"),
                        rs.getString("name"),
                        rs.getString("program")));
            }
            return students;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean updateStudent(Student student) {
        String sql = "UPDATE students SET name = ?,  program = ? WHERE studentID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.getName());
            pstmt.setString(3, student.getProgram());
            pstmt.setInt(4, student.getId());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean deleteStudent(int id) {
        String sql = "DELETE FROM students WHERE studentID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    // The rest of the StudentDAO methods (insertStudent, updateStudent, deleteStudent, selectStudent, selectAllStudents) 
    // remain unchanged from your original submission as they are not affected by the absence of the grades table.
}
                                                                                             
























