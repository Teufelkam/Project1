package dao;

import model.Student;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class Dao<T>{

    public static Connection getConnection()throws Exception{
        String url = "jdbc:postgresql://localhost:5432/Students";
        String userName = "postgres";
        String password = "1234";
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(url, userName, password);
        return connection;
    }

    abstract  public int add(T t) throws Exception;
    abstract  public int delete(T t) throws Exception;
    abstract  public int update(T t) throws Exception;

}
