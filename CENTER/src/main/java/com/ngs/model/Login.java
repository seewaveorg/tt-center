package com.ngs.model;
// Generated 13-Oct-2017 13:23:18 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Login generated by hbm2java
 */
public class Login  implements java.io.Serializable {


     private Integer id;
     private Rolls rolls;
     private User user;
     private String username;
     private String password;
     private Integer status;
     private Set<Log> logs = new HashSet<Log>(0);

    public Login() {
    }

	
    public Login(Rolls rolls, User user) {
        this.rolls = rolls;
        this.user = user;
    }
    public Login(Rolls rolls, User user, String username, String password, Integer status, Set<Log> logs) {
       this.rolls = rolls;
       this.user = user;
       this.username = username;
       this.password = password;
       this.status = status;
       this.logs = logs;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Rolls getRolls() {
        return this.rolls;
    }
    
    public void setRolls(Rolls rolls) {
        this.rolls = rolls;
    }
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Set<Log> getLogs() {
        return this.logs;
    }
    
    public void setLogs(Set<Log> logs) {
        this.logs = logs;
    }




}


