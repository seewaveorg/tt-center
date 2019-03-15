/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class TestConnectivity {
    
    public static void main(String args[]){
        boolean test = testConnectivity("192.168.30.40",3000);
        System.out.println(test);
        test = testConnectivity("192.168.30.35",3000);
        System.out.println(test);
    }
    
    
    public static boolean testConnectivity(String ipORurl, int timeOut ){
 boolean status = false;
      System.out.println("url :"+ipORurl+" timeout "+timeOut);
     try {
         status = InetAddress.getByName(ipORurl).isReachable(timeOut);
     } catch (Exception ex) {
         Logger.getLogger(TestConnectivity.class.getName()).log(Level.SEVERE, null, ex);
         System.out.println("Error : "+ex.getLocalizedMessage());
     }
   
               
                    return status;
        }
    
    
    
    

public static boolean WebSiteExists(String URLName) {

    System.out.println("URLName : "+URLName);
        try {
            HttpURLConnection.setFollowRedirects(false);
            // note : you may also need
            // HttpURLConnection.setInstanceFollowRedirects(false)
            HttpURLConnection con = (HttpURLConnection) new URL(URLName)
            .openConnection();
            con.setRequestMethod("HEAD");
            con.setConnectTimeout(2000); //set timeout to 5 seconds
           // return(con.getResponseCode() == HttpURLConnection.HTTP_OK);
           /**
            *

A 302 redirect means that the page was temporarily moved, while a 301 means that it was permanently moved.

301s are good for SEO value, while 302s aren't because 301s instruct clients to forget the value of the original URL, while the 302 keeps the value of the original and can thus potentially reduce the value by creating two, logically-distinct URLs that each produce the same content (search engines view them as distinct duplicates rather than a single resource with two names).

            */
           System.out.println(con.getResponseCode());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    
}
