package praktikantenverwaltung;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
/**
 * 
 * @author Barathum
 * Klasse zum konvertieren der alten Access Datenbank in SQLite Format
 */
public class AccessToSQLiteConverter
{
	/**
	 * Main Methode
	 * Nutzt andere Methoden um die Daten aus Access zu lesen und in die neue Datenbank zu schreiben
	 * @param args
	 */
    public static void main(String[] args)
    {
    	insertUpdateDeleteTable("DELETE FROM PRAKTIKANTEN");
    	insertUpdateDeleteTable("DELETE FROM ANSPRECHPARTNER");
    	SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    	SimpleDateFormat sdfold = new SimpleDateFormat("yyyy-MM-dd");
		Timestamp time = new Timestamp(System.currentTimeMillis());
		String zeit = sdf.format(time);
    	ArrayList<ArrayList<String>> daten_praktikanten = new ArrayList<ArrayList<String>>();
    	daten_praktikanten = getData("SELECT [ID] , [Anrede] , [Nachname] , [Vorname]"
    			+ " , [Geburtsdatum] , [Geburtsort] , [Straﬂe] , [Hausnummer]"
    			+ " , [PLZ] , [Wohnort] , [LandID] , [Telefonnummer]"
    			+ " , [Mobilnummer] , [EMail] , [SchulID] , [MiKi] , [Grad]"
    			+ " , [NachnameGV] , [VornameGV] , [AnmerkungPerson] FROM [Praktikanten]");
    	
    	ArrayList<ArrayList<String>> daten_praktika = new ArrayList<ArrayList<String>>();
    	daten_praktika = getData("SELECT [PraktikantenID] , [Startdatum] , [Enddatum] , [StatusID]"
    			+ " , [Anmerkung] , [ID] FROM [Praktika]");
    	
    	ArrayList<ArrayList<String>> daten_status = new ArrayList<ArrayList<String>>();
    	daten_status = getData("SELECT [ID] , [Bezeichnung] FROM [Status]");
    	
    	ArrayList<ArrayList<String>> daten_schule = new ArrayList<ArrayList<String>>();
    	daten_schule = getData("SELECT [ID] , [Schulname] , [Schultyp] , [Siemenspartnerschule] , "
    			+ "[Anmerkung] FROM [Schulen]");
    	ArrayList<ArrayList<String>> daten_einsatzorte = new ArrayList<ArrayList<String>>();
    	daten_einsatzorte = getData("SELECT [PraktikumID] , [EinsatzortID] , [Woche] FROM [EinsatzorteImPraktikum]");
    	
    	ArrayList<ArrayList<String>> daten_ansprech = new ArrayList<ArrayList<String>>();
    	daten_ansprech = getData("SELECT [ID] , [Nachname] , [Vorname] , [Telefonnummer] , [EMail]"
    			+ " , [Abteilung] , [Raumnummer] , [Anmerkung] FROM [Einsatzorte]");
    	
    	for (int i = 0; i < daten_ansprech.size(); i++) {
			ArrayList<String> liste = new ArrayList<String>();
			
			for (int j = 0; j < 14; j++) {
				liste.add("");
			}
			
			if (daten_ansprech.get(i).get(0).matches("..")) {
				liste.set(0, "1000" + daten_ansprech.get(i).get(0));//AnsprId1
			}else if (daten_ansprech.get(i).get(0).matches("...")) {
				liste.set(0, "100" + daten_ansprech.get(i).get(0));//AnsprId1
			}
			liste.set(1, daten_ansprech.get(i).get(1));
			liste.set(2, daten_ansprech.get(i).get(2));
			liste.set(3, daten_ansprech.get(i).get(3));
			liste.set(4, daten_ansprech.get(i).get(4));
			liste.set(5, daten_ansprech.get(i).get(5));
			liste.set(6, daten_ansprech.get(i).get(6));
			liste.set(7, daten_ansprech.get(i).get(7));
			

			liste.set(11, "0");
			liste.set(12, "0");
			liste.set(13, "0");
			
			insertUpdateDeleteTable("INSERT INTO ANSPRECHPARTNER " +
						"VALUES ('" + liste.get(0) +"','"+ liste.get(1) +"','"+ liste.get(2) +"','"+ liste.get(3) +"','"+ liste.get(4) +"','"+ liste.get(5) 
		                   +"','"+ liste.get(6) +"','"+ liste.get(7) +"','" + liste.get(8) +"','" + liste.get(9) +"','" + liste.get(10) +"','" + liste.get(11) +"','" + liste.get(12) + "','" + liste.get(13) +"');");
			
		}
    	for (int i = 0; i < daten_praktikanten.size(); i++) {
    		java.util.Date startOld = new java.util.Date();
			java.util.Date endOld = new java.util.Date();
    		ArrayList<String> liste = new ArrayList<String>();
    		for (int j = 0; j < 34; j++) {
				liste.add("");
			}
    		liste.set(0, daten_praktikanten.get(i).get(0));//ID
    		liste.set(1, daten_praktikanten.get(i).get(1));//Anrede
    		liste.set(2, daten_praktikanten.get(i).get(2));//NN
    		liste.set(3, daten_praktikanten.get(i).get(3));//VN
    		java.util.Date gbOld = new java.util.Date();
    		try {
				gbOld = sdfold.parse(daten_praktikanten.get(i).get(4));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		liste.set(4, sdf.format(gbOld));//Geburtsdatum
    		
    		liste.set(5, daten_praktikanten.get(i).get(5));//Geburtsort
    		liste.set(6, daten_praktikanten.get(i).get(6));//Str
    		liste.set(7, daten_praktikanten.get(i).get(8));//PLZ
    		if (daten_praktikanten.get(i).get(10).equals("1")) {
    			liste.set(8, "Deutschland");//Land
			} else {
				liste.set(8, "Deutschland");//Land
			}
    		liste.set(9, daten_praktikanten.get(i).get(11));//Telefon
    		liste.set(10, daten_praktikanten.get(i).get(13));//Mail
    		liste.set(11, daten_praktikanten.get(i).get(12));//Mobil
    		liste.set(12, daten_praktikanten.get(i).get(7));//Hausnr
    		liste.set(13, daten_praktikanten.get(i).get(9));//Ort
    		liste.set(14, daten_praktikanten.get(i).get(17));//GNN
    		liste.set(15, daten_praktikanten.get(i).get(18));//GVN
    		for (int j = 0; j < daten_schule.size(); j++) {
    			if(daten_schule.get(j).get(0).equals(daten_praktikanten.get(i).get(14))){
    				liste.set(16, daten_schule.get(j).get(1));//Schule
    	    		liste.set(17, daten_schule.get(j).get(2));//Schulform
    	    		if (daten_schule.get(j).get(3).equals("TRUE")) {
    	    			liste.set(18, "Ja");
    				} else {
    					liste.set(18, "Nein");
    				}
    	    		liste.set(19, daten_schule.get(j).get(4));//AnmerkSchule
    			}
    		}
    		
    		if (daten_praktikanten.get(i).get(15).equals("TRUE")) {
    			liste.set(20, "Ja");
			} else {
				liste.set(20, "Nein");
			}
    		liste.set(21, daten_praktikanten.get(i).get(16));//Grad
    		liste.set(22, daten_praktikanten.get(i).get(19));//Anmerkperson
    		for (int j = 0; j < daten_praktika.size(); j++) {
    			if (daten_praktika.get(j).get(0).equals(daten_praktikanten.get(i).get(0))) {
    				
    	    		try {
    					startOld = sdfold.parse(daten_praktika.get(j).get(1));
    					endOld = sdfold.parse(daten_praktika.get(j).get(2));
    				} catch (ParseException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    				liste.set(23, sdf.format(startOld));//Startdatum
    	    		liste.set(24, sdf.format(endOld));//Enddatum
    	    		liste.set(25, "leer");//Status
    				for (int j2 = 0; j2 < daten_status.size(); j2++) {
						if (daten_status.get(j2).get(0).equals(daten_praktika.get(j).get(3))) {
							liste.set(25, daten_status.get(j2).get(1));
						}
					}
    	    		liste.set(26, daten_praktika.get(j).get(4));//Anmerkprakt
    			}
			}
    		//setzt defaultwerte
    			liste.set(27, "0");//AnsprId1
        		liste.set(28, "0");//AnsprId2
        		liste.set(29, "0");//AnsprId3
    		for (int j = 0; j < daten_praktika.size(); j++) {
    			if (daten_praktika.get(j).get(0).equals(daten_praktikanten.get(i).get(0))) {
    				for (int k = 0; k < daten_einsatzorte.size(); k++) {
						if (daten_praktika.get(j).get(5).equals(daten_einsatzorte.get(k).get(0))) {
							if(daten_einsatzorte.get(k).get(2).equals("1")){
								if (daten_einsatzorte.get(k).get(1).matches("..")) {
									liste.set(27, "1000" + daten_einsatzorte.get(k).get(1));//AnsprId1
								}else if (daten_einsatzorte.get(k).get(1).matches("...")) {
									liste.set(27, "100" + daten_einsatzorte.get(k).get(1));//AnsprId1
								}
							}else if(daten_einsatzorte.get(k).get(2).equals("2")){
								if (daten_einsatzorte.get(k).get(1).matches("..")) {
									liste.set(28, "1000" + daten_einsatzorte.get(k).get(1));//AnsprId2
								}else if (daten_einsatzorte.get(k).get(1).matches("...")) {
									liste.set(28, "100" + daten_einsatzorte.get(k).get(1));//AnsprId2
								}
							}else if(daten_einsatzorte.get(k).get(2).equals("3")){
								if (daten_einsatzorte.get(k).get(1).matches("..")) {
									liste.set(29, "1000" + daten_einsatzorte.get(k).get(1));//AnsprId3
								}else if (daten_einsatzorte.get(k).get(1).matches("...")) {
									liste.set(29, "100" + daten_einsatzorte.get(k).get(1));//AnsprId3
								}
							}
						}
					}
    				
    			}
    				
			}
//    		liste.set(30, daten.get(i).get(1));//Info
    		liste.set(31, zeit);//Edit
    		if(startOld.before(time)){
    			liste.set(32, "1");//Unterlagen
    		}else{
    			liste.set(32, "0");//Unterlagen
    		}
    		
    		liste.set(33, zeit);//AntwortFrist
    		insertUpdateDeleteTable("INSERT INTO PRAKTIKANTEN " +
		                   "VALUES ('" + liste.get(0) +"','"+ liste.get(1) +"','"+ liste.get(2) +"','"+ liste.get(3) +"','"+ liste.get(4) +"','"+ liste.get(5) 
		                   +"','"+ liste.get(6) +"','"+ liste.get(7) +"','"+ liste.get(8) +"','"+ liste.get(9) +"','"+ liste.get(10) +"','"+ liste.get(11) +"','"+ liste.get(12)
		                   +"','"+ liste.get(13) +"','"+ liste.get(14) +"','"+ liste.get(15) +"','"+ liste.get(16) +"','"+ liste.get(17) +"','"+ liste.get(18) +"','"+ liste.get(19)
		                   +"','"+ liste.get(20) +"','"+ liste.get(21) +"','"+ liste.get(22) +"','"+ liste.get(23) +"','"+ liste.get(24) +"','"+ liste.get(25) +"','"+ liste.get(26)
		                   +"','"+ liste.get(27) +"','"+ liste.get(28) +"','"+ liste.get(29) +"','"+ liste.get(30) +"','"+ liste.get(31) +"','"+ liste.get(32) +"','"+ liste.get(33) + "');");
		}
//    	System.out.println(daten_praktikanten.toString());
    }
    /**
     * Methode die eine Verbindung zur SQLITE Datenbank aufbaut
     * @param d Pfad der neuen Datenbank
     * @return Verbindung ¸ber die dann Statements ¸bergeben werden kˆnnen
     */
    private static Connection connectToDatabase(String d)
	  {
	    Connection c = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection(d);
	      return c;
	    } catch ( Exception e ) {
	      e.printStackTrace();
	      System.exit(0);
	    }
//	    System.out.println("Datenbank verbunden");
	    return c;
	  }
    /**
     * F¸hrt den in sql ¸bergebenen Befehl ¸ber die Connection auf die SQLite Datenbank aus
     * @param sql Der auszuf¸hrende SQL Befehl
     */
    private static void insertUpdateDeleteTable(String sql){
		 {
			    Connection c = null;
			    Statement stmt = null;
			    try {
			      c = connectToDatabase("jdbc:sqlite:db/PraktikantenDB.db");
			      c.setAutoCommit(false);
			      
			      stmt = c.createStatement();
			      stmt.executeUpdate(sql);
			      stmt.close();
			      c.commit();
			      c.close();
			    } catch ( Exception e ) {
			      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			      System.exit(0);
			    }
//			    System.out.println("Daten erfolgreich eingef¸gt/ge‰ndert/gelˆscht");
			  }

	 }
    /**
     * Holt Daten , die in sql definiert sind , aus der Access Datenbank und gibt das Resultat als 2D-ArrayList
     * vom Typ String zur¸ck
     * @param sql Der Select Befehl f¸r die gew¸nschten Daten
     * @return 2D-ArrayList mit den Datens‰tzen und den dazugehˆrigen Daten return.get(0) = Datensatz return.get(0).get(0) Daten von Datensatz
     */
    private static ArrayList<ArrayList<String>> getData(String sql){
    	ArrayList<ArrayList<String>> daten = new ArrayList<ArrayList<String>>();
        try
        {
        	Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        	Connection conn=DriverManager.getConnection("jdbc:ucanaccess://c:/2012-10-28_Praktikanten-DB.mdb;password=Bacard1"); 
        	Statement s = conn.createStatement();
        	ResultSet rs = s.executeQuery(sql);
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
		      s.close();
		      conn.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        return daten;
    }
}