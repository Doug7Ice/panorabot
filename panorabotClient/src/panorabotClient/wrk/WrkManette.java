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
    public WrkManette(Wrk refWrk) {

        this.refWrk = refWrk;

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
                System.out.println("dasd");
            }

            @Override
            public void dpad(int direction, boolean pressed) {
                System.out.println("dir " + direction + " p " + pressed);
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
                System.out.println("b");
            }

            @Override
            public void buttonX(boolean pressed) {
                System.out.println("X");
            }

            @Override
            public void buttonY(boolean pressed) {
                System.out.println("Y");
            }

            @Override
            public void leftThumbDirection(double direction) {
                System.out.println("LEFT DIRECTION: " + direction);
            }

            @Override
            public void leftThumbMagnitude(double magnitude) {
                System.out.println("LEFT MAGN : " + magnitude);
            }

            @Override
            public void rightThumbMagnitude(double magnitude) {
                System.out.println("Magn: " + magnitude);
            }

            @Override
            public void rightThumbDirection(double direction) {
                System.out.println("direction : " + direction);
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
                    refWrk.augmenterRayon();
                }
            }

            @Override
            public void rightShoulder(boolean pressed) {
                if (pressed) {
                    refWrk.reduireRayon();
                }
            }

        });
    }

    public int valueOn20(double value) {
        return (int) (value * 20 + 1);
    }

    //Setters and Getters
    public ItfWrkManette getRefWrk() {
        return refWrk;
    }

    public void setRefWrk(ItfWrkManette refWrk) {
        this.refWrk = refWrk;
    }

    private ItfWrkManette refWrk;
    private XboxController xc;
}
