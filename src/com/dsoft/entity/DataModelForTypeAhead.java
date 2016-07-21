package com.dsoft.entity;

/**
 * Created by habib on 7/25/15.
 */
public class DataModelForTypeAhead {

    private Boolean  status;
    private String  error;
    private Person person;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "DataModelForTypeAhead{" +
                "status=" + status +
                ", error='" + error + '\'' +
                ", person=" + person +
                '}';
    }
}
