package panorabotSrv.wrk;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import panorabotSrv.ctrl.ItfCtrlWrk;
import panorabotSrv.wrk.ItfWrkCtrl;
import panorabotSrv.wrk.ItfWrkCtrl;
import panorabotSrv.wrk.WrkDB;
import panorabotSrv.wrk.WrkDB;
import panorabotSrv.wrk.WrkInput;
import panorabotSrv.wrk.WrkInput;
import panorabotSrv.wrk.WrkKJunior;
import panorabotSrv.wrk.WrkKJunior;
import panorabotSrv.wrk.WrkKjuniorCam;
import panorabotSrv.wrk.WrkKjuniorCam;
import panorabotSrv.wrk.WrkOutput;
import panorabotSrv.wrk.WrkOutput;
import panorabotSrv.wrk.WrkSocket;
import panorabotSrv.wrk.WrkSocket;

public class Wrk implements ItfWrkCtrl {

    public ItfCtrlWrk refCtrl;
    public WrkKJunior refWrkKjunior;
    public WrkKjuniorCam refWrkKjuniorCam;
    public WrkSocket refWrkSocket;
    public WrkInput refWrkInput;
    public WrkOutput refWrkOutput;
    public WrkDB refWrkDB;

    public Wrk() {
        lauchSocket();
    }

    public void lauchSocket() {
        try {
            ServerSocket socketServer = new ServerSocket(2009);
            this.refWrkSocket = new WrkSocket(socketServer, this);
            this.refWrkSocket.start();
        } catch (IOException ex) {
            Logger.getLogger(Wrk.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void lauchWrkInput(Socket socket) {
        this.refWrkInput = new WrkInput(this, socket);
        this.refWrkInput.start();
    }

    public void finalize() throws Throwable {

    }

    /**
     *
     * @param msg
     */
    public void afficheMessageConsole(String msg) {

    }

    /**
     *
     * @param error
     */
    public void affichePopupError(String error) {

    }

    /**
     *
     * @param client
     */
    public void afficheStatutClient(boolean client) {

    }

    /**
     *
     * @param log
     */
    public void ajouteLog(String log) {

    }

    /**
     *
     * @param moteurGauche
     * @param moteurDroite
     */
    public void bougeLeRobot(String commande) {

    }

    public void fermeLesThreads() {

    }

    public void lanceCapture(double rayon) {

    }

    /**
     *
     * @param stream
     */
    public void stockeImagesDB(InputStream stream) {

    }

    public void setRefCtrl(ItfCtrlWrk refCtrl) {
        this.refCtrl = refCtrl;
    }

}//end Wrk
