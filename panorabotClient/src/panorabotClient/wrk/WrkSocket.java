/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panorabotClient.wrk;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nathan
 */
public class WrkSocket extends Thread {

    public WrkSocket(Wrk refWrk) {
        this.refWrk = refWrk;
        
    }

    @Override
    public void run() {
        running = true;
        running = false;
        boolean tryConnect = true;
        while (tryConnect) {
            refWrk.afficheMessage("Connexion au serveur en cours", "info");
            tryConnect = !connecterSocket();
            if (tryConnect == true) {
                refWrk.afficheMessage("La connexion au serveur a échoué, réessai dans 2 secondes...", "error");
                System.out.println("connexion impossible réessais dans 2 sec");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(WrkSocket.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
        }
        refWrk.afficheMessage("Connexion au serveur effectuée !", "success");
//        while(running){
//            
//        }
    }

    public boolean connecterSocket() {
        try {
            socket = new Socket("192.168.2.1", 2009);
            out = new PrintWriter(socket.getOutputStream());
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    public void avancerRobotTCP(int vitesse) {
        out.println("D," + vitesse + "," + vitesse);
        out.flush();
    }

    public void stopperRobotTCP() {
        out.println("D,0,0");
        out.flush();
    }

    public void tournerADroiteTCP() {
        out.println("D,14,-14");
        out.flush();

    }

    public void tournerAGaucheTCP() {
        out.println("D,-14,14");
        out.flush();
    }

    public void lancerScanTCP(double rayon) {
        out.println("S," + rayon);
        out.flush();
    }

    private Wrk refWrk;
    private String actualPath;
    private ObjectInputStream in;
    private PrintWriter out;
    private boolean running;
    private Socket socket;
}
