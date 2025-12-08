package org.example;

import org.example.entity.Faculty;
import org.example.entity.Person;
import org.example.entity.Student;

import java.util.Arrays;
import java.util.List;

/**
 * @author aswan-kib
 * @since 12/8/25
 */
public class Main {
    public static void main(String[] args) {
        List<Person> personList = getPersonList();

        for(Person person: personList) {
            if (person instanceof Student) {
                Student student = (Student) person;

                System.out.println(student);
            } else {
                Faculty faculty = (Faculty)  person;

                faculty.assignCourse("Object-Oriented Design");
            }
        }
    }

    private static List<Person> getPersonList() {
        Person student1 = new Student("Abul Kalam", "abulKalam@gmail.com", "Computer Science", 60);
        Person student2 = new Student("Abdul Jabbar", "abduljabbar@gmail.com", "History", 30);
        Person faculty = new Faculty("Sohel Khan", "sohelkhan@gmail.com", "Math", 75000);

        return Arrays.asList(student1, student2, faculty);
    }
}