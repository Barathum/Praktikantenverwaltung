package praktikantenverwaltung;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
/**
 * 
 * @author Barathum
 * Control Klasse die vor dem eigentlichen Fenster existiert und allgemeine Methoden enthällt
 */
public class PraktikantenVerwaltung_Control {
	/**
	 * setzen der Fields
	 */
	private AnsichtPraktikant_Interface _viewprakt;
	private PraktikantenVerwaltung_ViewtabellePrakt _viewprakttabelle;
	private PraktikantenVerwaltung_ViewTabelleAnspr _viewansprtabelle;
	private PraktikantenVerwaltung_ViewAnspr _viewanspr;
	private PraktikantenVerwaltung_AuslastDiagramme _viewAuslast;
	private PraktikantenVerwaltung_ViewStart _viewStart;
	private PraktikantenVerwaltung_Modell _model; 
	private PraktikantenVerwaltung_Control _control; 
	private PraktikantenVerwaltung_ViewPasswortaendern _viewPasswortaendern;
	private Cryptor _crypt;
	private Integer hoechstePraktID = 100000;
	private Integer hoechsteAnsprID = 100000;
	private String passwort = "";
	private JPasswordField pass;
	private SHAtoTXTFile _sha = new SHAtoTXTFile();
	private ImageIcon img;
	/**
	 * Konstruktor der das Modell Initialisiert und die Listener anfügt
	 * außerdem wird die Methode PasswortPrompt aufgerufen
	 */
	public PraktikantenVerwaltung_Control(){
		this._crypt = new Cryptor();
		this._model = new PraktikantenVerwaltung_Modell(_crypt); 
		
		img = new ImageIcon(new ImageIcon("img/database.png").getImage());
		
//		ArrayList<ImageIcon> icons = new ArrayList<ImageIcon>();
//		icons.add(getImage("someImage16x16.gif"));
//		icons.add(getImage("someImage32x32.gif"));
//		window.setIconImages(icons);
//		_model.createTables();
		this._control = this;
		passWortPromptBeforeStart();
//		System.out.println(passwort);
	}
	public ImageIcon getImg(){
		return this.img;
	}
	
