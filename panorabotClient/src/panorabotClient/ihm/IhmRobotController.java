/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panorabotClient.ihm;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import panorabotClient.ctrl.ItfCtrlIhmRobot;

/**
 * FXML Controller class
 *
 * @author Nathan
 */
public class IhmRobotController implements Initializable, ItfIhmRobotCtrl {
    @FXML
    private Button btnArretDUrgence;
    @FXML
    private Slider sliderRayon;
    @FXML
    private ImageView imgDerniereCapture;
    @FXML
    private ImageView imgVideo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void btnArretDUrgenceAction(ActionEvent event) {
        
    }

    public ItfCtrlIhmRobot getRefCtrl() {
        return refCtrl;
    }

    public void setRefCtrl(ItfCtrlIhmRobot refCtrl) {
        this.refCtrl = refCtrl;
    }
    
    
    
    private ItfCtrlIhmRobot refCtrl;
    
}
