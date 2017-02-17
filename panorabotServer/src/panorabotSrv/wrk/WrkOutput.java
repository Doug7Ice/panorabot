package panorabotSrv.wrk;

import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Envoie les images au client.
 * @author ReyL03
 * @version 1.0
 * @updated 17-fevr.-2017 14:54:37
 */
public class WrkOutput {

	private ObjectOutputStream out;
	private Socket socket;

	public WrkOutput(Socket socket){
            this.socket = socket;
	}

	public void finalize() throws Throwable {

	}
	/**
	 * Envoye les images aux clients.
	 * 
	 * @param array    array
	 */
	public void envoieLesImages(int[] array){

	}
}//end WrkOutput