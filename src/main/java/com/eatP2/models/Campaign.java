package com.eatP2.models;

public class Campaign extends Product{

    private String startDate;
    private String endDate;
    private String status;

    public Campaign(String id,String name,  String startDate, String endDate, String status) {
        super(id,name);
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
