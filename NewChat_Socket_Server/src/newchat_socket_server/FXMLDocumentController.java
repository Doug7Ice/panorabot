/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newchat_socket_server;

import ctrl.Ctrl;
import ctrl.ItfCtrlIhm;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import wrk.Wrk;

/**
 * FXML Controller class
 *
 * @author Nathan
 */
public class FXMLDocumentController implements Initializable, ItfCtrlIhm {
    @FXML
    private TextArea txaConsole;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ItfIhmCtrl ctrl = new Ctrl();
        ctrl.setRefIhm(this);
    }    
    
}
