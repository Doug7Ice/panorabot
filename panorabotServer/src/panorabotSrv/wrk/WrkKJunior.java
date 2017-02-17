package panorabotSrv.wrk;

import jssc.SerialPort;
import jssc.SerialPortException;

/**
 * Worker permettant de communiquer avec le KJunior.
 * @author ReyL03
 * @version 1.0
 * @updated 17-févr.-2017 14:54:37
 */
public class WrkKJunior {

    private SerialPort serialPort;

    public WrkKJunior() {
        ouvrirPort();
    }

    public void finalize() throws Throwable {

    }

    /**
     *
     * @param moteurGauche
     * @param moteurDroite
     */

    /**
	 * Envoie une commande au robot.
	 * 
	 * @param commande    commande
	 */
    public void commandeLeRobot(String commande) {
        String msg = commande + "\n";
        try {
            serialPort.writeBytes(msg.getBytes());//Write data to port
        } catch (SerialPortException ex) {

        }
    }

	/**
	 * Ouvre le port COM pour communiquer avec le robot.
	 */
    public void ouvrirPort() {
        this.serialPort = new SerialPort("COM6");
        try {            
            serialPort.openPort();
            serialPort.setParams(SerialPort.BAUDRATE_57600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);//Set params. Also you can set params by this string: serialPort.setParams(9600, 8, 1, 0);
        } catch (SerialPortException ex) {
           System.out.println("erreur = " + ex);
        }
    }
}//end WrkKJunior
