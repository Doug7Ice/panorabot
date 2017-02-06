/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Thread;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nathan
 */
public class MonThreadImplements implements Runnable {
    
    @Override
    public void run (){
       for(int i=0;i<20;i++){
          System.out.println("Le thread : '" +
                  Thread.currentThread().getName() +
                  "' est en cours d'exécution " + i);
          try{
             Thread.sleep(250);
          }
          catch(java.lang.InterruptedException e){
             System.out.println("Interruption du Thread non attendue !");
          }
       }
       System.out.println("Fin de la boucle");
    }

}


