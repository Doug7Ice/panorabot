package panorabotSrv.wrk;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Envoie les images au client.
 * @author ReyL03
 * @version 1.0
 * @updated 17-fevr.-2017 14:54:37
 */
public class WrkOutput {

	private ObjectOutputStream out;
	private Socket socket;
        private ItfWrkWrkOutput refWrk;

	public WrkOutput(Socket socket,ItfWrkWrkOutput wrk ){
            this.socket = socket;
            this.refWrk = wrk;
            try {
                out = new ObjectOutputStream(this.socket.getOutputStream());
            } catch (IOException ex) {
                Logger.getLogger(WrkOutput.class.getName()).log(Level.SEVERE, null, ex);
            }
	}

	public void finalize() throws Throwable {

	}
	/**
	 * Envoye les images aux clients.
	 * 
	 * @param array    array
	 */
	public void envoieLesImages(int[] array){
            try {
            out.writeObject(array);
            out.flush();
        } catch (IOException ex) {
            System.out.println("erreur" + ex);
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(WrkKJuniorCam.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
}//end WrkOutput