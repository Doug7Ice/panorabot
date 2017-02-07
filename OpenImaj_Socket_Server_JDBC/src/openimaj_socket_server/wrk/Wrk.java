/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package openimaj_socket_server.wrk;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
import openimaj_socket_server.ctrl.ItfCtrlWrk;

/**
 *
 * @author ReyL03
 */
public class Wrk implements ItfWrkCtrl {

    public Wrk() {
        this.refWrkDB = new WrkDB();
    }

    @Override
    public void launchSocket() {
        System.out.println("Lancement du socket ...");
        try {
            socketServer = new ServerSocket(2009);
            wrkSocket = new WrkSocket(socketServer, refCtrl, this);
            wrkSocket.start();
        } catch (IOException e) {

            System.out.println("Erreur rencontrée : " + e);

        }
    }

    public void toDB(BufferedImage bi) {
        refWrkDB.toDB(bi);
    }

    public WrkSocket getWrkSocket() {
        return wrkSocket;
    }

    public void setWrkSocket(WrkSocket wrkSocket) {
        this.wrkSocket = wrkSocket;
    }

    public ItfCtrlWrk getRefCtrl() {
        return refCtrl;
    }

    public void setRefCtrl(ItfCtrlWrk refCtrl) {
        this.refCtrl = refCtrl;
    }

    @Override
    public void close() {
        System.out.println("On ferme !");
        try {
            wrkSocket.setReadStreamThread(false);
            wrkSocket.setRead(false);
            wrkSocket.join();
            wrkSocket = null;
            System.gc();
        } catch (InterruptedException ex) {
            Logger.getLogger(Wrk.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private WrkSocket wrkSocket;
    private ItfCtrlWrk refCtrl;
    private ServerSocket socketServer;
    private WrkDB refWrkDB;
}
