package bla;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import praktikantenverwaltung.PraktikantenVerwaltung_Modell;

public class AccessToSQLiteConverter
{
	private static PraktikantenVerwaltung_Modell _model;
    public static void main(String[] args)
    {
    	_model = new PraktikantenVerwaltung_Modell();
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
    			+ " , [Anmerkung] FROM [Praktika]");
    	
    	ArrayList<ArrayList<String>> daten_status = new ArrayList<ArrayList<String>>();
    	daten_status = getData("SELECT [ID] , [Bezeichnung] FROM [Status]");
    	
//    	UPDATE PRAKTIKANTEN set ANREDE = '" + liste.get(1) + "', NN = '" + liste.get(2) + "', VN = '" + liste.get(3) +
//				"', GB = '" + liste.get(4) + "', GO = '" + liste.get(5) + "', STR = '" + liste.get(6) + "', PLZ = '" + liste.get(7) + "', LAND = '" + liste.get(8) + 
//				"', TELE = '" + liste.get(9) + "', MAIL = '" + liste.get(10) + "', MOBIL = '" + liste.get(11) + "', HAUSNR = '" + liste.get(12) + "', ORT = '" + liste.get(13) +
//				"', GNN = '" + liste.get(14) + "', GVN = '" + liste.get(15) + "', SCHULE = '" + liste.get(16) + "', SCHULFORM = '" + liste.get(17) + "', PARTNERS = '" + liste.get(18) +
//				"', ANMERKSCHULE = '" + liste.get(19) + "', MIKI = '" + liste.get(20) + "', GRAD = '" + liste.get(21) + "', ANMERKPERSON = '" + liste.get(22) + "', STARTDATUM = '" + liste.get(23) +
//				"', ENDDATUM = '" + liste.get(24) + "', STATUS = '" + liste.get(25) + "', ANMERKPRAKT = '" + liste.get(26) + "', ANSPR1 = '" + liste.get(27) + "', ANSPR2 = '" + liste.get(28) +
//				"', ANSPR3 = '" + liste.get(29) + "', INFO = '" + liste.get(30) + "', EDIT = '" + liste.get(31) +"', UNTERLAGENVOLLST = '" + liste.get(32) + "', ANTWORTBIS = '" + liste.get(33) +
//				"' WHERE ID = '" + liste.get(0) + "';"
    	for (int i = 0; i < daten_praktikanten.size(); i++) {
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
    		liste.set(16, daten_praktikanten.get(i).get(14));//Schule
//    		liste.set(17, daten.get(i).get(1));//Schulform
//    		liste.set(18, daten.get(i).get(1));//Partnerschule
//    		liste.set(19, daten.get(i).get(1));//AnmerkSchule
    		if (daten_praktikanten.get(i).get(15).equals("TRUE")) {
    			liste.set(20, "Ja");
			} else {
				liste.set(20, "Nein");
			}
    		liste.set(21, daten_praktikanten.get(i).get(16));//Grad
    		liste.set(22, daten_praktikanten.get(i).get(19));//Anmerkperson
    		for (int j = 0; j < daten_praktika.size(); j++) {
    			if (daten_praktika.get(j).get(0).equals(daten_praktikanten.get(i).get(0))) {
    				java.util.Date startOld = new java.util.Date();
    				java.util.Date endOld = new java.util.Date();
    	    		try {
    					startOld = sdfold.parse(daten_praktika.get(j).get(1));
    					endOld = sdfold.parse(daten_praktika.get(j).get(2));
    				} catch (ParseException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    	    		System.out.println(sdf.format(startOld));
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
    		liste.set(27, "0");//AnsprId1
    		liste.set(28, "0");//AnsprId2
    		liste.set(29, "0");//AnsprId3
//    		liste.set(30, daten.get(i).get(1));//Info
//    		liste.set(31, daten.get(i).get(1));//Edit
    		liste.set(31, zeit);//Edit
    		liste.set(32, "0");//Unterlagen
    		liste.set(33, zeit);//AntwortFrist
    		_model.insertUpdateDeleteTable("INSERT INTO PRAKTIKANTEN " +
		                   "VALUES ('" + liste.get(0) +"','"+ liste.get(1) +"','"+ liste.get(2) +"','"+ liste.get(3) +"','"+ liste.get(4) +"','"+ liste.get(5) 
		                   +"','"+ liste.get(6) +"','"+ liste.get(7) +"','"+ liste.get(8) +"','"+ liste.get(9) +"','"+ liste.get(10) +"','"+ liste.get(11) +"','"+ liste.get(12)
		                   +"','"+ liste.get(13) +"','"+ liste.get(14) +"','"+ liste.get(15) +"','"+ liste.get(16) +"','"+ liste.get(17) +"','"+ liste.get(18) +"','"+ liste.get(19)
		                   +"','"+ liste.get(20) +"','"+ liste.get(21) +"','"+ liste.get(22) +"','"+ liste.get(23) +"','"+ liste.get(24) +"','"+ liste.get(25) +"','"+ liste.get(26)
		                   +"','"+ liste.get(27) +"','"+ liste.get(28) +"','"+ liste.get(29) +"','"+ liste.get(30) +"','"+ liste.get(31) +"','"+ liste.get(32) +"','"+ liste.get(33) + "');");
		}
//    	System.out.println(daten_praktikanten.toString());
    }
    public static ArrayList<ArrayList<String>> getData(String sql){
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