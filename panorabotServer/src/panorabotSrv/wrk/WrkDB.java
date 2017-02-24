package panorabotSrv.wrk;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Wrk permettant de controller la BD.
 *
 * @author ReyL03
 * @version 1.0
 * @updated 17-fevr.-2017 14:54:37
 */
public class WrkDB {

    private Connection dbConnection;
    private ItfWrkWrkDB refWrk;

    public WrkDB() {
        try {
            this.dbConnection = (Connection) DriverManager.getConnection("jdbc:mysql://canzalin.emf-informatique.ch:3306/canzalin_PanoraBot_DB", "canzalin_panorabotAdmin", "Emf12345?");
        } catch (SQLException ex) {
            Logger.getLogger(WrkDB.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
    }

    public void finalize() throws Throwable {

    }

    /**
     * Permet d'ajouter un log dans la base de donnees.
     *
     * @param log log
     */
    public void addLog(String log) {
    }

    /**
     * Receptionne les images d'une capture dans la BD. La capture peut etre
     * trouvee grace au numero de l'User ainsi que du Scan.
     *
     * @param pkUser
     * @param pkScan pkScan
     */
    public ArrayList<BufferedImage> getImages(int pkUser, int pkScan) {
        return null;
    }

    /**
     * Envoie dans la BD un Blob.
     *
     * @param stream stream
     */
    public boolean put(InputStream stream) {
        return false;
    }

    public boolean userConnection(String username, String password) {
        boolean ok = false;
        Statement statement;
        ResultSet rs = null;
        int nb = 0;
        try {
            String prep = "select pwd from T_User where username = ?";
            PreparedStatement ps = (PreparedStatement) dbConnection.prepareStatement(prep);
            ps.setString(1, username);
            statement = (Statement) dbConnection.createStatement();
            rs = ps.executeQuery();
            rs.next();
            String passwordDB = rs.getString("pwd");
            statement.close();
            
            if (passwordDB.equals(password)){
                ok = true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ok;
    }

}//end WrkDB
