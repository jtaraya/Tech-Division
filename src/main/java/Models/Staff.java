package Models;

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
