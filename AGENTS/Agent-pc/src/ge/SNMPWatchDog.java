/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ge;

/**
 *
 * @author root
 */
public class SNMPWatchDog {
    
    public static void main(String args[]){
        NETEPPCSNMPTrapReceiver net = new NETEPPCSNMPTrapReceiver();
        net.startNETEPPCSNMPMonitoring();
        
        GESNMPTrapReceiver ge = new GESNMPTrapReceiver();
        ge.startGESNMPTrapMonitorign();
        
        
        
    }
    
}
