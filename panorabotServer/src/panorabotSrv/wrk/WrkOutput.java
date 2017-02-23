package panorabotSrv.wrk;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Envoie les images au client.
 *
 * @author ReyL03
 * @version 1.0
 * @updated 17-fevr.-2017 14:54:37
 */
public class WrkOutput {

    private ObjectOutputStream outSendCam;
    private ObjectOutputStream outSendImgFromDB;
    private PrintWriter outSendTxt;
    private Socket socketSendCamAndReceive;
    private Socket socketSendImgFromDB;
    private Socket socketSendSQL;
    private ItfWrkWrkOutput refWrk;

    public WrkOutput(Socket socketSendCamAndReceive, Socket socketSendImgFromDB, Socket socketSendSQL, ItfWrkWrkOutput wrk) {
        this.socketSendCamAndReceive = socketSendCamAndReceive;
        this.socketSendImgFromDB = socketSendImgFromDB;
        this.socketSendSQL = socketSendSQL;
        this.refWrk = wrk;
        try {
            outSendCam = new ObjectOutputStream(this.socketSendCamAndReceive.getOutputStream());
            outSendImgFromDB = new ObjectOutputStream(this.socketSendImgFromDB.getOutputStream());
            outSendTxt = new PrintWriter(socketSendSQL.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(WrkOutput.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void finalize() throws Throwable {

    }

    /**
     * Envoye les images aux clients.
     *
     * @param array array
     */
    public void envoieLesImagesCam(int[] array) {
        try {
            outSendCam.writeObject(array);
            outSendCam.flush();
        } catch (IOException ex) {
            System.out.println("erreur" + ex);
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(WrkKJuniorCam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void envoieLesImagesBD(int[] array) {
        try {
            outSendImgFromDB.writeObject(array);
            outSendImgFromDB.flush();
        } catch (IOException ex) {
            System.out.println("erreur" + ex);
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(WrkKJuniorCam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendTxtClient(String msg) {
            outSendTxt.println(msg);
            outSendTxt.flush();
    }
}//end WrkOutput
