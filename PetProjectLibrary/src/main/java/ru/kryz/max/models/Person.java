package ru.kryz.max.models;


public class Person {

    private int id;

    //@NotEmpty(message = "Имя не должно быть пустым")
    //@Size(min = 2, max = 100, message = "Имя должно быть от 2 до 100 символов длиной")
    private String fullName;

    //@Min(value = 1900, message = "Год рождения должен быть больше, чем 1900")
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