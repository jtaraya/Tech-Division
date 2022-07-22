package dao;
import models.Staff;
import org.sql2o.*;
import java.util.List;


public class Sql2oStaffDao implements StaffDao {
    private final Sql2o sql2o;
    public Sql2oStaffDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void add(Staff staff) {
        String sql = "INSERT INTO staff (name,role,responsibility,departmentId) VALUES (:name,:role,:responsibility,:departmentId)";
        try(Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql, true)
                    .bind(staff)
                    .executeUpdate()
                    .getKey();
            staff.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }


    @Override
    public List<Staff> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM staff")
                    .executeAndFetch(Staff.class);
        }
    }

    @Override
    public List<Staff> getByDepartment(int departmentId) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM staff WHERE departmentId = :departmentId")
                    .addParameter("departmentId", departmentId)
                    .executeAndFetch(Staff.class);
        }
    }

    @Override
    public Staff findById(int id) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM staff WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Staff.class);
        }
    }

    @Override
    public void update(int id,String role){
        String sql = "UPDATE staff SET role = :role WHERE id=:id";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("role", role)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from staff WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void deleteAllStaff() {
        String sql = "DELETE from staff";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
}