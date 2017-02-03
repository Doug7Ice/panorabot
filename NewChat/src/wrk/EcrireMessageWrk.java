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
public class EcrireMessageWrk {

    public EcrireMessageWrk(SerialPort sp, Wrk wrk) {
        this.wrk = wrk;
        this.sp = sp;
    }
    
    
    private Wrk wrk;
    private SerialPort sp;
}
