package com.example.assignment.Model;

import java.io.Serializable;

public class SmsAndExpanseDataModel implements Serializable {

    String mobile, message, date, type;

    public SmsAndExpanseDataModel() { }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}