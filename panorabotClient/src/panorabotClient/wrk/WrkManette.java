package panorabotClient.wrk;

import ch.aplu.xboxcontroller.XboxController;
import ch.aplu.xboxcontroller.XboxControllerAdapter;

/**
 *
 * @author Nathan
 */
public class WrkManette {

    /**
     * Constructeur de la classe WrkManette. Initialise la connexion avec la
     * manette.
     * @param refWrk le wrk qui sera appelé lors des actions de la manette.
     */
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
                refWrk.avancerRobot(valueOn20(-value));
            }

            @Override
            public void rightTrigger(double value) {
                refWrk.avancerRobot(valueOn20(value));
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

    /**
     * Transforme une valeur double de 0 à 0.99 en valeur int de 1 à 20.
     * @param value la valeur de 0 à 0.99.
     * @return la valeur de 1 à 20.
     */
    private int valueOn20(double value) {
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
