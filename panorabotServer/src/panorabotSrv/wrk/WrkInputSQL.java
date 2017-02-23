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
public class WrkInputSQL extends Thread{
    private BufferedReader inSQL;
    private volatile boolean read;
    private Socket socketServerSQLogin;
    private ItfWrkWrkInputSQL refWrk;

    public WrkInputSQL(Socket socketServerSQLogin, ItfWrkWrkInputSQL refWrk) {
        super("InputSQL");
        this.socketServerSQLogin = socketServerSQLogin;
        refWrk.afficheMessageConsole(""+this.socketServerSQLogin.getLocalPort());       
        try {
            inSQL = new BufferedReader(new InputStreamReader(this.socketServerSQLogin.getInputStream()));
        } catch (IOException ex) {
            Logger.getLogger(WrkInputSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.refWrk = refWrk;
        this.read = true;
    }
    
    
    
    @Override
    //L,login,mdp
    public void run() {
        try {
            while (read) {
                String msg = inSQL.readLine();
                if (msg != null) {
                    refWrk.afficheMessageConsole(msg);
                    refWrk.envoieTxtAuClient("true");
                }
                
            }
        } catch (IOException e) {
            System.out.println("Déconnexion");
        }
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
    
    
}