	/**
	 * Erstellt ein Passwort eingabe Fenster
	 * überprüft, ob die Datenbank vorhanden und mit dem angegebenen Passwort entschlüsselt werden kann
	 * sollte dies gelingen wird die viewStart initialisiert
	 * wenn das passwort falsch ist erscheint das eingabefenster für das passwort erneut
	 */
	private void passWortPromptBeforeStart(){
		JPanel panelpw = new JPanel();
		JLabel labelpw = new JLabel("Passwort eingeben:");
		pass = new JPasswordField(10);
		panelpw.add(labelpw);
		panelpw.add(pass);
		String[] options = new String[]{"OK", "Schließen"};
		int option = JOptionPane.showOptionDialog(null, panelpw, "Passworteingabe",
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
		_crypt.decryptFile( "db/PraktikantenDB.db", _sha.generateHash(new String(password)) , false );
		passwort = _sha.generateHash(new String(password));
		_model.disconnectFromDatabase("jdbc:sqlite:db/PraktikantenDB.db" , passwort);
		_model.setPasswort(passwort);
		ArrayList<ArrayList<String>> daten = new ArrayList<ArrayList<String>>();
		daten = _model.getData("SELECT ID , NN , VN , EDIT FROM PRAKTIKANTEN ORDER BY NN;");
		this._viewStart = new PraktikantenVerwaltung_ViewStart(this._model , this._control , daten);
		addListener();
		} catch (GeneralSecurityException e) {
			try {
				_crypt.decryptFile( "db/keyfile.txt", new String(password) , true );
			} catch (GeneralSecurityException e3) {
				
			} catch (IOException e3){
				System.out.println("KeyFile fehlt!");
			}
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
	/**
	 * Getter für das Passwort
	 * @return gibt das passwort als String zurück
	 */
	protected String getPasswort() {
		return this.passwort;
	}
	/**
	 * Zeigt den View an
	 */
	protected void showView(){ 
		this._viewStart.setVisible(true); 
	}
	/**
	 * gibt die Aktuell höchste Praktikanten ID aus der Datenbank zurück
	 * @return Integerwert der aktuell höchsten Praktikanten ID
	 */
	protected Integer getHoechstePraktID(){
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
	 * @return Integerwert der aktuell höchsten Ansprechpartner ID
	 */
	protected Integer getHoechsteAnsprID(){
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
		            this._viewStart.setAuslastListener(new AuslastListener());
		            this._viewStart.setPasswortAendernListener(new passwortAendernListerner());
		            this._viewStart.setDatabaseExportListener(new datenbankExportListener());
	   } 
	   /**
	    * Listener für das Exportieren der Datenbank
	    * @author Barathum
	    *
	    */
	   private class datenbankExportListener implements ActionListener{
		   public void actionPerformed(ActionEvent e) { 
			   JPanel panelpw = new JPanel();
				JLabel labelpw = new JLabel("Wollen Sie die Datenbank wirklich exportieren? Diese liegt dann offen im Ordner DB");
				panelpw.add(labelpw);
				String[] options = new String[]{"Ja", "Nein"};
				int option = JOptionPane.showOptionDialog(null, panelpw, "",
				JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
				null, options, options[1]);
				if(option == 0) // pressing OK button
				{
				try {
				/**
				* Versucht die Datenbank zu entschlüsseln
				*/
				_crypt.decryptFile("db/PraktikantenDB.db", getPasswort(), false);
				} catch (GeneralSecurityException e2) {
				/**
				* Das Passwort war falsch
				*/
				System.out.println("Kein Zugriff!");
				} catch (IOException e2) {
				/**
				* Die Datei mit .encrypted ist nicht vorhanden
				*/
				System.out.println("Datei nicht gefunden!");
				}
				}
           } 
	   }
	   
	   /**
	    * Listener zum Aufrufen des Passwort ändern Fensters
	    * @author Barathum
	    *
	    */
	   private class passwortAendernListerner implements ActionListener{
		   public void actionPerformed(ActionEvent e) { 
               _viewPasswortaendern = new PraktikantenVerwaltung_ViewPasswortaendern(_control, _model);
               _viewPasswortaendern.setVisible(true);
           } 
	   }
	   
	   /**
	    * Listener zum aufrufen des Auslastdiagramm Fensters
	    * @author Barathum
	    *
	    */
	   private class AuslastListener implements ActionListener{ 
           public void actionPerformed(ActionEvent e) { 
        	   ArrayList<ArrayList<String>> datenanspr = new ArrayList<ArrayList<String>>();
        	   datenanspr = _model.getData("SELECT * FROM ANSPRECHPARTNER Order By NN;");
        	   ArrayList<ArrayList<String>> datenprakt = new ArrayList<ArrayList<String>>();
        	   datenprakt = _model.getData("SELECT * FROM PRAKTIKANTEN Order By NN;");
                _viewAuslast = new PraktikantenVerwaltung_AuslastDiagramme(_control, _model , datenanspr , datenprakt);
                _viewAuslast.setVisible(true);
            } 
	   }
	   /**
	    * 
	    * @author Barathum
	    * ListenerKlasse die ein neues Object vom Typen ViewPrakt erstellt
	    */
	   private class NeuerPraktListener implements ActionListener{ 
           public void actionPerformed(ActionEvent e) { 
                _viewprakt = new PraktikantenVerwaltung_ViewPrakt(_control, _model);
                _viewprakt.setVisible(true);
            } 
	   }
	   /**
	    * 
	    * @author Barathum
	    * ListenerKlasse die ein neues Object vom Typen ViewAnspr erstellt
	    */
	   private class NeuerAnsprListener implements ActionListener{ 
           public void actionPerformed(ActionEvent e) {
        	   _viewanspr = new PraktikantenVerwaltung_ViewAnspr(_control, _model);
        	   _viewanspr.setVisible(true);
            } 
	   }
	   /**
	    * 
	    * @author Barathum
	    * ListenerKlasse die ein neues Object vom Typen ViewTabellePrakt 
	    * erstellt und ihm Alle Einträge der Praktikanten Liste übergibt
	    */
	   private class TabellePraktListener implements ActionListener{ 
           public void actionPerformed(ActionEvent e) {
        	   ArrayList<ArrayList<String>> daten = new ArrayList<ArrayList<String>>();
       			daten = _model.getData("SELECT NN , VN , STATUS , STARTDATUM , ENDDATUM , ANMERKPRAKT , EDIT FROM PRAKTIKANTEN ORDER BY NN;");
       			
        	   _viewprakttabelle = new PraktikantenVerwaltung_ViewtabellePrakt(_control, _model , daten);
        	   _viewprakttabelle.setVisible(true);
            } 
	   }
	   /**
	    * 
	    * @author Barathum
	    * ListenerKlasse die ein neues Object vom Typen ViewTabelleAnspr 
	    * erstellt und ihm Alle Einträge der Ansprechpartner Liste übergibt
	    */
	   private class TabelleAnsprListener implements ActionListener{ 
           public void actionPerformed(ActionEvent e) { 
        	   ArrayList<ArrayList<String>> daten = new ArrayList<ArrayList<String>>();
        	   daten = _model.getData("SELECT NN , VN , TELE , MAIL , ABTEILUNG , RNR , BLOCKIERENVON , BLOCKIERENBIS FROM ANSPRECHPARTNER ORDER BY NN;");
      			
        	   _viewansprtabelle = new PraktikantenVerwaltung_ViewTabelleAnspr(_control, _model , daten);
        	   _viewansprtabelle.setVisible(true);
            } 
	   }
	   /**
	    * Methode, die eine 2D ArrayList in ein 2D Array umwandelt
	    * nötig für die Listen der Autocomplete Textfelder
	    * @param daten Die umzuwandelnde 2D ArrayList
	    * @return Das fertige 2D Array
	    */
		public Object[][] ArrayListtoArray(ArrayList<ArrayList<String>> daten){
			Object[][] Array = new String[daten.size()][];
				for (int i = 0; i < daten.size(); i++) {
				    ArrayList<String> row = daten.get(i);
				    Array[i] = row.toArray(new String[row.size()]);
				}
				return Array;
		}
}
