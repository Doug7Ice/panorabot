/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panorabotClient.wrk;

import ch.aplu.xboxcontroller.XboxController;
import ch.aplu.xboxcontroller.XboxControllerAdapter;

/**
 *
 * @author Nathan
 */
public class WrkManette {

    //Constructor
    public WrkManette(ItfWrkManette refWrk) {

        this.refWrk = refWrk;
        scanBlocked = false;
        xc = new XboxController();
        if (!xc.isConnected()) {
            xc.release();
            return;
        }

        xc.addXboxControllerListener(new XboxControllerAdapter() {
            @Override
            public void isConnected(boolean connected) {
                System.out.println("connect " + connected);
            }

            @Override
            public void buttonA(boolean pressed) {
                
            }

            @Override
            public void dpad(int direction, boolean pressed) {
                if (direction == 2 && pressed == true) {
                    refWrk.tournerADroite();
                } else if (direction == 2 && pressed == false) {
                    refWrk.stopperRobot();
                } else if (direction == 6 && pressed == true) {
                    refWrk.tournerAGauche();
                } else if (direction == 6 && pressed == false) {
                    refWrk.stopperRobot();
                }
            }

            @Override
            public void buttonB(boolean pressed) {
                
            }

            @Override
            public void buttonX(boolean pressed) {
                if (!scanBlocked && pressed) {
                    System.out.println("LANCER SCAN");
                    refWrk.lancerScan();
                }else{
                    System.out.println("SCAN BLOCKED");
                }

            }

            @Override
            public void buttonY(boolean pressed) {
                if (!scanBlocked && pressed) {
                    System.out.println("test du rayon lancé");
                    refWrk.lancerTestRayon();
                }else{
                    System.out.println("SCAN BLOCKED");
                }
            }

            @Override
            public void leftThumbDirection(double direction) {
                
            }

            @Override
            public void leftThumbMagnitude(double magnitude) {
                
            }

            @Override
            public void rightThumbMagnitude(double magnitude) {
                
            }

            @Override
            public void rightThumbDirection(double direction) {
                
            }

            @Override
            public void leftTrigger(double value) {
//                leftVibrate = (int) (65535 * value * value);
//                xc.vibrate(leftVibrate, rightVibrate);
//                if (!isTurning) {
                refWrk.avancerRobot(valueOn20(-value));
//                    System.out.println("L " + leftVibrate + " R " + rightVibrate);
//                }
            }

            @Override
            public void rightTrigger(double value) {
//                rightVibrate = (int) (65535 * value * value);
//                xc.vibrate(leftVibrate, rightVibrate);
//                if (!isTurning) {
                refWrk.avancerRobot(valueOn20(value));
//                    System.out.println("L " + leftVibrate + " R " + rightVibrate);
            }

            @Override
            public void leftShoulder(boolean pressed) {
                if (pressed) {
                    refWrk.reduireRayon();
                }
            }

            @Override
            public void rightShoulder(boolean pressed) {
                if (pressed) {
                    refWrk.augmenterRayon();
                }
            }

        });
    }

    public int valueOn20(double value) {
        return (int) (value * 20);
    }

    //Setters and Getters
    public ItfWrkManette getRefWrk() {
        return refWrk;
    }

    public void setRefWrk(ItfWrkManette refWrk) {
        this.refWrk = refWrk;
    }

    public boolean isScanBlocked() {
        return scanBlocked;
    }

    public void setScanBlocked(boolean scanBlocked) {
        this.scanBlocked = scanBlocked;
    }

    private ItfWrkManette refWrk;
    private XboxController xc;
    private boolean scanBlocked;
}
