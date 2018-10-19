package com.example.max.EltechProj1;

public class Timetable {
    private String dayOfTheWeek;
    public Lecture[] lessons = new Lecture[6];

    public Timetable(String day){
        this.dayOfTheWeek = day;
    }

    public String GetDay() {
        return dayOfTheWeek;
    }


}