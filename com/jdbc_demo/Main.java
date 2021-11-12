package com.jdbc_demo;

import java.sql.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        StudentDAO studentDAO=new StudentDAO();
//        Student student=studentDAO.get("john@gmail.com");
//        System.out.println(student.getName());
//        studentDAO.create("Student A",68432543,"student@gmail.com");
//        studentDAO.update("Student B",1234,"student@gmail.com");
        studentDAO.delete("student@gmail.com");

        ArrayList<Student> students= studentDAO.getAll();
        for (Student s:students) {
            System.out.println(s.getEmail());
        }
    }

    public static void readValues() {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:sqlite:C:\\Users\\markf\\IdeaProjects\\FirstJDBCDemo\\DB\\myfirstdb.db");
             Statement statement = conn.createStatement();) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM students WHERE name='John'");

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int phone = resultSet.getInt("phone");
                String email = resultSet.getString("email");
                System.out.println("Student name is " + name + ", phone " + phone + ", email " + email);
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong... " + e.getMessage());
        }
    }

    public static void addValues() {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:sqlite:C:\\Users\\markf\\IdeaProjects\\FirstJDBCDemo\\DB\\myfirstdb.db");
             Statement statement = conn.createStatement();) {
            statement.executeUpdate("INSERT INTO `students` Values ('John', 423573, 'john@gmail.com')");
        } catch (SQLException e) {
            System.out.println("Something went wrong... " + e.getMessage());
        }
    }

    public static void createTable() {
        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:sqlite:C:\\Users\\markf\\IdeaProjects\\FirstJDBCDemo\\DB\\myfirstdb.db");
            Statement statement = conn.createStatement();
            statement.execute("CREATE TABLE students (name Text, phone Integer, email Text)");
            statement.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Something went wrong... " + e.getMessage());
        }
    }
}
