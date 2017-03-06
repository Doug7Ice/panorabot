package panorabotSrv.wrk;

import databeans.InfosLogin;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openimaj.image.ImageUtilities;
import org.openimaj.image.MBFImage;

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

    public WrkInput(Wrk wrk, Socket sock) {
        super("Input");
        this.refWrk = wrk;
        this.socket = sock;
        this.read = true;
        try {
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(WrkInput.class.getName()).log(Level.SEVERE, null, ex);
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
        refWrk.lanceCapture(Double.parseDouble(cmd[1]));
    }

    private void checkUser(InfosLogin infos) {
        String username = infos.getUsername();
        String pwd = infos.getPassword();
        refWrk.checkLogin(username,pwd);
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
                    if (cmd.startsWith("D")){
                        bougeRobot(cmd);
                    }
                    else if (cmd.startsWith("S")){
                        lanceCapture(cmd);
                        refWrk.afficheMessageConsole("lancement du scan");
                        refWrk.sendTxtClient("S,start");
                    }
                    
                }
                if (objet instanceof InfosLogin) {
                    InfosLogin infos = (InfosLogin) objet;
                    checkUser(infos);
                }
            }
        } catch (IOException e) {
            refWrk.afficheMessageConsole("déconnection");
        } catch (ClassNotFoundException ex) {
            refWrk.afficheMessageConsole("Erreur lors de la lecture du flux tabarnak");
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
