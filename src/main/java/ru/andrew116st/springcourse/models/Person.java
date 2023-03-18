package ru.andrew116st.springcourse.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


public class Person {
    private int id;

    @NotEmpty(message = "Поле имя - не может быть пустым")
    @Size(min = 2, max = 30, message = "Длина имени должна быть от 2 до 30 букв")
    private String name;

    @Min(value = 1930, message = "Введите год рождения минимум от -  1930")
    private int birthday;

    public Person() {

    }

    public Person(int id, String name, int birthday) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthday() {
        return birthday;
    }

    public void setBirthday(int birthday) {
        this.birthday = birthday;
    }


}
