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
import wrk.Wrk;

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
    private Ctrl refCtrl;
    @FXML
    private Button btnExit;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Ctrl ctrl = new Ctrl();
        Wrk wrk = new Wrk();
        
        this.setRefCtrl(ctrl);
        ctrl.setRefIhm(this);
        ctrl.setRefWrk(wrk);
        wrk.setRefCtrl(ctrl);
    }    

    public Ctrl getRefCtrl() {
        return refCtrl;
    }

    public void setRefCtrl(Ctrl refCtrl) {
        this.refCtrl = refCtrl;
    }
    
    

    @FXML
    private void send(ActionEvent event) {
        System.out.println("helllllllo");
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
