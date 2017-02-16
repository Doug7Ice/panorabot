package panorabotSrv.ihm;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import panorabotSrv.ctrl.ItfCtrlIhm;

/**
 * @author ReyL03
 * @version 1.0
 * @created 14-f�vr.-2017 10:50:33
 */
public class Ihm implements ItfIhmCtrl {

    public ItfCtrlIhm refCtrl;
    @FXML
    private Label label;

    public Ihm() {

    }

    public void finalize() throws Throwable {

    }

    /**
     *
     * @param msg
     */
    public void afficheMessageConsole(String msg) {

    }

    /**
     *
     * @param error
     */
    public void affichePopupError(String error) {

    }

    /**
     *
     * @param statutClient
     */
    public void afficheStatutConnectionClient(boolean statutClient) {

    }

    /**
     *
     * @param statutRobot
     */
    public void afficheStatutConnectionRobot(boolean statutRobot) {

    }

    public void setRefCtrl(ItfCtrlIhm refCtrl) {
        this.refCtrl = refCtrl;
    }
    
}//end Ihm
