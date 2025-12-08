package org.example.entity;

import java.util.Objects;

/**
 * @author aswan-kib
 * @since 12/8/25
 */
public class Student extends Person {

    private String major;
    private int creditCompleted;

    public Student(String name, String email, String major, int creditCompleted) {
        super(name, email);

        this.major = major;
        this.creditCompleted = creditCompleted;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getCreditCompleted() {
        return creditCompleted;
    }

    public void setCreditCompleted(int creditCompleted) {
        this.creditCompleted = creditCompleted;
    }

    @Override
    public String toString() {
        return String.format("Id: %s,\n Name: %s,\n Email: %s,\n Major: %s,\n Credit Completed: %d",
                getId(), getName(), getEmail(), getMajor(), getCreditCompleted());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;

        return getId().equals(student.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(major, creditCompleted);
    }
}
