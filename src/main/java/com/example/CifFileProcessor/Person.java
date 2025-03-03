package com.example.CifFileProcessor.generator;

import java.time.LocalDate;

public class Person {
    private String name; // 20 characters
    private int age;     // 3 characters
    private LocalDate birthDate; // 10 characters, yyyy-MM-dd

    // Constructors, Getters, Setters

    public Person(String name, int age, LocalDate birthDate) {
        this.name = name;
        this.age = age;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
