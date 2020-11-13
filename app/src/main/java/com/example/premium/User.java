package com.example.premium;

import android.provider.ContactsContract;

public class User {

    private String firstName ,lastName ,email ,password ,bdDay ,bdMonth ,bdYear;

    public User(){}

    public User(String firstName, String lastName, String email, String password, String bdDay, String bdMonth, String bdYear) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.bdDay = bdDay;
        this.bdMonth = bdMonth;
        this.bdYear = bdYear;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBdDay(String bdDay) {
        this.bdDay = bdDay;
    }

    public void setBdMonth(String bdMonth) {
        this.bdMonth = bdMonth;
    }

    public void setBdYear(String bdYear) {
        this.bdYear = bdYear;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getBdDay() {
        return bdDay;
    }

    public String getBdMonth() {
        return bdMonth;
    }

    public String getBdYear() {
        return bdYear;
    }

}
