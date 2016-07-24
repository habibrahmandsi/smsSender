package com.dsoft.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @Author: Md. Habibur Rahman on 25/07/15.
 */

@Entity
@Table(name = "group_member")
public class GroupMember extends AbstractBaseEntity{

    @ManyToOne
    @JoinColumn(name="person_id")
    private Person person;

    @ManyToOne
    @JoinColumn(name="group_id")
    private Group group;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "GroupMember{" +
                "person=" + person +
                ", group=" + group +
                '}';
    }
}
