package panorabotSrv.wrk;

import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * @author ReyL03
 * @version 1.0
 * @created 14-févr.-2017 10:50:33
 */
public class WrkInput extends Thread {

	private ObjectInputStream in;
	private volatile boolean read;
	private Socket socket;
	public Wrk refWrk;

	public WrkInput(Wrk wrk){
            this.refWrk = wrk;
	}

	public void finalize() throws Throwable {
		super.finalize();
	}
	/**
	 * 
	 * @param moteurGauche
	 * @param moteurDroit
	 */
	private void bougeRobot(String commande){

	}

	private void lanceCapture(double rayon){

	}

	public void run(){

	}

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
        
        
}//end WrkInput