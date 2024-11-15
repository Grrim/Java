package com.example.spring_projekt.Domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "users")
public class User extends pl.edu.zut.springsoap.gen.User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "name is required")
    @NotEmpty(message = "name can not be null")
    @Size(min = 3, max = 50, message = "name must be between 3 and 50 characters")
    private String name;

    @NotBlank(message = "email is required")
    @NotEmpty(message = "email can not be null")
    @Pattern(regexp = "^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})$", message= "invalid email address")
    private String email;

    @Lob
    private byte[] file;

    @Override
    public String toString() {
        return "Name: " + name + "\nEmail: " + email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public byte[] getFile() {  return file;  }

    public void setFile(byte[] file) {  this.file = file;  }

}