package com.example.thuca.myfit.models;

/**
 * Created by THINKPAD on 29-Apr-18.
 */

public class Schedule {

    public String getTeacher_code() {
        return teacher_code;
    }

    public void setTeacher_code(String teacher_code) {
        this.teacher_code = teacher_code;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getSubject_code() {
        return subject_code;
    }

    public void setSubject_code(String subject_code) {
        this.subject_code = subject_code;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public String getMerged_class() {
        return merged_class;
    }

    public void setMerged_class(String merged_class) {
        this.merged_class = merged_class;
    }

    private String teacher_code;
    private String room;
    private String subject_code;
    private String subject_name;
    private String merged_class;

    public int getCa() {
        return ca;
    }

    public void setCa(int ca) {
        this.ca = ca;
    }

    private int ca;
    public Schedule(){

    }
    public Schedule(String teacher_code, String room, String subject_code, String subject_name, String merged_class){
        this.teacher_code=teacher_code;
        this.room=room;
        this.subject_code=subject_code;
        this.subject_name=subject_name;
        this.merged_class=merged_class;
    }
}
