package com.ngs.model;
// Generated 13-Oct-2017 13:23:18 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * User generated by hbm2java
 */
public class User  implements java.io.Serializable {


     private Integer id;
     private Company company;
     private PersonType personType;
     private String title;
     private String initials;
     private String firstname;
     private String lastname;
     private Date birthdate;
     private String gender;
     private String mobile;
     private String email;
     private String nic;
     private String addressLine1;
     private String addressLine2;
     private String addressLine3;
     private Integer status;
     private Date regtime;
     private Set<Login> logins = new HashSet<Login>(0);
     private Set<UserHasDepartment> userHasDepartments = new HashSet<UserHasDepartment>(0);
     private Set<Tt> tts = new HashSet<Tt>(0);
     private Set<TtUpdate> ttUpdates = new HashSet<TtUpdate>(0);
     private Set<Alerts> alertses = new HashSet<Alerts>(0);
     private Set<SystemsHasUser> systemsHasUsers = new HashSet<SystemsHasUser>(0);

    public User() {
    }

	
    public User(Company company, PersonType personType, String title, String initials, String lastname, String gender, String addressLine1, String addressLine2, String addressLine3, Date regtime) {
        this.company = company;
        this.personType = personType;
        this.title = title;
        this.initials = initials;
        this.lastname = lastname;
        this.gender = gender;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.addressLine3 = addressLine3;
        this.regtime = regtime;
    }
    public User(Company company, PersonType personType, String title, String initials, String firstname, String lastname, Date birthdate, String gender, String mobile, String email, String nic, String addressLine1, String addressLine2, String addressLine3, Integer status, Date regtime, Set<Login> logins, Set<UserHasDepartment> userHasDepartments, Set<Tt> tts, Set<TtUpdate> ttUpdates, Set<Alerts> alertses, Set<SystemsHasUser> systemsHasUsers) {
       this.company = company;
       this.personType = personType;
       this.title = title;
       this.initials = initials;
       this.firstname = firstname;
       this.lastname = lastname;
       this.birthdate = birthdate;
       this.gender = gender;
       this.mobile = mobile;
       this.email = email;
       this.nic = nic;
       this.addressLine1 = addressLine1;
       this.addressLine2 = addressLine2;
       this.addressLine3 = addressLine3;
       this.status = status;
       this.regtime = regtime;
       this.logins = logins;
       this.userHasDepartments = userHasDepartments;
       this.tts = tts;
       this.ttUpdates = ttUpdates;
       this.alertses = alertses;
       this.systemsHasUsers = systemsHasUsers;
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
    public PersonType getPersonType() {
        return this.personType;
    }
    
    public void setPersonType(PersonType personType) {
        this.personType = personType;
    }
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    public String getInitials() {
        return this.initials;
    }
    
    public void setInitials(String initials) {
        this.initials = initials;
    }
    public String getFirstname() {
        return this.firstname;
    }
    
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() {
        return this.lastname;
    }
    
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public Date getBirthdate() {
        return this.birthdate;
    }
    
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
    public String getGender() {
        return this.gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getMobile() {
        return this.mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public String getNic() {
        return this.nic;
    }
    
    public void setNic(String nic) {
        this.nic = nic;
    }
    public String getAddressLine1() {
        return this.addressLine1;
    }
    
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }
    public String getAddressLine2() {
        return this.addressLine2;
    }
    
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }
    public String getAddressLine3() {
        return this.addressLine3;
    }
    
    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }
    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Date getRegtime() {
        return this.regtime;
    }
    
    public void setRegtime(Date regtime) {
        this.regtime = regtime;
    }
    public Set<Login> getLogins() {
        return this.logins;
    }
    
    public void setLogins(Set<Login> logins) {
        this.logins = logins;
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
    public Set<TtUpdate> getTtUpdates() {
        return this.ttUpdates;
    }
    
    public void setTtUpdates(Set<TtUpdate> ttUpdates) {
        this.ttUpdates = ttUpdates;
    }
    public Set<Alerts> getAlertses() {
        return this.alertses;
    }
    
    public void setAlertses(Set<Alerts> alertses) {
        this.alertses = alertses;
    }
    public Set<SystemsHasUser> getSystemsHasUsers() {
        return this.systemsHasUsers;
    }
    
    public void setSystemsHasUsers(Set<SystemsHasUser> systemsHasUsers) {
        this.systemsHasUsers = systemsHasUsers;
    }




}


