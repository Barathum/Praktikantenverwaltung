package testpack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PraktikantenVerwaltung_Control {
	private PraktikantenVerwaltung_View _view; 
	private PraktikantenVerwaltung_Modell _model; 
	private Integer HoechstePraktID = 100000;
	private Integer HoechsteAnsprID = 100000;
	public PraktikantenVerwaltung_Control(){
		this._model = new PraktikantenVerwaltung_Modell(); 
		this._view = new PraktikantenVerwaltung_View(); 
//		_model.createTables();
		addListener();
	}
	public void showView(){ 
		        this._view.setVisible(true); 
	}
	private Integer getHoechstePraktID(){
		 ArrayList<ArrayList<String>> daten = new ArrayList<ArrayList<String>>();
		daten = _model.getData("SELECT * FROM PRAKTIKANTEN;");
//		System.out.println(daten);
		String ID;
		try {
			ID = daten.get(daten.size()-1).get(0).substring(2);
//			System.out.println("try");
		} catch (Exception e) {
			ID = HoechstePraktID.toString();
//			System.out.println("catch");
		}
		System.out.println(ID);
		return Integer.parseInt(ID);
	}
	private Integer getHoechsteAnsprID(){
		 ArrayList<ArrayList<String>> daten = new ArrayList<ArrayList<String>>();
		daten = _model.getData("SELECT * FROM ANSPRECHPARTNER;");
//		System.out.println(daten);
		String ID;
		try {
			ID = daten.get(daten.size()-1).get(0).substring(2);
//			System.out.println("try");
			System.out.println(ID);
		} catch (Exception e) {
			ID = HoechsteAnsprID.toString();
//			System.out.println("catch");
			System.out.println(ID);
		}
		System.out.println(ID);
		return Integer.parseInt(ID);
	}

	   private void addListener(){ 
		            this._view.setPraktSpeichernListener(new PraktSpeichernListener());
		            this._view.setAnsprSpeichernListener(new AnsprSpeichernListener());
		            this._view.setAnsprLoeschenListener(new AnsprLoeschenListener());
		            this._view.setPraktLoeschenListener(new PraktLoeschenListener());
	   } 
	   class PraktSpeichernListener implements ActionListener{ 
		   public void actionPerformed(ActionEvent e) { 
               ArrayList<String> datensatz = _view.getInhaltPrakt(); 
               ArrayList<ArrayList<String>> datensatzAnspr = _view.getInhaltAnspr();
               int updateOrInsert = 0;
               int updateOrInsertAnspr1 = 0;
               int updateOrInsertAnspr2 = 0;
               int updateOrInsertAnspr3 = 0;
               _model.connectToDatabase("jdbc:sqlite:PraktikantenDB.db");
               if (_view.getEditAnspr1()==true) {
            	   if (datensatzAnspr.get(0).get(0).equals("0") || datensatzAnspr.get(0).get(0) == null) {
            		   updateOrInsertAnspr1 = 2;
					} else {
							updateOrInsertAnspr1 = 1;
					}
               }
               if (_view.getEditAnspr2()==true) {
            	   if (datensatzAnspr.get(1).get(0).equals("0") || datensatzAnspr.get(0).get(0) == null) {
            		   updateOrInsertAnspr2 = 2;
					} else {
							updateOrInsertAnspr2 = 1;
					}
               }
               if (_view.getEditAnspr3()==true) {
            	   if (datensatzAnspr.get(2).get(0).equals("0") || datensatzAnspr.get(0).get(0) == null) {
	            		   updateOrInsertAnspr3 = 2;
					} else {
							updateOrInsertAnspr3 = 1;
					}
               }
               if (datensatz.get(0).equals("") || datensatz.get(0) == null) {
					updateOrInsert = 1;
				}
               String sql;
               sql = schreibeEintragPraktsql(updateOrInsert, datensatz);
               _model.insertUpdateDeleteTable(sql);
               sql = schreibeEintragAnsprsql(updateOrInsertAnspr1, datensatzAnspr.get(0));
               _model.insertUpdateDeleteTable(sql);
               sql = schreibeEintragAnsprsql(updateOrInsertAnspr2, datensatzAnspr.get(1));
               _model.insertUpdateDeleteTable(sql);
               sql = schreibeEintragAnsprsql(updateOrInsertAnspr3, datensatzAnspr.get(2));
               _model.insertUpdateDeleteTable(sql);
//               HoechstePraktID = getHoechstePraktID();
           } 
	   }
	   class PraktLoeschenListener implements ActionListener{ 
           public void actionPerformed(ActionEvent e) { 
                ArrayList<String> datensatz = _view.getInhaltPrakt(); 
                _model.connectToDatabase("jdbc:sqlite:PraktikantenDB.db"); 
                //hier überprüfen ob insert update
                _model.insertUpdateDeleteTable("//hier übergeben int(insert/update) sowie der liste");
               _view.setInfoPrakt("lol");
            } 
	   }
	   class AnsprSpeichernListener implements ActionListener{ 
		   public void actionPerformed(ActionEvent e) { 
               ArrayList<String> datensatz = null; 
               int updateOrInsert = 0;
               _model.connectToDatabase("jdbc:sqlite:PraktikantenDB.db"); 
               if (datensatz.get(0).equals("") || datensatz.get(0) == null) {
					updateOrInsert = 1;
				}
               String sql;
               sql = schreibeEintragPraktsql(updateOrInsert, datensatz);
               _model.insertUpdateDeleteTable(sql);
//               HoechstePraktID = getHoechstePraktID();
           }  
	   }
	   class AnsprLoeschenListener implements ActionListener{ 
           public void actionPerformed(ActionEvent e) { 
                ArrayList<String> datensatz = _view.getInhaltPrakt(); 
                _model.connectToDatabase("jdbc:sqlite:PraktikantenDB.db"); 
                //hier überprüfen ob insert update
                _model.insertUpdateDeleteTable("//hier übergeben int(insert/update) sowie der liste");
               _view.setInfoPrakt("lol");
            } 
	   }
	   

	public String schreibeEintragPraktsql(int i, ArrayList<String> liste){
		String sql;
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
		Timestamp time = new Timestamp(System.currentTimeMillis());
		String zeit = sdf.format(time);
		HoechstePraktID = getHoechstePraktID();
		HoechstePraktID++;
		String neuePraktID = "";
		neuePraktID = "SP" + HoechstePraktID.toString();
		if (i == 0) {
			_view.setInfoPrakt("Daten geupdatet am " + zeit);
			sql = "UPDATE PRAKTIKANTEN set ID = '" + liste.get(0) + "', ANREDE = '" + liste.get(1) + "', NN = '" + liste.get(2) + "', VN = '" + liste.get(3) +
					"', GB = '" + liste.get(4) + "', GO = '" + liste.get(5) + "', STR = '" + liste.get(6) + "', PLZ = '" + liste.get(7) + "', LAND = '" + liste.get(8) + 
					"', TELE = '" + liste.get(9) + "', MAIL = '" + liste.get(10) + "', MOBIL = '" + liste.get(11) + "', HAUSNR = '" + liste.get(12) + "', ORT = '" + liste.get(13) +
					"', GNN = '" + liste.get(14) + "', GVN = '" + liste.get(15) + "', SCHULE = '" + liste.get(16) + "', SCHULFORM = '" + liste.get(17) + "', PARTNERS = '" + liste.get(18) +
					"', ANMERKSCHULE = '" + liste.get(19) + "', MIKI = '" + liste.get(20) + "', GRAD = '" + liste.get(21) + "', ANMERKPERSON = '" + liste.get(22) + "', STARTDATUM = '" + liste.get(23) +
					"', ENDDATUM = '" + liste.get(24) + "', STATUS = '" + liste.get(25) + "', ANMERKPRAKT = '" + liste.get(26) + "', ANSPR1 = '" + liste.get(27) + "', ANSPR2 = '" + liste.get(28) +
					"', ANSPR3 = '" + liste.get(29) + "', INFO = '" + liste.get(30) + "', EDIT = '" + liste.get(31) +
					"';";
			System.out.println("update");
		} else {
		      _view.setInfoPrakt("Daten gespeichert am " + zeit);
			sql = "INSERT INTO PRAKTIKANTEN " +
	                   "VALUES ('" + neuePraktID +"','"+ liste.get(1) +"','"+ liste.get(2) +"','"+ liste.get(3) +"','"+ liste.get(4) +"','"+ liste.get(5) 
	                   +"','"+ liste.get(6) +"','"+ liste.get(7) +"','"+ liste.get(8) +"','"+ liste.get(9) +"','"+ liste.get(10) +"','"+ liste.get(11) +"','"+ liste.get(12)
	                   +"','"+ liste.get(13) +"','"+ liste.get(14) +"','"+ liste.get(15) +"','"+ liste.get(16) +"','"+ liste.get(17) +"','"+ liste.get(18) +"','"+ liste.get(19)
	                   +"','"+ liste.get(20) +"','"+ liste.get(21) +"','"+ liste.get(22) +"','"+ liste.get(23) +"','"+ liste.get(24) +"','"+ liste.get(25) +"','"+ liste.get(26)
	                   +"','"+ liste.get(27) +"','"+ liste.get(28) +"','"+ liste.get(29) +"','"+ liste.get(30) +"','"+ liste.get(31) +"');";
			System.out.println("insert");
		}
		return sql;
	}
	public String schreibeEintragAnsprsql(int i, ArrayList<String> liste){
		String sql;
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
		Timestamp time = new Timestamp(System.currentTimeMillis());
		String zeit = sdf.format(time);
		HoechsteAnsprID = getHoechsteAnsprID();
		System.out.println(HoechsteAnsprID);
		HoechsteAnsprID++;
		System.out.println(HoechsteAnsprID);
		String neueAnsprID = "";
		neueAnsprID = "AN" + HoechstePraktID.toString();
		if (i == 1) {
			_view.setInfoAnspr("Daten geupdatet am " + zeit);
			sql = "UPDATE ANSPRECHPARTNER set ID = '" + liste.get(0) + "', NN = '" + liste.get(1) + "', VN = '" + liste.get(2) + "', TELE = '" + liste.get(3) +
					"', MAIL = '" + liste.get(4) + "', ABTEILUNG = '" + liste.get(5) + "', RNR = '" + liste.get(6) + "', ANMERKEINSATZORT = '" + liste.get(7) + "', INFO = 'bla';";
			System.out.println("update");
		} else if (i == 2) {
		      _view.setInfoAnspr("Daten gespeichert am " + zeit);
			sql = "INSERT INTO ANSPRECHPARTNER " +
					"VALUES ('" + neueAnsprID +"','"+ liste.get(1) +"','"+ liste.get(2) +"','"+ liste.get(3) +"','"+ liste.get(4) +"','"+ liste.get(5) 
	                   +"','"+ liste.get(6) +"','"+ liste.get(7) +"',' bla ');";
//			System.out.println("insert");
//			System.out.println(liste.get(1));
		}else {
			sql = "";
		}
		return sql;
	}
}
