/*
 * TwoWaySerialComm.java
 *
 *
 * @author Kersten Tams Copyright 2009-2018
 * @author Lothar Roth  Copyright 2012-2018
 *
 */

package my.CVNavi;

/**
 *
 * @author ktams 
 */
import purejavacomm.*;
import java.io.IOException; 
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import static my.CVNavi.CVNavi.debugLevel;


public class TwoWaySerialComm {
    
    private SerialPort tw_serialPort = null;
    private boolean    tw_connected = false;
    private CVNavi KTUI = null;
    private String     tw_dev = null;
    private int        tw_baud = -1;
    private boolean    tw_rtscts = false;

    public TwoWaySerialComm()
    {
        super();
    }
    public Boolean isconnected()
    {
        return this.tw_connected;
    }
    // @SuppressWarnings("static-access")
    void close() // throws Exception
    {
        disconnect();
    }
    void disconnect() // throws Exception
    {
        if( debugLevel >= 1 ) {
            System.out.println("DISCONNECT called" );
        }
        if( tw_serialPort != null && tw_connected == true ) {
            if( debugLevel >= 1 ) {
                System.out.println("DISCONNECT closing port" );
            }
            tw_serialPort.close();
            if( debugLevel >= 1 ) {
                System.out.println("DISCONNECT closed port" );
            }
        }
        tw_connected = false;
        tw_dev = null;
        tw_baud = -1;
        tw_rtscts = false;
    }

