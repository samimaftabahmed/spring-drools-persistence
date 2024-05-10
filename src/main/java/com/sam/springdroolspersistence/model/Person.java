package com.sam.springdroolspersistence.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;
        return age == person.age && Objects.equals(name, person.name) && Objects.equals(gender, person.gender) && Objects.equals(toCompareName, person.toCompareName) && Objects.equals(toCompareGender, person.toCompareGender);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(name);
        result = 31 * result + age;
        result = 31 * result + Objects.hashCode(gender);
        result = 31 * result + Objects.hashCode(toCompareName);
        result = 31 * result + Objects.hashCode(toCompareGender);
        return result;
    }
}
