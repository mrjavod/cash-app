package com.is.cashApp.models;

public class Entries {

    private String desc;
    private Double cost;
    private String type;

    public Entries() {
    }

    public Entries(String desc, Double cost, String type) {
        this.desc = desc;
        this.cost = cost;
        this.type = type;
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
}
