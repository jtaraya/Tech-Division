package Models;

import java.util.ArrayList;

public class Departments {

    private String name;
    private int id;
    private static ArrayList<Departments> mInstances = new ArrayList<>();

    public Departments(String name){
        this.name = name;
        this.mInstances.add(this);
        this.id = this.mInstances.size();
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


}
