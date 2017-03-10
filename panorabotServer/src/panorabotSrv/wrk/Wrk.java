package panorabotSrv.wrk;

import databeans.ImgCam;
import databeans.ImgCapture;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
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
    private WrkInput refWrkInput;
    private WrkOutput refWrkOutput;
    private WrkDB refWrkDB;
    private boolean dejaFait;

    public Wrk() {
        this.refWrkKjunior = new WrkKJunior(this);
        this.refWrkDB = new WrkDB();
        dejaFait = false;
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
            affichePopupError(Wrk.class.getName()+" : "+ex.getMessage());
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

    /**
     * Creer le wrk output.
     *
     * @param socket
     */
    public void lauchWrkOutput(Socket socket) {
        this.refWrkOutput = new WrkOutput(socket, this);
    }

    public void finalize() throws Throwable {

    }

    /**
     * Affiche un message dans la console de l'Ihm.
     *
     * @param msg msg
     */
    public void afficheMessageConsole(String msg) {
        refCtrl.afficheMessageConsole(msg);
    }

    /**
     * Affiche un popup dans l'Ihm lors d'une erreur.
     *
     * @param error error
     */
    public void affichePopupError(String error) {
        refCtrl.affichePopupError(error);
    }

    /**
     * Affiche le statut du client dans l'Ihm.
     *
     * @param client client
     */
    public void afficheStatutClient(boolean client) {
        refCtrl.afficheStatutConnectionClient(client);
    }

    /**
     * Verifie l'identifiant et le mot de passe fournis par l'user. Si
     * l'username et le mot de passe correspond à celui stocke dans la DB, la
     * methode envoie "Login:OK" au client. Sinon elle envoie "Login:KO".
     *
     * Dans le cas ou l'acces a la DB est impossible, la methode envoie
     * "Login:DBout".
     *
     * @param username
     * @param password
     */
    public void checkLogin(String username, String password) {
        int ok = refWrkDB.userConnection(username, password);
        if (ok == 1) {
            refWrkOutput.envoie("Login:OK");
        } else if (ok == 0) {
            refWrkOutput.envoie("Login:KO");
        } else {
            refWrkOutput.envoie("Login:DBout");
        }
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
    public void bougeLeRobot(String commande) {
        refWrkKjunior.commandeLeRobot(commande);
    }

    /**
     * Ferme les tous les threads. Ferme également les acces DB.
     */
    public void fermeLesThreads() {
        System.out.println("L'application ainsi que ses Threads se ferment");
        if (refWrkKjuniorCam != null) {
            refWrkKjuniorCam.setOn(false);
            refWrkKjuniorCam = null;
        }
        if (refWrkDB != null) {
            refWrkDB.close();
        }
        if (refWrkInput != null) {
            refWrkInput.setRead(false);
            refWrkInput = null;
        }
        if (refWrkSocket != null) {
            refWrkSocket.setOn(false);
            refWrkSocket.closeSockets();
            refWrkSocket = null;
        }
        System.gc();
    }

    /**
     * Lance la capture. Elle demande au WrkKJunior de faire bouger le robot en
     * cercle selon le rayon donne en parametre. Demande egalement a
     * WrkKjuniorCam d'envoyer les photos prisent à partir de maintenant dans la
     * DB.
     *
     * @param rayon
     * @param test
     */
    @Override
    public void lanceCapture(double rayon,boolean test) {
        int moteurGauche = (int) rayon * 20 / 60;
        int moteurDroite = (int) (rayon + 10) * 20 / 60;

        String commande = "D," + moteurGauche + "," + moteurDroite;
        refWrkKjunior.commandeLeRobot(commande);
        System.out.println(commande);
        if (!test){
        refWrkKjuniorCam.setSendDB(true);
        refCtrl.afficheMessageConsole("Pas un test");
        }
    }

    /**
     * Stocke les images envoye par la camera dans la BD.
     *
     * @param stream stream
     */
    public void stockeImagesDB(InputStream stream) {

        if (!dejaFait) {
            refWrkDB.putCapture();
            dejaFait = true;
        }
        refWrkDB.putPhoto(stream);
    }

    public void setRefCtrl(ItfCtrlWrk refCtrl) {
        this.refCtrl = refCtrl;
    }

    /**
     * Affiche le statut du robot dans l'Ihm.
     *
     * @param robot client
     */
    public void afficheStatutKJunior(boolean robot) {
        refCtrl.afficheStatutConnectionRobot(robot);
    }

    /**
     * Envoie les images de la webcam en "temps reel" au client.
     *
     * @param cam
     */
    public void sendWebcam(ImgCam cam) {
        if (refWrkOutput == null) {
            refWrkOutput = new WrkOutput(refWrkSocket.getSocket(), this);
        }
        refWrkOutput.envoie(cam);
    }

    @Override
    /**
     * Cette methode demande a la base de donnees de lui fournir les images
     * prisent lors de la derniere capture et les envoies au client.
     */
    public void envoiePhotosDeLaCaptureAuClient() {
        ArrayList<ImgCapture> lesPhotos = refWrkDB.getImages(0, 0);
        for (ImgCapture photo : lesPhotos) {
            refWrkOutput.envoie(photo);
        }
    }

    /**
     * Arrete la reception des images du KJunior afin de ne pas surcharger le
     * reseau.
     */
    public void gestionCam(boolean a) {
        if (!a) {
            refWrkKjuniorCam.setSendDB(false);
            refWrkKjuniorCam.setOn(false);
        } else {
            refWrkKjuniorCam.setSendDB(true);
            refWrkKjuniorCam.setOn(true);
        }
    }

    /**
     * Methode permettant d'envoyer un string au client.
     *
     * @param txt
     */
    public void sendTxtClient(String txt) {
        refWrkOutput.envoie(txt);
    }

    /**
     * Demarre le wkr de la camera afin d'envoyer au client les images.
     */
    @Override
    public void showWebcam() {
        if (refWrkKjuniorCam == null) {
            this.refWrkKjuniorCam = new WrkKJuniorCam(this);
        }
        this.refWrkKjuniorCam.start();
    }

    @Override
    public boolean isUserConnected() {
        boolean ok = false;
        String user = refWrkDB.getUsernameConnecte();
        if (user != null) {
            ok = true;
        }
        return ok;
    }

    @Override
    public void envoiDB(boolean a) {
        refWrkKjuniorCam.setSendDB(a);
    }

}//end Wrk
