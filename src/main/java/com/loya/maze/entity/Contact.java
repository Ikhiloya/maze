package com.loya.maze.entity;

public class Contact {
    private String phone;
    private String email;
    private String address;
    private String lga;
    private String state;
    private String country;


    public Contact() {
    }

    public Contact(String phone, String email, String address, String lga, String state, String country) {
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.lga = lga;
        this.state = state;
        this.country = country;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLga() {
        return lga;
    }

    public void setLga(String lga) {
        this.lga = lga;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
