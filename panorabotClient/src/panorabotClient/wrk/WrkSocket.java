/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panorabotClient.wrk;

import databeans.ImgCam;
import databeans.InfosLogin;
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
    }

    @Override
    public void run() {
        running = true;
        try {
            running = true;
            while (running) {
                Object receivedObject = in.readObject();
                if (receivedObject instanceof ImgCam) {
                    int[] tabInt = ((ImgCam) receivedObject).getImg();
                    MBFImage i = new MBFImage(tabInt, 320, 180);
                    BufferedImage bi = ImageUtilities.createBufferedImage(i);
                    refWrk.afficheImage(bi);
                } else if (receivedObject instanceof String) {
                    String receivedString = (String) receivedObject;
                    refWrk.resultLogin(receivedString);
//                    int[] tabInt = (int[]) in.readObject();
//                byte[] tabBytes = (byte[]) in.readObject();
//                ByteArrayInputStream bais = new ByteArrayInputStream(tabBytes);           
//                BufferedImage bi = ImageIO.read(bais);

                }
            }
        } catch (IOException e) {
            refWrk.afficheMessage("déconnexion", "error");
        } catch (ClassNotFoundException ex) {
            refWrk.afficheMessage("Erreur lors de la lecture du flux", "error");
        }

//        while(running){
//            
//        }
    }

    public void connecterSocket() {
        try {
            socket = new Socket(ipServer, 2009);
            //socket.setSoTimeout(5);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            refWrk.afficheMessage("Connexion au serveur effectuee !", "success");
        } catch (IOException ex) {
            refWrk.afficheMessage("Erreur de connexion avec le serveur !", "error");
        }

    }

    public void avancerRobotTCP(int vitesse) {
        try {
            out.writeObject("D," + vitesse + "," + vitesse);
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(WrkSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void stopperRobotTCP() {
        try {
            out.writeObject("D,0,0");
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(WrkSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void tournerADroiteTCP() {
        try {
            out.writeObject("D,14,-14");
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(WrkSocket.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void tournerAGaucheTCP() {
        try {
            out.writeObject("D,-14,14");
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(WrkSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void lancerScanTCP(double rayon) {
        try {
            out.writeObject("S," + rayon);
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(WrkSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void login(String user, String mdp) {
        try {
            out.writeObject(new InfosLogin(user, mdp));
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(WrkSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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
    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    private ItfWrkWrkSocket refWrk;
    private String actualPath;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private volatile boolean running;
    private Socket socket;
}
