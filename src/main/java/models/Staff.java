package models;

import java.util.ArrayList;
import java.util.Objects;

public class Staff {

    private String name;
    private String role;
    private String responsibility;
    private int id;
    private int departmentId;

    private static ArrayList<Staff> mInstances = new ArrayList<>();

    public  Staff(String name,String role,String responsibility ,int departmentId){
        this.name =  name;
        this.role = role;
        this.responsibility = responsibility;
        this.departmentId = departmentId;
        this.mInstances.add(this);
        this.id = this.mInstances.size();

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Staff)) return false;
        Staff staff = (Staff) o;
        return  getId() == staff.getId() &&
                getName() == staff.getName() &&
                getRole() == staff.getRole() &&
                Objects.equals(getResponsibility(), staff.getResponsibility());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getRole(), getResponsibility(),getId());
    }
    public static ArrayList<Staff> getAll() {
        return mInstances;
    }


    public String getName(){
        //returns the staff name
        return this.name;
    }

    public String getRole(){
        //returns the staff Role
        return this.role;
    }

    public int getId(){
        //returns the staff id
        return this.id;
    }

    public String getResponsibility(){
        //returns the staff responsibility
        return this.responsibility;
    }

    public int getDepartmentId(){
        //returns the staff responsibility
        return this.departmentId;
    }

    public void setId(int id) {
        this.id = id;
    }



}