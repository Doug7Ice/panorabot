/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wrk;

import java.awt.Dimension;
import net.java.games.input.Version;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JToggleButton;
import net.java.games.input.Component;
import net.java.games.input.Component.Identifier;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

/**
 *
 * @author ReyL03
 */
public class ManetteWrk extends Thread {

    public ManetteWrk(boolean running, Wrk refWrk, EcrireMessageWrk sender) {
        super("ManetteWrk");
        this.running = running;
        this.refWrk = refWrk;
        this.refSender = sender;
        searchForControllers();

    }

    public void run() {
        running = true;
        while (running) {
            
            Controller controller = this.controller;

            // Pull controller for current data, and break while loop if controller is disconnected.
            if( !controller.poll() ){
                System.out.println("Manette déconnectée");
                break;
            }
            
            int xAxisPercentage = 0;
            int yAxisPercentage = 0;

            // Go trough all components of the controller.
            Component[] components = controller.getComponents();
            for (int i = 0; i < components.length; i++) {
                Component component = components[i];
                Identifier componentIdentifier = component.getIdentifier();

                // Buttons
                //if(component.getName().contains("Button")){ // If the language is not english, this won't work.
                if (componentIdentifier.getName().matches("^[0-9]*$")) { // If the component identifier name contains only numbers, then this is a button.
                    // Is button pressed?
                    boolean isItPressed = true;
                    if (component.getPollData() == 0.0f) {
                        isItPressed = false;
                    }

                    // Button index
                    String buttonIndex;
                    buttonIndex = component.getIdentifier().toString();

                    // We know that this component was button so we can skip to next component.
                    continue;
                }

                // Hat switch
                if (componentIdentifier == Component.Identifier.Axis.POV) {
                    float hatSwitchPosition = component.getPollData();
                    if (hatSwitchPosition > 2) {
                        refSender.writeMessage("D," + hatSwitchPosition + "," + hatSwitchPosition);
                    }
                    // We know that this component was hat switch so we can skip to next component.
                    continue;
                }

                // Axes
                if (component.isAnalog()) {
                    float axisValue = component.getPollData();
                    int axisValueInPercentage = 0;
                    if (axisValue != 0){
                    axisValueInPercentage = getAxisValueInPercentage(axisValue);
                    }
                    // X axis
                    if (componentIdentifier == Component.Identifier.Axis.X) {
                        xAxisPercentage = axisValueInPercentage;
                        if (xAxisPercentage != 0) {
                            int x = Math.abs(xAxisPercentage);
                            int y = Math.abs(xAxisPercentage);
                            System.out.println(x);
                            refSender.writeMessage("D," + x + "," + y);
                        }
                        continue; // Go to next component.
                    }
                    // Y axis
                    if (componentIdentifier == Component.Identifier.Axis.Y) {
                        yAxisPercentage = axisValueInPercentage;
                        if (xAxisPercentage != 0) {
                            int x = Math.abs(yAxisPercentage);
                            int y = Math.abs(yAxisPercentage);
                            System.out.println(y);
                            refSender.writeMessage("D," + x + "," + y);
                        }
                        continue; // Go to next component.
                    }

                }
            }
        }
        // We have to give processor some rest.
            try {
                Thread.sleep(25);
            } catch (InterruptedException ex) {
                Logger.getLogger(ManetteWrk.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    private void searchForControllers() {

        ControllerEnvironment ce
                = ControllerEnvironment.getDefaultEnvironment();

        // retrieve the available controllers
        Controller[] controllers = ce.getControllers();

        //fetch gamepad controller
        Controller gamePadContr = null;
        for (Controller c : controllers) {
            if (c.getType() == Controller.Type.GAMEPAD) {
                gamePadContr = c;

                break;
            }
        }

        //none found
        if (gamePadContr == null) {
            throw new NullPointerException("No gamepad found");
        } else {
            this.controller = gamePadContr;
        }
    }
    public int getAxisValueInPercentage(float axisValue) {
        int a = (int) (((2 - (1 - axisValue)) * 100) / 2);
        if (a > 20){
            refWrk.envoyerMsg("TROP VITE");
            a = 5;
        }
        return a;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    private volatile boolean running;
    private Wrk refWrk;
    private EcrireMessageWrk refSender;
    private Controller controller;
}
