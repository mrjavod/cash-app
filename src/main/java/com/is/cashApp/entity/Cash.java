package com.is.cashApp.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cash")
public class Cash {

    @Id
    @GeneratedValue
    private Integer id;

    private String description;

    private Double cost;

    @Temporal(TemporalType.DATE)
    private Date entryDate;

    private Integer typeId;

    private String typeName;

    public Cash() {
    }

    public Cash(String description, Double cost, Date entryDate, Integer typeId, String typeName) {
        this.description = description;
        this.cost = cost;
        this.entryDate = entryDate;
        this.typeId = typeId;
        this.typeName = typeName;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Double getCost() {
        return cost;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
