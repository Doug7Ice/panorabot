package panorabotSrv.ctrl;
import panorabotSrv.ihm.ItfIhmCtrl;
import panorabotSrv.wrk.ItfWrkCtrl;

/**
 * Controller de l'application Panorabot Serveur
 * @author ReyL03
 * @version 1.0
 * @updated 17-fevr.-2017 14:54:36
 */
public class Ctrl implements ItfCtrlIhm, ItfCtrlWrk {

	public ItfIhmCtrl refIhm;
	public ItfWrkCtrl refWrk;

	public Ctrl(){

	}

	public void finalize() throws Throwable {

	}
	/**
	 * Ferme les threads.
	 */
	public void fermeLesThreads(){
            refWrk.fermeLesThreads();
	}

	/**
	 * Affiche un message dans la console de l'Ihm.
	 * 
	 * @param msg    msg
	 */
	public void afficheMessageConsole(String msg){
            refIhm.afficheMessageConsole(msg);
	}

	/**
	 * Affiche une erreur dans un popup.
	 * 
	 * @param error    error
	 */
	public void affichePopupError(String error){

	}

	/**
	 * Affiche statut de la connexion client dans l'Ihm.
	 * 
	 * @param statutClient    statutClient
	 */
	public void afficheStatutConnectionClient(boolean statutClient){
            refIhm.afficheStatutConnectionClient(statutClient);
	}

	/**
	 * Affiche statut de la connexion client dans le robot.
	 * 
	 * @param statutRobot    statutRobot
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