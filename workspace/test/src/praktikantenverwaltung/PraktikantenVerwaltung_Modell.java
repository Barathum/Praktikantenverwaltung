package praktikantenverwaltung;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import org.apache.commons.io.FileUtils;

/**
 * 
 * @author Barathum
 * Das Modell erm�glicht verbindungsaufbau mit der Datenbank und Befehle auf ihr auszuf�hren
 * Au�erdem wird hier das ver- und entschl�sseln durchgef�hrt
 */
public class PraktikantenVerwaltung_Modell {
	
	
private String passwort;
private Cryptor _crypt;
	/**
	 * Kontruktor der die verschl�sselungsklasse setzt
	 * @param _crypt die klasse die f�r die verschl�sselung zust�ndig ist
	 */
	public PraktikantenVerwaltung_Modell(Cryptor _crypt) {
		this._crypt = _crypt;
	}
	//	 public void main( String args[] )
//	  {
//	    connectToDatabase("jdbc:sqlite:test.db");
////	    String sql = "CREATE TABLE PRAKTIKANTEN " +
////                "(ID INT PRIMARY KEY     NOT NULL," +
////                " NAME           TEXT, " + 
////                " AGE            INT, " + 
////                " ADDRESS        CHAR(50), " + 
////                " SALARY         REAL)"; 
////	    createTable(sql);
//	    String sql = "INSERT INTO PRAKTIKANTEN (ID,NAME,AGE,ADDRESS,SALARY) " +
//                "VALUES (2, 'Paul', 32, 'California', 20000.00 );";
//	    insertUpdateDeleteTable(sql);
////	    sdasdasdadsadasd
//	    
//	  }
	/**
	 * Entschl�sselt die Datenbank und Baut verbindung mit Datenbank d auf
	 * @param d Pfad zur Datenbank ohne bestimmte Endungen also "name.db"
	 * @param pw Das Passwort welches zum entschl�sseln genutzt werden soll
	 * @return gibt die aufgebaute Verbindung zur�ck
	 */
	 private Connection connectToDatabase(String d , String pw)
	  {
		 try {
			this._crypt.decryptFile(d.substring(12), pw);
		} catch (GeneralSecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 
	    Connection c = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection(d + ".decrypted.db");
	      return c;
	    } catch ( Exception e ) {
	      e.printStackTrace();
	      System.exit(0);
	    }
//	    System.out.println("Datenbank verbunden");
	    return c;
	  }
	 
