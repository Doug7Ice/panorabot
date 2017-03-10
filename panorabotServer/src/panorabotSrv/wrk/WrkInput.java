package panorabotSrv.wrk;

import databeans.InfosLogin;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Cette classe permet de gerer les communications envoyes par le client.
 *
 * @author ReyL03
 * @version 1.0
 * @updated 17-fevr.-2017 14:54:37
 */
public class WrkInput extends Thread {

    private ObjectInputStream in;
    private volatile boolean read;
    private Socket socket;
    private ItfWrkWrkInput refWrk;
    private volatile boolean captureEnCours;

    public WrkInput(Wrk wrk, Socket sock) {
        super("Input");
        this.refWrk = wrk;
        this.socket = sock;
        this.read = true;
        this.captureEnCours = false;
        try {
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            refWrk.affichePopupError(WrkInput.class.getName() + " : " + ex.getMessage());
        }
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
        refWrk.afficheMessageConsole(commande);
    }

    /**
     * Demande au robot de lancer la capture de l'objet en lui fournissant le
     * rayon de type double.
     *
     *
     * @param commande
     */
    private void lanceCapture(String commande) {
        String[] cmd = commande.split(",");
        //choper le rayon
        refWrk.lanceCapture(Double.parseDouble(cmd[1]),false);
    }
    private void lanceCapture(String commande,boolean test) {
        String[] cmd = commande.split(",");
        //choper le rayon
        refWrk.lanceCapture(Double.parseDouble(cmd[1]),test);
    }

    private void checkUser(InfosLogin infos) {
        String username = infos.getUsername();
        String pwd = infos.getPassword();
        refWrk.checkLogin(username, pwd);
    }

    private void envoiePhotosDeLaCaptureAuClient(String cmd) {
        refWrk.envoiePhotosDeLaCaptureAuClient();
    }

    private void gestionScan(boolean a) {
        refWrk.envoiDB(a);
        refWrk.gestionCam(a);
    }

    /**
     * Scanne les commandes envoye par le client.
     */
    public void run() {
        try {
            read = true;
            while (read) {
                Object objet = in.readObject();
                if (objet instanceof String) {
                    String cmd = (String) objet;
                    if (cmd.startsWith("D")) {
                        if (captureEnCours) {
                            refWrk.afficheMessageConsole("arret scan");
                            gestionScan(false);
                        }
                        bougeRobot(cmd);
                    } else if (cmd.startsWith("T")) {
                        lanceCapture(cmd,true);

                    } else if (cmd.startsWith("S")) {
                        if (!captureEnCours) {
                            lanceCapture(cmd);
                            gestionScan(true);
                            captureEnCours = true;
                            refWrk.afficheMessageConsole("lancement du scan");

                        } else {
                            gestionScan(captureEnCours);
                            //a faire
                        }
                        //Thread.sleep(10000);
                        // refWrk.sendTxtClient("S,stop");
                        //refWrk.arreteCapture();
                    } else if (cmd.startsWith("C")) {
                        refWrk.sendTxtClient("S,start");
                        gestionScan(false);
                        bougeRobot("D,0,0");
                        envoiePhotosDeLaCaptureAuClient(cmd);
                        refWrk.sendTxtClient("S,stop");
                    }

                }
                if (objet instanceof InfosLogin) {
                    InfosLogin infos = (InfosLogin) objet;
                    checkUser(infos);
                }
            }
        } catch (IOException e) {
            refWrk.affichePopupError("Deconnexion du client");
        } catch (ClassNotFoundException ex) {
            refWrk.affichePopupError(WrkInput.class.getName() + " : " + ex.getMessage());
        }
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

}//end WrkInput
