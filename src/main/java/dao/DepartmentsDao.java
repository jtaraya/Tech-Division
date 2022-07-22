package dao;

import models.Departments;

import java.util.List;

public interface DepartmentsDao {
    //LIST
    List<Departments> getAll();

    //CREATE
    void add (Departments category);

    //READ
    Departments findById(int id);

    //UPDATE
    void update(int id, String name);

    //DELETE
    void deleteById(int id);
    void deleteAllDepartments();
}