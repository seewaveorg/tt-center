package com.ngs.model;
// Generated 13-Oct-2017 13:23:18 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Branch generated by hbm2java
 */
public class Branch  implements java.io.Serializable {


     private Integer id;
     private Company company;
     private String name;
     private String description;
     private String lon;
     private String lat;
     private Date regtime;
     private String spare1;
     private String spare2;
     private Integer status;
     private Set<Systems> systemses = new HashSet<Systems>(0);

    public Branch() {
    }

	
    public Branch(Company company) {
        this.company = company;
    }
    public Branch(Company company, String name, String description, String lon, String lat, Date regtime, String spare1, String spare2, Integer status, Set<Systems> systemses) {
       this.company = company;
       this.name = name;
       this.description = description;
       this.lon = lon;
       this.lat = lat;
       this.regtime = regtime;
       this.spare1 = spare1;
       this.spare2 = spare2;
       this.status = status;
       this.systemses = systemses;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Company getCompany() {
        return this.company;
    }
    
    public void setCompany(Company company) {
        this.company = company;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    public String getLon() {
        return this.lon;
    }
    
    public void setLon(String lon) {
        this.lon = lon;
    }
    public String getLat() {
        return this.lat;
    }
    
    public void setLat(String lat) {
        this.lat = lat;
    }
    public Date getRegtime() {
        return this.regtime;
    }
    
    public void setRegtime(Date regtime) {
        this.regtime = regtime;
    }
    public String getSpare1() {
        return this.spare1;
    }
    
    public void setSpare1(String spare1) {
        this.spare1 = spare1;
    }
    public String getSpare2() {
        return this.spare2;
    }
    
    public void setSpare2(String spare2) {
        this.spare2 = spare2;
    }
    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Set<Systems> getSystemses() {
        return this.systemses;
    }
    
    public void setSystemses(Set<Systems> systemses) {
        this.systemses = systemses;
    }




}


