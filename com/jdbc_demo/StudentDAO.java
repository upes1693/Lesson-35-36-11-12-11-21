package com.jdbc_demo;

import java.sql.*;
import java.util.ArrayList;

public class StudentDAO implements DAO{
    @Override
    public Student get(String email) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:sqlite:C:\\Users\\markf\\IdeaProjects\\FirstJDBCDemo\\DB\\myfirstdb.db");
             PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM students WHERE email=?");) {
            preparedStatement.setString(1,email);
            ResultSet resultSet = preparedStatement.executeQuery();

            Student student=null;

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int phone = resultSet.getInt("phone");
                student=new Student(name,phone,email);
            }
            return student;
        } catch (SQLException e) {
            System.out.println("Something went wrong... " + e.getMessage());
            return null;
        }
    }

    @Override
    public ArrayList<Student> getAll() {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:sqlite:C:\\Users\\markf\\IdeaProjects\\FirstJDBCDemo\\DB\\myfirstdb.db");
             Statement statement = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM students");

            ArrayList<Student> students=new ArrayList<>();

            while (resultSet.next()) {
                Student student=null;

                String name = resultSet.getString("name");
                int phone = resultSet.getInt("phone");
                String email = resultSet.getString("email");

                //resultSet.updateInt(2,98765);

                student=new Student(name,phone,email);
                students.add(student);
            }
            return students;
        } catch (SQLException e) {
            System.out.println("Something went wrong... " + e.getMessage());
            return null;
        }
    }

    @Override
    public void create(String name, int phone, String email) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:sqlite:C:\\Users\\markf\\IdeaProjects\\FirstJDBCDemo\\DB\\myfirstdb.db");
             PreparedStatement statement = conn.prepareStatement("INSERT INTO `students` Values(?,?,?)");){
            statement.setString(3,email);
            statement.setString(1,name);
            statement.setInt(2,phone);

            statement.executeUpdate();
        }
        catch (SQLException e){
            System.out.println("Something went wrong... " + e.getMessage());
        }
    }

    @Override
    public void update(String name, int phone, String email) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:sqlite:C:\\Users\\markf\\IdeaProjects\\FirstJDBCDemo\\DB\\myfirstdb.db");
             PreparedStatement statement = conn.prepareStatement("UPDATE `students` SET name=?, phone=? WHERE email=?");){
            statement.setString(3,email);
            statement.setString(1,name);
            statement.setInt(2,phone);

            statement.executeUpdate();
        }
        catch (SQLException e){
            System.out.println("Something went wrong... " + e.getMessage());
        }
    }

    @Override
    public void delete(String email) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:sqlite:C:\\Users\\markf\\IdeaProjects\\FirstJDBCDemo\\DB\\myfirstdb.db");
             PreparedStatement statement = conn.prepareStatement("DELETE FROM `students` WHERE email=?");){
            statement.setString(1,email);

            statement.executeUpdate();
        }
        catch (SQLException e){
            System.out.println("Something went wrong... " + e.getMessage());
        }
    }
}
