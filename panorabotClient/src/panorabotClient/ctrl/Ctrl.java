/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panorabotClient.ctrl;

import java.awt.image.BufferedImage;
import panorabotClient.ihm.ItfIhmLoginCtrl;
import panorabotClient.ihm.ItfIhmRobotCtrl;
import panorabotClient.wrk.ItfWrkCtrl;

/**
 *
 * @author Nathan
 */
public class Ctrl implements ItfCtrlIhmLogin, ItfCtrlIhmRobot, ItfCtrlWrk {

    @Override
    public void connecter(String user, String mdp) {
        refWrk.connecter(user, mdp);
    }
    
    @Override
    public void stopperDroneDUrgence() {
        refWrk.stopperRobotDUrgence();
    }
    
     @Override
    public void lancerSocket() {
        refWrk.lancerSocket();
    }
    
    @Override
    public void augmenterRayon() {
        ihmRobot.augmenterRayon();
    }
    
    @Override
    public void afficherPopup(String message, String type) {
        ihmRobot.afficherPopup(message, type);
    }
    
    @Override
    public void afficheImage(BufferedImage img) {
        ihmRobot.afficheImage(img);
    }
    
    @Override
    public int getActualRayon() {
        return ihmRobot.getActualRayon();
    }
    @Override
    public void recupererImagesScan() {
        refWrk.recupererImagesScan();
    }
    
    @Override
    public void lancerConversion() {
        refWrk.lancerConversion();
    }
    
    @Override
    public void bloquerBoutonConversion(boolean blocked) {
        ihmRobot.bloquerBoutonConversion(blocked);
    }
    
    @Override
    public void quit() {
        refWrk.quit();
    }
    
    @Override
    public void resultLogin(String result) {
        ihmLogin.resultLogin(result);
    }

    
    public void reduireRayon(){
        ihmRobot.reduireRayon();
    }

    public ItfWrkCtrl getWrk() {
        return refWrk;
    }

    public void setWrk(ItfWrkCtrl wrk) {
        this.refWrk = wrk;
    }

    public ItfIhmRobotCtrl getIhmRobot() {
        return ihmRobot;
    }

    public void setIhmRobot(ItfIhmRobotCtrl ihmRobot) {
        this.ihmRobot = ihmRobot;
    }

    public ItfIhmLoginCtrl getIhmLogin() {
        return ihmLogin;
    }

    public void setIhmLogin(ItfIhmLoginCtrl ihmLogin) {
        this.ihmLogin = ihmLogin;
    }
    
    
    
    

    private ItfWrkCtrl refWrk;
    private ItfIhmRobotCtrl ihmRobot;
    private ItfIhmLoginCtrl ihmLogin;

    

    

    
    
    

    

    

    

    

   

    
}
