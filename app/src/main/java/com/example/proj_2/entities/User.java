package com.example.proj_2.entities;


import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

//
@Entity(indices = {@Index(value = {"username"}, unique = true)})
public class User {
    @PrimaryKey(autoGenerate = true)
    public int id;

    private String firstName;
    private String lastName;
    private String username;
    private String password;

    public void setFirstName(String firstName) throws IllegalArgumentException {
        if (firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("Enter Valid First Name");
        }
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) throws IllegalArgumentException {
        if (lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Enter Valid Last Name");
        }
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }


    public void setPassword(String password) throws IllegalArgumentException {
        if (password.trim().isEmpty()) {
            throw new IllegalArgumentException("Enter Valid Password");
        }
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) throws IllegalArgumentException {
        if (username.trim().isEmpty()) {
            throw new IllegalArgumentException("Enter Valid Username");
        }
        this.username = username;
    }

    public String getUsername() {
        return username;
    }


}


