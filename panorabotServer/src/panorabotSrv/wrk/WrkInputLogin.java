/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panorabotSrv.wrk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ReyL03
 */
public class WrkInputLogin extends Thread {

    private ItfWrkWrkInputLogin refWrk;
    private BufferedReader in;
    private volatile boolean read;
    private Socket socket;

    public WrkInputLogin(ItfWrkWrkInputLogin refWrk, Socket socket) {
        super("InputLogin");
        this.refWrk = refWrk;
        this.socket = socket;
        this.read = true;
        try {
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(WrkInputLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void finalize() throws Throwable {
		super.finalize();
	}
    
    public void run(){
        try {          
            while (read) {
                String msg = in.readLine();
                if (msg != null) {
                    String[] login = msg.split(",");
                    if (login.length == 3){
                    boolean ok = refWrk.checkLogin(login[1],login[2]);
                    if (ok){
                        refWrk.sendTxtClient("true");
                    }
                    else{
                        refWrk.sendTxtClient("false");
                    }
                    
                    }
                }
            }
        } catch (IOException e) {
                System.out.println("Déconnexion");
        }
    }

    public void setRead(boolean read) {
        this.read = read;
    }
    
}
