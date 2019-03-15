/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge;

/**
 *
 * @author admin
 * 
 * 
 */


import static ge.Receiver.objArray;
import java.io.BufferedReader;
import java.io.FileReader;
import snmp_watchdog.*;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
//import static javax.swing.UIManager.getString;
import org.json.simple.JSONObject;

import org.snmp4j.CommandResponder;
import org.snmp4j.CommandResponderEvent;
import org.snmp4j.MessageDispatcherImpl;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.mp.MPv1;
import org.snmp4j.mp.MPv2c;
import org.snmp4j.mp.MPv3;
import org.snmp4j.security.AuthMD5;
import org.snmp4j.security.PrivDES;
import org.snmp4j.security.SecurityModels;
import org.snmp4j.security.SecurityProtocols;
import org.snmp4j.security.USM;
import org.snmp4j.security.UsmUser;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.TcpAddress;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultTcpTransportMapping;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.MultiThreadedMessageDispatcher;
import org.snmp4j.util.ThreadPool;

/**
 * @author mchopker
 * 
 */
public class NETSNMPTrapReceiver implements CommandResponder {

	private MultiThreadedMessageDispatcher dispatcher;
	private Snmp snmp = null;
	private Address listenAddress;
	private ThreadPool threadPool;
	private int n = 0;
	private long start = -1;
        HashMap<String, String> alarmsMap = new HashMap<String, String>();
        private static String path = "RFC1628-SNMP-ALARMS.txt";
        private static Dispatcher dpcr = new Dispatcher();
	public NETSNMPTrapReceiver() {
	}
        
        public static void StartNetUpsMonitoring(String[] args) {
             System.out.println(".net monitoring starting ");
             try {
                NETSNMPTrapReceiver GEtrap = new NETSNMPTrapReceiver();
                // load the defined alarm list
                GEtrap.loadAlrms(path);
                // load the `system details from the api
               Receiver.getSystemsForCustomer(1);
                  // now iterate the json array
                 
        for(int i=0;i<objArray.size();i++){
            System.out.println(objArray.get(i));
            
                JSONObject json = (JSONObject) objArray.get(i); //jsonStr - мой json в видео строки    
            System.out.println(json.get("ip")+" url "+json.get("url"));
        }
               
           
                
               GEtrap.run();
                
                // start the second thread
                //MyRunnable myRunnable = new MyRunnable(10);
                //Thread t = new Thread(myRunnable);
                //t.start();
                
                // And From your main() method or any other method (Timer has stoped hear)
               // Timer timer = new Timer();
               // timer.schedule(new MyTimerTask(), 0, 30000); // every 60 seconds
                
                
            } catch (Exception ex) {
                Logger.getLogger(GESNMPTrapReceiver.class.getName()).log(Level.SEVERE, null, ex);
            }
                
	}

