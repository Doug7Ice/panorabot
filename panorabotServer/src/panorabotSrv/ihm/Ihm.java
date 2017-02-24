package panorabotSrv.ihm;

import javafx.scene.paint.Color;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.shape.Rectangle;
import panorabotSrv.ctrl.ItfCtrlIhm;

/**
 * Ihm pour l'application Panorabot version serveur. Modelise avec JavaFX8
 * @author ReyL03
 * @version 1.0
 * @updated 17-fevr.-2017 14:54:37
 */
public class Ihm implements ItfIhmCtrl, Initializable {

    public ItfCtrlIhm refCtrl;
    @FXML
    private TextArea txaConsole;
    @FXML
    private Rectangle etatClient;
    @FXML
    private Rectangle etatRobot;

    public Ihm() {

    }
    
     @Override
    public void initialize(URL location, ResourceBundle resources) {
         afficheStatutConnectionClient(false);
         afficheStatutConnectionRobot(false);
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
        if (statutClient){
            etatClient.setFill(Color.LIGHTGREEN);
        }
        else{
            etatClient.setFill(Color.LIGHTCORAL);
        }
    }

    /**
	 * Affiche le statut de la connexion avec le robot.
	 * 
	 * @param statutRobot    statutRobot
	 */
    public void afficheStatutConnectionRobot(boolean statutRobot) {
        if (statutRobot){
            etatRobot.setFill(Color.LIGHTGREEN);
        }
        else{
            etatRobot.setFill(Color.LIGHTCORAL);
        }
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