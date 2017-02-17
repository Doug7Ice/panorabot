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
 * @author ReyL03
 * @version 1.0
 * @updated 17-févr.-2017 14:54:38
 */
public class WrkSocket extends Thread {

    private volatile boolean on;
    private Socket socket;
    private ServerSocket socketServeur;
    private Wrk refWrk;

    public WrkSocket(ServerSocket socketServeur, Wrk wrk) {
        super("Socket");
        this.socketServeur = socketServeur;
        this.refWrk = wrk;
    }

	/**
	 * Scanne les requetes envoyes par le client.
	 */
    public void run() {
        try {
            on = true;
            refWrk.afficheMessageConsole("DO YOU SEE ME ???");
            while (on) {
                socket = socketServeur.accept(); // Un client se connecte on l'accepte
                System.out.println("L'utilisateur est connectÃ© !");
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

	/**
	 * Ferme les sockets.
	 */
    public void closeSockets() {
        try {
            if (socket != null && socket.isConnected()) {
                socket.close();
            }
            this.on = false;
            socketServeur.close();
        } catch (IOException ex) {
            Logger.getLogger(WrkSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
