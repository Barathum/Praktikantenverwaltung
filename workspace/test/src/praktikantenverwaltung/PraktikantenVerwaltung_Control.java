package praktikantenverwaltung;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import org.apache.commons.io.FileUtils;

public class PraktikantenVerwaltung_Control {
	/**
	 * setzen der Fields
	 */
	private PraktikantenVerwaltung_ViewPrakt _viewprakt;
	private PraktikantenVerwaltung_ViewtabellePrakt _viewprakttabelle;
	private PraktikantenVerwaltung_ViewTabelleAnspr _viewansprtabelle;
	private PraktikantenVerwaltung_ViewAnspr _viewanspr;
	private PraktikantenVerwaltung_ViewStart _viewStart;
	private PraktikantenVerwaltung_Modell _model; 
	private PraktikantenVerwaltung_Control _control; 
	private Cryptor _crypt;
	private Integer hoechstePraktID = 100000;
	private Integer hoechsteAnsprID = 100000;
	private String neuePraktID = "";
	private String passwort = "";
	/**
	 * Konstruktor der Modell und View Initialisiert und die Listener anfügt
	 */
	public PraktikantenVerwaltung_Control(){
		this._crypt = new Cryptor();
		this._model = new PraktikantenVerwaltung_Modell(_crypt); 
//		_model.createTables();
		this._control = this;
		passWortPromptBeforeStart();
//		System.out.println(passwort);
	}
	private void passWortPromptBeforeStart(){
		JPanel panelpw = new JPanel();
		JLabel labelpw = new JLabel("Passwort eingeben:");
		JPasswordField pass = new JPasswordField(10);
		panelpw.add(labelpw);
		panelpw.add(pass);
		String[] options = new String[]{"OK", "Schließen"};
		int option = JOptionPane.showOptionDialog(null, panelpw, "The title",
		                         JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
		                         null, options, options[0]);
		char[] password = null;
		if(option == 0) // pressing OK button
		{
			password = pass.getPassword();
			try {
				/**
				 * Versucht die Datenbank zu entschlüsseln
				 */
				_crypt.decryptFile( "db/PraktikantenDB.db", new String(password) );
				_model.disconnectFromDatabase("jdbc:sqlite:db/PraktikantenDB.db" , new String(password));
				passwort = new String(password);
				_model.setPasswort(passwort);
				ArrayList<ArrayList<String>> daten = new ArrayList<ArrayList<String>>();
				daten = _model.getData("SELECT ID , NN , VN , EDIT FROM PRAKTIKANTEN ORDER BY NN;");
				this._viewStart = new PraktikantenVerwaltung_ViewStart(this._model , this._control , daten);
				addListener();
			} catch (GeneralSecurityException e) {
				/**
				 * Das Passwort war falsch
				 */
				System.out.println("Passwort Falsch!");
				passWortPromptBeforeStart();
			} catch (IOException e) {
				/**
				 * Die Datei mit .encrypted ist nicht vorhanden
				 */
				System.out.println("Datei nicht gefunden!");
				System.exit(0);
			}
		}else {
			System.exit(0);
		}
	}
	protected String getPasswort() {
		return this.passwort;
	}
	/**
	 * Zeigt den View an
	 */
	public void showView(){ 
		this._viewStart.setVisible(true); 
	}
	/**
	 * gibt die Aktuell höchste Praktikanten ID aus der Datenbank zurück
	 * @return
	 */
	public Integer getHoechstePraktID(){
		 ArrayList<ArrayList<String>> daten = new ArrayList<ArrayList<String>>();
		daten = _model.getData("SELECT * FROM PRAKTIKANTEN;");
		String ID;
		try {
			ID = daten.get(daten.size()-1).get(0).substring(2);
		} catch (Exception e) {
			ID = hoechstePraktID.toString();
		}
		return Integer.parseInt(ID);
	}
	/**
	 * Gibt die höchste Ansprechpartner Id aus der Datenbank zurück
	 * @return
	 */
	public Integer getHoechsteAnsprID(){
		 ArrayList<ArrayList<String>> daten = new ArrayList<ArrayList<String>>();
		daten = _model.getData("SELECT * FROM ANSPRECHPARTNER;");
		String ID;
		try {
			ID = daten.get(daten.size()-1).get(0);
		} catch (Exception e) {
			ID = hoechsteAnsprID.toString();
		}
		return Integer.parseInt(ID);
	}
	/**
	 * fügt die Listener dem View hinzu
	 */
	   private void addListener(){ 
		            this._viewStart.setNeuerPraktListener(new NeuerPraktListener());
		            this._viewStart.setNeuerAnsprListener(new NeuerAnsprListener());
		            this._viewStart.setTabellePraktListener(new TabellePraktListener());
		            this._viewStart.setTabelleAnsprListener(new TabelleAnsprListener());
	   } 
	   class NeuerPraktListener implements ActionListener{ 
           public void actionPerformed(ActionEvent e) { 
                _viewprakt = new PraktikantenVerwaltung_ViewPrakt(_control, _model);
                _viewprakt.setVisible(true);
//        		ArrayList<String> liste = _viewprakt.getInhaltPrakt();
//        		String sql;
//        		sql = "INSERT INTO PRAKTIKANTEN " +
//                        "VALUES ('" + neuePraktID +"','"+ liste.get(1) +"','"+ liste.get(2) +"','"+ liste.get(3) +"','"+ liste.get(4) +"','"+ liste.get(5) 
//                        +"','"+ liste.get(6) +"','"+ liste.get(7) +"','"+ liste.get(8) +"','"+ liste.get(9) +"','"+ liste.get(10) +"','"+ liste.get(11) +"','"+ liste.get(12)
//                        +"','"+ liste.get(13) +"','"+ liste.get(14) +"','"+ liste.get(15) +"','"+ liste.get(16) +"','"+ liste.get(17) +"','"+ liste.get(18) +"','"+ liste.get(19)
//                        +"','"+ liste.get(20) +"','"+ liste.get(21) +"','"+ liste.get(22) +"','"+ liste.get(23) +"','"+ liste.get(24) +"','"+ liste.get(25) +"','"+ liste.get(26)
//                        +"','"+ liste.get(27) +"','"+ liste.get(28) +"','"+ liste.get(29) +"','"+ liste.get(30) +"','"+ liste.get(31) +"','"+ liste.get(32) +"','"+ liste.get(33) +"');";
//        		for (int i = 0; i < 200; i++) {
//        			_model.insertUpdateDeleteTable(sql);
//				}
            } 
	   }
	   class NeuerAnsprListener implements ActionListener{ 
           public void actionPerformed(ActionEvent e) {
        	   _viewanspr = new PraktikantenVerwaltung_ViewAnspr(_control, _model);
        	   _viewanspr.setVisible(true);
            } 
	   }
	   class TabellePraktListener implements ActionListener{ 
           public void actionPerformed(ActionEvent e) {
        	   ArrayList<ArrayList<String>> daten = new ArrayList<ArrayList<String>>();
       			daten = _model.getData("SELECT NN , VN , STATUS , STARTDATUM , ENDDATUM , ANMERKPRAKT , EDIT FROM PRAKTIKANTEN ORDER BY NN;");
       			
        	   _viewprakttabelle = new PraktikantenVerwaltung_ViewtabellePrakt(_control, _model , daten);
        	   _viewprakttabelle.setVisible(true);
            } 
	   }
	   
