/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cms.models;

public class Lecturer {
    private int lecturerID;
    private String name;
    private String role; // Although an ENUM in the DB, represented as String in Java for simplicity
    private String expertise;
 public Lecturer() {
        this.lecturerID = 0;
        this.name = "";
        this.role = "";
        this.expertise = "";
    }
    public Lecturer(int lecturerID, String name, String role, String expertise) {
        this.lecturerID = lecturerID;
        this.name = name;
        this.role = role;
        this.expertise = expertise;
    }

    // Getters and Setters
    public int getLecturerID() {
        return lecturerID;
    }

    public void setLecturerID(int lecturerID) {
        this.lecturerID = lecturerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }
}
