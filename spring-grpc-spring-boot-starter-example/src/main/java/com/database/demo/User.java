package com.database.demo;

import java.time.LocalDateTime;
import java.util.List;

public class User {
    private String name;
    private String email;
    private String phone;

    List<String> groups= new java.util.ArrayList<>();

    private int id;

    private LocalDateTime createdAt;
    public User(String name, String email, String phone) {
        this(name, email, phone, 0, LocalDateTime.now());
    }
    public User(String name, String email, String phone, int id, LocalDateTime createdAt) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.id = id;
        this.createdAt = createdAt;
    }


    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<String> getGroups() {
        return groups;
    }

    public void setGroups(List<String> groups) {
        this.groups = groups;
    }
}