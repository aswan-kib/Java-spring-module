package org.example.entity;

/**
 * @author aswan-kib
 * @since 12/8/25
 */
public class Course {

    private String courseCode;
    private String courseTitle;
    private int maxStudents;

    public  Course(String courseCode, String courseTitle, int maxStudents) {
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
        this.maxStudents = maxStudents;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public int getMaxStudents() {
        return maxStudents;
    }

    public void setMaxStudents(int maxStudents) {
        this.maxStudents = maxStudents;
    }
}
