package models;

import java.util.ArrayList;
import java.util.Objects;

public class Departments {
    private String name;
    private int id;
    private static ArrayList<Departments> mInstances = new ArrayList<>();

    public Departments(String name){
        this.name = name;
        this.mInstances.add(this);
        this.id = this.mInstances.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Departments)) return false;
        Departments departments = (Departments) o;
        return  getId() == departments.getId() &&
                Objects.equals(getName(), departments.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(),getId());
    }

    public static ArrayList<Departments> getAll(){
        return mInstances;
    }

    public String getName(){
        return this.name;
    }

    public int getId(){
        return this.id;
    }


    public void setId(int id) {
        this.id = id;
    }


}
