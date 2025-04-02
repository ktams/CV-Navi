/*
 * PortLister.java
 *
 *
 * @author Kersten Tams Copyright 2009-2020
 * @author Lothar Roth  Copyright 2012-2020
 *
 */

package my.CVNavi;

import purejavacomm.*;
import java.util.ArrayList;
import java.util.Enumeration;
import static my.CVNavi.CVNavi.debugLevel;

/**
 *
 * @author Lothar
 */

public class PortLister {

    public static String[] listSerialPorts(CVNavi CVNavi) {
        Enumeration<CommPortIdentifier> ports;
        try {
            ports = CommPortIdentifier.getPortIdentifiers();
        } catch (UnsatisfiedLinkError e) {
            System.out.println("listSerialPorts: UnsatisfiedLinkError: "+ e);
            CVNavi.mbGeneric( CVNavi, "PROBLEM: Library may be missing or does not fit.", "serial port loader", e.getMessage() );
            return null;
        } catch (Exception | Error e) {
            System.out.println("listSerialPorts Exception/Error: "+ e);
            CVNavi.mbGeneric( CVNavi, "PROBLEM", "reading serial/USB ports failed", e.getMessage() );
            return null;
        }
        ArrayList portList = new ArrayList();
        String portArray[] = null;
        while (ports.hasMoreElements()) {
            CommPortIdentifier port = (CommPortIdentifier) ports.nextElement();
            if( debugLevel > 0 ) {
                System.out.println(" PortLister");
                System.out.println(" found port type["+port.getPortType()+"] name["+port.getName()+"]" );
                if( debugLevel > 1 ) {
                    System.out.println( "  port.toString()="+port.toString()+"" ) ;
                    System.out.println( "  port.getCurrentOwner()="+port.getCurrentOwner()+"" ) ;
                }
            }
            if (port.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                portList.add(port.getName());
            }
        }
        portArray = (String[]) portList.toArray(new String[0]);
        return portArray;
    }
    
}
