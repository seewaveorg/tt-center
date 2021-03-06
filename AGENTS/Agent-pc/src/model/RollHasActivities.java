package model;
// Generated Aug 29, 2017 2:09:57 PM by Hibernate Tools 4.3.1



/**
 * RollHasActivities generated by hbm2java
 */
public class RollHasActivities  implements java.io.Serializable {


     private Integer id;
     private Activities activities;
     private Rolls rolls;
     private Integer status;

    public RollHasActivities() {
    }

	
    public RollHasActivities(Activities activities, Rolls rolls) {
        this.activities = activities;
        this.rolls = rolls;
    }
    public RollHasActivities(Activities activities, Rolls rolls, Integer status) {
       this.activities = activities;
       this.rolls = rolls;
       this.status = status;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Activities getActivities() {
        return this.activities;
    }
    
    public void setActivities(Activities activities) {
        this.activities = activities;
    }
    public Rolls getRolls() {
        return this.rolls;
    }
    
    public void setRolls(Rolls rolls) {
        this.rolls = rolls;
    }
    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }




}


