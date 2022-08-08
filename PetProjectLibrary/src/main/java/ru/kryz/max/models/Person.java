package ru.kryz.max.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Person {

    private int id;

    @NotEmpty(message = "Should not be empty")
    @Size(min = 2, max = 50, message = "Name should be between 2 and 50 characters")
    private String fullName;

    @Min(value = 1900, message = "Should be greater than 1900")
    private int yearOfBirth;

    //empty constructor need for Spring
    public Person() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}
