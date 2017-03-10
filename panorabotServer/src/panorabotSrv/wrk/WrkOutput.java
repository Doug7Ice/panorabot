package panorabotSrv.wrk;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
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

	public WrkOutput(Socket socket,ItfWrkWrkOutput wrk){
            this.socket = socket;
            this.refWrk = wrk;
            try {
                
                out = new ObjectOutputStream(this.socket.getOutputStream());
            } catch (IOException ex) {
                refWrk.affichePopupError(WrkOutput.class.getName()+" : "+ex.getMessage());
            }
	}

	public void finalize() throws Throwable {

	}
	/**
	 * Envoye les images aux clients.
	 * 
	 * @param array    array
	 */
	public void envoie(Object object){
            try {
            out.writeObject(object);
            out.flush();
        } catch (IOException ex) {
             refWrk.affichePopupError(WrkOutput.class.getName()+" : "+ex.getMessage());
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
             refWrk.affichePopupError(WrkOutput.class.getName()+" : "+ex.getMessage());
        }
	}
}//end WrkOutput