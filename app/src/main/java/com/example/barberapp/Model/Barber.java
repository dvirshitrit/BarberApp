package com.example.barberapp.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Barber implements Serializable {

    public String name;
    public String barberId;
    public String phone;
    public List<Service> service;
    public ArrayList<String>timeSlot;

    public Barber(String name, String barberId, String phone, List<Service> services,ArrayList<String>timeSlot) {
        this.name = name;
        this.barberId = barberId;
        this.phone = phone;
        this.service = services;
        this.timeSlot=timeSlot;
    }

    public Barber() {
    }

    public ArrayList<String> getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(ArrayList<String> timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBarberId() {
        return barberId;
    }

    public void setBarberId(String barberId) {
        this.barberId = barberId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Service> getService() {
        return service;
    }

    public void setService(List<Service> services) {
        this.service = services;
    }
}
