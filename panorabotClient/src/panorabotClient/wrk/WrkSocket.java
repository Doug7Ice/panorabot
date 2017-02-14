/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panorabotClient.wrk;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nathan
 */
public class WrkSocket extends Thread{

    public WrkSocket(Wrk refWrk) {
        this.refWrk = refWrk;
        running = false;
        try {
            socket = new Socket("192.168.1.1", 8080);
            out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(WrkSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void run(){
        running = true;
//        while(running){
//            
//        }
    }
    
    
    public void avancerRobotTCP(int vitesse){
        try {
            out.writeChars("D," + vitesse + "," + vitesse);
        } catch (IOException ex) {
            Logger.getLogger(WrkSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    public void stopperRobotTCP(){
        try {
            out.writeChars("D,0,0");
        } catch (IOException ex) {
            Logger.getLogger(WrkSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void tournerADroiteTCP(){
        try {
            out.writeChars("D,14,-14");
        } catch (IOException ex) {
            Logger.getLogger(WrkSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void tournerAGaucheTCP(){
        try {
            out.writeChars("D,-14,14");
        } catch (IOException ex) {
            Logger.getLogger(WrkSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private Wrk refWrk;
    private String actualPath;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private boolean running;
    private Socket socket;
}
