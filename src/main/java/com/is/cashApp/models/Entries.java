package com.is.cashApp.models;

public class Entries {

    private String desc;
    private Double cost;
    private String type;
    private String date;

    public Entries() {
    }

    public Entries(String desc, Double cost, String type, String date) {
        this.desc = desc;
        this.cost = cost;
        this.type = type;
        this.date = date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
