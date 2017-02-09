/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wrk;

import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPort;
import jssc.SerialPortException;

/**
 *
 * @author Nathan
 */
public class EcrireMessageWrk {

    public EcrireMessageWrk(SerialPort sp, Wrk wrk) {
        this.wrk = wrk;
        this.sp = sp;
        setComputerName();
    }
    
    public void writeMessage(String message){
        String msg = message +"\n";
        //String msg = name + " -- " + message +"\n";
        try {
            sp.writeBytes(msg.getBytes());//Write data to port
            wrk.showMessage(msg);
        } catch (SerialPortException ex) {
            
        }
    }
    
    public void setComputerName() {
        //InetAddress.getLocalHost().getHostName();
        name = System.getProperty("user.name");
    }
    
    
    private Wrk wrk;
    private SerialPort sp;
    private String name;
}
