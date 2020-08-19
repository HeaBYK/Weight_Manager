package com.example.weight_manager.getterSetter;

import java.io.Serializable;
import java.text.Collator;
import java.util.Comparator;

public class Kilocal implements Serializable {
    private String workName = "";
    private String kcalPerHour = "";
    private String timePer100Kcal = "";

    public String getWorkName(){
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }
    public String getKcalPerHour() {
        return kcalPerHour;
    }


    public void setKcalPerHour(String kcalPerHour) {
        this.kcalPerHour = kcalPerHour;
    }

    public String getTimePer100Kcal() {
        return timePer100Kcal;
    }

    public void setTimePer100Kcal(String timePer100Kcal) {
        this.timePer100Kcal = timePer100Kcal;
    }

    public static final Comparator<Kilocal> comp = new Comparator<Kilocal>() {
        private final Collator Coll = Collator.getInstance();
        @Override
        public int compare(Kilocal kilo1, Kilocal kilo2) {
            return Coll.compare(kilo1.workName,kilo2.workName);
        }
    };
}