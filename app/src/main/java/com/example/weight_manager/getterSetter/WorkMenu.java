package com.example.weight_manager.getterSetter;

import java.util.ArrayList;

public class WorkMenu {
    private int id = -1;
    private String name = "";
    private ArrayList<Work> work = null;

    public WorkMenu(int id, String name, ArrayList<Work> work) {
        this.id = id;
        this.name = name;
        this.work = work;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Work> getWork() {
        return work;
    }

    public void setWork(ArrayList<Work> work) {
        this.work = work;
    }

}