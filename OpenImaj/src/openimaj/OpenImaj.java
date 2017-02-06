/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package openimaj;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.openimaj.image.MBFImage;
import org.openimaj.video.Video;
import org.openimaj.video.VideoDisplay;
import org.openimaj.video.capture.VideoCapture;

/**
 *
 * @author ReyL03
 */
public class OpenImaj extends Application {
    
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
        launch(args);
    }
    
     public static void displayTest() {
 
        Video<MBFImage> video;
        Ihm ihm = new Ihm();
        try {
            video = new VideoCapture(1980, 1080);
            VideoDisplay<MBFImage> display = ihm.displayVideo(video);
            ihm.setVisible(true);      
        } catch (Exception ex) {
            System.out.println("displayTest : " + ex.toString());
        }
    }
    
}