    void connect( CVNavi ktui) throws Exception
    {
        if( debugLevel > 1 ) {
            String sunModel = System.getProperty("sun.arch.data.model");
            String osName = System.getProperty("os.name");
            String osArch = System.getProperty("os.arch");
            if( debugLevel >= 1 ) {
                System.out.println("inside CONNECT myModel["+sunModel+"] osName["+osName+"] osArch["+osArch+"]");
            }
        }
        if( debugLevel > 2 ) {
            System.out.println("NEW CONNECT ktui["+ktui+"] KTUI["+KTUI+"]");
        }
        if( KTUI == null && ktui != null )
            KTUI = ktui;
        if( KTUI != null ) {
            if( debugLevel >= 1 ) {
                System.out.println("NEW CONNECT dev["+KTUI.gsSchnittstelle+"] baud["+KTUI.gsBaudRate+"] rtscts["+KTUI.gsRtsCts+"]");
            }
        } else {
            if( debugLevel >= 1 ) {
                System.out.println("NEW CONNECT without valid KTUI -> ABORT CONNECT" );
            }
            return;
        }
        if( tw_connected ) {
            if( tw_dev.equalsIgnoreCase(KTUI.gsSchnittstelle) && tw_baud == KTUI.gsBaudRate && tw_rtscts == KTUI.gsRtsCts) {
                if( debugLevel >= 1 ) {
                    System.out.println("CONNECT but already OPENED with identical parameters" );
                }
                return;
            }
            else {
                if( debugLevel >= 1 ) {
                    System.out.println("CONNECT but already OPENED with other parameters OLD dev["+tw_dev+"] baud["+tw_baud+"] rtscts["+tw_rtscts+"] "
                            +"NEW dev["+KTUI.gsSchnittstelle+"] baud["+KTUI.gsBaudRate+"] rtscts["+KTUI.gsRtsCts+"]");
                }
                disconnect();
            }
        } else {
            System.out.println("CONNECT and not OPENED" );
        }

        CommPortIdentifier portIdentifier = null;
        try {
            portIdentifier = CommPortIdentifier.getPortIdentifier(KTUI.gsSchnittstelle);
        }
        catch (NoSuchPortException e) {
            System.out.println("NoSuchPortException EXCEPTION in CONNECT");
            return;
        }
        catch (Exception e) {
            System.out.println("EXCEPTION in CONNECT "+ e);
            return;
        }
        catch (UnsatisfiedLinkError e) {
            System.out.println("connect: UnsatisfiedLinkError: "+ e);
            KTUI.mbGeneric( KTUI, "INFO: Library may be missing or does not fit.", "rxtxSerial.dll", e.getMessage() );
            return;
        }
        catch (NoClassDefFoundError e) {
            System.out.println("NoClassDefFoundError in CONNECT "+ e);
            return;
        }
        catch (Error e) {
            System.out.println("Error in CONNECT "+ e);
            return;
        }
        if( debugLevel > 0 ) {
            System.out.println("inside CONNECT");
        }

        if( debugLevel >= 2 ) {
            System.out.println("inside CONNECT name["+portIdentifier.getName()+"] portType["+portIdentifier.getPortType()+"] "
                    +"currentOwner["+portIdentifier.getCurrentOwner()+"] str["+portIdentifier.toString()+"] class["+portIdentifier.getClass().toString()+"]");
        } else if( debugLevel >= 0 ) {
            System.out.println("inside CONNECT name["+portIdentifier.getName()+"] portType["+portIdentifier.getPortType()+"] "
                    +"currentOwner["+portIdentifier.getCurrentOwner()+"]");
        }

        if ( portIdentifier.isCurrentlyOwned() )
        {
            KTUI.mbDeviceOwned( null );
        }
        else
        {
            if( debugLevel > 0 ) {
                System.out.println("call portIdentifier.open PRE");
            }
            CommPort commPort = portIdentifier.open(this.getClass().getName(),2000);
            if( debugLevel > 0 ) {
                System.out.println("call portIdentifier.open POST");
            }

            if( commPort instanceof SerialPort )
            {
                tw_serialPort = (SerialPort) commPort;
                switch(KTUI.getZentrale())
                {
                    case c.cuOpenDCC: // OpenDCC
                        tw_serialPort.setSerialPortParams(KTUI.gsBaudRate,SerialPort.DATABITS_8,SerialPort.STOPBITS_2,SerialPort.PARITY_NONE);
                       break;

                    case c.cuIntellibox1: // Intellibox
                        tw_serialPort.setSerialPortParams(KTUI.gsBaudRate,SerialPort.DATABITS_8,SerialPort.STOPBITS_2,SerialPort.PARITY_NONE);
                        break;

                    case c.cuMasterControl: // MasterControl
                        tw_serialPort.setSerialPortParams(KTUI.gsBaudRate,SerialPort.DATABITS_8,SerialPort.STOPBITS_2,SerialPort.PARITY_NONE);
                        break;
                }
                showRTSDTR( "connect: ", tw_serialPort, " pre  set flowcontrol");
                if (KTUI.gsRtsCts) {
                    // tw_serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN);
                    // tw_serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_OUT);
                    // OR ???
                    tw_serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN | SerialPort.FLOWCONTROL_RTSCTS_OUT);
                }
                tw_serialPort.enableReceiveTimeout(500);
                tw_connected = true;
                tw_dev = KTUI.gsSchnittstelle;
                tw_baud = KTUI.gsBaudRate;
                tw_rtscts = KTUI.gsRtsCts;

                showRTSDTR( "connect: ", tw_serialPort, " pre  optional set" );
                if( ! tw_serialPort.isRTS() ) {
                    System.out.println("connect: set RTS");
                    tw_serialPort.setRTS(true);
                }
                if( ! tw_serialPort.isDTR() ) {
                    System.out.println("connect: set DTR");
                    tw_serialPort.setDTR(true);
                }
                showRTSDTR( "connect: ", tw_serialPort, " post optional set" );
            }
            else
            {
                System.out.println("Error: Only serial or USB ports are handled.");
            }
        }
    }

    void showRTSDTR( SerialPort sp ) {
        showRTSDTR( "", sp, "" );
    }
    void showRTSDTR( String s1, SerialPort sp, String s2 ) {
        if( debugLevel > 0 ) {
            System.out.println(s1+"RTS="+sp.isRTS()+" DTR="+sp.isDTR()+s2);
        }
    }

    int read(byte[] bArray) {
        int n = 0;
        try {
            InputStream in = tw_serialPort.getInputStream();
            n = in.read(bArray);
            return n;
        } catch (IOException | NullPointerException ex) {
            // TODO darf das deaktiviert sein ? KTUI.mbDeviceReadProblem();
            Logger.getLogger(TwoWaySerialComm.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
   }

    void write(int[] data) {
        try {
            int n = data.length;
            byte[] bArray = new byte[n];
            OutputStream out = tw_serialPort.getOutputStream();
            for (int i = 0; i < n; i++)
            {
                bArray[i] = (byte) data[i];
            }
            out.write(bArray);
        } catch (IOException ex) {
            KTUI.mbDeviceWriteProblem( null );
        }
    }

    void write(byte b) {
        try {
            OutputStream out = tw_serialPort.getOutputStream();
            out.write(b);
        } catch (IOException ex) {
            KTUI.mbDeviceWriteProblem( null );
        }
    }

    void write(byte[] bArr) {
        try {
            OutputStream out = tw_serialPort.getOutputStream();
            for( int i = 0 ; i < bArr.length ; i++ ) {
                out.write(bArr[i]);
            }
        } catch (IOException ex) {
            KTUI.mbDeviceWriteProblem( null );
        }
    }

    void write(String s) {
        try {
            OutputStream out = tw_serialPort.getOutputStream();
            int n = s.length();
            byte[] bArray = new byte[n];
            for (int i = 0; i < n; i++)
            {
                bArray[i] = (byte) s.charAt(i);
                out.write(bArray[i]);
            }
            // out.write(bArray);
        } catch (IOException ex) {
            KTUI.mbDeviceWriteProblem( null );
        }        
    }
}

