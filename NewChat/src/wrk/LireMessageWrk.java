/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wrk;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortTimeoutException;

/**
 *
 * @author Nathan
 */
public class LireMessageWrk extends Thread{

    public LireMessageWrk(SerialPort sp, Wrk wrk) {
        this.sp = sp;
        this.wrk = wrk;
    }
    
    
    @Override
    public void run(){
        isReading = true;
        while(isReading){
            readPort();
        }
    }
    
    private void readPort(){
        try {
            byte tabbyte [] = sp.readBytes(1,100);
            if (tabbyte != null) {
                wrk.showMessage(new String(tabbyte));                
            }
        } catch (SerialPortException ex) {
            System.out.println(ex);
        } catch (SerialPortTimeoutException ex) {            
        } 
    }

    public boolean isIsReading() {
        return isReading;
    }

    public void setIsReading(boolean isReading) {
        this.isReading = isReading;
    }
    
    
    
    private SerialPort sp;
    private boolean isReading;
    private Wrk wrk;
}
