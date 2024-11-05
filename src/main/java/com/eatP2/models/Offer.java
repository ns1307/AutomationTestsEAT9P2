package com.eatP2.models;

public class Offer extends Product{
    private String id;
    private String name;
    private String startDate;
    private String endDate;
    private String status;

    public Offer(String id, String name, String startDate, String endDate) {
        super(id,name);
        this.startDate = startDate;
        this.endDate = endDate;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
