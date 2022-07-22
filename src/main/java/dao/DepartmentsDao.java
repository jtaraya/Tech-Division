package dao;

import models.Departments;

import java.util.List;

public interface DepartmentsDao {
    //List
    List<Departments> getAll();

    //Departments category
    void add (Departments category);

    //Read
    Departments findById(int id);

    //update
    void update(int id, String name);

    //Delete
    void deleteById(int id);
    void deleteAllDepartments();
}
