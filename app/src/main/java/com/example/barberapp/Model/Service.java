package com.example.barberapp.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Service implements Serializable {
    private String name;
    private String price;
    private ArrayList<String> queuesNotAvailable;

    public Service(String name, String price,ArrayList<String> queuesNotAvailable) {
        this.name = name;
        this.price = price;
        this.queuesNotAvailable = queuesNotAvailable;
    }

    public Service() {
    }

    public ArrayList<String> getQueuesNotAvailable() {
        return queuesNotAvailable;
    }

    public void setQueuesNotAvailable(ArrayList<String> queuesNotAvailable) {
        this.queuesNotAvailable = queuesNotAvailable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
