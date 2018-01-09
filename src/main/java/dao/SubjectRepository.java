package dao;

import model.Student;
import model.Subject;
import serialization.JSONSerialization;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SubjectRepository {
    public static void fromJSONToDatabase(String filename) throws Exception{
        JSONSerialization<Subject> jsonSerialization = new JSONSerialization<>();
        List<Subject> list = jsonSerialization.listFromFile(filename, Subject.class);
        SubjectDao subjectDao = new SubjectDao();
        for (Subject subject : list) {
            subjectDao.add(subject);
        }
    }
}
