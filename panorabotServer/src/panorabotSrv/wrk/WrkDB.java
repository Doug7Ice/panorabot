package panorabotSrv.wrk;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;

/**
 * @author ReyL03
 * @version 1.0
 * @created 14-févr.-2017 10:50:33
 */
public class WrkDB {

	private Connection dbConnection;

	public WrkDB(){

	}

	public void finalize() throws Throwable {

	}
	/**
	 * 
	 * @param log
	 */
	public void addLog(String log){

	}

	/**
	 * 
	 * @param pkUser
	 * @param pkScan
	 */
	public ArrayList<BufferedImage> getImages(int pkUser, int pkScan){
		return null;
	}

	/**
	 * 
	 * @param stream
	 */
	public boolean put(InputStream stream){
		return false;
	}
                
}//end WrkDB