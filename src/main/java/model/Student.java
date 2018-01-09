package model;
import
        com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Period;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Student implements Comparable<Student>{

    private int studentID;
    private String name;
    private String surname;
    @JsonIgnore
    private Group group;
    private LocalDate dateOfBirth;
    private HashMap<Subject, Integer> points = new HashMap<>();

    @JsonIgnore
    public long getAge() {
        if(Period.between(dateOfBirth, LocalDate.now()).getYears() >= 200)
            throw new IllegalArgumentException();

        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    @JsonIgnore
    public double getAveragePoint() {
        if(points.keySet().stream().filter(el->el.getTypeOfSubject()=="EXAM").count() == 0) {
            return 0;
        }
        else {

            Integer summ = 0;
            for (Subject subject : points.keySet()) {
                if (subject.getTypeOfSubject()== "EXAM") {
                    summ+= points.get(subject);
                }
            }
            return summ/points.keySet().stream().filter(el->el.getTypeOfSubject()=="EXAM").count();
        }
    }

    public void addSubjectAndMark(Subject subject, Integer mark){
        this.points.put(subject, mark);
    };

    public boolean setMark(String name, Integer mark) {
        if(this.points.containsKey(new Subject(name, Subject.type.CREDIT))) {
            this.points.put(new Subject(name, Subject.type.CREDIT), mark);
            return true;
        }
        else if (this.points.containsKey(new Subject(name, Subject.type.EXAM))) {
            this.points.put(new Subject(name, Subject.type.EXAM), mark);
            return true;
        }
        else return false;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setDateOfBirth(int day, int month,  int year) {
        if(Period.between(LocalDate.of(year,month, day), LocalDate.now()).getYears() >= 200)
            throw new IllegalArgumentException();
        else this.dateOfBirth = LocalDate.of(year, month, day);
    }

    @JsonIgnore
    public void setGroup(Group group) {
        this.group = group;
    }

    public void setPoints(HashMap<Subject, Integer> points) {
        this.points = points;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    @JsonIgnore
    public Group getGroup() {
        return group;
    }

    public HashMap<Subject, Integer> getPoints() {
        return points;
    }

//    public boolean setSubjectsAndMarksSeparately(List<String> subjectsNames, List<Subject.type> types, List<Integer> marks  ) {
//
//        if(subjectsNames.size() == types.size()) {
//            points.clear();
//            List<Subject> subjects = new ArrayList<>();
//            for (int i = 0; i < subjectsNames.size(); i++) {
//                subjects.add(new Subject(subjectsNames.get(i), types.get(i)));
//            }
//
//
//            if (subjects.size() == marks.size()) {
//                for (int i = 0; i < subjects.size(); i++) {
//
//                    this.points.put(subjects.get(i), marks.get(i));
//
//                }
//            }
//            return true;
//        }
//        else return false;
//    }

    public Student() {}

    public Student(int studentID) {
        this.studentID = studentID;
    }

    public Student(String name, String surname, LocalDate dateOfBirth) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
    }

    // constructor are used when creating value with subject list copying
    public Student(int studentID, String name, String surname, Group group) {
        this.studentID = studentID;
        this.name = name;
        this.surname = surname;
        this.group = group;
    }

    public Student(Integer groupNumber, int studentID, String name, String surname, ArrayList<Subject> subjects, ArrayList<Integer> marks, Group group) {
        this.studentID = studentID;
        this.name = name;
        this.surname = surname;
        this.group = group;
        this.points = new HashMap<Subject, Integer>();

        for(int i = 0; i < subjects.size(); i++) {
            this.points.put(subjects.get(i), marks.get(i));
        }
    }

    @Override
    public int compareTo(Student student){
        int i = this.surname.compareTo(student.surname);

        return (i==0?this.name.compareTo(student.name):i);
    }

    // overriding equals and hashCode methods let us compare values from HashSet of Student object by the only inner variable(studentID)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return studentID == student.studentID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentID);
    }


    @Override
    public String toString() {
        return "Student{" +
                "studentID=" + studentID +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", points=" + points +
                '}';
    }
}