/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cms;

import com.mycompany.cms.models.Lecturer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LecturerDAO {
  

    public boolean insertLecturer(Lecturer lecturer) {
        String sql = "INSERT INTO Lecturers(name, role, expertise) VALUES(?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, lecturer.getName());
            pstmt.setString(2, lecturer.getRole());
            pstmt.setString(3, lecturer.getExpertise());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                // Retrieve and set the auto-generated key
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        lecturer.setLecturerID(generatedKeys.getInt(1));
                    } else {
                        throw new SQLException("Creating lecturer failed, no ID obtained.");
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static Lecturer selectLecturer(int lecturerID) {
        String sql = "SELECT * FROM Lecturers WHERE lecturerID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, lecturerID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Lecturer(
                        rs.getInt("lecturerID"),
                        rs.getString("name"),
                        rs.getString("role"),
                        rs.getString("expertise"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Lecturer> selectAllLecturers() {
        List<Lecturer> lecturers = new ArrayList<>();
        String sql = "SELECT * FROM Lecturers";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lecturers.add(new Lecturer(
                        rs.getInt("lecturerID"),
                        rs.getString("name"),
                        rs.getString("role"),
                        rs.getString("expertise")));
            }
            return lecturers;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean updateLecturer(Lecturer lecturer) {
        String sql = "UPDATE Lecturers SET name = ?, role = ?, expertise = ? WHERE lecturerID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, lecturer.getName());
            pstmt.setString(2, lecturer.getRole());
            pstmt.setString(3, lecturer.getExpertise());
            pstmt.setInt(4, lecturer.getLecturerID());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean deleteLecturer(int lecturerID) {
        String sql = "DELETE FROM Lecturers WHERE lecturerID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, lecturerID);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
