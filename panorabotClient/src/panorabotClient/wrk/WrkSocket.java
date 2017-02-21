/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panorabotClient.wrk;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openimaj.image.ImageUtilities;
import org.openimaj.image.MBFImage;

/**
 *
 * @author Nathan
 */
public class WrkSocket extends Thread {
    private final String ipServer = "192.168.2.1";
    
    
    public WrkSocket(ItfWrkWrkSocket refWrk) {
        super("THREAD - WrkSocket");
        this.refWrk = refWrk;
        connecterSocket();
    }

    @Override
    public void run() {
        running = true;
        boolean tryConnect = true;
        while (running & tryConnect) {
            System.out.println("running " + running);
            refWrk.afficheMessage("Connexion au serveur en cours", "info");
            tryConnect = !connecterSocket();
            if (tryConnect == true) {
                refWrk.afficheMessage("La connexion au serveur a echoue, reessai dans 2 secondes...", "error");
                System.out.println("connexion impossible reessais dans 2 sec");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(WrkSocket.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        try {
            if (running) {
                in = new ObjectInputStream(socketKJunior.getInputStream());
                running = true;
                while (running) {
                    int[] tabInt = (int[]) in.readObject();
//                byte[] tabBytes = (byte[]) in.readObject();
//                ByteArrayInputStream bais = new ByteArrayInputStream(tabBytes);           
//                BufferedImage bi = ImageIO.read(bais);
                    MBFImage i = new MBFImage(tabInt, 320, 180);
                    BufferedImage bi = ImageUtilities.createBufferedImage(i);
                    refWrk.afficheImage(bi);
                }
            }
        } catch (IOException e) {
            refWrk.afficheMessage("déconnection", "error");
        } catch (ClassNotFoundException ex) {
            refWrk.afficheMessage("Erreur lors de la lecture du flux", "error");
        }

//        while(running){
//            
//        }
    }

    public boolean connecterSocket() {
        try {
            socketKJunior = new Socket(ipServer, 2009);
            outKJunior = new PrintWriter(socketKJunior.getOutputStream());
            socketLoginBD = new Socket(ipServer, 2017);
            outLogin = new PrintWriter(socketLoginBD.getOutputStream());
            socketReceiveScansBD = new Socket(ipServer, 2008);
            outScans = new PrintWriter(socketReceiveScansBD.getOutputStream());
            refWrk.afficheMessage("Connexion au serveur effectuee !", "success");
            return true;
        } catch (IOException ex) {
            return false;
        }

    }

    public void avancerRobotTCP(int vitesse) {
        outKJunior.println("D," + vitesse + "," + vitesse);
        outKJunior.flush();
    }

    public void stopperRobotTCP() {
        outKJunior.println("D,0,0");
        outKJunior.flush();
    }

    public void tournerADroiteTCP() {
        outKJunior.println("D,14,-14");
        outKJunior.flush();

    }

    public void tournerAGaucheTCP() {
        outKJunior.println("D,-14,14");
        outKJunior.flush();
    }

    public void lancerScanTCP(double rayon) {
        outKJunior.println("S," + rayon);
        outKJunior.flush();
    }
    
    public boolean login(String user, String mdp){
        boolean resultBool = false;
        try {
            inLogin = new BufferedReader(new InputStreamReader(socketLoginBD.getInputStream()));
            outLogin.println("L," + user + "," + mdp);
            String result = inLogin.readLine();
            if (result.equals("true")) {
                resultBool = true;
            }else{
                resultBool = false;
            }
        } catch (IOException ex) {
            refWrk.afficheMessage("Erreur de logon", "error");
        }
        return resultBool;
    }

    //Setters and Getters
    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    private ItfWrkWrkSocket refWrk;
    private String actualPath;
    private ObjectInputStream in;
    private PrintWriter outKJunior;
    private PrintWriter outLogin;
    private PrintWriter outScans;
    private volatile boolean running;
    private Socket socketKJunior;
    private Socket socketReceiveScansBD;
    private Socket socketLoginBD;
    private BufferedReader inLogin;
}
