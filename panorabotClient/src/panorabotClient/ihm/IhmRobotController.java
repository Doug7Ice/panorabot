/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panorabotClient.ihm;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
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
    @FXML
    private Button btnDeconnexion;
    @FXML
    private TextField txtRayon;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sliderRayon.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double value =20 + (double) newValue.intValue() / 100 * 80;
                txtRayon.setText(""+  value);
            }
        });
    }
    
    @Override
    public void augmenterRayon() {
        int newRayon = Integer.parseInt(txtRayon.getText()) + 5;
        txtRayon.setText("" + newRayon);
    }
    
    @Override
    public void reduireRayon() {
        int newRayon = Integer.parseInt(txtRayon.getText()) - 5;
        txtRayon.setText("" + newRayon);
    }

    @FXML
    private void btnArretDUrgenceAction(ActionEvent event) {

    }

    @FXML
    private void btnDecoOnAction(ActionEvent event) {
        stage.setScene(sceneLogin);
    }

    public ItfCtrlIhmRobot getRefCtrl() {
        return refCtrl;
    }

    public void setRefCtrl(ItfCtrlIhmRobot refCtrl) {
        this.refCtrl = refCtrl;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setSceneLogin(Scene sceneLogin) {
        this.sceneLogin = sceneLogin;
    }

    private Scene sceneLogin;
    private ItfCtrlIhmRobot refCtrl;
    private Stage stage;

    
    
}
