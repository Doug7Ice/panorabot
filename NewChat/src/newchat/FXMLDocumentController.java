/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newchat;

import ctrl.Ctrl;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author ReyL03
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private Button btnSend;
    @FXML
    private TextArea txaMessages;
    @FXML
    private TextField txtMessage;
    private ctrl.Ctrl refCtrl;
    @FXML
    private Button btnExit;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    public Ctrl getRefCtrl() {
        return refCtrl;
    }

    public void setRefCtrl(Ctrl refCtrl) {
        this.refCtrl = refCtrl;
    }
    
    

    @FXML
    private void send(ActionEvent event) {
        String msg = txtMessage.getText();
        refCtrl.envoyerMsg(msg);
    }
    public void afficheMsg(String msg){
        txaMessages.appendText(msg);
        System.out.println(msg);
    }

    @FXML
    private void close2(ActionEvent event) {
        refCtrl.fermer();
    }
    
}
