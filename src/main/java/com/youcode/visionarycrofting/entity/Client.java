package com.youcode.visionarycrofting.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table
public class Client implements Serializable {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String address;


    @OneToMany (mappedBy = "client", fetch = FetchType.EAGER)
    private List<Command> commandList;


    public Client(Long id, String name, String email, String password, String phone, String address) {
        this.id = id;

        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;

    }

    public Client(String name, String email, String password, String phone, String address) {

        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;

    }

    public Client ( Long id ) {
        this.id = id;
    }

    public List<Command> getCommandList() {
        return commandList;
    }

    public void setCommandList(List<Command> commandList) {
        this.commandList = commandList;
    }

    public void setCommand(Command command) {
        this.commandList.add(command) ;
    }
    public Client() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    @Override
    public String toString ( ) {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", commandList=" + commandList +
                '}';
    }
}
