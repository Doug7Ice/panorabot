/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Thread;

/**
 *
 * @author Nathan
 */
public class MonThreadExtends extends Thread {

    public MonThreadExtends(String name) {
        super(name);
    }
    

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("Le thread : '" +
                    this.getName() +
                    "' est en cours d'exécution " + i);
            try {
                Thread.sleep(250);
            } catch (java.lang.InterruptedException e) {
                System.out.println("Interrupted !");
            }
        }
        System.out.println("Mon thread se termine");
    }
}
