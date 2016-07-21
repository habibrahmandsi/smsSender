package com.dsoft.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: Md. Habibur Rahman on 25/07/15.
 */

@Entity
@Table(name = "user")
public class User extends AbstractBaseEntity{

    public User(){
        this.dateOfBirth = new Date();
        this.joiningDate = new Date();
        this.active = true;
        this.maxDiscountPercent = 10d;
    }

    @Column(name = "name")
    private String name;

    @Column(name = "user_name", unique = true)
    private String userName;

    @Column(name = "father_name")
    private String fatherName;

    @Column(name = "mother_name")
    private String motherName;

    @Column(name = "sex")
    private String sex;

    @Column(name = "age")
    private Integer age;

    @Column(name = "religion")
    private String religion;

    @Column(name = "national_id_no")
    private String nationalIdNo;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "address")
    private String address;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "is_active")
    private boolean active;

    @Column(name = "email")
    private String email;

    @Column(name = "user_type")
    private String userType;

    @Column(name = "joining_date")
    private Date joiningDate;

    @Column(name = "starting_salary")
    private Double startingSalary;

    @Column(name = "currentSalary")
    private Double currentSalary;

    @Column(name = "max_discount_percent")
    private Double maxDiscountPercent;

    @Column(name = "profile_picture_path")
    private String profilePicturePath;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getNationalIdNo() {
        return nationalIdNo;
    }

    public void setNationalIdNo(String nationalIdNo) {
        this.nationalIdNo = nationalIdNo;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Date getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }

    public Double getStartingSalary() {
        return startingSalary;
    }

    public void setStartingSalary(Double startingSalary) {
        this.startingSalary = startingSalary;
    }

    public Double getCurrentSalary() {
        return currentSalary;
    }

    public void setCurrentSalary(Double currentSalary) {
        this.currentSalary = currentSalary;
    }

    public Double getMaxDiscountPercent() {
        return maxDiscountPercent;
    }

    public void setMaxDiscountPercent(Double maxDiscountPercent) {
        this.maxDiscountPercent = maxDiscountPercent;
    }

    public String getProfilePicturePath() {
        return profilePicturePath;
    }

    public void setProfilePicturePath(String profilePicturePath) {
        this.profilePicturePath = profilePicturePath;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", fatherName='" + fatherName + '\'' +
                ", motherName='" + motherName + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", religion='" + religion + '\'' +
                ", nationalIdNo='" + nationalIdNo + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", active=" + active +
                ", email='" + email + '\'' +
                ", userType='" + userType + '\'' +
                ", joiningDate=" + joiningDate +
                ", startingSalary=" + startingSalary +
                ", currentSalary=" + currentSalary +
                ", maxDiscountPercent='" + maxDiscountPercent + '\'' +
                ", profilePicturePath='" + profilePicturePath + '\'' +
                '}';
    }
}
