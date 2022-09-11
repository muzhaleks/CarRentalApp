package com.github.muzhaleks.model;

import java.io.Serializable;
import java.util.Objects;

public class Role implements Serializable {
    private int id;
    private String role;

    public Role() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    @Override
    public boolean equals(Object object){
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Role role1 = (Role) object;
        return id == role1.id && Objects.equals(role, role1.role);
    }
    @Override
    public int hashCode(){
        return Objects.hash(id, role);
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role='" + role + '\'' +
                '}';
    }
}
