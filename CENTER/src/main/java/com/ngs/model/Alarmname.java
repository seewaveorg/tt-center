package com.ngs.model;
// Generated 13-Oct-2017 13:23:18 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Alarmname generated by hbm2java
 */
public class Alarmname  implements java.io.Serializable {


     private Integer id;
     private String oid;
     private String name;
     private String clear;
     private String comment;
     private Integer status;
     private Set<Alarm> alarms = new HashSet<Alarm>(0);

    public Alarmname() {
    }

    public Alarmname(String oid,String name,String clear, String comment, Integer status, Set<Alarm> alarms) {
       this.oid = oid;
       this.name = name;
       this.clear = clear;
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
    
    public String getOid() {
        return this.oid;
    }
    
    public void setOid(String oid) {
        this.oid = oid;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getClear() {
        return this.clear;
    }
    
    public void setClear(String clear) {
        this.clear = clear;
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


