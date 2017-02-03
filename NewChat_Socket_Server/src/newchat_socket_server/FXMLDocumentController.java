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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author Nathan
 */
public class FXMLDocumentController implements Initializable, ItfCtrlIhm {
    @FXML
    private TextArea txaConsole;
    @FXML
    private Button btnSocket;

    /**
     * Initializes the controller class.
     */
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.ctrl = new Ctrl();
        this.ctrl.setRefIhm(this);
        
    }

    @Override
    public void afficherMessage(String msg) {
        txaConsole.appendText(msg + "\n");
                }

    @FXML
    private void btnSocketAction(ActionEvent event) {
        ctrl.lauchSocket();
        
    }
    
    private ItfIhmCtrl ctrl;
    
}
