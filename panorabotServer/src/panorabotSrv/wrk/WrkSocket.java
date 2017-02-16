/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panorabotSrv.wrk;

import java.io.IOException;
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
    private Wrk refWrk;

    public WrkSocket(ServerSocket socketServeur,Wrk wrk) {
        this.socketServeur = socketServeur;
        this.refWrk = wrk;
    }
    
    
    
    public void run(){
        try {
            on = true;
            refWrk.afficheMessageConsole("DO YOU SEE ME ???");
            while (this.on) {
                
                socket = socketServeur.accept(); // Un client se connecte on l'accepte
                System.out.println("L'utilisateur est connecté !");
                refWrk.lauchWrkInput(socket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
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
