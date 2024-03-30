package com.mycompany.cms.reports;

import com.mycompany.cms.models.Course;
import com.mycompany.cms.models.Student;
import com.mycompany.cms.CourseDAO;
import com.mycompany.cms.StudentDAO;

import java.util.List;

public class StudentReportGenerator {

    public static String generateStudentReport(int studentID) {
        StudentDAO studentDAO = new StudentDAO();
        Student student = studentDAO.selectStudent(studentID);
        if (student != null) {
            StringBuilder report = new StringBuilder();
            report.append("Student Name: ").append(student.getName()).append("\n");
            report.append("Programme: ").append(student.getProgram()).append("\n\n");
            report.append("Modules Enrolled:\n");

            CourseDAO courseDAO = new CourseDAO();
            List<Course> enrolledModules = studentDAO.findEnrolledModules(studentID);

            // Retrieve completed modules
            List<Course> completedModules = studentDAO.findCompletedModules(studentID);

            for (Course course : enrolledModules) {
                // Check if the enrolled module is also in the completed list
                boolean isCompleted = false;
                for (Course completedCourse : completedModules) {
                    if (course.getCourseID() == completedCourse.getCourseID()) {
                        isCompleted = true;
                        break;
                    }
                }
                // Print only if not completed
                if (!isCompleted) {
                    report.append(course.getName()).append("\n");
                }
            }

            // Print completed modules with grades
            report.append("\nModules Completed:\n");
            for (Course course : completedModules) {
                report.append(course.getName()).append(" - Grade: A\n");
            }

            // You need to implement a method to determine modules that need to be repeated
            report.append("\nModules Needing Repeat:\n");
            List<Course> repeatModules = studentDAO.findRepeatModules(studentID);
            for (Course course : repeatModules) {
                report.append(course.getName()).append("\n");
            }

            // Print the report on the console
            System.out.println(report.toString());

            return report.toString();
        } else {
            return "Student not found!";
        }
    }
}







//package com.mycompany.cms.reports;
//
//import com.mycompany.cms.models.Course;
//import com.mycompany.cms.models.Student;
//import com.mycompany.cms.CourseDAO;
//import com.mycompany.cms.StudentDAO;
//
//import java.util.List;
//
//public class StudentReportGenerator {
//
//    public static void generateStudentReport(int studentID) {
//        StudentDAO studentDAO = new StudentDAO();
//        Student student = studentDAO.selectStudent(studentID);
//        if (student != null) {
//            System.out.println("Student Name: " + student.getName());
//            System.out.println("Programme: " + student.getProgram());
//
//            System.out.println("\nModules Enrolled:");
//            CourseDAO courseDAO = new CourseDAO();
//            List<Course> enrolledModules = studentDAO.findEnrolledModules(studentID);
//
//            // Retrieve completed modules
//            List<Course> completedModules = studentDAO.findCompletedModules(studentID);
//
//            for (Course course : enrolledModules) {
//                // Check if the enrolled module is also in the completed list
//                boolean isCompleted = false;
//                for (Course completedCourse : completedModules) {
//                    if (course.getCourseID() == completedCourse.getCourseID()) {
//                        isCompleted = true;
//                        break;
//                    }
//                }
//                // Print only if not completed
//                if (!isCompleted) {
//                    System.out.println(course.getName());
//                }
//            }
//
//            // Print completed modules with grades
//            System.out.println("\nModules Completed:");
//            for (Course course : completedModules) {
//                System.out.println(course.getName() + " - Grade: A" );
//            }
//
//            // You need to implement a method to determine modules that need to be repeated
//            System.out.println("\nModules Needing Repeat:");
//            List<Course> repeatModules = studentDAO.findRepeatModules(studentID);
//            for (Course course : repeatModules) {
//                System.out.println(course.getName());
//            }
//        } else {
//            System.out.println("Student not found!");
//        }
//    }
//
//}
//
//
