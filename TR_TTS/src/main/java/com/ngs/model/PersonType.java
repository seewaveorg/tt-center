package com.ngs.model;
// Generated 28-Jul-2017 12:19:42 by Hibernate Tools 3.6.0


import java.util.HashSet;
import java.util.Set;

/**
 * PersonType generated by hbm2java
 */
public class PersonType  implements java.io.Serializable {


     private Integer id;
     private String name;
     private Integer status;
     private Set<User> users = new HashSet<User>(0);

    public PersonType() {
    }

    public PersonType(String name, Integer status, Set<User> users) {
       this.name = name;
       this.status = status;
       this.users = users;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Set<User> getUsers() {
        return this.users;
    }
    
    public void setUsers(Set<User> users) {
        this.users = users;
    }




}

