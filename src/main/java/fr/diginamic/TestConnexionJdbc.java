package fr.diginamic.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;

public class TestConnexionJdbc {
	

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		 // recupere le fichier properties
        ResourceBundle db = ResourceBundle.getBundle("database");
        Connection connection = null;

        try {

            // enregistre le pilote
            Class.forName(db.getString("db.driver"));

            // creer la connection
            	connection = DriverManager.getConnection(db.getString("db.url"), db.getString("db.user"),
                        db.getString("db.pass"));
                // affiche la connexion
                boolean valid = connection.isValid(500);
                if (valid) {
                    // SEVERE = Erreur
                    // INFO = info
                    // WARNING = Avertissement
                    // FINEST = Debug
                    //LOGGER.log(Level.INFO, "La connection est ok");
                	System.out.println("La connexion est valide");
                } else {
                    //LOGGER.log(Level.SEVERE, "Il y a une erreur de connection");
                	System.out.println("Il y a déconnexion");
                }
        } catch (SQLException | ClassNotFoundException e) {
            // Handle errors for JDBC
            //LOGGER.log(Level.SEVERE, "Erreur de communication avec la base", e);
        	System.err.println("base déconnectée : " + e.getMessage());
        }
        finally {
        	try {
        		if (connection != null) {
        		connection.close();
        	}
        	} catch (Exception e) {
        		System.err.println(e.getMessage());
        	}
        	System.out.println("Base déconnectée finally");
        }
    }
}
