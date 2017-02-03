/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wrk;

import ctrl.Ctrl;
import ctrl.ItfCtrlWrk;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Nathan
 */
public class Wrk implements ItfCtrlWrk {

    public Wrk(Ctrl refCtrl) {
        this.refCtrl = refCtrl;
    }

    public void launchSocket2() {
        refCtrl.afficherMessage("Lancement du socket ...");
        System.out.println("Lancement du socket ...");
        try {
            socketServer = new ServerSocket(2009);
            System.out.println("Socket Lancé, en attente de l'utilisateur...");
            socket = socketServer.accept();
            System.out.println("Utilisateur Connecté");
            System.out.println("Informations du socket : " +
                    socket.getLocalAddress()+
                    " " + socket.getLocalPort());
            socketServer.close();
            socket.close();

        } catch (IOException e) {

            System.out.println("Erreur rencontrée : " + e);

        }
    }
    @Override
    public void launchSocket() {
        System.out.println("Lancement du socket ...");
        try {
            socketServer = new ServerSocket(2009);
            Thread t = new WrkSocket(socketServer, refCtrl);
            t.start();
        } catch (IOException e) {

            System.out.println("Erreur rencontrée : " + e);

        }
    }

    private WrkSocket wrkSocket;
    private ItfWrkCtrl refCtrl;
    private ServerSocket socketServer;
    private Socket socket;

}
