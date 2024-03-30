/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cms;

import com.mycompany.cms.models.Feedback;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDAO {


    public boolean insertFeedback(Feedback feedback) {
        String sql = "INSERT INTO Feedback(courseID, studentID, comment, rating) VALUES(?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, feedback.getCourseID());
            pstmt.setInt(2, feedback.getStudentID());
            pstmt.setString(3, feedback.getComment());
            pstmt.setInt(4, feedback.getRating());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Feedback selectFeedback(int feedbackID) {
        String sql = "SELECT * FROM Feedback WHERE feedbackID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, feedbackID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Feedback(
                        rs.getInt("feedbackID"),
                        rs.getInt("courseID"),
                        rs.getInt("studentID"),
                        rs.getString("comment"),
                        rs.getInt("rating"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Feedback> selectAllFeedbacks() {
        List<Feedback> feedbacks = new ArrayList<>();
        String sql = "SELECT * FROM Feedback";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                feedbacks.add(new Feedback(
                        rs.getInt("feedbackID"),
                        rs.getInt("courseID"),
                        rs.getInt("studentID"),
                        rs.getString("comment"),
                        rs.getInt("rating")));
            }
            return feedbacks;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public boolean updateFeedback(Feedback feedback) {
        String sql = "UPDATE Feedback SET courseID = ?, studentID = ?, comment = ?, rating = ? WHERE feedbackID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, feedback.getCourseID());
            pstmt.setInt(2, feedback.getStudentID());
            pstmt.setString(3, feedback.getComment());
            pstmt.setInt(4, feedback.getRating());
            pstmt.setInt(5, feedback.getFeedbackID());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean deleteFeedback(int feedbackID) {
        String sql = "DELETE FROM Feedback WHERE feedbackID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, feedbackID);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
