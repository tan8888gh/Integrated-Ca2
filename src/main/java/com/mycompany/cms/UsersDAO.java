/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cms;

import com.mycompany.cms.models.Users;
import java.sql.*;

public class UsersDAO {

public Users getUserByUsernameAndPassword(String username, String password) throws SQLException {
    String sql = "SELECT * FROM Users WHERE username = ? AND password = ?";
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, username);
        pstmt.setString(2, password);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return new Users(
                    rs.getInt("userID"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("role"));
        }
    }
    return null;
}

    public boolean insertUser(Users user) {
        String sql = "INSERT INTO Users(username, password, role) VALUES(?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword()); // Note: It's crucial to hash passwords in production applications.
            pstmt.setString(3, user.getRole());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Users selectUser(int userID) {
        String sql = "SELECT * FROM Users WHERE userID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Users(
                        rs.getInt("userID"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role"));
            }
//            return Users;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public boolean updateUser(Users user) {
        String sql = "UPDATE Users SET username = ?, password = ?, role = ? WHERE userID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword()); // Again, password hashing is recommended.
            pstmt.setString(3, user.getRole());
            pstmt.setInt(4, user.getUserID());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean deleteUser(int userID) {
        String sql = "DELETE FROM Users WHERE userID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userID);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}

