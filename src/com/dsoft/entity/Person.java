package com.dsoft.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @Author: Md. Habibur Rahman on 25/07/15.
 */

@Entity
@Table(name = "person")
public class Person extends AbstractBaseEntity{

    @Column(name = "name")
    private String name;

    @Column(name = "mobile_no", unique = true)
    private String mobileNO;

    @Column(name = "father_name")
    private String fatherName;

    @Column(name = "mother_name")
    private String motherName;

    @Column(name = "designation")
    private String designation;

    @Column(name = "address")
    private String address;


    @Transient
    private Boolean active;

    @Transient
    private Boolean saved;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNO() {
        return mobileNO;
    }

    public void setMobileNO(String mobileNO) {
        this.mobileNO = mobileNO;
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

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getSaved() {
        return saved;
    }

    public void setSaved(Boolean saved) {
        this.saved = saved;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", mobileNO='" + mobileNO + '\'' +
                ", fatherName='" + fatherName + '\'' +
                ", motherName='" + motherName + '\'' +
                ", designation='" + designation + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
