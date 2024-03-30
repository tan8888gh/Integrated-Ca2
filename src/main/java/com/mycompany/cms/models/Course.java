///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.mycompany.cms.models;
//
//public class Course {
//    private int id;
//    private String name;
//    private String module;
//    private int lecturerId; // Assuming a lecturer is identified by an integer ID
//    private String room; // New attribute for room
//
//    public Course(int id, String name, String module, int lecturerId, String room) {
//        this.id = id;
//        this.name = name;
//        this.module = module;
//        this.lecturerId = lecturerId;
//        this.room = room; // Initialize the new attribute
//    }
//
//    // Getters and setters including for the new room attribute
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getModule() {
//        return module;
//    }
//
//    public void setModule(String module) {
//        this.module = module;
//    }
//
//    public int getLecturerId() {
//        return lecturerId;
//    }
//
//    public void setLecturerId(int lecturerId) {
//        this.lecturerId = lecturerId;
//    }
//    
//    public String getRoom() {
//        return room;
//    }
//
//    public void setRoom(String room) {
//        this.room = room;
//    }
//
//    @Override
//    public String toString() {
//        return "Course{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", module='" + module + '\'' +
//                ", lecturerId=" + lecturerId +
//                ", room='" + room + '\'' + // Display the room attribute
//                '}';
//    }
//}
package com.mycompany.cms.models;

public class Course {
    private int courseID; // Changed from 'id' to 'courseID' to match the table column name
    private String name;
    private String program; // Changed from 'module' to 'program' to match the table column name
    private int lecturerId;
    private String room;

    public Course(int courseID, String name, String program, int lecturerId, String room) {
        this.courseID = courseID;
        this.name = name;
        this.program = program;
        this.lecturerId = lecturerId;
        this.room = room;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public int getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(int lecturerId) {
        this.lecturerId = lecturerId;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseID=" + courseID +
                ", name='" + name + '\'' +
                ", program='" + program + '\'' +
                ", lecturerId=" + lecturerId +
                ", room='" + room + '\'' +
                '}';
    }
}
