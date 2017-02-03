/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wrk;

import ctrl.Ctrl;
import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.SerialPort;
import jssc.SerialPortException;

/**
 *
 * @author Nathan
 */
public class Wrk {

    public Wrk() {
        ouvrirPort();
        wrkWrite = new EcrireMessageWrk(serialPort, this);
        wrkRead = new LireMessageWrk(serialPort, this);
        wrkRead.start();
    }
    
    
    public void ouvrirPort(){
        this.serialPort = new SerialPort("COM1");
        try {            
            serialPort.openPort();
            serialPort.setParams(SerialPort.BAUDRATE_57600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_2,
                    SerialPort.PARITY_EVEN);//Set params. Also you can set params by this string: serialPort.setParams(9600, 8, 1, 0);
        } catch (SerialPortException ex) {
           System.out.println("erreur = " + ex);
           wrkRead.setIsReading(false);
        }
    }
    
    public void envoyerMsg(String msg){
        wrkWrite.writeMessage(msg);
    }
    
    public void showMessage(String msg){
       refCtrl.msgAfficher(msg);
    }
    
    public void fermer() {
        try {
            wrkRead.setIsReading(false);
            serialPort.closePort();            
        } catch (SerialPortException ex) {
            Logger.getLogger(Wrk.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    
    
    //SETTERS AND GETTERS
    public EcrireMessageWrk getWrkWrite() {
        return wrkWrite;
    }

    public void setWrkWrite(EcrireMessageWrk wrkWrite) {
        this.wrkWrite = wrkWrite;
    }

    public LireMessageWrk getWrkRead() {
        return wrkRead;
    }

    public void setWrkRead(LireMessageWrk wrkRead) {
        this.wrkRead = wrkRead;
    }

    public Ctrl getRefCtrl() {
        return refCtrl;
    }

    public void setRefCtrl(Ctrl refCtrl) {
        this.refCtrl = refCtrl;
    }

    
    
    //VARIABLES
    private SerialPort serialPort;
    private EcrireMessageWrk wrkWrite;
    private LireMessageWrk wrkRead;
    private Ctrl refCtrl;
}