	 /**
	  * Verschl�sselt die Datei wieder und l�scht die unverschl�sselte
	  * @param d Pfad zur Datenbank ohne bestimmte Endungen also "name.db"
	  * @param pw das Passwort mit welchem die Datenbank verschl�sselt werden soll
	  */
	 protected void disconnectFromDatabase(String d , String pw)
	  {
		 try {
			this._crypt.encryptFile(d.substring(12), pw);
		} catch (GeneralSecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 
		 try {
			FileUtils.forceDelete(new File(d.substring(12) + ".decrypted.db"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	 
	 /**
	  * Setzt das Passwort
	  * @param pw Das Passwort f�r die Datenbankverschl�sselung in Stringform
	  */
	 protected void setPasswort(String pw){
		 passwort = pw;
	 }
	 /**
	  * Erstellt Tabellen Praktikanten und Ansprechpartner
	  * nur ben�tigt, wenn die Datenbank nicht existiert
	  */
	 private void createTables(){
		    Connection c = null;
		    Statement stmt = null;
		    try {
		      c = connectToDatabase("jdbc:sqlite:db/PraktikantenDB.db" , passwort);
		      stmt = c.createStatement();
		      String sql = "CREATE TABLE PRAKTIKANTEN " +
	                   "(ID TEXT," +
	                   " ANREDE TEXT, " + 
	                   " NN TEXT, " + 
	                   " VN TEXT, " + 
	                   " GB TEXT, " +
	                   " GO TEXT, " +
	                   " STR TEXT, " +
	                   " PLZ TEXT, " +
	                   " LAND TEXT, " +
	                   " TELE TEXT, " +
	                   " MAIL TEXT, " +
	                   " MOBIL TEXT, " +
	                   " HAUSNR TEXT, " +
	                   " ORT TEXT, " +
	                   " GNN TEXT, " +
	                   " GVN TEXT, " +
	                   " SCHULE TEXT, " +
	                   " SCHULFORM TEXT, " +
	                   " PARTNERS TEXT, " +
	                   " ANMERKSCHULE TEXT, " +
	                   " MIKI TEXT, " +
	                   " GRAD TEXT, " +
	                   " ANMERKPERSON TEXT, " +
	                   " STARTDATUM TEXT, " +
	                   " ENDDATUM TEXT, " +
	                   " STATUS TEXT, " +
	                   " ANMERKPRAKT TEXT, " +
	                   " ANSPR1 TEXT, " +
	                   " ANSPR2 TEXT, " +
	                   " ANSPR3 TEXT, " +
	                   " INFO TEXT, " +
	                   " EDIT TEXT, " +
	                   " UNTERLAGENVOLLST TEXT, " +
	                   " ANTWORTBIS TEXT)"; 
		      stmt.executeUpdate(sql);
		      sql = "CREATE TABLE ANSPRECHPARTNER " +
	                   "(ID TEXT," +
	                   " NN TEXT, " + 
	                   " VN TEXT, " + 
	                   " TELE TEXT, " + 
	                   " MAIL TEXT, " +
	                   " ABTEILUNG TEXT, " +
	                   " RNR TEXT, " +
	                   " ANMERKEINSATZORT TEXT, " +
	                   " INFO TEXT, " +
	                   " BLOCKIERENVON  TEXT, " +
	                   " BLOCKIERENBIS  TEXT, " +
	                   " ETECH  TEXT, " +
	                   " KAUFM  TEXT, " +
	                   " INF TEXT)";
		      stmt.executeUpdate(sql);
		      stmt.close();
		      c.close();
		      disconnectFromDatabase("jdbc:sqlite:db/PraktikantenDB.db" , passwort);
		    } catch ( Exception e ) {
		      e.printStackTrace();
		      System.exit(0);
		    }
//		    System.out.println("Tabelle erfolgreich erstellt");
	 }
	 /**
	  * Baut verbindung auf und f�r das Statement definiert durch den String sql auf der Datenbank aus
	  * Insert Update oder Delete Befehle
	  * Disconnected dann von der Datenbank
	  * @param sql Der auszuf�hrende Befehl
	  */
	 protected void insertUpdateDeleteTable(String sql){
		 {
			    Connection c = null;
			    Statement stmt = null;
			    try {
			      c = connectToDatabase("jdbc:sqlite:db/PraktikantenDB.db" , passwort);
			      c.setAutoCommit(false);
			      
			      stmt = c.createStatement();
			      stmt.executeUpdate(sql);
			      stmt.close();
			      c.commit();
			      c.close();
			      disconnectFromDatabase("jdbc:sqlite:db/PraktikantenDB.db" , passwort);
			    } catch ( Exception e ) {
			      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			      System.exit(0);
			    }
//			    System.out.println("Daten erfolgreich eingef�gt/ge�ndert/gel�scht");
			  }

	 }
	 /**
	  * Baut Verbindung auf und holt die Daten laut dem sql Befehl aus der Datenbank
	  * SELECT-Befehle
	  * Disconnected dann von der Datenbank
	  * gibt als Ergebnis eine 2D ArrayList vom Typ String zur�ck in der die zweite Dimension die Datens�tze und die
	  * erste Dimension die Eintr�ge pro Datensatz wiederspiegelt
	  * @param sql Der auszuf�hrende Select Befehl
	  * @return 2D ArrayList der Daten; return.get(0) = Datensatz 1; return.get(0).get(0) = Datensatz 1 Eintrag 1
	  */
	 protected ArrayList<ArrayList<String>> getData(String sql){
		  {
			    Connection c = null;
			    Statement stmt = null;
			    ArrayList<ArrayList<String>> daten = new ArrayList<ArrayList<String>>();
			    try {
				  c = connectToDatabase("jdbc:sqlite:db/PraktikantenDB.db" , passwort);
			      c.setAutoCommit(false);
			      
			      stmt = c.createStatement();
			      ResultSet rs = stmt.executeQuery(sql);
			      int numcols = rs.getMetaData().getColumnCount();
			      while (rs.next()) {
			          ArrayList<String> row = new ArrayList<>(numcols);
			          int i = 1;
			          while (i <= numcols) {
			              row.add(rs.getString(i++));
			          }
			          daten.add(row);
			      }
			      rs.close();
			      stmt.close();
			      c.close();
			      disconnectFromDatabase("jdbc:sqlite:db/PraktikantenDB.db" , passwort);
			    } catch ( Exception e ) {
			      e.printStackTrace();
			      System.exit(0);
			    }
//			    System.out.println("Daten geholt");
			    return daten;
			  }
	 }
}
