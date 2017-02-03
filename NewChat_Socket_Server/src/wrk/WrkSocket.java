/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wrk;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Nathan
 */
public class WrkSocket extends Thread {

    public WrkSocket(ServerSocket socketServer, ItfWrkCtrl ctrl) {
        this.socketServer = socketServer;
        this.ctrl = ctrl;
        listSocket = new ArrayList<>();
    }

    public void launchSocket() {
        System.out.println("Lancement du socket ...");
        try {
            socketServer = new ServerSocket(2009);
            System.out.println("Socket Lancé, en attente de l'utilisateur...");
            socket = socketServer.accept();
            System.out.println("Utilisateur Connecté");
            System.out.println("Informations du socket : "
                    + socket.getLocalAddress()
                    + " " + socket.getLocalPort());
            socketServer.close();
            socket.close();

        } catch (IOException e) {

            System.out.println("Erreur rencontrée : " + e);

        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                ctrl.afficherMessage("En attente de l'utilisateur...");
                socket = socketServer.accept(); // Un client se connecte on l'accepte
                ctrl.afficherMessage("L'utilisateur numéro " + nbrUtilisateur + " est connecté !");
                nbrUtilisateur++;
                WrkSocketStream socketUser = new WrkSocketStream("user" + nbrUtilisateur,socket, ctrl, this);
                threadStreamUtilisateur = socketUser;
                listSocket.add(socketUser);
                threadStreamUtilisateur.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void diffuseMessage(String msg){
        for (WrkSocketStream t : listSocket) {
            t.diffuseMessage(msg);
        }
    }

    private Thread threadStreamUtilisateur;
    private ServerSocket socketServer;
    private Socket socket;
    private ArrayList<WrkSocketStream> listSocket;
    private int nbrUtilisateur = 1;
    private ItfWrkCtrl ctrl;
}
