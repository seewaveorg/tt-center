package com.ngs.model;
// Generated 13-Oct-2017 13:23:18 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Department generated by hbm2java
 */
public class Department  implements java.io.Serializable {


     private Integer id;
     private Company company;
     private String name;
     private Integer status;
     private Set<UserHasDepartment> userHasDepartments = new HashSet<UserHasDepartment>(0);
     private Set<Tt> tts = new HashSet<Tt>(0);

    public Department() {
    }

	
    public Department(Company company) {
        this.company = company;
    }
    public Department(Company company, String name, Integer status, Set<UserHasDepartment> userHasDepartments, Set<Tt> tts) {
       this.company = company;
       this.name = name;
       this.status = status;
       this.userHasDepartments = userHasDepartments;
       this.tts = tts;
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
    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Set<UserHasDepartment> getUserHasDepartments() {
        return this.userHasDepartments;
    }
    
    public void setUserHasDepartments(Set<UserHasDepartment> userHasDepartments) {
        this.userHasDepartments = userHasDepartments;
    }
    public Set<Tt> getTts() {
        return this.tts;
    }
    
    public void setTts(Set<Tt> tts) {
        this.tts = tts;
    }




}

