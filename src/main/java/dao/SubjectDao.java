package dao;

import model.Student;
import model.Subject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SubjectDao extends Dao<Subject>{

    @Override
    public int add(Subject subject) throws Exception{

        PreparedStatement preparedStatement = getConnection().prepareStatement("INSERT INTO subject(name,type) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, subject.getName());
        preparedStatement.setString(2, subject.getTypeOfSubject());


        int result = preparedStatement.executeUpdate();
        try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                subject.setId(generatedKeys.getInt("id"));
            }
            else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        }
        preparedStatement.close();

        return result;
        //return statement.executeUpdate("INSERT INTO students VALUES()")

    }

    @Override
    public int delete(Subject subject) throws Exception{
        PreparedStatement preparedStatement = getConnection().prepareStatement("DELETE FROM subject WHERE id = ?");
        preparedStatement.setInt(1,subject.getId());

        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        return result;
    }


    @Override
    public int update(Subject subject) throws Exception{
        PreparedStatement preparedStatement = getConnection().prepareStatement("UPDATE subject SET name = ?, type = ? WHERE id = ?");
        preparedStatement.setString(1, subject.getName());
        preparedStatement.setString(2, subject.getTypeOfSubject());

        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        return result;
    }

    public Subject getSubject(Integer id) throws Exception{
        PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM subject where id = ?");
        preparedStatement.setInt(1, id);
        preparedStatement.close();

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        String name = resultSet.getString(3);
        Subject.type typeOfSubject;
        if(resultSet.getString(2) == "EXAM") {
            typeOfSubject = Subject.type.EXAM;
        }
        else {
            typeOfSubject = Subject.type.CREDIT;
        }

        return new Subject(id, name, typeOfSubject);
    }

}
