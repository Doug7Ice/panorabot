/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package openimaj_socket_server.wrk;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import openimaj_socket_server.ctrl.ItfCtrlWrk;

/**
 *
 * @author ReyL03
 */
public class Wrk implements ItfWrkCtrl{

    public Wrk() {
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
    private WrkSocket wrkSocket;
    private ItfCtrlWrk refCtrl;
    private ServerSocket socketServer;
    private Socket socket;
}
