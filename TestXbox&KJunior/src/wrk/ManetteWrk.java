/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wrk;

import ch.aplu.xboxcontroller.XboxController;
import ch.aplu.xboxcontroller.XboxControllerAdapter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ReyL03
 */
public class ManetteWrk {

    public ManetteWrk(boolean running, Wrk refWrk, EcrireMessageWrk sender) {
        this.running = running;
        this.refWrk = refWrk;
        this.refSender = sender;

        xc = new XboxController();
        if (!xc.isConnected()) {
            xc.release();
            return;
        }

        xc.addXboxControllerListener(new XboxControllerAdapter() {
            public void isConnected(boolean connected) {
                System.out.println("connect " + connected);
            }

            public void buttonA(boolean pressed) {
                System.out.println("dasd");
                refSender.writeMessage("D,5,5");
            }

            public void dpad(int direction, boolean pressed) {
                System.out.println("dir " + direction + " p " + pressed);
            }

            public void buttonB(boolean pressed) {
                System.out.println("b");
                refSender.writeMessage("D,0,0");
            }

            public void buttonX(boolean pressed) {
                System.out.println("X");
            }

            public void buttonY(boolean pressed) {
                System.out.println("Y");
            }

            public void leftThumbDirection(double direction) {
                System.out.println("LEFT DIRECTION: " + direction);
            }

            public void leftThumbMagnitude(double magnitude) {
                System.out.println("LEFT MAGN : " + magnitude);
            }

            public void rightThumbMagnitude(double magnitude) {
                System.out.println("Magn: " + magnitude);
            }

            public void rightThumbDirection(double direction) {
                System.out.println("direction : " + direction);
            }

            public void leftTrigger(double value) {
//                leftVibrate = (int) (65535 * value * value);
//                xc.vibrate(leftVibrate, rightVibrate);
                  avancerDrone(valueOn20(-value));
                System.out.println("L " + leftVibrate + " R " + rightVibrate);
            }

            public void rightTrigger(double value) {
//                rightVibrate = (int) (65535 * value * value);
//                xc.vibrate(leftVibrate, rightVibrate);
                avancerDrone(valueOn20(value));
                System.out.println("L " + leftVibrate + " R " + rightVibrate);
            }
        });

    }

    public void avancerDrone(int value) {
        refSender.writeMessage("D," + value  + "," + value);
    }
    
    public int valueOn20(double value){
        return (int)(value * 20 + 1);
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void close() {
        xc.release();
    }

    private volatile boolean running;
    private Wrk refWrk;
    private EcrireMessageWrk refSender;
    private XboxController xc;
    private int leftVibrate = 0;
    private int rightVibrate = 0;
}
