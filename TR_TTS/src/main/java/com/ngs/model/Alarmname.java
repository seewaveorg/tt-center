package com.ngs.model;
// Generated 28-Jul-2017 12:19:42 by Hibernate Tools 3.6.0


import java.util.HashSet;
import java.util.Set;

/**
 * Alarmname generated by hbm2java
 */
public class Alarmname  implements java.io.Serializable {


     private Integer id;
     private String name;
     private String comment;
     private Integer status;
     private Set<Alarm> alarms = new HashSet<Alarm>(0);

    public Alarmname() {
    }

    public Alarmname(String name, String comment, Integer status, Set<Alarm> alarms) {
       this.name = name;
       this.comment = comment;
       this.status = status;
       this.alarms = alarms;
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
    public String getComment() {
        return this.comment;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }
    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Set<Alarm> getAlarms() {
        return this.alarms;
    }
    
    public void setAlarms(Set<Alarm> alarms) {
        this.alarms = alarms;
    }




}


