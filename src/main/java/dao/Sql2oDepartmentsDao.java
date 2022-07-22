package dao;

import models.Departments;
import models.Staff;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oDepartmentsDao implements DepartmentsDao{

    private final Sql2o sql2o;

    public Sql2oDepartmentsDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public List<Departments> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM departments")
                    .executeAndFetch(Departments.class);
        }
    }

}
