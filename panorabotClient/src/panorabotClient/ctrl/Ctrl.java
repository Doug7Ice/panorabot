/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panorabotClient.ctrl;

import panorabotClient.ihm.ItfIhmRobotCtrl;
import panorabotClient.wrk.ItfWrkCtrl;

/**
 *
 * @author Nathan
 */
public class Ctrl implements ItfCtrlIhmLogin, ItfCtrlIhmRobot, ItfCtrlWrk {

    @Override
    public boolean connecter(String user, String mdp) {
        return refWrk.connecter(user, mdp);
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
    
    

    private ItfWrkCtrl refWrk;
    private ItfIhmRobotCtrl ihmRobot;

    

   

    
}
