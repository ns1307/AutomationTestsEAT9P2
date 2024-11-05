package com.eatP2.models;


import java.util.Objects;

public class ContactMedium {
    private String email;
    private String homePhone;
    private String mobilePhone;
    private String fax;

    public ContactMedium(String email, String homePhone, String mobilePhone, String fax) {
        this.email = email;
        this.homePhone = homePhone;
        this.mobilePhone = mobilePhone;
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactMedium that = (ContactMedium) o;
        return Objects.equals(email, that.email) &&
                Objects.equals(homePhone, that.homePhone) &&
                Objects.equals(mobilePhone, that.mobilePhone) &&
                Objects.equals(fax, that.fax);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, homePhone, mobilePhone, fax);
    }
}

