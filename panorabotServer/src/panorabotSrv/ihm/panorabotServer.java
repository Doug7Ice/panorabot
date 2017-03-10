/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panorabotSrv.ihm;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import panorabotSrv.ctrl.Ctrl;
import panorabotSrv.wrk.Wrk;

/**
 *
 * @author ReyL03
 */
public class panorabotServer extends Application {

    @Override
    public void start(Stage stage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
//        
//        Scene scene = new Scene(root);
//        
//        stage.setScene(scene);
//        stage.show();

//Parent root = FXMLLoader.load(getClass().getResource("IhmLogin.fxml"));
        FXMLLoader loaderIhm = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        Parent rootIhm = (Parent) loaderIhm.load();
        // Récupére le controleur
        Ihm ihm = loaderIhm.getController();
        Ctrl ctrl = new Ctrl();
        Wrk wrk = new Wrk();

        ctrl.setRefWrk(wrk);
        ctrl.setRefIhm(ihm);
        wrk.setRefCtrl(ctrl);
        Scene scene = new Scene(rootIhm);
        ihm.setRefCtrl(ctrl);
        stage.setScene(scene);
        stage.setTitle("Panorabot Server Edition");
        stage.show();
        //Lors de la fermeture de l'IHM
        stage.setOnCloseRequest(e -> ihm.quitter());
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
