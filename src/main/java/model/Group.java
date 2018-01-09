package model;

import java.util.*;

public class Group {

    private int number;
    private int id;
    private Set<Student> students = new TreeSet<Student>();
    private List<Subject> subjects;

    // fills students value
    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(int studentID) {
        for (Student student : students) {
            if (student.getStudentID() == studentID) {
                students.remove(student);
            }
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setStudents(HashSet<Student> students) {
        this.students = students;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;

    }

    public int getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public Group() {}

    public Group(int number,  int id) {
        this.id = id;
        this.number = number;
    }

    public Group(int number, ArrayList<Subject> subjects, int id) {
        this.id = id;
        this.number = number;
        this.subjects = subjects;
    }
}
