package panorabotSrv.wrk;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import panorabotSrv.ctrl.ItfCtrlWrk;

/**
 * Worker de l'application Panorabot Serveur.
 * @author ReyL03
 * @version 1.0
 * @updated 17-f�vr.-2017 14:54:37
 */
public class Wrk implements ItfWrkCtrl {

    public ItfCtrlWrk refCtrl;
    public WrkKJunior refWrkKjunior;
    public WrkKjuniorCam refWrkKjuniorCam;
    public WrkSocket refWrkSocket;
    public WrkInput refWrkInput;
    public WrkOutput refWrkOutput;
    public WrkDB refWrkDB;

    public Wrk() {
        this.refWrkKjunior = new WrkKJunior();
        lauchSocket();
    }

	/**
	 * Demarre le socket TCP.
	 */
    public void lauchSocket() {
        try {
            ServerSocket socketServer = new ServerSocket(2009);
            this.refWrkSocket = new WrkSocket(socketServer, this);
            this.refWrkSocket.start();
        } catch (IOException ex) {
            Logger.getLogger(Wrk.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

	/**
	 * Demarre le Thread WrkInput.
	 * 
	 * @param socket
	 */
    public void lauchWrkInput(Socket socket) {
        this.refWrkInput = new WrkInput(this, socket);
        this.refWrkInput.start();
    }

    public void finalize() throws Throwable {

    }

    /**
	 * Affiche un message dans la console de l'Ihm.
	 * 
	 * @param msg    msg
	 */
    public void afficheMessageConsole(String msg) {
        refCtrl.afficheMessageConsole(msg);
    }

    /**
	 * Affiche un popup dans l'Ihm lors d'une erreur.
	 * 
	 * @param error    error
	 */
    public void affichePopupError(String error) {

    }

    /**
	 * Affiche le statut du client dans l'Ihm.
	 * 
	 * @param client    client
	 */
    public void afficheStatutClient(boolean client) {

    }

    /**
	 * Ajoute un log dans la base de donnees.
	 * 
	 * @param log    log
	 */
    public void ajouteLog(String log) {

    }

    /**
	 * Envoie une commande au robot. Utilise pour commander le robot.
	 * 
	 * @param commande
	 */
    public void bougeLeRobot(String commande) {
        refWrkKjunior.commandeLeRobot(commande);
    }

	/**
	 * Ferme les threads.
	 */
    public void fermeLesThreads() {
        System.out.println("L'application ainsi que ses Threads se ferment");
        if (refWrkKjuniorCam != null) {
            refWrkKjuniorCam.setOn(false);
            refWrkKjuniorCam = null;
        }
        if (refWrkSocket != null) {
            refWrkSocket.setOn(false);
            refWrkSocket.closeSockets();
            refWrkSocket = null;
        }
        if (refWrkInput != null) {
            refWrkInput.setRead(false);
            refWrkInput = null;
        }
        System.gc();
    }

	/**
	 * Lance la capture. Elle demande au KJunior de bouger en cercle selon le rayon
	 * donne en parametre. Active egalement la camera du KJunior
	 * 
	 * @param rayon
	 */
    public void lanceCapture(double rayon) {

    }

    /**
	 * Stocke les images envoyer par la camera dans la BD.
	 * 
	 * @param stream    stream
	 */
    public void stockeImagesDB(InputStream stream) {

    }

    public void setRefCtrl(ItfCtrlWrk refCtrl) {
        this.refCtrl = refCtrl;
    }

	/**
	 * Affiche le statut du robot dans l'Ihm.
	 * 
	 * @param robot    client
	 */
	public void afficheStatutKJunior(boolean robot){

	}

}//end Wrk
