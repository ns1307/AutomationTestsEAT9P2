package com.eatP2.models;

import java.util.Objects;

public class BillingAccount {
    private String id;
    private String accName;
    private String accStatus;
    private String accType;
    private String accDescription;
    private Address address;
    public BillingAccount(String id, String accName, String accStatus, String accType, String accDescription, Address adress) {
        this.id=id;
        this.accName = accName;
        this.accStatus = accStatus;
        this.accType = accType;
        this.accDescription = accDescription;
        this.address = adress;
    }

    public BillingAccount(String accName, String accStatus, String accType, String accDescription) {
        this.id=null;
        this.accName = accName;
        this.accStatus = accStatus;
        this.accType = accType;
        this.accDescription = accDescription;
        this.address = null;
    }

    public BillingAccount(String accName, String accStatus, String accType, String accDescription, Address address) {
        this.id=null;
        this.accName = accName;
        this.accStatus = accStatus;
        this.accType = accType;
        this.accDescription = accDescription;
        this.address = address;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getAccName() {
        return accName;
    }
    public void setAccName(String accName) {
        this.accName = accName;
    }
    public String getAccStatus() {
        return accStatus;
    }
    public void setAccStatus(String accStatus) {
        this.accStatus = accStatus;
    }
    public String getAccType() {
        return accType;
    }
    public void setAccType(String accType) {
        this.accType = accType;
    }
    public String getAccDescription() {
        return accDescription;
    }
    public void setAccDescription(String accDescription) {
        this.accDescription = accDescription;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    public Address getAddress() {
        return address;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BillingAccount that = (BillingAccount) o;
        return Objects.equals(accName, that.accName) &&
                Objects.equals(accStatus, that.accStatus) &&
                Objects.equals(accType, that.accType) &&
                Objects.equals(accDescription, that.accDescription) &&
                Objects.equals(address.getCity(), that.address.getCity()) &&
                Objects.equals(address.getDescription(), that.address.getDescription()) &&
                Objects.equals(address.getDistrict(), that.address.getDistrict()) &&
                Objects.equals(address.getPostalCode(), that.address.getPostalCode());

    }

    @Override
    public int hashCode() {
        return Objects.hash(accName, accStatus, accType, accDescription,address);
    }

}