	   class TabelleAnsprListener implements ActionListener{ 
           public void actionPerformed(ActionEvent e) { 
        	   ArrayList<ArrayList<String>> daten = new ArrayList<ArrayList<String>>();
        	   daten = _model.getData("SELECT NN , VN , TELE , MAIL , ABTEILUNG , RNR , BLOCKIERENVON , BLOCKIERENBIS FROM ANSPRECHPARTNER ORDER BY NN;");
      			
        	   _viewansprtabelle = new PraktikantenVerwaltung_ViewTabelleAnspr(_control, _model , daten);
        	   _viewansprtabelle.setVisible(true);
            } 
	   }
	   
		public Object[][] ArrayListtoArray(ArrayList<ArrayList<String>> daten){
			Object[][] Array = new String[daten.size()][];
				for (int i = 0; i < daten.size(); i++) {
				    ArrayList<String> row = daten.get(i);
				    Array[i] = row.toArray(new String[row.size()]);
				}
				return Array;
		}
	   /**
	    * Methode zum schreiben der SQL Befehle für Praktikanten
	    * @param i update(0)/insert(1)
	    * @param liste Datenliste Praktikant
	    * @return
	    */
	public String schreibeEintragPraktsql(int i, ArrayList<String> liste){
		String sql;
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
		Timestamp time = new Timestamp(System.currentTimeMillis());
		String zeit = sdf.format(time);
		if (i == 0) {
			String info = "Daten geupdatet am " + zeit;
			_viewprakt.setInfoPrakt(info);
			liste.set(30, info);
			sql = "UPDATE PRAKTIKANTEN set ANREDE = '" + liste.get(1) + "', NN = '" + liste.get(2) + "', VN = '" + liste.get(3) +
					"', GB = '" + liste.get(4) + "', GO = '" + liste.get(5) + "', STR = '" + liste.get(6) + "', PLZ = '" + liste.get(7) + "', LAND = '" + liste.get(8) + 
					"', TELE = '" + liste.get(9) + "', MAIL = '" + liste.get(10) + "', MOBIL = '" + liste.get(11) + "', HAUSNR = '" + liste.get(12) + "', ORT = '" + liste.get(13) +
					"', GNN = '" + liste.get(14) + "', GVN = '" + liste.get(15) + "', SCHULE = '" + liste.get(16) + "', SCHULFORM = '" + liste.get(17) + "', PARTNERS = '" + liste.get(18) +
					"', ANMERKSCHULE = '" + liste.get(19) + "', MIKI = '" + liste.get(20) + "', GRAD = '" + liste.get(21) + "', ANMERKPERSON = '" + liste.get(22) + "', STARTDATUM = '" + liste.get(23) +
					"', ENDDATUM = '" + liste.get(24) + "', STATUS = '" + liste.get(25) + "', ANMERKPRAKT = '" + liste.get(26) + "', ANSPR1 = '" + liste.get(27) + "', ANSPR2 = '" + liste.get(28) +
					"', ANSPR3 = '" + liste.get(29) + "', INFO = '" + liste.get(30) + "', EDIT = '" + liste.get(31) +
					"' WHERE ID = '" + liste.get(0) + "';";
//			System.out.println("update");
		}else if (i == 4) {
			sql = "DELETE from PRAKTIKANTEN where ID='" + liste.get(0) + "';";
		}else{
			hoechstePraktID = getHoechstePraktID();
			hoechstePraktID++;
			neuePraktID = "SP" + hoechstePraktID.toString();
			
			String info = "Daten gespeichert am " + zeit;
			_viewprakt.setInfoPrakt(info);
			liste.set(30, info);
			sql = "INSERT INTO PRAKTIKANTEN " +
	                   "VALUES ('" + neuePraktID +"','"+ liste.get(1) +"','"+ liste.get(2) +"','"+ liste.get(3) +"','"+ liste.get(4) +"','"+ liste.get(5) 
	                   +"','"+ liste.get(6) +"','"+ liste.get(7) +"','"+ liste.get(8) +"','"+ liste.get(9) +"','"+ liste.get(10) +"','"+ liste.get(11) +"','"+ liste.get(12)
	                   +"','"+ liste.get(13) +"','"+ liste.get(14) +"','"+ liste.get(15) +"','"+ liste.get(16) +"','"+ liste.get(17) +"','"+ liste.get(18) +"','"+ liste.get(19)
	                   +"','"+ liste.get(20) +"','"+ liste.get(21) +"','"+ liste.get(22) +"','"+ liste.get(23) +"','"+ liste.get(24) +"','"+ liste.get(25) +"','"+ liste.get(26)
	                   +"','"+ liste.get(27) +"','"+ liste.get(28) +"','"+ liste.get(29) +"','"+ liste.get(30) +"','"+ liste.get(31) +"');";
//			System.out.println("insert");
		}
		return sql;
	}
	public String getEintragPrakt(int i, ArrayList<String> liste){
		String sql;
		sql = "SELECT * from PRAKTIKANTEN where ID='" + liste.get(0) + "';";
		return sql;
	}
	public String getEintragAnspr(int i, ArrayList<String> liste){
		String sql;
		sql = "SELECT * from ANSPRECHPARTNER where NN='" + liste.get(0) + "' AND VN ='" + liste.get(1) + "' AND TELE ='" + liste.get(2) + "';";
		return sql;
	}
	
}
