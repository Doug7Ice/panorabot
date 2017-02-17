package panorabotSrv.wrk;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Sous-worker permettant de controller la camera du KJunior.
 * @author ReyL03
 * @version 1.0
 * @updated 17-fevr.-2017 14:54:37
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

	/**
	 * Recpetionne les images provenant de la camera du KJunior.
	 */
    public void run() {

    }

	/**
	 * Envoie les images au Wrk afin qu'elles soient stocke dans la DB.
	 * 
	 * @param intArr
	 */
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
