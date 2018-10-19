package com.example.max.EltechProj1;

public class Lecture{
    private String subjOfStudy;
    private String lessonType;
    private String teacher;
    private String lectureHall;
    public String timeOfLesson;

    public Lecture(String input){
        String[] parsedInput = input.split("/");
        subjOfStudy = parsedInput[0];
        if (!parsedInput[1].equals("null")) lessonType = parsedInput[1];
        else lessonType = "";
        if (!parsedInput[2].equals("null")) teacher = parsedInput[2];
        else teacher = "";
        if (!parsedInput[3].equals("null")) lectureHall = parsedInput[3];
        else lectureHall = "";
        if (!parsedInput[1].equals("null")) timeOfLesson = parsedInput[4];
        else timeOfLesson = "";
    }

    public String GetLessons() {
        return subjOfStudy;
    }

    public String GetLessonsType(){
        return lessonType;
    }

    public String GetTeacher() {
        return teacher;
    }

    public String GetLectureHall() {
        return lectureHall;
    }

    public String GetTime(){return timeOfLesson;}
}
