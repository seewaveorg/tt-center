package model;
// Generated Aug 29, 2017 2:09:57 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Request generated by hbm2java
 */
public class Request  implements java.io.Serializable {


     private Integer id;
     private Parameters parameters;
     private Systems systems;
     private String value;
     private String comment;
     private Date regtime;
     private Integer status;

    public Request() {
    }

	
    public Request(Parameters parameters, Systems systems) {
        this.parameters = parameters;
        this.systems = systems;
    }
    public Request(Parameters parameters, Systems systems, String value, String comment, Date regtime, Integer status) {
       this.parameters = parameters;
       this.systems = systems;
       this.value = value;
       this.comment = comment;
       this.regtime = regtime;
       this.status = status;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Parameters getParameters() {
        return this.parameters;
    }
    
    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }
    public Systems getSystems() {
        return this.systems;
    }
    
    public void setSystems(Systems systems) {
        this.systems = systems;
    }
    public String getValue() {
        return this.value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    public String getComment() {
        return this.comment;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }
    public Date getRegtime() {
        return this.regtime;
    }
    
    public void setRegtime(Date regtime) {
        this.regtime = regtime;
    }
    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }




}


