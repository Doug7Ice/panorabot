package panorabotSrv.wrk;

import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author ReyL03
 * @version 1.0
 * @created 14-févr.-2017 10:50:33
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
	 * 
	 * @param array
	 */
	public void envoieLesImages(int[] array){

	}
}//end WrkOutput