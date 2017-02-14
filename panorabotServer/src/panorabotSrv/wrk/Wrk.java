package panorabotSrv.wrk;
import panorabotSrv.ctrl.ItfCtrlWrk;
import panorabotSrv.wrk.ItfWrkCtrl;
import panorabotSrv.wrk.ItfWrkCtrl;
import panorabotSrv.wrk.WrkDB;
import panorabotSrv.wrk.WrkDB;
import panorabotSrv.wrk.WrkInput;
import panorabotSrv.wrk.WrkInput;
import panorabotSrv.wrk.WrkKJunior;
import panorabotSrv.wrk.WrkKJunior;
import panorabotSrv.wrk.WrkKjuniorCam;
import panorabotSrv.wrk.WrkKjuniorCam;
import panorabotSrv.wrk.WrkOutput;
import panorabotSrv.wrk.WrkOutput;
import panorabotSrv.wrk.WrkSocket;
import panorabotSrv.wrk.WrkSocket;

public class Wrk implements ItfWrkCtrl {

	public ItfCtrlWrk m_ItfCtrlWrk;
	public WrkKJunior m_WrkKJunior;
	public WrkKjuniorCam m_WrkKjuniorCam;
	public WrkSocket m_WrkSocket;
	public WrkInput m_WrkInput;
	public WrkOutput m_WrkOutput;
	public WrkDB m_WrkDB;

	public Wrk(){

	}

	public void finalize() throws Throwable {

	}
	/**
	 * 
	 * @param msg
	 */
	public void afficheMessageConsole(String msg){

	}

	/**
	 * 
	 * @param error
	 */
	public void affichePopupError(String error){

	}

	/**
	 * 
	 * @param client
	 */
	public void afficheStatutClient(boolean client){

	}

	/**
	 * 
	 * @param log
	 */
	public void ajouteLog(String log){

	}

	/**
	 * 
	 * @param moteurGauche
	 * @param moteurDroite
	 */
	public void bougeLeRobot(int moteurGauche, int moteurDroite){

	}

	public void fermeLesThreads(){

	}

	public void lanceCapture(){

	}

	/**
	 * 
	 * @param stream
	 */
	public void stockeImagesDB(InputStream stream){

	}
}//end Wrk