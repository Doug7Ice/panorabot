package panorabotClient.wrk;

import databeans.ImgCam;
import databeans.ImgCapture;
import databeans.InfosLogin;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import javax.imageio.ImageIO;
import org.openimaj.image.ImageUtilities;
import org.openimaj.image.MBFImage;

/**
 * Classe threadée s'occupant d'envoyer et de recevoir les informations 
 * sur le réseau (TCP).
 * @author Nathan Canzali
 */
public class WrkTransmission extends Thread {
    
    private final String ipServer = "192.168.2.1";

    /**
     * Constructeur de WrkSocket.
     * @param refWrk le worker que WrkSocket apellera.
     */
    public WrkTransmission(ItfWrkWrkSocket refWrk) {
        super("THREAD - WrkSocket");
        this.refWrk = refWrk;
    }
    
    /**
     * Initialise le Socket au port 2009 ainsi que les ObjectInputStream et 
     * ObjectInputStream pour pouvoir envoyer et reçevoir des informations en
     * TCP.
     */
    public void connecterSocket() {
        try {
            socket = new Socket(ipServer, 2009);
            socket.setSoTimeout(7000);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            stopperRobotTCP();
            refWrk.afficheMessage("Connexion au serveur effectuee !", "success");
        }
        catch (SocketTimeoutException ste){
            refWrk.afficheMessage("Timeout atteint, connexion avec le serveur impossible", "error");
        }catch (IOException ex) {
            refWrk.afficheMessage("Erreur de connexion avec le serveur !", "error");
        } 

    }

    @Override
    /**
     * Classe run héritée de la classe Thread. Elle s'occupe de recevoir un 
     * objet via un ObjectInputStream (TCP) puis le traite en fonction de 
     * la classe dont il est l'instance.
     */
    public void run() {
        running = true;
        try {
            while (running) {
                Object receivedObject = in.readObject();
                if (receivedObject instanceof ImgCam) {
                    int[] tabInt = ((ImgCam) receivedObject).getImg();
                    MBFImage i = new MBFImage(tabInt, 1280, 720);
                    BufferedImage bi = ImageUtilities.createBufferedImage(i);
                    refWrk.afficheImage(bi);
                } else if (receivedObject instanceof String) {

                    String receivedString = (String) receivedObject;
                    if (receivedString.startsWith("Login")) {
                        refWrk.resultLogin(receivedString);
                    } else if (receivedString.startsWith("S")) {
                        traiteStringScan(receivedString);
                    }
                } else if (receivedObject instanceof ImgCapture) {
                    InputStream in = new ByteArrayInputStream(((ImgCapture) receivedObject).getImg());
                    BufferedImage bImageFromConvert = ImageIO.read(in);
                    refWrk.sauverImageCapture(bImageFromConvert);

                }
            }
        } catch (SocketException decoException) {
            System.out.println("ON A KILL LE SOCKET");
        } catch (IOException e) {
            refWrk.afficheMessage("déconnexion", "error");
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            refWrk.afficheMessage("Erreur lors de la lecture du flux", "error");
        }
    }

    /**
     * Méthode private s'occupant de traiter le String reçu du serveur. Si le
     * serveur envoie un String "S, start" cela signifie que le robot a commencé
     * un scan, s'il envoie un String S, stop" cela signifie que le robot a fini
     * le scan.
     * @param receivedString le String reçu du serveur et commençant par "S".
     */
    private void traiteStringScan(String receivedString) {
        String[] tabString = receivedString.split(",");
        if (tabString.length == 2) {
            if (tabString[1].equals("start")) {
                refWrk.bloquerScan(true);
            } else if (tabString[1].equals("stop")) {
                refWrk.bloquerScan(false);
            }
        }
    } 

    /**
     * Utilise l'ObjectOutputStream pour demander au serveur de faire avancer le
     * robot (via TCP).
     * @param vitesse la vitesse à laquelle le robot doit avancer (peut être
     * négative).
     */
    public void avancerRobotTCP(int vitesse) {
        try {
            out.writeObject("D," + vitesse + "," + vitesse);
            out.flush();
        } catch (IOException ex) {
            refWrk.afficheMessage(ex.toString(), "error");
        }
    }

    /**
     * Utilise l'ObjectOutputStream pour demander au serveur de stopper le
     * robot (via TCP).
     */
    public void stopperRobotTCP() {
        try {
            out.writeObject("D,0,0");
            out.flush();
        } catch (IOException ex) {
            refWrk.afficheMessage(ex.toString(), "error");
        }
    }

    /**
     * Utilise l'ObjectOutputStream pour demander au serveur de faire tourner à 
     * droite le robot (via TCP).
     */
    public void tournerADroiteTCP() {
        try {
            out.writeObject("D,14,-14");
            out.flush();
        } catch (IOException ex) {
            refWrk.afficheMessage(ex.toString(), "error");
        }

    }

    /**
     * Utilise l'ObjectOutputStream pour demander au serveur de faire tourner à 
     * gauche le robot (via TCP).
     */
    public void tournerAGaucheTCP() {
        try {
            out.writeObject("D,-14,14");
            out.flush();
        } catch (IOException ex) {
            refWrk.afficheMessage(ex.toString(), "error");
        }
    }

    /**
     * Utilise l'ObjectOutputStream pour demander au serveur de lancer le scan 
     * (via TCP). 
     * @param rayon le rayon du scan.
     */
    public void lancerScanTCP(double rayon) {
        try {
            out.writeObject("S," + rayon);
            out.flush();
        } catch (IOException ex) {
            refWrk.afficheMessage(ex.toString(), "error");
        }
    }
    
    /**
     * Utilise l'ObjectOutputStream pour demander au serveur de lancer un test 
     * de rayon du scan (via TCP). 
     * @param rayon le rayon du test.
     */
    public void lancerTestRayonTCP(int rayon) {
        try {
            out.writeObject("T," + rayon);
            out.flush();
        } catch (IOException ex) {
            refWrk.afficheMessage(ex.toString(), "error");
        }
    }

    /**
     * Utilise l'ObjectOutputStream pour demander au serveur de connecter un 
     * utilisateur grâce à un Bean InfoLogin (via TCP). 
     * @param user le username.
     * @param mdp le mot de passe.
     */
    public void login(String user, String mdp) {
        try {
            out.writeObject(new InfosLogin(user, mdp));
            out.flush();
        } catch (IOException ex) {
            refWrk.afficheMessage(ex.toString(), "error");
        }
    }

    /**
     * Utilise l'ObjectOutputStream pour demander au serveur de récupérer les 
     * images du scan (via TCP).
     */
    public void recupererImagesScanTCP() {
        try {
            out.writeObject("C");
            out.flush();
        } catch (IOException ex) {
            refWrk.afficheMessage(ex.toString(), "error");
        }
    }

    /**
     * Méthode appelée lorsque l'application doit quitter, elle ferme les 
     * InputStream et Outputstream ainsi que le socket.
     */
    public void quit() {
        try {
            System.out.println("IL KILL LE SOCKET MON DIEU");
            running = false;
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
            if (socket != null) {
                socket.close();
            }

        } catch (IOException ingnoredException) {

        }
    }
    
    //Setters and Getters
    /**
     * Retourne la valeur du boolean running.
     * @return la valeur du boolean running.
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Change la valeur du boolean running.
     * @param running la nouvelle valeur du boolean running.
     */
    public void setRunning(boolean running) {
        this.running = running;
    }

    private ItfWrkWrkSocket refWrk;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private volatile boolean running;
    private Socket socket;

}
