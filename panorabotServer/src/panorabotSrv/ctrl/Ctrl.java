package panorabotSrv.ctrl;
import panorabotSrv.ihm.ItfIhmCtrl;
import panorabotSrv.wrk.ItfWrkCtrl;

/**
 * @author ReyL03
 * @version 1.0
 * @created 14-f�vr.-2017 10:50:33
 */
public class Ctrl implements ItfCtrlIhm, ItfCtrlWrk {

	public ItfIhmCtrl refIhm;
	public ItfWrkCtrl refWrk;

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
            refIhm.afficheMessageConsole(msg);
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

    public void setRefIhm(ItfIhmCtrl refIhm) {
        this.refIhm = refIhm;
    }

    public void setRefWrk(ItfWrkCtrl refWrk) {
        this.refWrk = refWrk;
    }
        
}//end Ctrl