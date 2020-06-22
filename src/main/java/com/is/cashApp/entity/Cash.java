package com.is.cashApp.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "cash")
public class Cash {

    @Id
    @GeneratedValue
    private Integer id;

    private String description;

    private Double cost;

    private String entryDate;

    private Integer typeId;

    private String typeName;

    public Cash() {
    }

    public Cash(String description, Double cost, String entryDate, Integer typeId, String typeName) {
        this.description = description;
        this.cost = cost;
        this.entryDate = entryDate;
        this.typeId = typeId;
        this.typeName = typeName;
    }
}
