/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wrk;

/**
 *
 * @author ReyL03
 */
public class ManetteWrk extends Thread{
    
    

    public ManetteWrk(boolean running, Wrk refWrk) {
        super("ManetteWrk");
        this.running = running;
        this.refWrk = refWrk;
        joystick = new JInputJoystick(Controller.Type.STICK, Controller.Type.GAMEPAD);

    }
     public void run(){
         
     }
    
    
    private volatile boolean running;
    private Wrk refWrk;
    private JInputJoystick joystick;
}
