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
    private String usernameConnecte;

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

    public int userConnection(String username, String password) {
        int ok = 3;
        String passwordDB = null;
        Statement statement;
        ResultSet rs = null;
        try {
            if (!dbConnection.isClosed()) {
                String prep = "select pwd from T_User where username = ? and pwd = ?";
                PreparedStatement ps = (PreparedStatement) dbConnection.prepareStatement(prep);
                ps.setString(1, username);
                ps.setString(2, password);
                statement = (Statement) dbConnection.createStatement();
                rs = ps.executeQuery();
                rs.next();
                if (rs.getRow() == 0) {
                    ok = 0;
                }
                if (ok != 0) {
                    passwordDB = rs.getString("pwd");
                    statement.close();
                    if (passwordDB.equals(password)) {
                        ok = 1;
                        this.usernameConnecte = username;
                    } else {
                        ok = 0;
                    }
                }
            } else {
                ok = 3;
            }
        } catch (SQLException ex) {
            ok = 3;
            Logger.getLogger(WrkDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ok;
    }

    public void putCapture(InputStream bi) {
        com.mysql.jdbc.Statement statement;
        int nb = 0;
        try {
            String prep = "insert t_blobfish set image = ?";
            com.mysql.jdbc.PreparedStatement ps = (com.mysql.jdbc.PreparedStatement) dbConnection.prepareStatement(prep);
            ps.setBlob(1, bi);
            statement = (com.mysql.jdbc.Statement) dbConnection.createStatement();
            nb = ps.executeUpdate();
            statement.close();
        } catch (SQLException ex) {
            String erreur = "upodaaatePerson - " + ex.toString();
            System.out.println(erreur);
        }
    }

    public String getUsernameConnecte() {
        return usernameConnecte;
    }

}//end WrkDB
