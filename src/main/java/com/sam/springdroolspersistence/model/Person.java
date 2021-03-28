package com.sam.springdroolspersistence.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Person implements Serializable {

    private String name;
    private int age;
    private String gender;
    private String toCompareName;
    private String toCompareGender;

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        System.out.println(LocalDateTime.now().toString());
        this.gender = gender;
    }

    public String getToCompareName() {
        return toCompareName;
    }

    public void setToCompareName(String toCompareName) {
        this.toCompareName = toCompareName;
    }

    public String getToCompareGender() {
        return toCompareGender;
    }

    public void setToCompareGender(String toCompareGender) {
        this.toCompareGender = toCompareGender;
    }
}
