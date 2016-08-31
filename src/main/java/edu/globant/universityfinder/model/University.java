package edu.globant.universityfinder.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by federico.calarco on 8/17/2016.
 */
public class University implements Serializable {


    private Long id;
    private String name;
    private String location;
    private String address;
    private String webpage;
    private String phone;
    private String email;
    private List<Career> careers = new ArrayList<Career>();

    public University() {
    }

    public University(String name, String location, String address, String webpage, String phone, String email) {
        this.name = name;
        this.location = location;
        this.address = address;
        this.webpage = webpage;
        this.phone = phone;
        this.email = email;
    }

    public University(Long id, String name, String location, String address, String webpage, String phone, String email, List<Career> careers) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.address = address;
        this.webpage = webpage;
        this.phone = phone;
        this.email = email;
        this.careers = careers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWebpage() {
        return webpage;
    }

    public void setWebpage(String webpage) {
        this.webpage = webpage;
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

    public List<Career> getCareers() {
        return careers;
    }

    public void setCareers(List<Career> careers) {
        this.careers = careers;
    }


}