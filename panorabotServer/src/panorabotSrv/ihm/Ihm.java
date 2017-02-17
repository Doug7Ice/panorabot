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
 * @author ReyL03
 * @version 1.0
 * @created 14-f�vr.-2017 10:50:33
 */
public class Ihm implements ItfIhmCtrl,Initializable {

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
     *
     * @param msg
     */
    @Override
    public void afficheMessageConsole(String msg) {
        txaConsole.appendText(msg + "\n");
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
    
    public void quitter(){
        refCtrl.fermeLesThreads();
    }

    public void setRefCtrl(ItfCtrlIhm refCtrl) {
        this.refCtrl = refCtrl;
    }
    
    
    
}//end Ihm
