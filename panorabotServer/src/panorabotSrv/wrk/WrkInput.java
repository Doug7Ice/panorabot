package panorabotSrv.wrk;

/**
 * @author ReyL03
 * @version 1.0
 * @created 14-févr.-2017 10:50:33
 */
public class WrkInput extends Thread {

	private ObjectInputStream in;
	private volatile boolean read;
	private Socket socket;
	public Wrk m_Wrk;

	public WrkInput(){

	}

	public void finalize() throws Throwable {
		super.finalize();
	}
	/**
	 * 
	 * @param moteurGauche
	 * @param moteurDroit
	 */
	public void bougeRobot(int moteurGauche, int moteurDroit){

	}

	public void lanceCapture(){

	}

	public void run(){

	}
}//end WrkInput