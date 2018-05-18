package com.example.thuca.myfit.models;

public class User {
    private String username;
    private String name;
    private String email;
    private String phone;
    private String max_role;
    private String[] roles;
    //for teacher
    private String department_code;
    private String teacher_code;
    private String import_hash;

    public User(String username, String name, String email, String phone, String max_role, String[] roles) {
        this.username = username;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.max_role = max_role;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMax_role() {
        return max_role;
    }

    public void setMax_role(String max_role) {
        this.max_role = max_role;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    public String getDepartment_code() {
        return department_code;
    }

    public void setDepartment_code(String department_code) {
        this.department_code = department_code;
    }

    public String getTeacher_code() {
        return teacher_code;
    }

    public void setTeacher_code(String teacher_code) {
        this.teacher_code = teacher_code;
    }

    public String getImport_hash() {
        return import_hash;
    }

    public void setImport_hash(String import_hash) {
        this.import_hash = import_hash;
    }
}
