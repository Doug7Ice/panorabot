/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panorabotClient.ihm;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import panorabotClient.ctrl.ItfCtrlIhmLogin;

/**
 * FXML Controller class
 *
 * @author Nathan
 */
public class IhmLoginController implements Initializable, ItfIhmLoginCtrl {

    @FXML
    private TextField txtUser;
    @FXML
    private Button btnConnexion;
    @FXML
    private PasswordField txtPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        try {
//            Parent root = FXMLLoader.load(getClass().getResource("IhmRobot.fxml"));
//            Scene scene = new Scene(root);
//            stageRobotIhm = new Stage();
//            stageRobotIhm.setScene(scene);
//
//            Ctrl ctrl = new Ctrl();
//            Wrk wrk = new Wrk();
//
//            ctrl.setWrk(wrk);
//            wrk.setCtrl(ctrl);
//            refCtrl = ctrl;
//            
//        } catch (IOException ex) {
//            Logger.getLogger(IhmLoginController.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
    }

    @Override
    public void resultLogin(String result) {
        switch (result) {
            case "Login:OK":
                Platform.runLater(() ->stage.setScene(sceneRobot));
                Platform.runLater(() ->stage.show());
                break;
            case "Login:KO":
                refCtrl.afficherPopup("Login incorrect", "error");
                break;
            case "Login:DBout":
                refCtrl.afficherPopup("Erreur avec la base de données", "error");
                break;
        }

    }

    @FXML
    private void btnConnexionOnAction(ActionEvent event) {
        refCtrl.lancerSocket();
        refCtrl.connecter(txtUser.getText(), txtPassword.getText());
    }

    void setSceneLogin(Scene scene) {
        this.sceneLogin = scene;
    }

    void setSceneRobot(Scene scene) {
        this.sceneRobot = scene;
    }

    public ItfCtrlIhmLogin getRefCtrl() {
        return refCtrl;
    }

    public void setRefCtrl(ItfCtrlIhmLogin refCtrl) {
        this.refCtrl = refCtrl;
    }

    void setStage(Stage stage) {
        this.stage = stage;
    }

    private ItfCtrlIhmLogin refCtrl;
    private IhmRobotController ihmRobotCtrl;
    private Stage stage;
    private Scene sceneLogin;
    private Scene sceneRobot;

}
