package panorabotSrv.ihm;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import panorabotSrv.ctrl.Ctrl;
import panorabotSrv.ctrl.ItfCtrlIhm;
import panorabotSrv.wrk.Wrk;

/**
 * Ihm pour l'application Panorabot version serveur. Modelise avec JavaFX8
 * @author ReyL03
 * @version 1.0
 * @updated 17-févr.-2017 14:54:37
 */
public class Ihm implements ItfIhmCtrl, Initializable {

    public ItfCtrlIhm refCtrl;
    @FXML
    private TextArea txaConsole;

    public Ihm() {

    }
    
     @Override
    public void initialize(URL location, ResourceBundle resources) {
//        Ctrl ctrl = new Ctrl();
//        Wrk wrk = new Wrk();
//        
//        ctrl.setRefIhm(this);
//        ctrl.setRefWrk(wrk);
//        wrk.setRefCtrl(ctrl);
//        this.setRefCtrl(ctrl);
    }

    public void finalize() throws Throwable {
    }
    /**
	 * Affiche un message dans la console.
	 * 
	 * @param msg    msg
	 */
    @Override
    public void afficheMessageConsole(String msg) {
        txaConsole.appendText(msg + "\n");
    }

    /**
	 * Affiche un popup lors d'une erreur. Le string contient le message d'erreur.
	 * 
	 * @param error    error
	 */
    public void affichePopupError(String error) {

    }

    /**
	 * Affiche le statut de la connexion avec le client.
	 * 
	 * @param statutClient    statutClient
	 */
    public void afficheStatutConnectionClient(boolean statutClient) {

    }

    /**
	 * Affiche le statut de la connexion avec le robot.
	 * 
	 * @param statutRobot    statutRobot
	 */
    public void afficheStatutConnectionRobot(boolean statutRobot) {

    }
    
	/**
	 * Ferme les threads lors de la fermeture de l'application.
	 */
    public void quitter(){
        refCtrl.fermeLesThreads();
    }

    public void setRefCtrl(ItfCtrlIhm refCtrl) {
        this.refCtrl = refCtrl;
    }
    
    
    
}//end Ihm