/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * 
 * @author admin
 * 
 * 
 **/


public class Dispatcher {
   // private int system = 1;
    private String code = "1234567890";
    
    //public void DispatchAlarms(List<Alarm> alarms)throws Exception{
        public void DispatchAlarms(Alarm alarm){            // end text file read and compaire
                    
                 // System.out.println("List received "+alarms.size() );
          
             try{
           // for(Alarm alarm:alarms){
                    System.out.println(alarm.getAlarm());
                    System.out.println("Encoded value "+URLEncoder.encode(alarm.getStatus()+"",  "UTF-8"));
                    System.out.println("Alarm status is "+alarm.getStatus());
                String restAlarm= URLEncoder.encode(alarm.getAlarm(),  "UTF-8");
                String restAlarmDesc= URLEncoder.encode(alarm.getAlarmDesc(),  "UTF-8");
                String restAlarmId = URLEncoder.encode(alarm.getAlarmId()+"",  "UTF-8");
                String restStatus = URLEncoder.encode(alarm.getStatus()+"",  "UTF-8");
                String restType = URLEncoder.encode(alarm.getType()+"",  "UTF-8");
                String restSeviartiy = URLEncoder.encode(alarm.getSeviarity()+"",  "UTF-8");
                String restSystem = URLEncoder.encode(alarm.getSystemId()+"",  "UTF-8");
               
                
                String url = "http://localhost:8080/TR_TTS/alarm/"+restSystem+"/" +restAlarm+"/" +restAlarmId + "/2017/"+restType+"/"+restSeviartiy+"/"+restAlarmDesc+"/"+restStatus+"/.do";
              
                //String url = "http://220.247.201.250:3388/TR_TTS/alarm/"+restSystem+"/" +restAlarm+"/" +restAlarmId + "/2017/"+restType+"/"+restSeviartiy+"/"+restAlarmDesc+"/"+restStatus+"/.do";
               // String url = "http://192.168.12.105:8080/TR_TTS/alarm/"+restSystem+"/" +restAlarm+"/" +restAlarmId + "/2017/"+restType+"/"+restSeviartiy+"/"+restAlarmDesc+"/"+restStatus+"/.do";
                // String url = "http://192.168.1.7:8080/NextGenMed-phase2/alarm/"+restSystem+"/" + restAlarm + "/2017/2/"+restSeviartiy+"/details/"+restStatus+"/.do";
                
               fileManager.writeToFile("API Calling - "+url);
                // String restUrl2 = URLDecoder.decode(status, "UTF-8");
                // System.out.println("testtttt : "+restUrl2);
                // System.out.println("url "+url);
                URL obj = new URL(url);

                System.out.println("URL : " + obj);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                con.setRequestMethod("GET");
                 con.setConnectTimeout(2000); //set timeout to 5 seconds
                 con.setReadTimeout(2500);
                 
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                System.out.println(response.toString());
              
             }catch(Exception e){
                 e.printStackTrace();
             }
          
    }
    
    
          public void DispatchTextAlarms(Alarm alarm){            // end text file read and compaire
                    
                 // System.out.println("List received "+alarms.size() );
          
                 try{
             
           // for(Alarm alarm:alarms){
                    System.out.println(alarm.getAlarm());
                    System.out.println("Encoded value "+URLEncoder.encode(alarm.getStatus()+"",  "UTF-8"));
                    System.out.println("Alarm status is "+alarm.getStatus());
                String restAlarm= URLEncoder.encode(alarm.getAlarm(),  "UTF-8");
                String restAlarmDesc= URLEncoder.encode(alarm.getAlarmDesc(),  "UTF-8");
                String restAlarmId = URLEncoder.encode(alarm.getAlarmId()+"",  "UTF-8");
                String restStatus = URLEncoder.encode(alarm.getStatus()+"",  "UTF-8");
                String restType = URLEncoder.encode(alarm.getType()+"",  "UTF-8");
                String restSeviartiy = URLEncoder.encode(alarm.getSeviarity()+"",  "UTF-8");
                String restSystem = URLEncoder.encode(alarm.getSystemId()+"",  "UTF-8");
                
                String url = "http://localhost:8080/TR_TTS/alarmtext/"+restSystem+"/" +restAlarm+ "/2017/"+restType+"/"+restSeviartiy+"/"+restAlarmDesc+"/"+restStatus+"/.do";
                
                //String url = "http://220.247.201.250:3388/TR_TTS/alarmtext/"+restSystem+"/" +restAlarm+ "/2017/"+restType+"/"+restSeviartiy+"/"+restAlarmDesc+"/"+restStatus+"/.do";
                // String url = "http://192.168.12.105:8080/TR_TTS/alarmtext/"+restSystem+"/" +restAlarm+ "/2017/"+restType+"/"+restSeviartiy+"/"+restAlarmDesc+"/"+restStatus+"/.do";
                // String url = "http://192.168.1.7:8080/NextGenMed-phase2/alarm/"+restSystem+"/" + restAlarm + "/2017/2/"+restSeviartiy+"/details/"+restStatus+"/.do";
                
               fileManager.writeToFile("API Calling - "+url);
                // String restUrl2 = URLDecoder.decode(status, "UTF-8");
                // System.out.println("testtttt : "+restUrl2);
                // System.out.println("url "+url);
                URL obj = new URL(url);

                System.out.println("URL : " + obj);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                con.setRequestMethod("GET");
                // con.setConnectTimeout(2000); //set timeout to 5 seconds
                //  con.setReadTimeout(2500);
                  
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                System.out.println(response.toString());
              
                 }catch(Exception e){
                     try {
                         e.printStackTrace();
                         fileManager.writeToFile("API calling Exception alarm "+alarm.getAlarm()+" to system "+alarm.getSystemId());
                     } catch (IOException ex) {
                         Logger.getLogger(Dispatcher.class.getName()).log(Level.SEVERE, null, ex);
                     }
               
                 }
          
    }
    
    
    public void DispatchHB(int sytemID) throws Exception{
                    // end text file read and compaire
         
           try{
                String restSystem = URLEncoder.encode(sytemID+"",  "UTF-8");
                String restCode= URLEncoder.encode(code,  "UTF-8");
                
                 String url = "http://localhost:8080/TR_TTS/heartbeat/"+restSystem+"/"+restCode+"/.do";
               //String url = "http://220.247.201.250:3388/TR_TTS/heartbeat/"+restSystem+"/"+restCode+"/.do";
                //String url = "http://192.168.12.105:8080/TR_TTS/heartbeat/"+restSystem+"/"+restCode+"/.do";
                // String url = "http://192.168.1.7:8080/NextGenMed-phase2/heartbeat/"+restSystem+"/"+restCode+"/.do";
                
                fileManager.writeToFile("API Calling - "+url);
                // String restUrl2 = URLDecoder.decode(status, "UTF-8");
                // System.out.println("testtttt : "+restUrl2);
                // System.out.println("url "+url);
                URL obj = new URL(url);

                System.out.println("URL : " + obj);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                con.setRequestMethod("GET");
                //con.setRequestMethod("HEAD");
                
                con.setConnectTimeout(2000); //set timeout to 5 seconds
                 con.setReadTimeout(2500);
                
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                System.out.println(response.toString());
               
           }catch(Exception e){
               e.printStackTrace();
           }
            
    }
    
}
