package panorabotClient.ihm;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import panorabotClient.ctrl.Ctrl;
import panorabotClient.wrk.Wrk;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Nathan
 */
public class panorabotClient extends Application {

    @Override
    public void start(Stage stage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("IhmLogin.fxml"));
//
//        Scene scene = new Scene(root);
//
//        stage.setScene(scene);
//        stage.show();

        //Parent root = FXMLLoader.load(getClass().getResource("IhmLogin.fxml"));
        FXMLLoader loaderIhmLogin = new FXMLLoader(getClass().getResource("IhmLogin.fxml"));
        Parent rootIhmLogin = (Parent) loaderIhmLogin.load();
        //Parent root = FXMLLoader.load(getClass().getResource("IhmLogin.fxml"));
        FXMLLoader loaderIhmRobot = new FXMLLoader(getClass().getResource("IhmRobot.fxml"));
        Parent rootIhmRobot = (Parent) loaderIhmRobot.load();
        // Récupére le controleur
        IhmLoginController ihmLogin = loaderIhmLogin.getController();
        IhmRobotController ihmRobot = loaderIhmRobot.getController();
        Ctrl ctrl = new Ctrl();
        Wrk wrk = new Wrk();

        ctrl.setWrk(wrk);
        ctrl.setIhmRobot(ihmRobot);
        wrk.setCtrl(ctrl);
        Scene sceneLogin = new Scene(rootIhmLogin);
        Scene sceneRobot = new Scene(rootIhmRobot);
        ihmLogin.setRefCtrl(ctrl);
        ihmLogin.setSceneLogin(sceneLogin);
        ihmLogin.setSceneRobot(sceneRobot);
        ihmLogin.setStage(stage);
        ihmRobot.setRefCtrl(ctrl);
        ihmRobot.setSceneLogin(sceneLogin);
        ihmRobot.setStage(stage);
        stage.setScene(sceneLogin);
        stage.setTitle("Java FX8");
        stage.show();

//        stage.setFullScreenExitHint("");
//        stage.setFullScreenExitKeyCombination(new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_DOWN));
//        stage.setFullScreen(true);
//       
        stage.setOnCloseRequest(e -> ihmRobot.quitter());
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        launch(args);
    }

}
