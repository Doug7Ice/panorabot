/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wrk;

import jssc.SerialPort;

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
            
        }
    }
    
    private SerialPort sp;
    private boolean isReading;
    private Wrk wrk;
}
