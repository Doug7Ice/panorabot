/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panorabotSrv.wrk;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
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
    private Socket socketCommandsAndWebcam;
    private Socket socketImgFromBD;
    private Socket socketLogin;
    private ServerSocket socketServerCommandsAndWebcam;
    private ServerSocket socketServerImgFromBD;
    private ServerSocket socketServerLogin;
    private ItfWrkWrkSocket refWrk;

    public WrkSocket(ServerSocket socketServerCommandsAndWebcam, ServerSocket socketServerImgFromBD, ServerSocket socketServerLogin, ItfWrkWrkSocket wrk) {
        super("WrkConnexion");
        this.socketServerCommandsAndWebcam = socketServerCommandsAndWebcam;
        this.socketServerImgFromBD = socketServerImgFromBD;
        this.socketServerLogin = socketServerLogin;
        this.refWrk = wrk;
    }

    /**
     * Scanne les requetes envoyes par le client.
     */
    public void run() {
        try {
            on = true;
            while (on) {
                if (!on){
                    break;
                }
                socketCommandsAndWebcam = socketServerCommandsAndWebcam.accept(); // Un client se connecte on l'accepte
                socketImgFromBD = socketServerImgFromBD.accept();
                socketLogin = socketServerLogin.accept();
                refWrk.afficheStatutClient(socketCommandsAndWebcam.isConnected() && socketImgFromBD.isConnected() && socketLogin.isConnected());
                refWrk.lauchWrkInput(socketCommandsAndWebcam);
                refWrk.lauchWrkInputLogin(socketLogin);
                refWrk.lauchWrkOutput(socketCommandsAndWebcam, socketImgFromBD, socketLogin);
                refWrk.showWebcam();
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

    public Socket getSocketCommandsAndWebcam() {
        return socketCommandsAndWebcam;
    }

    public void setSocketCommandsAndWebcam(Socket socketCommandsAndWebcam) {
        this.socketCommandsAndWebcam = socketCommandsAndWebcam;
    }

    public Socket getSocketImgFromBD() {
        return socketImgFromBD;
    }

    public void setSocketImgFromBD(Socket socketImgFromBD) {
        this.socketImgFromBD = socketImgFromBD;
    }

    public Socket getSocketLogin() {
        return socketLogin;
    }

    public void setSocketLogin(Socket socketLogin) {
        this.socketLogin = socketLogin;
    }

    /**
     * Ferme les sockets.
     */
    public void closeSockets() {    
        try {
            if (socketCommandsAndWebcam != null && socketCommandsAndWebcam.isConnected() && socketImgFromBD != null && socketImgFromBD.isConnected() && socketLogin != null && socketLogin.isConnected()) {
            socketCommandsAndWebcam.close();
            socketImgFromBD.close();
            socketLogin.close();
        }
        this.on = false;
        socketServerImgFromBD.close();
        socketServerLogin.close();
        socketServerCommandsAndWebcam.close();
    }
    catch (IOException ex

    
        ) {
            Logger.getLogger(WrkSocket.class.getName()).log(Level.SEVERE, null, ex);
    }
}

}
