package ru.shulz.model;

import java.util.ArrayList;

public class JsonData {
    public String id;
    public String title;
    public String name;
    public String phone;
    public String email;
    public String education;
    public boolean work;
    public ArrayList<Object> keySkills;
    public Address address;

    public static class Address {
        public String city;
        public String country;
    }
}
