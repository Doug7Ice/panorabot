/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package openimaj_socket_server.wrk;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import openimaj_socket_server.ctrl.ItfCtrlWrk;

/**
 *
 * @author ReyL03
 */
public class WrkSocket extends Thread{

    public WrkSocket(ServerSocket socketServer, ItfCtrlWrk ctrl) {
        super("WrkConnexion");
        this.socketServer = socketServer;
        this.refCtrl = ctrl;
        this.threadStream = null;
    }
    
    public void run() {
        try {
            read = true;
            while (this.read) {
                
                socket = socketServer.accept(); // Un client se connecte on l'accepte
                System.out.println("L'utilisateur numéro est connecté !");
                threadStream = new WrkSocketStream(socket, refCtrl, this);
                
                threadStream.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setRead(boolean read) {
        this.read = read;
    }
    public void setReadStreamThread(boolean read){
        threadStream.setRead(read);
        if (read){
            try{
            threadStream.join();
            threadStream = null;
            }
            catch (Exception e){
                System.out.println(e);
            }
        }
    }
    
    public void fermeThreadStream(){
        threadStream = null;
    }
    
    private ServerSocket socketServer;
    private Socket socket;
    private ItfCtrlWrk refCtrl;
    private WrkSocketStream threadStream;
    private volatile boolean read;
}
