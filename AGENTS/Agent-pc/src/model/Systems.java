package model;
// Generated Aug 29, 2017 2:09:57 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Systems generated by hbm2java
 */
public class Systems  implements java.io.Serializable {


     private Integer id;
     private Company company;
     private String name;
     private String description;
     private Integer status;
     private String lon;
     private String lat;
     private String url;
     private String ip;
     private String spare1;
     private String spare2;
     private Set alarms = new HashSet(0);
     private Set tts = new HashSet(0);
     private Set heartbeats = new HashSet(0);
     private Set systemsHasUsers = new HashSet(0);
     private Set requests = new HashSet(0);
     private Set systemsHasParameterses = new HashSet(0);

    public Systems() {
    }

	
    public Systems(Company company) {
        this.company = company;
    }
    public Systems(Company company, String name, String description, Integer status, String lon, String lat, String url, String ip, String spare1, String spare2, Set alarms, Set tts, Set heartbeats, Set systemsHasUsers, Set requests, Set systemsHasParameterses) {
       this.company = company;
       this.name = name;
       this.description = description;
       this.status = status;
       this.lon = lon;
       this.lat = lat;
       this.url = url;
       this.ip = ip;
       this.spare1 = spare1;
       this.spare2 = spare2;
       this.alarms = alarms;
       this.tts = tts;
       this.heartbeats = heartbeats;
       this.systemsHasUsers = systemsHasUsers;
       this.requests = requests;
       this.systemsHasParameterses = systemsHasParameterses;
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
    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
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
    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    public String getIp() {
        return this.ip;
    }
    
    public void setIp(String ip) {
        this.ip = ip;
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
    public Set getAlarms() {
        return this.alarms;
    }
    
    public void setAlarms(Set alarms) {
        this.alarms = alarms;
    }
    public Set getTts() {
        return this.tts;
    }
    
    public void setTts(Set tts) {
        this.tts = tts;
    }
    public Set getHeartbeats() {
        return this.heartbeats;
    }
    
    public void setHeartbeats(Set heartbeats) {
        this.heartbeats = heartbeats;
    }
    public Set getSystemsHasUsers() {
        return this.systemsHasUsers;
    }
    
    public void setSystemsHasUsers(Set systemsHasUsers) {
        this.systemsHasUsers = systemsHasUsers;
    }
    public Set getRequests() {
        return this.requests;
    }
    
    public void setRequests(Set requests) {
        this.requests = requests;
    }
    public Set getSystemsHasParameterses() {
        return this.systemsHasParameterses;
    }
    
    public void setSystemsHasParameterses(Set systemsHasParameterses) {
        this.systemsHasParameterses = systemsHasParameterses;
    }




}


