package dao;

import model.Group;
import model.Student;
import model.Subject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class GroupDao extends Dao<Group>{

    @Override
    public int add(Group group) throws Exception{
        PreparedStatement preparedStatement = getConnection().prepareStatement("INSERT INTO groups VALUES(?)");
        preparedStatement.setInt(1,group.getNumber());

        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        return result;
    }

    @Override
    public int delete(Group group) throws Exception{
        PreparedStatement preparedStatement = getConnection().prepareStatement("DELETE FROM groups WHERE id = ?");
        preparedStatement.setInt(1, group.getId());

        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        return result;
    }

    @Override
    public int update(Group group) throws Exception{
        PreparedStatement preparedStatement = getConnection().prepareStatement("UPDATE groups SET number = ? WHERE id = ?");
        preparedStatement.setInt(1, group.getNumber());
        preparedStatement.setInt(2, group.getId());

        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        return result;
    }

    public void addGroupStudentsAndMarks(Group group) throws Exception{
        PreparedStatement preparedStatement = getConnection().prepareStatement("INSERT INTO groups VALUES(?)", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1,group.getNumber());
        preparedStatement.executeUpdate();

        try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {

            if (generatedKeys.next()) {
                group.setId(generatedKeys.getInt("id"));
            }
            else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
            preparedStatement.close();

            SubjectDao subjectDao = new SubjectDao();
            for (Subject subject : group.getSubjects()) {
                subjectDao.add(subject);
            }

            StudentDao studentDao = new StudentDao();
            for (Student student : group.getStudents()) {
                student.setGroup(group);
                studentDao.addStudentAndMarks(student, group);
            }
        }
    }

    public Double getAvarageMarkOfGroup(Group group) throws Exception {
        PreparedStatement preparedStatement;
        preparedStatement = getConnection().prepareStatement("SELECT AVG(student_subject.mark)AS av_mark FROM student_subject INNER JOIN  students ON students.group_id = ?");
        preparedStatement.setInt(1, group.getId());
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        Double av_mark = resultSet.getDouble("av_mark");
        preparedStatement.close();

        return av_mark;
    }

}
