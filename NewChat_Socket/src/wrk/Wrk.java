/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wrk;

import ctrl.Ctrl;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Nathan
 */
public class Wrk {

    public Wrk() {
        ouvrirPort();
        wrkWrite = new EcrireMessageWrk(this.socket, this);
        wrkRead = new LireMessageWrk(this.socket,this, "Thread LireMessageWrk");
        wrkRead.start();
    }

    public void ouvrirPort() {
        try {
            this.socket = new Socket("192.168.2.1", 2009);
        } catch (UnknownHostException e) {

            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void envoyerMsg(String msg) {
        wrkWrite.writeMessage(msg);
    }

    public void showMessage(String msg) {
        refCtrl.msgAfficher(msg);
    }

    public void fermer() {
        try {
            wrkRead.setIsReading(false);
            socket.close();
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    //SETTERS AND GETTERS
    public EcrireMessageWrk getWrkWrite() {
        return wrkWrite;
    }

    public void setWrkWrite(EcrireMessageWrk wrkWrite) {
        this.wrkWrite = wrkWrite;
    }

    public LireMessageWrk getWrkRead() {
        return wrkRead;
    }

    public void setWrkRead(LireMessageWrk wrkRead) {
        this.wrkRead = wrkRead;
    }

    public Ctrl getRefCtrl() {
        return refCtrl;
    }

    public void setRefCtrl(Ctrl refCtrl) {
        this.refCtrl = refCtrl;
    }

    //VARIABLES
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private EcrireMessageWrk wrkWrite;
    private LireMessageWrk wrkRead;
    private Ctrl refCtrl;
}
