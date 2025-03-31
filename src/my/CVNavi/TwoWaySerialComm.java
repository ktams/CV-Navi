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
import java.nio.ByteBuffer;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.logging.Level;
import java.util.logging.Logger;
import static my.CVNavi.CVNavi.debugLevel;
import static my.CVNavi.CVNavi.gsSchnittstelle;
import static my.CVNavi.CVNavi.iComType;
import static my.CVNavi.CVNavi.port;


public class TwoWaySerialComm {
    
    private SerialPort tw_serialPort = null;
    private boolean    tw_connected = false;
    private CVNavi     CVNavi = null;
    private String     tw_dev = null;
    private int        tw_baud = -1;
    private boolean    tw_rtscts = false;

    public TwoWaySerialComm()
    {
        super();
    }
    public Boolean isconnected()
    {
        if( iComType == c.TCP_IP ) {
            if( debugLevel >= 4 ) {
                System.out.println("inside ISCONNECTED TCP_IP");
            }
            if( CVNavi.clientSocket == null ) {
                System.out.println("inside ISCONNECTED TCP_IP clientSocket == null");
                return false;
            }
            return CVNavi.clientSocket.isConnected();
        }
        return this.tw_connected;
    }

    // @SuppressWarnings("static-access")
    void close() // throws Exception
    {
        disconnect();
    }
    void disconnect() // throws Exception
    {
        if( debugLevel > 0 ) {
            System.out.println("DISCONNECT called" );
        }
        if( iComType == c.TCP_IP ) {
            if( debugLevel > 0 ) {
                System.out.println("inside DISCONNECT TCP_IP");
            }
            if ( CVNavi.clientSocket.isConnected() ){
                try {
                    CVNavi.clientSocket.close();
                } catch (IOException ex) {
                    Logger.getLogger(TwoWaySerialComm.class.getName()).log(Level.SEVERE, null, ex);
                }
                CVNavi.clientSocket = null;
                if( debugLevel >= 1 ) {
                    System.out.println("DISCONNECTED" );
                }
            } else {
                System.out.println("disconnect called but NOT CONNECTED" );
            }
            return;
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

    void connect( CVNavi cvnavi) throws Exception
    {
        if( debugLevel > 0 ) {
            System.out.println("inside CONNECT iComType = "+iComType);
        }
        if( debugLevel > 1 ) {
            String sunModel = System.getProperty("sun.arch.data.model");
            String osName = System.getProperty("os.name");
            String osArch = System.getProperty("os.arch");
            if( debugLevel >= 1 ) {
                System.out.println("inside CONNECT myModel["+sunModel+"] osName["+osName+"] osArch["+osArch+"]");
            }
        }
        if( debugLevel > 2 ) {
            System.out.println("NEW CONNECT cvnavi["+cvnavi+"] CVNavi["+CVNavi+"]");
        }
        if( CVNavi == null && cvnavi != null )
            CVNavi = cvnavi;
        if( CVNavi == null ) {
            if( debugLevel >= 1 ) {
                System.out.println("NEW CONNECT without valid CVNavi -> ABORT CONNECT" );
            }
            return;
        }
        if( iComType == c.TCP_IP ) {
            if( debugLevel > 2 ) {
                System.out.println("inside CONNECT TCP_IP");
            }

            if( CVNavi.clientSocket == null ) {
                if( debugLevel > 0 ) {
                    System.out.println("inside CONNECT TCP_IP clientSocket == null -> NEW CONNECT");
                }
                // LRLRLR java.net.preferIPv4Stack
                System.out.println("inside CONNECT TCP_IP conn to host["+gsSchnittstelle+"] port=["+port+"]");
                CVNavi.clientSocket = SocketChannel.open(new InetSocketAddress(gsSchnittstelle, port));
                CVNavi.clientSocket.configureBlocking(false);
                if( CVNavi.clientSocket == null ) {
                    System.out.println("inside CONNECT TCP_IP clientSocket == null AFTER connect");
                } else {
                    if( debugLevel > 0 ) {
                        System.out.println("inside CONNECT TCP_IP"+
                            " isBlocking="+CVNavi.clientSocket.isBlocking()+
                            " isConnected="+CVNavi.clientSocket.isConnected()+
                            " isConnectionPending="+CVNavi.clientSocket.isConnectionPending()+
                            " isOpen="+CVNavi.clientSocket.isOpen() );
                    }
                    if( debugLevel >= 0 ) {
                        System.out.println("inside CONNECT TCP_IP"
                                +"\n Connection["+CVNavi.clientSocket.toString()+"]"
                                +"\n LocalAddr ["+CVNavi.clientSocket.socket().getLocalAddress().toString()+"]"
                                +"\n LocalPort ["+CVNavi.clientSocket.socket().getLocalPort()+"]"
                                +"\n RemoteAddr["+CVNavi.clientSocket.socket().getRemoteSocketAddress().toString()+"]"
                                +"\n RemotePort["+CVNavi.clientSocket.socket().getPort()+"]"
                        );
                    }
                }
            } else {
                if( debugLevel > 0 ) {
                    System.out.println("inside CONNECT TCP_IP clientSocket != null -> ALREADY CONNECTED");
                }
                if( debugLevel > 0 ) {
                    System.out.println("inside CONNECT TCP_IP"+
                        " isBlocking="+CVNavi.clientSocket.isBlocking()+
                        " isConnected="+CVNavi.clientSocket.isConnected()+
                        " isConnectionPending="+CVNavi.clientSocket.isConnectionPending()+
                        " isOpen="+CVNavi.clientSocket.isOpen() );
                }
            }
            return;
        }

        if( debugLevel >= 1 ) {
            System.out.println("NEW CONNECT dev["+CVNavi.gsSchnittstelle+"] baud["+CVNavi.gsBaudRate+"] rtscts["+CVNavi.gsRtsCts+"]");
        }
        if( tw_connected ) {
            if( tw_dev.equalsIgnoreCase(CVNavi.gsSchnittstelle) && tw_baud == CVNavi.gsBaudRate && tw_rtscts == CVNavi.gsRtsCts) {
                if( debugLevel >= 1 ) {
                    System.out.println("CONNECT but already OPENED with identical parameters" );
                }
                return;
            }
            else {
                if( debugLevel >= 1 ) {
                    System.out.println("CONNECT but already OPENED with other parameters OLD dev["+tw_dev+"] baud["+tw_baud+"] rtscts["+tw_rtscts+"] "
                            +"NEW dev["+CVNavi.gsSchnittstelle+"] baud["+CVNavi.gsBaudRate+"] rtscts["+CVNavi.gsRtsCts+"]");
                }
                disconnect();
            }
        } else {
            System.out.println("CONNECT and not OPENED" );
        }

        CommPortIdentifier portIdentifier = null;
        try {
            if( debugLevel > 3 ) {
                System.out.println("CommPortIdentifier.getPortIdentifier("+CVNavi.gsSchnittstelle+")");
            }
            portIdentifier = CommPortIdentifier.getPortIdentifier(CVNavi.gsSchnittstelle);
        }
        catch (NoSuchPortException e) {
            System.out.println("NoSuchPortException EXCEPTION in CONNECT for "+CVNavi.gsSchnittstelle);
            return;
        }
        catch (Exception e) {
            System.out.println("EXCEPTION in CONNECT "+ e);
            return;
        }
        catch (UnsatisfiedLinkError e) {
            System.out.println("connect: UnsatisfiedLinkError: "+ e);
            CVNavi.mbGeneric( CVNavi, "INFO: Library may be missing or does not fit.", "rxtxSerial.dll", e.getMessage() );
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
            System.out.println("inside CONNECT SERIAL");
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
            CVNavi.mbDeviceOwned( null );
            System.out.println("mbDeviceOwned by["+portIdentifier.getCurrentOwner()+"] name["+portIdentifier.getName()+"] type["+portIdentifier.getPortType()+"]");
        }
        else
        {
            if( debugLevel > 3 ) {
                System.out.println("call portIdentifier.open PRE");
            }
            CommPort commPort = portIdentifier.open(this.getClass().getName(),2000);
            if( debugLevel > 3 ) {
                System.out.println("call portIdentifier.open POST");
            }

            if( commPort instanceof SerialPort )
            {
                if( debugLevel > 3 ) {
                    System.out.println("commPort instanceof SerialPort A");
                }
                tw_serialPort = (SerialPort) commPort;
                if( debugLevel > 3 ) {
                    System.out.println("commPort instanceof SerialPort B");
                }
                switch(CVNavi.getZentrale())
                {
                    case c.cuOpenDCC: // OpenDCC
                        tw_serialPort.setSerialPortParams(CVNavi.gsBaudRate,SerialPort.DATABITS_8,SerialPort.STOPBITS_2,SerialPort.PARITY_NONE);
                       break;

                    case c.cuIntellibox1: // Intellibox
                        tw_serialPort.setSerialPortParams(CVNavi.gsBaudRate,SerialPort.DATABITS_8,SerialPort.STOPBITS_2,SerialPort.PARITY_NONE);
                        break;

                    case c.cuMasterControl1: // MasterControl
                        tw_serialPort.setSerialPortParams(CVNavi.gsBaudRate,SerialPort.DATABITS_8,SerialPort.STOPBITS_2,SerialPort.PARITY_NONE);
                        break;
                }
                System.out.println("FlowControlMode is ["+tw_serialPort.getFlowControlMode()+"]" );
                if (CVNavi.gsRtsCts) {
                    // tw_serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN);
                    // tw_serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_OUT);
                    // OR ???
                    tw_serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN | SerialPort.FLOWCONTROL_RTSCTS_OUT);
                }
                tw_serialPort.enableReceiveTimeout(500);
                tw_connected = true;
                tw_dev = CVNavi.gsSchnittstelle;
                tw_baud = CVNavi.gsBaudRate;
                tw_rtscts = CVNavi.gsRtsCts;

                if( CVNavi.gsUse_RTSDTR == true ) {
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
            }
            else
            {
                System.out.println("Error: Only serial or USB ports are handled.");
                if( debugLevel > 3 ) {
                    System.out.println("commPort NOT instanceof SerialPort commPort"+commPort.toString());
                }
            }
        }
    }

    void showRTSDTR( SerialPort sp ) {
        showRTSDTR( "", sp, "" );
    }
    void showRTSDTR( String s1, SerialPort sp, String s2 ) {
        if( CVNavi.gsUse_RTSDTR == true ) {
            if( debugLevel > 4 ) {
                System.out.println(s1+"RTS="+sp.isRTS()+" DTR="+sp.isDTR()+s2);
            }
        }
    }

    int read(byte[] bArray) {
        int ret = 0;
        if( iComType == c.TCP_IP ) {
            if( debugLevel > 0 ) {
                System.out.println("inside READ TCP_IP begin");
            }

            if( CVNavi.clientSocket == null ) {
                System.out.println("inside READ TCP_IP clientSocket == null");
                return 0;
            }
            if( ! CVNavi.clientSocket.isConnected() ) {
                System.out.println("inside READ TCP_IP clientSocket not connected");
                return 0;
            }

            ByteBuffer buf = ByteBuffer.allocate(0xFFFF);
            int BytesRead = -1;
            try {
                BytesRead = CVNavi.clientSocket.read(buf);
                if (BytesRead == -1) {
                    System.out.println("inside READ TCP_IP read returned -1");
                    return -1;
                }
                if( debugLevel >= 1 ) {
                    System.out.println("inside READ TCP_IP BytesRead="+BytesRead);
                }
            } catch (IOException ex) {
                System.out.println("inside READ TCP_IP EXECPTION on read START");
                Logger.getLogger(TwoWaySerialComm.class.getName()).log(Level.SEVERE, "TwoWaySerialComm CATCHED exception on READ", ex);
                System.out.println("inside READ TCP_IP EXECPTION on read CALL disconnect()");
                this.disconnect();
                System.out.println("inside READ TCP_IP EXECPTION on read END");
            }
            if( (debugLevel >= 2) && tw_connected ) {
                System.out.println("inside READ TCP_IP"+
                        " isBlocking="+CVNavi.clientSocket.isBlocking()+
                        " isConnected="+CVNavi.clientSocket.isConnected()+
                        " isConnectionPending="+CVNavi.clientSocket.isConnectionPending()+
                        " isOpen="+CVNavi.clientSocket.isOpen() );
            }

            if( BytesRead > 0 ) {
                if( debugLevel >= 4 ) {
                    System.out.println("inside READ TCP_IP buf.array().length="+buf.array().length);
                    System.out.println("inside READ TCP_IP buf.toString="+buf.toString());
                }

                int off = buf.arrayOffset();
                int pos = buf.position();

                ret = buf.position() - buf.arrayOffset();

                if( debugLevel >= 2 ) {
                    System.out.println("inside READ TCP_IP remaining="+buf.remaining()+" arrayOffset="+buf.arrayOffset()+" position="+buf.position());
                }
                buf.position(0);
                buf.get(bArray, 0, BytesRead);
                if( debugLevel >= 1 ) {
                    System.out.println("inside READ TCP_IP bArray.length="+bArray.length+" ret="+ret );
                }
            }
            if( debugLevel > 0 ) {
                System.out.println("inside READ TCP_IP end ret["+ret+"]");
            }
            return ret;
        }
        int n = 0;
        try {
            InputStream in = tw_serialPort.getInputStream();
            n = in.read(bArray);
            return n;
        } catch (IOException | NullPointerException ex) {
            // TODO darf das deaktiviert sein ? CVNavi.mbDeviceReadProblem();
            Logger.getLogger(TwoWaySerialComm.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
   }

    void write(int[] data) {
        if( iComType == c.TCP_IP ) {
            if( debugLevel >= 0 ) {
                System.out.println("inside WRITE(int) TCP_IP");
            }
            int n = data.length;
            byte[] bArr = new byte[n];
            for (int i = 0; i < n; i++)
            {
                bArr[i] = (byte) data[i];
            }

            ByteBuffer src = ByteBuffer.wrap(bArr);
            try {
                CVNavi.clientSocket.write(src);
            } catch (IOException ex) {
                Logger.getLogger(TwoWaySerialComm.class.getName()).log(Level.SEVERE, null, ex);
            }
            return;
        }
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
            CVNavi.mbDeviceWriteProblem( null );
        }
    }

    void write(byte b) {
        if( iComType == c.TCP_IP ) {
            if( debugLevel >= 0 ) {
                System.out.println("inside WRITE(byte) TCP_IP");
            }
            byte[] bArr = new byte[1] ;
            bArr[0] = b;
            ByteBuffer src = ByteBuffer.wrap(bArr);
            try {
                CVNavi.clientSocket.write(src);
            } catch (IOException ex) {
                Logger.getLogger(TwoWaySerialComm.class.getName()).log(Level.SEVERE, null, ex);
            }
            return;
        }
        try {
            OutputStream out = tw_serialPort.getOutputStream();
            out.write(b);
        } catch (IOException ex) {
            CVNavi.mbDeviceWriteProblem( null );
        }
    }

    void write(byte[] bArr) {
        if( iComType == c.TCP_IP ) {
            if( debugLevel > 0 ) {
                System.out.println("inside WRITE(byteArr) TCP_IP length="+bArr.length);
            }
            ByteBuffer src = ByteBuffer.wrap(bArr);;
            try {
                CVNavi.clientSocket.write(src);
            } catch (IOException ex) {
                Logger.getLogger(TwoWaySerialComm.class.getName()).log(Level.SEVERE, null, ex);
            }
            if( debugLevel >= 4 ) {
                CVNavi.dumpbArray(bArr);
            }
            return;
        }
        try {
            OutputStream out = tw_serialPort.getOutputStream();
            for( int i = 0 ; i < bArr.length ; i++ ) {
                out.write(bArr[i]);
            }
        } catch (IOException ex) {
            CVNavi.mbDeviceWriteProblem( null );
        }
    }

    void write(String s) {
        if( iComType == c.TCP_IP ) {
            if( debugLevel > 0 ) {
                System.out.println("inside WRITE(String) TCP_IP s=["+s+"]");
            }
            ByteBuffer src = CVNavi.str_to_bb(s);;
            try {
                CVNavi.clientSocket.write(src);
            } catch (IOException ex) {
                Logger.getLogger(TwoWaySerialComm.class.getName()).log(Level.SEVERE, null, ex);
            }
            if( debugLevel > 0 ) {
                System.out.println("inside WRITE(String) TCP_IP s=["+s+"] ENDE");
            }
            return;
        }
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
            CVNavi.mbDeviceWriteProblem( null );
        }        
    }
}

