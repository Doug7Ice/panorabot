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

	private ObjectOutputStream outCommandsAndWebcam;
        private ObjectOutputStream outImgFromBD;
        private PrintWriter outLogin;
	private Socket socketCommandsAndWebcam;
        private Socket socketImgFromBD;
        private Socket socketLogin;
        private ItfWrkWrkOutput refWrk;

	public WrkOutput(Socket socketCommandsAndWebcam,Socket socketImgFromBD,Socket socketLogin,ItfWrkWrkOutput wrk ){
            this.socketCommandsAndWebcam = socketCommandsAndWebcam;
            this.socketImgFromBD = socketImgFromBD;
            this.socketLogin = socketLogin;
            this.refWrk = wrk;
            try {
                
                outCommandsAndWebcam = new ObjectOutputStream(this.socketCommandsAndWebcam.getOutputStream());
                outImgFromBD = new ObjectOutputStream(this.socketImgFromBD.getOutputStream());
                outLogin = new PrintWriter(this.socketLogin.getOutputStream());
                //outLogin = new ObjectOutputStream(this.socketLogin.getOutputStream());
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
            outCommandsAndWebcam.writeObject(array);
            outCommandsAndWebcam.flush();
        } catch (IOException ex) {
            System.out.println("erreur" + ex);
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(WrkKJuniorCam.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
        
        public void envoieTxtClient(String msg){            
            outLogin.println(msg);
            outLogin.flush();
        }
}//end WrkOutput