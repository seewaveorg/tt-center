/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author root
 */
public class Receiver {
    public static JSONArray objArray = null ;
   // public static void main(String args[]) throws Exception {
      //  int cusid = 1;
    public static void getSystemsForCustomer(int cusid) {
        try{
        //URL oracle = new URL("http://220.247.201.250:3388/TR_TTS/sysdetails/"+cusid+"/.do");
        URL oracle = new URL("http://localhost:8080/TR_TTS/sysdetails/"+cusid+"/.do");
        
        //URL oracle = new URL("http://192.168.1.35:8080/TR_TTS/sysdetails/"+cusid+"/.do");
        
        URLConnection yc = oracle.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                                    yc.getInputStream()));
        String inputLine;
        JSONParser parser = new JSONParser();
        
        while ((inputLine = in.readLine()) != null) {
           // System.out.println(inputLine);
           //String t = "[{\"user_id\": \"someValue\"}]";
            
            objArray = (JSONArray) parser.parse(inputLine);
        }
        in.close();
        
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
    
    
    
}
