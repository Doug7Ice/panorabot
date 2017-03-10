/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panorabotSrv.wrk;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Cree le socket et scanne les requetes de connexion des clients.
 *
 * @author ReyL03
 * @version 1.0
 * @updated 17-fevr.-2017 14:54:38
 */
public class WrkSocket extends Thread {

    private volatile boolean on;
    private Socket socket;
    private ServerSocket socketServer;
    private ItfWrkWrkSocket refWrk;

    public WrkSocket(ServerSocket socketServeur, ItfWrkWrkSocket wrk) {
        super("WrkConnexion");
        this.socketServer = socketServeur;
        this.refWrk = wrk;
    }

    /**
     * Scanne les requetes envoyes par le client.
     */
    public void run() {
        try {
            on = true;
            //refWrk.affichePopupError("Tkt");
            while (on) {
                if (!on){
                    break;
                }
                socket = socketServer.accept(); // Un client se connecte on l'accepte
                if (socket.isConnected()){
                refWrk.afficheStatutClient(socket.isConnected());
                refWrk.lauchWrkInput(socket);
                refWrk.lauchWrkOutput(socket);
                refWrk.showWebcam();
                }
            }
            
        }catch (SocketException a){
            System.out.println("L'application c'est déconnectée.");
        } 
        catch (IOException e) {
            refWrk.affichePopupError(WrkSocket.class.getName()+" : "+e.getMessage());
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
    
    

    /**
     * Ferme les sockets.
     */
     public void closeSockets() {
        try {
            if (socket != null && socket.isConnected()) {
                socket.close();
            }
            this.on = false;
            socketServer.close();
        } catch (IOException ex) {
            refWrk.affichePopupError(WrkSocket.class.getName()+" : "+ex.getMessage());
        }
    }

}
