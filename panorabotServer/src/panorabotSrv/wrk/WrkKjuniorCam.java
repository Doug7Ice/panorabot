package panorabotSrv.wrk;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author ReyL03
 * @version 1.0
 * @created 14-févr.-2017 10:50:33
 */
public class WrkKjuniorCam extends Thread {

    private ObjectOutputStream out;
    private volatile boolean on;
    public Wrk refWrk;

    public WrkKjuniorCam(Wrk wrk) {
        super("KjuniorCam");
        this.refWrk = wrk;
    }

    public void finalize() throws Throwable {
        super.finalize();
    }

    public void run() {

    }

    private void sendPrintScreen(int[] intArr) {
        try {
            out.writeObject(intArr);
            out.flush();
        } catch (IOException ex) {
            System.out.println("erreur" + ex);
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(WrkKjuniorCam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }
    
    
}//end WrkKjuniorCam
