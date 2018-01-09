package dao;

import model.Group;
import model.Student;
import model.Subject;

import java.sql.*;

public class StudentDao extends Dao<Student>{
    //private static final String insertMark = "INSERT INTO student_and_subjects(mark, subject_id, student_id) VALUES (?,?,?)";
    private static final String deleteMark = "DELETE FROM student_and_subjects WHERE (student_id=?)AND(subject_id = ?)";
    private static final String updateMark = "UPDATE student_and_subjects SET mark=?,subject_id=?,student_id=? WHERE (subject_id = ?)AND(student_id=?) ";
    @Override
    public int add(Student student1) throws Exception{

        PreparedStatement preparedStatement = getConnection().prepareStatement("INSERT INTO student(name, surname, birthdate, group_id) VALUES(?,?,?,?)");
        preparedStatement.setString(1, student1.getName());
        preparedStatement.setString(2, student1.getSurname());
        preparedStatement.setDate(3, java.sql.Date.valueOf(student1.getDateOfBirth()));
        preparedStatement.setInt(4, student1.getGroup().getId());

        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        return result;
        //return statement.executeUpdate("INSERT INTO students VALUES()")

    }

    @Override
    public int delete(Student student) throws Exception{
        PreparedStatement preparedStatement = getConnection().prepareStatement("DELETE FROM student WHERE id = ?");
        preparedStatement.setInt(1, student.getStudentID());

        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        return result;
    }

    @Override
    public int update(Student student) throws Exception{
        PreparedStatement preparedStatement = getConnection().prepareStatement("UPDATE student SET name = ?, surname = ? WHERE id = ?");
        preparedStatement.setString(1,student.getName());
        preparedStatement.setString(2,student.getSurname());
        preparedStatement.setInt(3, student.getStudentID());

        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        return result;
    }
    public Double getAverageMarkOfStudent(Student student)throws Exception{
        PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT AVG(mark)AS av_mark FROM student_subject where student_id = ?");
        preparedStatement.setInt(1,student.getStudentID());
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        double av_mark = resultSet.getDouble("av_mark");

        return av_mark;
    }

    public void addStudentAndMarks(Student student, Group group)throws Exception {
        PreparedStatement preparedStatement = getConnection().prepareStatement("INSERT INTO student(name, surname, birthdate, group_id) VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, student.getName());
        preparedStatement.setString(2, student.getSurname());
        preparedStatement.setDate(3, java.sql.Date.valueOf(student.getDateOfBirth()));
        preparedStatement.setInt(4, student.getGroup().getId());
        preparedStatement.executeUpdate();

        try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {

            if (generatedKeys.next()) {
               student.setStudentID(generatedKeys.getInt("id"));
            }
            else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
            preparedStatement.close();

            StudentDao studentDao = new StudentDao();
            for (Subject subject: group.getSubjects()) {
                if(student.getPoints().containsKey(subject)) {
                    studentDao.addMark(student, subject, student.getPoints().get(subject));
                }
            }

        }

    }

    public Integer addMark(Student student, Subject subject, int mark) throws Exception {
        Statement statement = getConnection().createStatement();
        PreparedStatement preparedStatement = getConnection().prepareStatement("INSERT INTO student_and_subjects(mark, subject_id, student_id) VALUES (?,?,?)");
        preparedStatement.setInt(1, mark);
        preparedStatement.setInt(2, subject.getId());
        preparedStatement.setInt(3, student.getStudentID());
        System.out.println(subject.getId());

        return preparedStatement.executeUpdate();
    }

    public Integer deleteMark(Student student, Subject subject) throws Exception {
        Statement statement = getConnection().createStatement();
        PreparedStatement preparedStatement = getConnection().prepareStatement(deleteMark);
        preparedStatement.setInt(1, student.getStudentID());
        preparedStatement.setInt(2, subject.getId());

        return preparedStatement.executeUpdate();
    }

    public Integer updateMark(Student student, Subject subject) throws Exception {
        Statement statement = getConnection().createStatement();
        PreparedStatement preparedStatement = getConnection().prepareStatement(updateMark);
        preparedStatement.setInt(1, student.getPoints().get(subject));
        preparedStatement.setInt(2, subject.getId());
        preparedStatement.setInt(3, student.getStudentID());
        preparedStatement.setInt(4, subject.getId());
        preparedStatement.setInt(5, student.getStudentID());

        return preparedStatement.executeUpdate();
    }

    public Student getStudent(Integer id) throws Exception{
        PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM students INNER JOIN groups ON students.group_id = groups.id WHERE students.id=?");
        preparedStatement.setInt(1, id);
        preparedStatement.close();

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        String name = resultSet.getString("name");
        String surname = resultSet.getString(2);

        Group group = new Group(resultSet.getInt("number"), resultSet.getInt("id"));

        return new Student(id, name, surname, group);
    }
}
