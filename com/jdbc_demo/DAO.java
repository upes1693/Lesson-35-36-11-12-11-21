package com.jdbc_demo;

import java.util.ArrayList;

public interface DAO {
    Student get(String email);
    ArrayList<Student> getAll();
    void create(String name, int phone, String email);
    void update(String name, int phone, String email);
    void delete(String email);
}
