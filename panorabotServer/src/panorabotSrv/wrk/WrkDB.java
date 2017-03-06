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
    private int nbCapture;

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

    public void putCapture() {
        com.mysql.jdbc.Statement statement;
        int nb = 0;
        try {
            statement = (com.mysql.jdbc.Statement) dbConnection.createStatement();

            String prepCapture = "INSERT INTO T_Capture (date,FK_User) VALUES (?,?)";
            //com.mysql.jdbc.PreparedStatement psCapture = (com.mysql.jdbc.PreparedStatement) dbConnection.prepareStatement(prepCapture);
            PreparedStatement psCapture = dbConnection.prepareStatement(prepCapture, Statement.RETURN_GENERATED_KEYS);
            java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
            psCapture.setDate(1, date);
            psCapture.setInt(2, 1);
            psCapture.executeUpdate();         
            ResultSet keys = psCapture.getGeneratedKeys();
            keys.next();
            nbCapture = keys.getInt(1);

            statement.close();
        } catch (SQLException ex) {
            String erreur = "erreur - " + ex.toString();
            System.out.println(erreur);
        }
    }

    public String getUsernameConnecte() {
        return usernameConnecte;
    }

    public void putPhoto(InputStream bi) {
        int nb = 0;
        com.mysql.jdbc.Statement statement;
        try {
            statement = (com.mysql.jdbc.Statement) dbConnection.createStatement();
            String prepPhoto = "INSERT INTO T_Photo (photo,FK_Capture) VALUES (?,?)";
            com.mysql.jdbc.PreparedStatement psPhoto;
            psPhoto = (com.mysql.jdbc.PreparedStatement) dbConnection.prepareStatement(prepPhoto);
            psPhoto.setBlob(1, bi);
            psPhoto.setInt(2, nbCapture);
            nb = psPhoto.executeUpdate();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(WrkDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void close() {
        try {
            this.dbConnection.close();
        } catch (SQLException ex) {
            Logger.getLogger(WrkDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}//end WrkDB
