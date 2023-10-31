package com.ecom.ECommerce.entity;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name="accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "fname_acc")
    private String fname;


    @Column(name = "lname_acc")
    private String lname;


    @Column(name = "password_acc")
    private String password;


    @Column(name = "email_acc")
    private String email;

    @Column(name="status_acc")
    private int status;

    @Column(name="type_acc")
    private int type;

    private Timestamp timestamp_acc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    @JsonCreator
    public Account(@JsonProperty("fname") String fname,@JsonProperty("lname") String lname,@JsonProperty("password") String password,@JsonProperty("email") String email, @JsonProperty("status") int status,@JsonProperty("type") int type) {
        this.fname = fname;
        this.lname = lname;
        this.password = password;
        this.email = email;
        this.status = status;
        this.type = type;
    }

    public Account(int id, String fname, String lname, String password, String email, int status, int type) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.password = password;
        this.email = email;
        this.status = status;
        this.type = type;
    }
}
