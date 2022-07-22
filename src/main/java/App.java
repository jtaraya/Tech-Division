import dao.Sql2oDepartmentsDao;
import dao.Sql2oStaffDao;
import models.Departments;
import models.Staff;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.*;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        String connectionString = "jdbc:postgresql://localhost:5432/technologydivision";
        Sql2o sql2o = new Sql2o(connectionString, "postgres", null);
        Sql2oStaffDao staffDao = new Sql2oStaffDao(sql2o);
        Sql2oDepartmentsDao departmentsDao = new Sql2oDepartmentsDao(sql2o);

        //get: show all staff in all departments and show all departments
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Staff> staff = staffDao.getAll();
            List<Departments> departments = departmentsDao.getAll();
            model.put("myStaff", staff);
            model.put("myDepartments",departments);
            return new ModelAndView(model, "staff-list.hbs");
        }, new HandlebarsTemplateEngine());

        //get: show a form to create a new department
        //  /departments/new
        get("/departments/new",(req,res)->{
            Map <String,Object> model = new HashMap<String,Object>();
            List<Departments> departments = departmentsDao.getAll();
            model.put("myDepartments",departments);
            return new ModelAndView(model,"new-department-form.hbs");
        },new HandlebarsTemplateEngine());

        //post: process a form to create a new department
        //  /departments
        post("/departments",(req, res) -> {
            String name = req.queryParams("name");
            System.out.println(name);
            Departments newDepartment = new Departments(name);
            departmentsDao.add(newDepartment);
            res.redirect("/departments");
            return null;
        },new HandlebarsTemplateEngine());

        //get: get all departments
        get("/departments",(req,res)->{
            Map <String,Object> model = new HashMap<String,Object>();
            List<Departments> departments = departmentsDao.getAll();
            model.put("myDepartments",departments);
            return new ModelAndView(model,"department-list.hbs");
        },new HandlebarsTemplateEngine());

        //get: edit department form
        get("/departments/:id/edit",(req,res)->{
            int myId = Integer.parseInt(req.params("id"));
            System.out.println(myId);
            Map <String,Object> model = new HashMap<String,Object>();
            model.put("myDepartment",departmentsDao.findById(myId));
            return new ModelAndView(model,"department-edit-form.hbs");
        },new HandlebarsTemplateEngine());

        //post: update department
        post("/departments/:id",(req,res)->{
            int departmentId = Integer.parseInt(req.params("id"));
            String departmentName = req.queryParams("name");
            departmentsDao.update(departmentId,departmentName);
            res.redirect("/departments");
            return null;
        },new HandlebarsTemplateEngine());

        //get: delete department
        get("/departments/:id/delete",(req,res)->{
            int departmentId = Integer.parseInt(req.params("id"));
            departmentsDao.deleteById(departmentId);
            res.redirect("/departments");
            return null;
        },new HandlebarsTemplateEngine());

        //get: add new staff form
        get("/staff/new",(req,res)->{
            Map<String, Object> model = new HashMap<>();
            List<Departments> departments = departmentsDao.getAll();
            model.put("myDepartments",departments);
            return new ModelAndView(model,"new-staff-form.hbs");
        },new HandlebarsTemplateEngine());

        //post: add new staff
        post("/staff/new",(req,res)->{
            String name = req.queryParams("name");
            String role = req.queryParams("role");
            int departmentId = Integer.parseInt(req.queryParams("department"));
            String responsibility = req.queryParams("responsibility");
            Staff staff = new Staff(name,role,responsibility,departmentId);
            staffDao.add(staff);
            res.redirect("/");
            return null;
        },new HandlebarsTemplateEngine());

        //get: Edit staff form
        get("/staff/:id/edit",(req,resp)->{
            int id = Integer.parseInt(req.params("id"));
            Map<String,Object> model = new HashMap<String,Object>();
            model.put("myStaff",staffDao.findById(id));
            model.put("myDepartments",departmentsDao.getAll());
            return new ModelAndView(model,"staff-edit-form.hbs");
        },new HandlebarsTemplateEngine());

        //post save staff update
        post("/staff/:id",(req,resp)->{
            int id = Integer.parseInt(req.params("id"));
            String role = req.queryParams("role");
            staffDao.update(id,role);
            resp.redirect("/");
            return null;
        },new HandlebarsTemplateEngine());

        //pos: delete staff
        get("/staff/:id/delete",(req,resp)->{
            int id = Integer.parseInt(req.params("id"));
            staffDao.deleteById(id);
            resp.redirect("/");
            return null;
        },new HandlebarsTemplateEngine());


        //get: staff by department
        get("/department/:id/staff",(req,res)->{
            int departmentId = Integer.parseInt(req.params("id"));
            Map<String,Object> model = new HashMap<>();
            model.put("myStaff",staffDao.getByDepartment(departmentId));
            model.put("myDepartments",departmentsDao.getAll());
            model.put("myDepartment",departmentsDao.findById(departmentId));
            return new ModelAndView(model,"staff-list.hbs");
        },new HandlebarsTemplateEngine());


    }
}