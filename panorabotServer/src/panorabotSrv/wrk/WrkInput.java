package panorabotSrv.wrk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * @author ReyL03
 * @version 1.0
 * @created 14-févr.-2017 10:50:33
 */
public class WrkInput extends Thread {

	private BufferedReader in;
	private volatile boolean read;
	private Socket socket;
	public Wrk refWrk;

	public WrkInput(Wrk wrk,Socket sock){
            this.refWrk = wrk;
            this.socket = sock;
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
            try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true) {
                String msg = in.readLine();
                if (msg != null) {
                    System.out.println("message envoyé : " + msg);
                }
            }
        } catch (IOException e) {
                System.out.println("Erreur");
        }
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