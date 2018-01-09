package model;



import dao.*;
import serialization.JSONSerialization;
import serialization.XMLSerialization;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.*;

import static model.Subject.type.CREDIT;
import static model.Subject.type.EXAM;

public class Main {
////    public void checkResult() {
////        Student student = new Student(1, "d", "dfs", 2);
////        StudentDao studentDao = new StudentDao();
////        String[] subjects = {"Math"};
////        Subject.type[] types = {EXAM};
////        Integer[] marks = {1};
////        List<String> subjects1 = Arrays.asList(subjects);
////        List<Subject.type> types1 = Arrays.asList(types);
////        List<Integer> marks1 = Arrays.asList(marks);
////
////        System.out.println(subjects1.get(0));
//
//    }
    public static void main(String[] args) throws Exception {

//        Subject subject = new Subject("fdsfsd", Subject.type.EXAM);
//        Student student = new Student();
//        List<Subject> subjects = new ArrayList<>();
//        subjects.add(new Subject("dsf", Subject.type.EXAM));
//        ArrayList<Integer> marks = new ArrayList<>();
//        marks.add(1);
//        student.setSubjectsAndMarks(subjects , marks);
//        System.out.println(student.getAveragePoint());

//       HashMap<Subject, Integer> hashMap = new HashMap<>();
//       hashMap.put(new Subject("dfs", Subject.type.EXAM), 45);
//       System.out.println(hashMap.get(new Subject("dfs", Subject.type.EXAM)));
//
//        Student student = new Student();
//        String[] subjects = {"Math"};
//        Subject.type[] types = {Subject.type.EXAM};
//        Integer[] marks = {1};
//        List<String> subjects1 = Arrays.asList(subjects);
//        List<Subject.type> types1 = Arrays.asList(types);
//        List<Integer> marks1 = Arrays.asList(marks);
//
//        Student student1 = new Student(7, "Alex", "Tsyhanovych", 1);
//
//        StudentDao studentDao = new StudentDao();
//
//        studentDao.add(student1);
//        String url = "jdbc:postgresql://localhost:5432/Students";
//        String userName = "postgres";
//        String password = "1234";
//        Class.forName("org.postgresql.Driver");
//        try(Connection connection = DriverManager.getConnection(url, userName, password);
//            Statement statement = connection.createStatement()) {
//
//            statement.executeUpdate("INSERT INTO student VALUES (1, 243, 'silver', 'dsf')");
//            statement.executeUpdate("INSERT INTO groups VALUES (1,243)");
//
//
//        }

//        ArrayList<Subject> subjects = new ArrayList<>();
//        subjects.add(new Subject("df", EXAM));
//        subjects.add(new Subject("fsd", CREDIT));
//        System.out.println(subjects.get(0).getName());
//        Subject subject = new Subject("dfgfd", EXAM);
//        System.out.println(subject.getName());
//        Group group = new Group(234, subjects, 1);
//        Student student = new Student(1, "sd1", "df", group);
//        Student student1 = new Student(1, "sd2", "df", group);
//        Student student2 = new Student(1, "sd3", "df", group);
//        student.addSubjectAndMark(subjects.get(0), 4);
//        student.addSubjectAndMark(subjects.get(1), 5);
//        student.setDateOfBirth(1,12,1999);
//        group.addStudent(student);
//        group.addStudent(student1);
//        group.addStudent(student2);
//        List<Group> list = new ArrayList<>();
//        list.add(group);
//        list.add(group);
//        JSONSerialization<Group> jsonSerialization = new JSONSerialization<>();
//        jsonSerialization.toFile(list, "text3.json");
//        GroupRepository groupRepository = new GroupRepository();
//        groupRepository.fromJSONToDatabase("text3.json");
//        XMLSerialization xmlSerialization = new XMLSerialization();
//        xmlSerialization.toFile(list, "text.xml");
//        GroupRepository groupRepository = new GroupRepository();
//        groupRepository.fromXMLToDatabase("text.xml");

    }
}
