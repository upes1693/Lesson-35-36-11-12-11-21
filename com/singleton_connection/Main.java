package com.singleton_connection;

import com.jdbc_demo.Student;

import java.sql.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws SQLException, InterruptedException {
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t1: "+printDetails(getAll()));
            }
        });

        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t2: "+printDetails(getAll()));
            }
        });

        t1.start();
        t2.start();
        Thread.sleep(3000);
        System.out.println(SingletonConnection.getInstance().getInstanceCount());
    }

    private static ArrayList<Student> getAll(){
        try {
            Connection conn = SingletonConnection.getInstance().getConnection();
            Statement statement = conn.createStatement();

            Thread.sleep(1000);

            ResultSet resultSet = statement.executeQuery("SELECT * FROM students");

            ArrayList<Student> students=new ArrayList<>();

            while (resultSet.next()) {
                Student student=null;

                String name = resultSet.getString("name");
                int phone = resultSet.getInt("phone");
                String email = resultSet.getString("email");

                student=new Student(name,phone,email);
                students.add(student);
            }
            return students;
        } catch (SQLException | InterruptedException e) {
            System.out.println("Something went wrong... " + e.getMessage());
            return null;
        }
    }

    public static String printDetails(ArrayList<Student> students){
        StringBuilder builder=new StringBuilder();
        for (Student s: students) {
            builder.append(s.getName()+", ");
        }

        return builder.toString();
    }
}
