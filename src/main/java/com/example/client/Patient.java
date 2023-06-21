package com.example.client;

import javafx.scene.control.Button;

public class Patient {
    private String name;
    private String age;
    private String phone;
    private Button button;

    public Patient(String name, String age, String phone, Button button) {
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.button = button;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }

    public Button getButton() {
        return button;
    }
}
