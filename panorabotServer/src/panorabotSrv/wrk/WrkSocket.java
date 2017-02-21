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
    private Socket socketCamAndReceive;
    private Socket socketSendImgFromDB;
    private Socket socketSQLogin;
    private ServerSocket socketSendCamAndReceiveSrv;
    private ServerSocket socketSendImgFromDBSrv;
    private ServerSocket socketSQLoginSrv;
    private ItfWrkWrkSocket refWrk;

    public WrkSocket(ServerSocket socketSendCamAndReceive,ServerSocket socketSendImgFromDB,ServerSocket socketSQLogin, ItfWrkWrkSocket wrk) {
        super("WrkConnexion");
        this.socketSendCamAndReceiveSrv = socketSendCamAndReceive;
        this.socketSendImgFromDBSrv = socketSendImgFromDB;
        this.socketSQLoginSrv = socketSQLogin;
        this.refWrk = wrk;
    }

    /**
     * Scanne les requetes envoyes par le client.
     */
    public void run() {
        try {
            on = true;
            while (on) {
                if (on = false) {
                    break;
                }
                socketCamAndReceive = socketSendCamAndReceiveSrv.accept(); // Un client se connecte on l'accepte
                socketSendImgFromDB = socketSendImgFromDBSrv.accept();
                socketSQLogin = socketSQLoginSrv.accept();
                                
                if (socketCamAndReceive.isConnected() && socketSendImgFromDB.isConnected() && socketSQLogin.isConnected()) {
                    refWrk.afficheStatutClient(true);
                    refWrk.lauchWrkInput(socketCamAndReceive, socketSQLogin);
                    refWrk.lauchWrkOutput(socketCamAndReceive,socketSendImgFromDB);
                    refWrk.showWebcam();

                } else {
                    refWrk.afficheStatutClient(false);
                    refWrk.closeWebcam();
                    refWrk.closeWrkInput();
                }              
            }
            while(!on){
                if (socketCamAndReceive.isConnected() && socketSendImgFromDB.isConnected() && socketSQLogin.isConnected()){
                    on = true;
                    this.run();
                }
                else{
                    refWrk.afficheStatutClient(false);
                    refWrk.closeWebcam();
                    refWrk.closeWrkInput();
                }
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

    public Socket getSocketCamAndReceive() {
        return socketCamAndReceive;
    }

    public void setSocketCamAndReceive(Socket socketCamAndReceive) {
        this.socketCamAndReceive = socketCamAndReceive;
    }

    public Socket getSocketSendImgFromDB() {
        return socketSendImgFromDB;
    }

    public void setSocketSendImgFromDB(Socket socketSendImgFromDB) {
        this.socketSendImgFromDB = socketSendImgFromDB;
    }

    public Socket getSocketSQLogin() {
        return socketSQLogin;
    }

    public void setSocketSQLogin(Socket socketSQLogin) {
        this.socketSQLogin = socketSQLogin;
    }


    

    /**
     * Ferme les sockets.
     */
    public void closeSockets() {
        try {
            if (socketCamAndReceive != null && socketCamAndReceive.isConnected() && socketSQLogin != null && socketSQLogin.isConnected() && socketSendImgFromDB != null && socketSendImgFromDB.isConnected()) {
                socketCamAndReceive.close();
                socketSQLogin.close();
                socketSendImgFromDB.close();
            }
            this.on = false;
            socketSQLoginSrv.close();
            socketSendCamAndReceiveSrv.close();
            socketSendImgFromDBSrv.close();
        } catch (IOException ex) {
            Logger.getLogger(WrkSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