        /**
	public static void main(String[] args) {
             System.out.println(".net monitoring starting ");
             try {
                NETSNMPTrapReceiver GEtrap = new NETSNMPTrapReceiver();
                // load the defined alarm list
                GEtrap.loadAlrms(path);
                // load the `system details from the api
               Receiver.getSystemsForCustomer(1);
                  // now iterate the json array
                 
        for(int i=0;i<objArray.size();i++){
            System.out.println(objArray.get(i));
            
                JSONObject json = (JSONObject) objArray.get(i); //jsonStr - мой json в видео строки    
            System.out.println(json.get("ip")+" url "+json.get("url"));
        }
               
           
                
               GEtrap.run();
                
                // start the second thread
                //MyRunnable myRunnable = new MyRunnable(10);
                //Thread t = new Thread(myRunnable);
                //t.start();
                
                // And From your main() method or any other method (Timer has stoped hear)
               // Timer timer = new Timer();
               // timer.schedule(new MyTimerTask(), 0, 30000); // every 60 seconds
                
                
            } catch (Exception ex) {
                Logger.getLogger(GESNMPTrapReceiver.class.getName()).log(Level.SEVERE, null, ex);
            }
                
	}

* 
* **/
	private void run() {
		try {
			init();
			snmp.addCommandResponder(this);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void init() throws UnknownHostException, IOException {
		threadPool = ThreadPool.create("Trap", 100);
		dispatcher = new MultiThreadedMessageDispatcher(threadPool,
				new MessageDispatcherImpl());
		listenAddress = GenericAddress.parse(System.getProperty(
				"snmp4j.listenAddress", "udp:0.0.0.0/162"));
		TransportMapping<?> transport;
		if (listenAddress instanceof UdpAddress) {
			transport = new DefaultUdpTransportMapping(
					(UdpAddress) listenAddress);
		} else {
			transport = new DefaultTcpTransportMapping(
					(TcpAddress) listenAddress);
		}
		USM usm = new USM(SecurityProtocols.getInstance(), new OctetString(
				MPv3.createLocalEngineID()), 0);
		usm.setEngineDiscoveryEnabled(true);

		snmp = new Snmp(dispatcher, transport);
		snmp.getMessageDispatcher().addMessageProcessingModel(new MPv1());
		snmp.getMessageDispatcher().addMessageProcessingModel(new MPv2c());
		snmp.getMessageDispatcher().addMessageProcessingModel(new MPv3(usm));
		SecurityModels.getInstance().addSecurityModel(usm);
		snmp.getUSM().addUser(
				new OctetString("MD5DES"),
				new UsmUser(new OctetString("MD5DES"), AuthMD5.ID,
						new OctetString("UserName"), PrivDES.ID,
						new OctetString("PasswordUser")));
		snmp.getUSM().addUser(new OctetString("MD5DES"),
				new UsmUser(new OctetString("MD5DES"), null, null, null, null));

		snmp.listen();
	}
        
        

	public void processPdu(CommandResponderEvent event) {
            try{
           // List<Alarm> alarmlist = new ArrayList();
            Alarm alarm = new Alarm();
		if (start < 0) {
			start = System.currentTimeMillis() - 1;
		}
		n++;
		if ((n % 100 == 1)) {
			System.out.println("Processed "
					+ (n / (double) (System.currentTimeMillis() - start))
					* 1000 + "/s, total=" + n);
		}

                    System.out.println("system event is "+event.toString());
                    System.out.println(" event ip is "+event.getPeerAddress().toString());
                    String ip = event.getPeerAddress().toString().substring(0, event.getPeerAddress().toString().indexOf('/'));
                    System.out.println(" ip is : "+ip);
                    
                    int systemID = 1;
                    // get the system id from ip
                      for(int i=0;i<objArray.size();i++){
                           System.out.println(objArray.get(i));
                         
                           JSONObject json = (JSONObject) objArray.get(i);
                             String jsonip = (String) json.get("ip");
                             System.out.println("jsonips is -"+jsonip);
                             System.out.println("ip is -"+ip);
                             System.out.println("-"+json.get("id")+"-");
                               if(jsonip.equals(ip.trim())){
                                   System.out.println("json is "+json.get("id"));
                                   
                                   long longsysiD =(long) json.get("id");
                                   systemID = (int) (long) longsysiD;
                                   //systemID = i;
                                   //systemID = Integer.parseInt((String) json.get("id"));
                                   //systemID =1;
                                   System.out.println("systemID "+systemID);
                                   break;
                               }
                      }
                    
                    fileManager.writeToFile("alarm received from "+event.getPeerAddress().toString());
		StringBuffer msg = new StringBuffer();
		msg.append(event.toString());
		Vector<? extends VariableBinding> varBinds = event.getPDU()
				.getVariableBindings();
		if (varBinds != null && !varBinds.isEmpty()) {
			Iterator<? extends VariableBinding> varIter = varBinds.iterator();
			while (varIter.hasNext()) {
				VariableBinding var = varIter.next();
				msg.append(var.toString()).append(";");
                                System.out.println(" iterating var "+var.toString());
                               
                                 String[] oidDetail = var.toString().trim().split("=", -1); 
                                 System.out.println(" gong to switch 0 "+oidDetail[0]+" 1 "+oidDetail[1]);
                                 String oidget ="";
                                 
                                 if(oidDetail[0].trim().length()>=24){
                                 oidget = oidDetail[0].trim().substring (0,24);
                                 System.out.println("oidget taken "+oidget);
                                 //systemID = 3; // hard coded due to .net issue
                                 }else{
                                     oidget = oidDetail[0].trim();
                                 }
                               // switch(oidDetail[0].trim()){
                                 switch(oidget){     
                                        case "1.3.6.1.6.3.1.1.4.1.0" :
                                            System.out.println("alamr status is checking ");
                                            if(oidDetail[1].trim().equals("1.3.6.1.2.1.33.2.3") || oidDetail[1].trim() =="1.3.6.1.2.1.33.2.3"){ // this is a alarm insertion to the sytem
                                                alarm.setStatus(1);
                                                System.out.println("status is one");
                                            }else if(oidDetail[1].trim().equals("1.3.6.1.2.1.33.2.4")|| oidDetail[1].trim() =="1.3.6.1.2.1.33.2.4"){ // this is a alarm clearing
                                                alarm.setStatus(0);
                                                System.out.println("status is two");
                                            }else{
                                                alarm.setStatus(1);
                                            }
                                             break;
                                
                                        case "1.3.6.1.2.1.33.1.6.2.1.1" :
                                            break;
                                        case "1.3.6.1.2.1.33.1.6.2.1.2" :
                                           
                                        System.out.println("Found "+oidDetail[0].trim()+" - "+oidDetail[1].trim());
                                       
                                        // iterating the map

                                            Set<String> keySet = alarmsMap.keySet();
                                             Iterator<String> keySetIterator = keySet.iterator();
                                             while (keySetIterator.hasNext()) {
                                                  
                                                String key = keySetIterator.next();
                                                 System.out.println(" Going to match "+oidget+" with "+key);
                                                 
                                                 // for .net in oidDetail[1] is having a . we have to remove it
                                                 String alarmOid = oidDetail[1].trim();
                                                 if(alarmOid.charAt(0)=='.'){
                                                     alarmOid =alarmOid.substring(alarmOid.indexOf(".") + 1);
                                                }
                                                 System.out.println("alarm detail is "+alarmOid);
                                                 
                                                 if(key.equals(alarmOid)){ // changes for .net requiremnts
                                                 // if(key.equals(oidDetail[1].trim())){ // this is the map alarm string
                                                    String[]  alarmdesc = alarmsMap.get(key).trim().split("\\^", -1); 
                                                     System.out.println("alarmdesc "+ Arrays.toString(alarmdesc));
                                                     
                                                      // assign values to alarm
                                                      alarm.setAlarm(alarmdesc[0]);
                                                      alarm.setSeviarity(Integer.parseInt(alarmdesc[1]));
                                                      alarm.setAlarmId(Integer.parseInt(alarmdesc[2]));
                                                      alarm.setAlarmDesc(oidDetail[1].trim());
                                                      alarm.setType(4); // alarm type is 4
                                                      
                                                     
                                                      // setting the system id to alarm
                                                      alarm.setSystemId(systemID);
                                                      
                                                      
                                                    
                                                     String alarmSeviarity = "unknown";
                                                     switch(alarmdesc[1]){
                                                         case "1":
                                                         alarmSeviarity  = "CRITICAL";
                                                         break;
                                                         case "2":
                                                         alarmSeviarity ="MAJOR";
                                                         break;
                                                         case "3":
                                                          alarmSeviarity = "MINOR";
                                                          break;
                                                         default : alarmSeviarity = "INFORMATION";
                                                     }
                                                 
                                                
                                                    System.out.println("key: " + key + " value: " + alarmsMap.get(key));
                                                    
                                                    }
                                             }
                                             
                                            // put the alarm into the aalarm list
                                            // alarmlist.add(alarm);
                                            break;
                                        default : System.out.println("Not found "+oidDetail[0].trim()+" - "+oidDetail[1].trim());
                                
			}
		}
                     //   if(alarmlist.size()>0){
                            
                           // dpcr.DispatchAlarms(alarmlist);
                     //   }
                     
                System.out.println("Dispatching alarm details ############################");
                System.out.println(alarm.getAlarm());
                System.out.println("alarm id is "+alarm.getAlarmId());
                System.out.println("system id is "+alarm.getSystemId());
                
		dpcr.DispatchAlarms(alarm);
                fileManager.writeToFile("Message Received: " + msg.toString()+"alarm "+alarm.getAlarm()+" to system "+alarm.getSystemId());
                System.out.println("Message Received: " + msg.toString()+"alarm "+alarm.getAlarm());
	}
            }catch(Exception e){
                e.printStackTrace();
            }
       
        }
        
        
        
        public void loadAlrms(String url){
        //alarmsArrya
          try (BufferedReader br = new BufferedReader(new FileReader(url))) {

                                String sCurrentLine;

                                while ((sCurrentLine = br.readLine()) != null) {
                                       // System.out.println(sCurrentLine);
                                        String[] columnDetail = sCurrentLine.split("\t", -1);
                                       alarmsMap.put(columnDetail[0], columnDetail[2].trim()+"^"+columnDetail[3].trim()+"^"+columnDetail[4].trim());
                                }

                        } catch (IOException e) {
                                e.printStackTrace();
                        }
          
          
          // iterating the map
          
           Set<String> keySet = alarmsMap.keySet();
            Iterator<String> keySetIterator = keySet.iterator();
            while (keySetIterator.hasNext()) {
             
               String key = keySetIterator.next();
               System.out.println("key: " + key + " value: " + alarmsMap.get(key));
            }

           
                    
        }
        
        
}
