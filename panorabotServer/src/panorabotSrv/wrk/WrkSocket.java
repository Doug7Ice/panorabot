/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panorabotSrv.wrk;

import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author ReyL03
 */
public class WrkSocket extends Thread{
    private volatile boolean on;
    private Socket socket;
    private ServerSocket socketServeur;

    public WrkSocket(Socket socket, ServerSocket socketServeur) {
        this.socket = socket;
        this.socketServeur = socketServeur;
    }
    
    
    
    public void run(){
        
    }
    
    private void bougeLeRobot(String commande){
        
    }
    
    private void lanceCapture(double rayon){
        
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public ServerSocket getSocketServeur() {
        return socketServeur;
    }

    public void setSocketServeur(ServerSocket socketServeur) {
        this.socketServeur = socketServeur;
    }

    
    
    
}
