package dao;

import model.Student;
import model.Subject;
import serialization.JSONSerialization;

import java.util.List;

public class StudentRepository {
    public static void fromJSONToDatabase(String filename) throws Exception{
        JSONSerialization<Student> jsonSerialization = new JSONSerialization<>();
        List<Student> list = jsonSerialization.listFromFile(filename, Student.class);

        StudentDao studentDao = new StudentDao();
        for (Student student : list) {
            studentDao.add(student);
            for (Subject subject : student.getPoints().keySet()) {
                studentDao.addMark(student, subject, student.getPoints().get(subject));
            }
        }
    }
}
