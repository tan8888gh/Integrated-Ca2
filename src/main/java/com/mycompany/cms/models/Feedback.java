/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cms.models;

public class Feedback {
    private int feedbackID;
    private int courseID;
    private int studentID;
    private String comment;
    private int rating;

    public Feedback(int feedbackID, int courseID, int studentID, String comment, int rating) {
        this.feedbackID = feedbackID;
        this.courseID = courseID;
        this.studentID = studentID;
        this.comment = comment;
        this.rating = rating;
    }

    // Getters and setters
    public int getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(int feedbackID) {
        this.feedbackID = feedbackID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}

