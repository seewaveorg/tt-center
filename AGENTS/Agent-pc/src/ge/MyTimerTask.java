/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge;

import static ge.Receiver.objArray;
import java.util.Date;
import java.util.TimerTask;
import org.json.simple.JSONObject;

/**
 *
 * @author root
 */
public class MyTimerTask extends TimerTask{
    Dispatcher dispatcher = new Dispatcher();
     Alarm alarm = new Alarm();
     public void run() {
       System.out.println("Heart Beatk packet sending to the server ---- > "); 
        try{
            // iterate the system list and then send the hb with system id
         for(int i=0;i<objArray.size();i++){
            System.out.println(objArray.get(i));
            
           JSONObject json = (JSONObject) objArray.get(i); //jsonStr - мой json в видео строки    
            System.out.println(json.get("ip"));
            System.out.println("-"+json.get("id")+"-");
             Date date = new Date();
             
           // if(TestConnectivity.testConnectivity((String) json.get("ip"), 3000)){
              //  if(TestConnectivity.testConnectivity((String) json.get("url"), 3000)){
           // url was hardcoded untill udeepa implement it
           // if(TestConnectivity.WebSiteExists("http://cybersec.dyndns.org:8812/")){
            if(TestConnectivity.WebSiteExists((String) json.get("url"))){
         
                System.out.println("Connectivity is ok ");
             // clearing the alarm
               alarm.setAlarm("Connectivity Lost");
               alarm.setAlarmId(0);
               alarm.setAlarmDesc("IP "+(String) json.get("ip")+" not responding !");
               alarm.setAlarmOriginatedTime(date);
               alarm.setSeviarity(0);
               alarm.setStatus(0);
               alarm.setSystemId(((Number) json.get("id")).intValue());
               alarm.setType(1);
            
               dispatcher.DispatchHB(((Number)json.get("id")).intValue());
               
               // if connectivity ok 
               
            }else{
               
                System.out.println("there is no connectivity to ups web");
                
               alarm.setAlarm("Connectivity Lost");
               alarm.setAlarmId(0);
               alarm.setAlarmDesc("IP "+(String) json.get("ip")+" not responding !");
               alarm.setAlarmOriginatedTime(date);
               alarm.setSeviarity(1);
               alarm.setStatus(1);
               alarm.setSystemId(((Number) json.get("id")).intValue());
               alarm.setType(1);
              //dispatcher.DispatchAlarms(alarm);
            }
               dispatcher.DispatchAlarms(alarm);
        }
        
        }catch(Exception e){
            System.out.println("Heart Beat Sending Error "+e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
}


/**
 * 
 * 
 https://stackoverflow.com/questions/12908412/print-hello-world-every-x-seconds

You can also take a look at Timer and TimerTask classes which you can use to schedule your task to run every n seconds.

You need a class that extends TimerTask and override the public void run() method, which will be executed everytime you pass an instance of that class to timer.schedule() method..

Here's an example, which prints Hello World every 5 seconds: -

class SayHello extends TimerTask {
    public void run() {
       System.out.println("Hello World!"); 
    }
}

// And From your main() method or any other method
Timer timer = new Timer();
timer.schedule(new SayHello(), 0, 5000);


 * 
 */