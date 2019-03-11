package com.ngs.model;
// Generated 18-Oct-2017 09:46:59 by Hibernate Tools 4.3.1



/**
 * SystemsHasUser generated by hbm2java
 */
public class SystemsHasUser  implements java.io.Serializable {


     private Integer id;
     private Systems systems;
     private User user;
     private String sms;
     private String email;
     private Integer status;

    public SystemsHasUser() {
    }

	
    public SystemsHasUser(Systems systems, User user) {
        this.systems = systems;
        this.user = user;
    }
    public SystemsHasUser(Systems systems, User user, String sms, String email, Integer status) {
       this.systems = systems;
       this.user = user;
       this.sms = sms;
       this.email = email;
       this.status = status;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Systems getSystems() {
        return this.systems;
    }
    
    public void setSystems(Systems systems) {
        this.systems = systems;
    }
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    public String getSms() {
        return this.sms;
    }
    
    public void setSms(String sms) {
        this.sms = sms;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }




}

