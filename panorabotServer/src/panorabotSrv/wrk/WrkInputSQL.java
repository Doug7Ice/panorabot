/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panorabotSrv.wrk;

import java.io.BufferedReader;
import java.net.Socket;

/**
 *
 * @author ReyL03
 */
public class WrkInputSQL implements Runnable{
    private BufferedReader inSQL;
    private volatile boolean read;
    private Socket socketServerSQLogin;
    private ItfWrkWrkInput refWrk;
    
    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
