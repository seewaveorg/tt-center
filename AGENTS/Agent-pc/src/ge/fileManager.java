/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author root
 */
public class fileManager {
    
MyDateUtil date = new MyDateUtil();
     
     public static void writeToFile(String content) throws IOException{
         
         Date now = new Date();
	DateFormat Dayformater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat Dayformater2 = new SimpleDateFormat("yyyy-MM-dd");
		
        //String text = "Hello world";
        BufferedWriter output = null;
        try {
            File file = new File(Dayformater2.format(now)+"-log.txt");
            if(!file.exists()){
             file.createNewFile(); // if file already exists will do nothing 
            }
          
            FileWriter fw = new FileWriter(file,true); //the true will append the new data
            fw.write("\n"+content+"\t"+Dayformater.format(now));//appends the string to the file
            fw.close();
       
        } catch ( IOException e ) {
            e.printStackTrace();
        } finally {
          if ( output != null ) {
            output.close();
          }
        }
    }
    
}
