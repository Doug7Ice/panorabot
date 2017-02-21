package panorabotSrv.wrk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Cette classe permet de gerer les communications envoyes par le client.
 *
 * @author ReyL03
 * @version 1.0
 * @updated 17-fevr.-2017 14:54:37
 */
public class WrkInputCommands extends Thread {

    private BufferedReader inCommands;
    private volatile boolean read;
    private Socket socketSendCamAndReceive;
    
    private ItfWrkWrkInput refWrk;

    public WrkInputCommands(ItfWrkWrkInput wrk, Socket socketSendCamAndReceive) {
        super("InputCommands");
        this.refWrk = wrk;
        this.socketSendCamAndReceive = socketSendCamAndReceive;
        this.read = true;
    }

    public void finalize() throws Throwable {
        super.finalize();
    }

    /**
     * Demande au Wrk de bouger le robot.
     *
     * @param commande
     */
    private void bougeRobot(String commande) {
        refWrk.bougeLeRobot(commande);
    }

    /**
     * Demande au robot de lancer la capture de l'objet en lui fournissant le
     * rayon de type double.
     *
     * @param commande
     */
    private void lanceCapture(String commande) {
//double rayon
        refWrk.lanceCapture(0);
    }

    /**
     * Scanne les commandes envoye par le client.
     */
    public void run() {
        try {
            if (inCommands == null) {
                inCommands = new BufferedReader(new InputStreamReader(socketSendCamAndReceive.getInputStream()));
            }
            while (read) {
                String msg = inCommands.readLine();
                if (msg != null) {
                    refWrk.afficheMessageConsole(msg);
                    System.out.println("message envoyé : " + msg);
                    if (msg.startsWith("D")) {
                        bougeRobot(msg);
                    } else if (msg.startsWith("S")) {
                        lanceCapture(msg);
                    } else {
                        refWrk.afficheMessageConsole("YOURE SCREWD");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Déconnexion");
        }
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public Socket getSocketSendCamAndReceive() {
        return socketSendCamAndReceive;
    }

    public void setSocketSendCamAndReceive(Socket socketSendCamAndReceive) {
        this.socketSendCamAndReceive = socketSendCamAndReceive;
    }

   

}//end WrkInput
