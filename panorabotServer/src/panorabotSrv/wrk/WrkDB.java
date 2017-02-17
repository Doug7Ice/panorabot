package panorabotSrv.wrk;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;

/**
 * Wrk permettant de controller la BD.
 * @author ReyL03
 * @version 1.0
 * @updated 17-févr.-2017 14:54:37
 */
public class WrkDB {

	private Connection dbConnection;

	public WrkDB(){

	}

	public void finalize() throws Throwable {

	}
	/**
	 * Permet d'ajouter un log dans la base de donnees.
	 * 
	 * @param log    log
	 */
	public void addLog(String log){

	}

	/**
	 * Receptionne les images d'une capture dans la BD. La capture peut etre trouvee
	 * grace au numero de l'User ainsi que du Scan.
	 * 
	 * @param pkUser
	 * @param pkScan    pkScan
	 */
	public ArrayList<BufferedImage> getImages(int pkUser, int pkScan){
		return null;
	}

	/**
	 * Envoie dans la BD un Blob.
	 * 
	 * @param stream    stream
	 */
	public boolean put(InputStream stream){
		return false;
	}
                
}//end WrkDB