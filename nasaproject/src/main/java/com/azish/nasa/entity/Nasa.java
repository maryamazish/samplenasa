package com.azish.nasa.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "nasa")
public class Nasa {
    // @Id annotation specifies the primary key of an entity.
    // @GeneratedValue provides the generation strategy specification for the primary key values.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "method_name")
    private String methodName;
    @Column(name = "milliseconds")
    private Long milliseconds;
    @Column(name = "created_date")
    private Date createdDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Long getMilliseconds() {
        return milliseconds;
    }

    public void setMilliseconds(Long milliseconds) {
        this.milliseconds = milliseconds;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
