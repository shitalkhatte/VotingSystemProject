package com.example.VotingSystem.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor

public class Region
{
    private String area;
    private String city;

    private String state;
    private int pinCode;

    public Region(String area, String city, String state, int pinCode) {
        this.area = area;
        this.city = city;
        this.state = state;
        this.pinCode = pinCode;
    }

    public String getArea() {
        return area;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }
}
