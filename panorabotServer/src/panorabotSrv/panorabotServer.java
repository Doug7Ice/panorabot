/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panorabotSrv;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import panorabotSrv.ctrl.Ctrl;
import panorabotSrv.ihm.Ihm;
import panorabotSrv.wrk.Wrk;

/**
 *
 * @author ReyL03
 */
public class panorabotServer extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Ctrl ctrl = new Ctrl();
        Wrk wrk = new Wrk();
        Ihm ihm = new Ihm();
        
        ctrl.setRefIhm(ihm);
        ctrl.setRefWrk(wrk);
        wrk.setRefCtrl(ctrl);
        ihm.setRefCtrl(ctrl);
        launch(args);
    }
    
}
