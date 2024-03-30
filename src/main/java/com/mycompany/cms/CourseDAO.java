

package com.mycompany.cms;

import com.mycompany.cms.models.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    public Course findById(int courseID) {
        String sql = "SELECT * FROM Courses WHERE courseID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, courseID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Course(rs.getInt("courseID"), rs.getString("name"), rs.getString("program"), rs.getInt("lecturerID"), rs.getString("room"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Course> findAll() {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM Courses";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                courses.add(new Course(rs.getInt("courseID"), rs.getString("name"), rs.getString("program"), rs.getInt("lecturerID"), rs.getString("room")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    public boolean insert(Course course) {
        String sql = "INSERT INTO Courses (name, program, lecturerID, room) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, course.getName());
            stmt.setString(2, course.getProgram());
            stmt.setInt(3, course.getLecturerId());
            stmt.setString(4, course.getRoom());
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        course.setCourseID(generatedKeys.getInt(1));
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Course course) {
        String sql = "UPDATE Courses SET name = ?, program = ?, lecturerID = ?, room = ? WHERE courseID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, course.getName());
            stmt.setString(2, course.getProgram());
            stmt.setInt(3, course.getLecturerId());
            stmt.setString(4, course.getRoom());
            stmt.setInt(5, course.getCourseID());
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int courseID) {
        String sql = "DELETE FROM Courses WHERE courseID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, courseID);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
