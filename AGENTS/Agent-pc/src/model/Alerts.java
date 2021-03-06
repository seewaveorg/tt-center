package model;
// Generated Aug 29, 2017 2:09:57 PM by Hibernate Tools 4.3.1



/**
 * Alerts generated by hbm2java
 */
public class Alerts  implements java.io.Serializable {


     private Integer id;
     private TtUpdate ttUpdate;
     private User user;
     private String regtime;
     private String comment;
     private String medium;
     private Integer status;

    public Alerts() {
    }

	
    public Alerts(User user) {
        this.user = user;
    }
    public Alerts(TtUpdate ttUpdate, User user, String regtime, String comment, String medium, Integer status) {
       this.ttUpdate = ttUpdate;
       this.user = user;
       this.regtime = regtime;
       this.comment = comment;
       this.medium = medium;
       this.status = status;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public TtUpdate getTtUpdate() {
        return this.ttUpdate;
    }
    
    public void setTtUpdate(TtUpdate ttUpdate) {
        this.ttUpdate = ttUpdate;
    }
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    public String getRegtime() {
        return this.regtime;
    }
    
    public void setRegtime(String regtime) {
        this.regtime = regtime;
    }
    public String getComment() {
        return this.comment;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }
    public String getMedium() {
        return this.medium;
    }
    
    public void setMedium(String medium) {
        this.medium = medium;
    }
    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }




}


