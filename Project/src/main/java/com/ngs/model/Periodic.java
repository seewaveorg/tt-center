package com.ngs.model;
// Generated 13-Oct-2017 13:23:18 by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Periodic generated by hbm2java
 */
public class Periodic  implements java.io.Serializable {


     private Integer id;
     private SystemsHasParameters systemsHasParameters;
     private String value;
     private Date regtime;
     private Integer status;

    public Periodic() {
    }

	
    public Periodic(SystemsHasParameters systemsHasParameters) {
        this.systemsHasParameters = systemsHasParameters;
    }
    public Periodic(SystemsHasParameters systemsHasParameters, String value, Date regtime, Integer status) {
       this.systemsHasParameters = systemsHasParameters;
       this.value = value;
       this.regtime = regtime;
       this.status = status;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public SystemsHasParameters getSystemsHasParameters() {
        return this.systemsHasParameters;
    }
    
    public void setSystemsHasParameters(SystemsHasParameters systemsHasParameters) {
        this.systemsHasParameters = systemsHasParameters;
    }
    public String getValue() {
        return this.value;
    }
    
    public void setValue(String value) {
        this.value = value;
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

