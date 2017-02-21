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
 *
 * @author ReyL03
 * @version 1.0
 * @updated 17-fevr.-2017 14:54:37
 */
public class Wrk implements ItfWrkCtrl, ItfWrkWrkDB, ItfWrkWrkInput, ItfWrkWrkKJunior, ItfWrkWrkKJuniorCam, ItfWrkWrkOutput, ItfWrkWrkSocket {

    private ItfCtrlWrk refCtrl;
    private WrkKJunior refWrkKjunior;
    private WrkKJuniorCam refWrkKjuniorCam;
    private WrkSocket refWrkSocket;
    private WrkInputCommands refWrkInputCommand;
    private WrkInputSQL refWrkInputSQL;
    private WrkOutput refWrkOutput;
    private WrkDB refWrkDB;

    public Wrk() {
        this.refWrkKjunior = new WrkKJunior(this);
        lauchSocket();
    }

    /**
     * Demarre le socket TCP.
     */
    public void lauchSocket() {
        try {
            ServerSocket socketServerSendCamAndReceive = new ServerSocket(2009);
            ServerSocket socketServerSendImgFromDB = new ServerSocket(2008);
            ServerSocket socketServerSQLogin = new ServerSocket(2017);
            this.refWrkSocket = new WrkSocket(socketServerSendCamAndReceive,socketServerSendImgFromDB,socketServerSQLogin, this);
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
    @Override
    public void lauchWrkInput(Socket socketSendCamAndReceive,Socket socketSQLogin) {
        if (refWrkInput == null) {
            this.refWrkInput = new WrkInputCommands(this, socketSendCamAndReceive);
            this.refWrkInput.start();
            this.refWrkInput.setRead(true);
        } else if (refWrkInput.isAlive()) {
            this.refWrkInput.setRead(true);
        } else {
            this.refWrkInput.start();
            this.refWrkInput.setRead(true);
        }
        
        
    }

    @Override
    public void lauchWrkOutput(Socket socketSendCamAndReceive,Socket socketSendImgFromDB) {
        this.refWrkOutput = new WrkOutput(socketSendCamAndReceive,socketSendImgFromDB, this);
    }

    public void finalize() throws Throwable {

    }

    /**
     * Affiche un message dans la console de l'Ihm.
     *
     * @param msg msg
     */
    @Override
    public void afficheMessageConsole(String msg) {
        refCtrl.afficheMessageConsole(msg);
    }

    /**
     * Affiche un popup dans l'Ihm lors d'une erreur.
     *
     * @param error error
     */
    @Override
    public void affichePopupError(String error) {

    }

    /**
     * Affiche le statut du client dans l'Ihm.
     *
     * @param client client
     */
    @Override
    public void afficheStatutClient(boolean client) {
        refCtrl.afficheStatutConnectionClient(client);
    }

    /**
     * Ajoute un log dans la base de donnees.
     *
     * @param log log
     */
    public void ajouteLog(String log) {

    }

    /**
     * Envoie une commande au robot. Utilise pour commander le robot.
     *
     * @param commande
     */
    @Override
    public void bougeLeRobot(String commande) {
        refWrkKjunior.commandeLeRobot(commande);
        this.lanceCapture(0);
    }

    /**
     * Ferme les threads.
     */
    @Override
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
     * Lance la capture. Elle demande au KJunior de bouger en cercle selon le
     * rayon donne en parametre. Active egalement la camera du KJunior
     *
     * @param rayon
     */
    @Override
    public void lanceCapture(double rayon) {

    }

    /**
     * Stocke les images envoyer par la camera dans la BD.
     *
     * @param stream stream
     */
    @Override
    public void stockeImagesDB(InputStream stream) {

    }

    public void setRefCtrl(ItfCtrlWrk refCtrl) {
        this.refCtrl = refCtrl;
    }

    /**
     * Affiche le statut du robot dans l'Ihm.
     *
     * @param robot client
     */
    @Override
    public void afficheStatutKJunior(boolean robot) {
        refCtrl.afficheStatutConnectionRobot(robot);
    }

    @Override
    public void sendWebcam(int[] arrayImg) {
        if (refWrkOutput == null) {
            refWrkOutput = new WrkOutput(refWrkSocket.getSocketCamAndReceive(),refWrkSocket.getSocketSendImgFromDB(), this);
        }
        refWrkOutput.envoieLesImagesCam(arrayImg);
    }

    @Override
    public void showWebcam() {
        if (refWrkKjuniorCam == null) {
            this.refWrkKjuniorCam = new WrkKJuniorCam(this);
        }
        if (refWrkKjuniorCam.isAlive()) {
            refWrkKjuniorCam.setOn(true);
        } else {
            this.refWrkKjuniorCam.start();
            refWrkKjuniorCam.setOn(true);
        }

    }

    @Override
    public void closeWebcam() {
        if (refWrkKjuniorCam != null) {
            refWrkKjuniorCam.setOn(false);
        }
    }

    @Override
    public void closeWrkInput() {
        if (refWrkInput != null) {
            refWrkInput.setRead(false);
        }
    }

}//end Wrk
