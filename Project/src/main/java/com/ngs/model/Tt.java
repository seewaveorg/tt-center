package com.ngs.model;
// Generated 13-Oct-2017 13:23:18 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Tt generated by hbm2java
 */
public class Tt  implements java.io.Serializable {


     private Integer id;
     private Company company;
     private Department department;
     private Systems systems;
     private User user;
     private Date regtime;
     private String comment;
     private Integer status;
     private Set<LessonLearnt> lessonLearnts = new HashSet<LessonLearnt>(0);
     private Set<Alarm> alarms = new HashSet<Alarm>(0);
     private Set<TtUpdate> ttUpdates = new HashSet<TtUpdate>(0);

    public Tt() {
    }

	
    public Tt(Date regtime) {
        this.regtime = regtime;
    }
    public Tt(Company company, Department department, Systems systems, User user, Date regtime, String comment, Integer status, Set<LessonLearnt> lessonLearnts, Set<Alarm> alarms, Set<TtUpdate> ttUpdates) {
       this.company = company;
       this.department = department;
       this.systems = systems;
       this.user = user;
       this.regtime = regtime;
       this.comment = comment;
       this.status = status;
       this.lessonLearnts = lessonLearnts;
       this.alarms = alarms;
       this.ttUpdates = ttUpdates;
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
    public Department getDepartment() {
        return this.department;
    }
    
    public void setDepartment(Department department) {
        this.department = department;
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
    public Date getRegtime() {
        return this.regtime;
    }
    
    public void setRegtime(Date regtime) {
        this.regtime = regtime;
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
    public Set<LessonLearnt> getLessonLearnts() {
        return this.lessonLearnts;
    }
    
    public void setLessonLearnts(Set<LessonLearnt> lessonLearnts) {
        this.lessonLearnts = lessonLearnts;
    }
    public Set<Alarm> getAlarms() {
        return this.alarms;
    }
    
    public void setAlarms(Set<Alarm> alarms) {
        this.alarms = alarms;
    }
    public Set<TtUpdate> getTtUpdates() {
        return this.ttUpdates;
    }
    
    public void setTtUpdates(Set<TtUpdate> ttUpdates) {
        this.ttUpdates = ttUpdates;
    }




}

