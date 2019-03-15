/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge;

import java.util.Date;

/**
 *
 * @author root
 */
public class Alarm {
    
    private Date alarmOriginatedTime;
    private Date alarmTerminatedTime;
    private String alarm;
    private String alarmDesc;
    private int alarmId;
    private int type;
    private int seviarity;
    private int systemId;
    private Date receivedTime;
    private int status;

    /**
     * @return the alarmOriginatedTime
     */
    public Date getAlarmOriginatedTime() {
        return alarmOriginatedTime;
    }

    /**
     * @param alarmOriginatedTime the alarmOriginatedTime to set
     */
    public void setAlarmOriginatedTime(Date alarmOriginatedTime) {
        this.alarmOriginatedTime = alarmOriginatedTime;
    }

    /**
     * @return the alarmTerminatedTime
     */
    public Date getAlarmTerminatedTime() {
        return alarmTerminatedTime;
    }

    /**
     * @param alarmTerminatedTime the alarmTerminatedTime to set
     */
    public void setAlarmTerminatedTime(Date alarmTerminatedTime) {
        this.alarmTerminatedTime = alarmTerminatedTime;
    }

    /**
     * @return the alarm
     */
    public String getAlarm() {
        return alarm;
    }

    /**
     * @param alarm the alarm to set
     */
    public void setAlarm(String alarm) {
        this.alarm = alarm;
    }

    /**
     * @return the alarmDesc
     */
    public String getAlarmDesc() {
        return alarmDesc;
    }

    /**
     * @param alarmDesc the alarmDesc to set
     */
    public void setAlarmDesc(String alarmDesc) {
        this.alarmDesc = alarmDesc;
    }

    /**
     * @return the alarmId
     */
    public int getAlarmId() {
        return alarmId;
    }

    /**
     * @param alarmId the alarmId to set
     */
    public void setAlarmId(int alarmId) {
        this.alarmId = alarmId;
    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * @return the seviarity
     */
    public int getSeviarity() {
        return seviarity;
    }

    /**
     * @param seviarity the seviarity to set
     */
    public void setSeviarity(int seviarity) {
        this.seviarity = seviarity;
    }

    /**
     * @return the systemId
     */
    public int getSystemId() {
        return systemId;
    }

    /**
     * @param systemId the systemId to set
     */
    public void setSystemId(int systemId) {
        this.systemId = systemId;
    }

    /**
     * @return the receivedTime
     */
    public Date getReceivedTime() {
        return receivedTime;
    }

    /**
     * @param receivedTime the receivedTime to set
     */
    public void setReceivedTime(Date receivedTime) {
        this.receivedTime = receivedTime;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    
    
    
    
    
}
