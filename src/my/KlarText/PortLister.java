/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package my.KlarText;

import purejavacomm.*;
import java.util.ArrayList;
import java.util.Enumeration;
import static my.KlarText.KlarTextUI.debugLevel;

/**
 *
 * @author Lothar
 */

public class PortLister {

    public static String[] listSerialPorts(KlarTextUI KTUI) {
        Enumeration<CommPortIdentifier> ports;
        try {
            ports = CommPortIdentifier.getPortIdentifiers();
        } catch (UnsatisfiedLinkError e) {
            System.out.println("listSerialPorts: UnsatisfiedLinkError: "+ e);
            KTUI.mbGeneric( KTUI, "PROBLEM: Library may be missing or does not fit.", "rxtxSerial.dll", e.getMessage() );
            return null;
        } catch (Exception | Error e) {
            System.out.println("listSerialPorts Exception/Error: "+ e);
            KTUI.mbGeneric( KTUI, "PROBLEM", "reading serial/USB ports failed", e.getMessage() );
            return null;
        }
        ArrayList portList = new ArrayList();
        String portArray[] = null;
        while (ports.hasMoreElements()) {
            CommPortIdentifier port = (CommPortIdentifier) ports.nextElement();
            if( debugLevel >= 1 ) {
                System.out.println("found port type["+port.getPortType()+"] for name["+port.getName()+"]" );
            }
            if (port.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                portList.add(port.getName());
            }
        }
        portArray = (String[]) portList.toArray(new String[0]);
        return portArray;
    }
    
}
