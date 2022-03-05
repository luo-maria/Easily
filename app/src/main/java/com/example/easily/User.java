package com.example.easily;
import java.io.Serializable;
public class User implements Serializable{
    private int student_id;
    private String username;
    private String password;
    private String student_name;
    private int student_num;
    private String gender;
    private int age;
    private String Institute;
    private String major;
    public User() {
        super();
        // TODO Auto-generated constructor stub
    }
    public User(String username, String password,String student_name,int student_num,String gender,int age,String Institute,String major ) {
        super();
        this.username = username;
        this.password = password;
        this.student_name=student_name;
        this.student_num=student_num;
        this.gender=gender;
        this.age=age;
        this.Institute=Institute;
        this.major=major;
    }
    public int getId() {
        return student_id;
    }
    public void setId(int id) {
        this.student_id = student_id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getStudent_name() {
        return student_name;
    }
    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }
    public int getStudent_num() { return student_num; }
    public void setStudent_num(int student_num) {
        this.student_num = student_num;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) { this.age = age; }
    public String getInstitute() {
        return Institute;
    }
    public void setInstitute(String Institute) {
        this.Institute = Institute;
    }
    public String getMajor() {
        return major;
    }
    public void setMajor(String major) {
        this.major = major;
    }
//    @Override
//    public String toString() {
////        return "User [id=" + student_id + ", username=" + username + ", password="
////                + password + "student_name="+student_name+"student_num="+student_num+"gender="+gender+
////                "age="+age+"Institute="+Institute+"major="+major+"]";
////    }
}
