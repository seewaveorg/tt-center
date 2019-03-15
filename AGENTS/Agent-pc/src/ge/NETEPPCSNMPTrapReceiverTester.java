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
public class NETEPPCSNMPTrapReceiverTester  {

	private MultiThreadedMessageDispatcher dispatcher;
	private Snmp snmp = null;
	private Address listenAddress;
	private ThreadPool threadPool;
	private int n = 0;
	private long start = -1;
        HashMap<String, String> EPPCAlarmMap = new HashMap<String, String>();
        
          
        private static Dispatcher dpcr = new Dispatcher();
	public NETEPPCSNMPTrapReceiverTester() {
	}


        
	public static void main(String[] args) {
             System.out.println(".net monitoring starting ");
             try {
                NETEPPCSNMPTrapReceiverTester ECCPtrap = new NETEPPCSNMPTrapReceiverTester();
                // load the defined alarm list
                            // load the defined alarm list
                 ECCPtrap.loadAlrms("ECCP-ALARMS.txt");
                 
                 ECCPtrap.SelfTest();
                
                
            } catch (Exception ex) {
                Logger.getLogger(NETEPPCSNMPTrapReceiverTester.class.getName()).log(Level.SEVERE, null, ex);
            }
             
                
	}

        
        public void SelfTest(){
            
            try {
                fileManager.writeToFile("ECCP Self Test started ###################" );
                Alarm alarm = new Alarm();
                alarm.setSystemId(3);
                
                
                     // iterating the map
          
           Set<String> keySet = EPPCAlarmMap.keySet();
            Iterator<String> keySetIterator = keySet.iterator();
            while (keySetIterator.hasNext()) {

               String key = keySetIterator.next();
               System.out.println("key: " + key + " value: " + EPPCAlarmMap.get(key));
                                                      alarm.setAlarm(EPPCAlarmMap.get(key));
                                                      alarm.setSeviarity(2);
                                                      alarm.setAlarmId(2);
                                                      alarm.setAlarmDesc(key);
                                                      alarm.setType(4); // alarm type is 4
                                                      
                dpcr.DispatchTextAlarms(alarm);
                //fileManager.writeToFile("Message Received: " + msg.toString()+"alarm "+alarm.getAlarm()+" to system "+alarm.getSystemId());
               
            }
            
            
            
                
            } catch (Exception ex) {
                ex.printStackTrace();
            }
             
             
             
             
        }

        
        
        
        public void loadAlrms(String url){
        //alarmsArrya
          try (BufferedReader br = new BufferedReader(new FileReader(url))) {

                                String sCurrentLine;
                                int i = 0;

                                while ((sCurrentLine = br.readLine()) != null) {
                                    i = i+1;
                                       // System.out.println(sCurrentLine);
                                 // String[] columnDetail = sCurrentLine.split("\t", -1);
                                  //   EPPCAlarmMap.put(i+"",sCurrentLine.trim());
                             
                                    // EPPCAlarmMap.put("1","INFORMATION: The UPS is not on Bypass.");
                                      //EPPCAlarmMap.put("2","INFORMATION: The testing of UPS is completed.");
                                     // EPPCAlarmMap.put("1","WARNING: UPS module or charger module plug-in.");
                                     //  EPPCAlarmMap.put("3","WARNING: UPS module or charger module unplugs.");
                                      // EPPCAlarmMap.put("4","INFORMATION: UPS Output On.");
                                        EPPCAlarmMap.put("5","INFORMATION: The UPS is not on Bypass.");
                                }

                        } catch (IOException e) {
                                e.printStackTrace();
                        }
          
          
          // iterating the map
          
           Set<String> keySet = EPPCAlarmMap.keySet();
            Iterator<String> keySetIterator = keySet.iterator();
            while (keySetIterator.hasNext()) {
             
               String key = keySetIterator.next();
               System.out.println("key: " + key + " value: " + EPPCAlarmMap.get(key));
            }

           
                    
        }
        
String alrms =""+       
"WARNING: Utility power not available."+
"INFORMATION: Utility power has restored."+
"SEVERE: The UPS batteries are low and will soon be exhausted."+
"INFORMATION: The UPS has return from a low battery condition."+
"SEVERE: The UPS is not working fine."+
"INFORMATION: The UPS is working fine."+
"WARNING: The UPS has switched to battery backup power."+
"INFORMATION: The UPS is not on battery power."+
"INFORMATION: The testing is going on ups."+
"INFORMATION: The testing of UPS is completed."+
"INFORMATION: The UPS has enabled bypass."+
"INFORMATION: The UPS is not on Bypass."+
"SEVERE: Communication to the UPS has been lost."+
"INFORMATION: Communication with the UPS has been established."+
"WARNING: The UPS is going to shutdown output."+
"INFORMATION: The UPS is not going to shutdown output."+
"WARNING: The UPS is going to shutdown outlet1."+
"INFORMATION: The UPS is not going to shutdown outlet1."+
"WARNING: The UPS is going to shutdown outlet2."+
"INFORMATION: The UPS is not going to shutdown outlet2."+
"INFORMATION: UPS has entered sleep mode;power to load has been cut off."+
"INFORMATION: The UPS woke up from sleep mode;power to load has been restored."+
"SEVERE: The UPS temperature is too high."+
"INFORMATION: The UPS internal temperature return to normal."+
"SEVERE: The UPS is overload."+
"INFORMATION: The UPS load return from overload."+
"WARNING: UPS module or charger module plug-in."+
"WARNING: UPS module or charger module unplugs."+
"WARNING: Sensor Temperature over high Set point."+
"INFORMATION: Sensor Temperature not over high Set point."+
"WARNING: Sensor Temperature under low Set point."+
"INFORMATION: Sensor Temperature not under low Set point."+
"WARNING: Sensor Humidity over high Set point."+
"INFORMATION: Sensor Humidity not over high Set point."+
"WARNING: Sensor Humidity under low Set point."+
"INFORMATION: Sensor Humidity not under low Set point."+
"WARNING: Contact Alarm-1 activated."+
"INFORMATION: Contact Alarm-1 not active."+
"WARNING: Contact Alarm-2 activated."+
"INFORMATION: Contact Alarm-2 not active."+
"WARNING: Internal warning."+
"INFORMATION: Return from Internal warning."+
"WARNING: EPO Active."+
"INFORMATION: Return from EPO Active."+
"WARNING: Module Unlock."+
"INFORMATION: Return from Module Unlock."+
"WARNING: Main 1 Neutral loss."+
"INFORMATION: Return from Main 1 Neutral loss."+
"WARNING: Main 1 phase error."+
"INFORMATION: Return from Main 1 phase error."+
"WARNING: Site fault."+
"INFORMATION: Return from Site fault."+
"WARNING: Bypass Abnormal."+
"INFORMATION: Return from Bypass Abnormal."+
"WARNING: Bypass Phase Error."+
"INFORMATION: Return from Bypass Phase Error."+
"WARNING: Battery Open."+
"INFORMATION: Return from Battery Open."+
"WARNING: Battery Over Charge."+
"INFORMATION: Return from Battery Over Charge."+
"WARNING: Battery Reverse."+
"INFORMATION: Return from Battery Reverse."+
"WARNING: Overload forewarning."+
"INFORMATION: Return from Overload forewarning."+
"WARNING: Overload Warning."+
"INFORMATION: Return from Overload Warning."+
"WARNING: Fan Lock."+
"INFORMATION: Return from Fan Lock."+
"WARNING: Maintain cover is open."+
"INFORMATION: Return from Maintain cover is open."+
"WARNING: Charger fault."+
"INFORMATION: Return from Charger fault."+
"WARNING: Module location error."+
"INFORMATION: Return from Module location error."+
"WARNING: Turn on abnormal."+
"INFORMATION: Return from Turn on abnormal."+
"WARNING: Redundancy loss."+
"INFORMATION: Return from Redundancy loss."+
"WARNING: Hot Swap Active."+
"INFORMATION: Return from Hot Swap Active."+
"WARNING: Battery Inform."+
"INFORMATION: Return from Battery Inform."+
"WARNING: Inspection Inform."+
"INFORMATION: Return from Inspection Inform."+
"WARNING: Guarantee Inform."+
"INFORMATION: Return from Guarantee Inform."+
"WARNING: Temperature Low."+
"INFORMATION: Return from Temperature Low."+
"WARNING: Temperature High."+
"INFORMATION: Return from Temperature High."+
"WARNING: Battery Over Temperature."+
"INFORMATION: Return from Battery Over Temperature."+
"WARNING: Fan Maintain Inform."+
"INFORMATION: Return from Fan Maintain Inform."+
"WARNING: Bus Capacitance Maintain Inform."+
"INFORMATION: Return from Bus Capacitance Maintain Inform."+
"WARNING: System Over Capacity."+
"INFORMATION: Return from System Over Capacity."+
"WARNING: UPS Battery Bad."+
"INFORMATION: UPS not Battery Bad."+
"WARNING: Input Bad."+
"INFORMATION: Input Not Bad."+
"WARNING: Output Bad."+
"INFORMATION: Output not Bad."+
"WARNING: Output Off As Requested."+
"INFORMATION: Not Output Off As Requested."+
"WARNING: UPS Off As Requested."+
"INFORMATION: Not UPS Off As Requested."+
"WARNING: UPS Output Off."+
"INFORMATION: UPS Output On."+
"WARNING: UPS System Off."+
"INFORMATION: UPS System On."+
"WARNING: Fuse Failure."+
"INFORMATION: Fuse not Failure."+
"WARNING: Diagnostic Test Failed."+
"INFORMATION: Not Diagnostic Test Failed."+
"WARNING: Awaiting Power."+
"INFORMATION: Not Awaiting Power."+
"WARNING: Shutdown Pending."+
"INFORMATION: Not Shutdown Pending."+
"WARNING: Shutdown Imminent."+
"INFORMATION: Not Shutdown Imminent.";
        
        
}
