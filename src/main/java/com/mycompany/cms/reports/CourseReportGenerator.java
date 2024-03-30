package com.mycompany.cms.reports;

import com.mycompany.cms.CourseDAO;
import com.mycompany.cms.LecturerDAO;
import com.mycompany.cms.EnrollmentDAO;
import com.mycompany.cms.models.Course;
import com.mycompany.cms.models.Lecturer;

import java.util.List;

public class CourseReportGenerator {

    private CourseDAO courseDao;
    private LecturerDAO lecturerDao;
    private EnrollmentDAO enrollmentDao;

    public CourseReportGenerator(CourseDAO courseDao, LecturerDAO lecturerDao, EnrollmentDAO enrollmentDao) {
        this.courseDao = courseDao;
        this.lecturerDao = lecturerDao;
        this.enrollmentDao = enrollmentDao;
    }

    public String generateReport() {
        List<Course> courses = courseDao.findAll();
        if (courses == null || courses.isEmpty()) {
            System.out.println("No courses found.");
            return "";
        }

        StringBuilder report = new StringBuilder();
        report.append("Course Report:\n");
        report.append("==============\n");
        
        for (Course course : courses) {
            Lecturer lecturer = lecturerDao.selectLecturer(course.getLecturerId());
            int enrollmentCount = enrollmentDao.getEnrolledStudents(course.getCourseID());
            String lecturerName = lecturer != null ? lecturer.getName() : "Unknown";
            String room = course.getRoom().isEmpty() ? "Online" : course.getRoom();

            report.append("Module: ").append(course.getName()).append("\n");
            report.append("Program: ").append(course.getProgram()).append("\n");
            report.append("Enrolled Students: ").append(enrollmentCount).append("\n");
            report.append("Lecturer: ").append(lecturerName).append("\n");
            report.append("Room: ").append(room).append("\n\n");
        }

        // Print the report on the console
        System.out.println(report.toString());
        return report.toString();
    }
    
}

//
//package com.mycompany.cms.reports;
//
//import com.mycompany.cms.CourseDAO;
//import com.mycompany.cms.LecturerDAO;
//import com.mycompany.cms.EnrollmentDAO;
//import com.mycompany.cms.models.Course;
//import com.mycompany.cms.models.Lecturer;
//
//import java.util.List;
//
//public class CourseReportGenerator {
//
//    private CourseDAO courseDao;
//    private LecturerDAO lecturerDao;
//    private EnrollmentDAO enrollmentDao;
////     public CourseReportGenerator() {
////        this.courseDao = null;
////        this.lecturerDao = null;
////        this.enrollmentDao = null;
////    }
//    public CourseReportGenerator(CourseDAO courseDao, LecturerDAO lecturerDao, EnrollmentDAO enrollmentDao) {
//        this.courseDao = courseDao;
//        this.lecturerDao = lecturerDao;
//        this.enrollmentDao = enrollmentDao;
//    }
//
//    public void generateReport() {
//        List<Course> courses = courseDao.findAll();
//        if (courses == null || courses.isEmpty()) {
//            System.out.println("No courses found.");
//            return;
//        }
//
//        System.out.println("Course Report:");
//        System.out.println("==============");
//        for (Course course : courses) {
//            Lecturer lecturer = lecturerDao.selectLecturer(course.getLecturerId());
//            int enrollmentCount = enrollmentDao.getEnrolledStudents(course.getCourseID());
//            String lecturerName = lecturer != null ? lecturer.getName() : "Unknown";
//            String room = course.getRoom().isEmpty() ? "Online" : course.getRoom();
//
//            System.out.printf("Module: %s\n", course.getName());
//            System.out.printf("Program: %s\n", course.getProgram());
//            System.out.printf("Enrolled Students: %d\n", enrollmentCount);
//            System.out.printf("Lecturer: %s\n", lecturerName);
//            System.out.printf("Room: %s\n\n", room);
//        }
//    }

    // Main method for testing
//    public static void main(String[] args) {
//        CourseDAO courseDao = new CourseDAO();
//        LecturerDAO lecturerDao = new LecturerDAO();
//        EnrollmentDAO enrollmentDao = new EnrollmentDAO();
//
//        CourseReportGenerator reportGenerator = new CourseReportGenerator(courseDao, lecturerDao, enrollmentDao);
//        reportGenerator.generateReport();
//    }
//}
