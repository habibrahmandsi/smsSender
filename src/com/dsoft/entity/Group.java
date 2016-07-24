package com.dsoft.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * @Author: Md. Habibur Rahman on 25/07/15.
 */

@Entity
@Table(name = "contact_group")
public class Group extends AbstractBaseEntity{

    @Column(name = "name")
    private String name;

    @Transient
    private List<Person> personList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    @Override
    public String toString() {
        return "Group{" +
                "name='" + name + '\'' +
                ", personList=" + personList +
                '}';
    }
}
