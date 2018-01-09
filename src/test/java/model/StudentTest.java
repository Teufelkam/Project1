package model;


import org.testng.annotations.*;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.*;

import static model.Subject.type.CREDIT;
import static model.Subject.type.EXAM;
import static org.testng.Assert.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentTest {
    Student studentWithAllCredits = new Student();
    Student studentWithAllExams = new Student();
    Student studentWithExamsAndCredits = new Student();

    @BeforeClass
    public void setUp() throws Exception {
        studentWithAllCredits.addSubjectAndMark(new Subject("Math", CREDIT), 3);
        studentWithAllCredits.addSubjectAndMark(new Subject("Math2", CREDIT), 4);
        studentWithAllCredits.addSubjectAndMark(new Subject("Math3", CREDIT), 5);

        studentWithAllExams.addSubjectAndMark(new Subject("Math", EXAM), 5);
        studentWithAllExams.addSubjectAndMark(new Subject("Math2", EXAM), 5);
        studentWithAllExams.addSubjectAndMark(new Subject("Math3", EXAM), 2);

        studentWithExamsAndCredits.addSubjectAndMark(new Subject("Math", CREDIT), 3);
        studentWithExamsAndCredits.addSubjectAndMark(new Subject("Math2", EXAM), 5);
        studentWithExamsAndCredits.addSubjectAndMark(new Subject("Math3", CREDIT), 5);
    }

    @AfterClass
    public void tearDown() throws Exception {

    }

    @DataProvider(name = "getAveragePointFunctionTesting")
    public Object[][] firstDataProvider() {
        return new Object[][]{
                {studentWithAllCredits, 0d},
                {studentWithAllExams, 4d},
                {studentWithExamsAndCredits,5d}
        };
    }

    @Test(dataProvider = "getAveragePointFunctionTesting")
    public void checkGetAveragePointMethod(Student student, Double result) {

        assertTrue(result.equals(student.getAveragePoint()) ,student.getAveragePoint() + " - result");

    }

    @DataProvider(name = "toCompare")
    public Object[][] secondDataProvider() {
        return new Object[][] {
                {"Alex", "Alex", "Tsyhanovch", "Tsyhanovych", -1},
                {"Alex", "Alex", "Tsyhanovych", "Tsyhanovych", 0},

        };
    }

    @Test(dataProvider = "toCompare")
    public void checkToCompareMethod(String firstName, String secondName, String firstSurname, String secondSurname, Integer result) {
        Group group = new Group();
        Student student = new Student(1, firstName, firstSurname, group);
            if (result > 0)
            assertTrue(student.compareTo(new Student(1, secondName, secondSurname, group)) >= 1, student.compareTo(new Student(1, secondName, secondSurname,group)) + "FAILED!");
            else if ( result < 0)
            assertTrue(student.compareTo(new Student(1, secondName, secondSurname, group)) <= -1, student.compareTo(new Student(1, secondName, secondSurname, group)) + "FAILED!");
            else
            assertTrue(student.compareTo(new Student(1, secondName, secondSurname, group)) == 0, student.compareTo(new Student(1, secondName, secondSurname, group)) + "FAILED!");
    }

    @DataProvider(name = "setMark")
    public Object[][] thirdDataProvider() {
        return new Object[][] {
                {"Math", true},
                {"This", false}

        };
    }

    @Test(dataProvider = "setMark")
    public void checkSetMarksMethod(String name, boolean result) {
        Student student = new Student();
        student.addSubjectAndMark(new Subject("Math", Subject.type.EXAM), 5);
        assertTrue(student.setMark(name, 5) == result, "FAILED!");

    }
    public void testEquals() throws Exception {

    }
}