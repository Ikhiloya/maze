package com.loya.maze.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Document(collection = "Users")
public class User {
    @Id
    private String uuid;
    private String username;
    private String password;
    private List<Role> roles;


    protected User() {
    }

    public User(String uuid, String username, String password, List<Role> roles) {
        this.uuid = uuid;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
