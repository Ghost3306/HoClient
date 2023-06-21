package com.example.client;

import javafx.scene.control.Button;

public class Appoint {
    private String name;
    private String age;
    private String date;
    private Button button;

    public Appoint(String name, String age, String date,Button button) {
        this.name = name;
        this.age = age;
        this.date = date;
        this.button = button;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getPhone() {
        return date;
    }

    public Button getButton() {
        return button;
    }
}
