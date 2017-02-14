package panorabotSrv.ctrl;
import panorabotSrv.ihm.ItfIhmCtrl;
import panorabotSrv.wrk.ItfWrkCtrl;

/**
 * @author ReyL03
 * @version 1.0
 * @created 14-f�vr.-2017 10:50:33
 */
public class Ctrl implements ItfCtrlIhm, ItfCtrlWrk {

	public ItfIhmCtrl m_ItfIhmCtrl;
	public ItfWrkCtrl m_ItfWrkCtrl;

	public Ctrl(){

	}

	public void finalize() throws Throwable {

	}
	public void fermeLesThreads(){

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
	 * @param statutClient
	 */
	public void afficheStatutConnectionClient(boolean statutClient){

	}

	/**
	 * 
	 * @param statutRobot
	 */
	public void afficheStatutConnectionRobot(boolean statutRobot){

	}
}//end Ctrl