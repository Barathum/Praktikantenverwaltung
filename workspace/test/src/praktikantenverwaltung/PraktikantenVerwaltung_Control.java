package praktikantenverwaltung;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
/**
 * 
 * @author Barathum
 * Control Klasse die vor dem eigentlichen Fenster existiert und allgemeine Methoden enth�llt
 */
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
	private String passwort = "";
	private JPasswordField pass;
	/**
	 * Konstruktor der das Modell Initialisiert und die Listener anf�gt
	 * au�erdem wird die Methode PasswortPrompt aufgerufen
	 */
	public PraktikantenVerwaltung_Control(){
		this._crypt = new Cryptor();
		this._model = new PraktikantenVerwaltung_Modell(_crypt); 
//		_model.createTables();
		this._control = this;
		passWortPromptBeforeStart();
//		System.out.println(passwort);
	}
	/**
	 * Erstellt ein Passwort eingabe Fenster
	 * �berpr�ft, ob die Datenbank vorhanden und mit dem angegebenen Passwort entschl�sselt werden kann
	 * sollte dies gelingen wird die viewStart initialisiert
	 * wenn das passwort falsch ist erscheint das eingabefenster f�r das passwort erneut
	 */
	private void passWortPromptBeforeStart(){
		JPanel panelpw = new JPanel();
		JLabel labelpw = new JLabel("Passwort eingeben:");
		pass = new JPasswordField(10);
		panelpw.add(labelpw);
		panelpw.add(pass);
		String[] options = new String[]{"OK", "Schlie�en"};
		int option = JOptionPane.showOptionDialog(null, panelpw, "The title",
		JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
		null, options, options[0]);
		char[] password = null;
		if(option == 0) // pressing OK button
		{
		password = pass.getPassword();
		try {
		/**
		* Versucht die Datenbank zu entschl�sseln
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
	/**
	 * Getter f�r das Passwort
	 * @return gibt das passwort als String zur�ck
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
	 * gibt die Aktuell h�chste Praktikanten ID aus der Datenbank zur�ck
	 * @return Integerwert der aktuell h�chsten Praktikanten ID
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
	 * Gibt die h�chste Ansprechpartner Id aus der Datenbank zur�ck
	 * @return Integerwert der aktuell h�chsten Ansprechpartner ID
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
	 * f�gt die Listener dem View hinzu
	 */
	   private void addListener(){ 
		            this._viewStart.setNeuerPraktListener(new NeuerPraktListener());
		            this._viewStart.setNeuerAnsprListener(new NeuerAnsprListener());
		            this._viewStart.setTabellePraktListener(new TabellePraktListener());
		            this._viewStart.setTabelleAnsprListener(new TabelleAnsprListener());
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
	    * erstellt und ihm Alle Eintr�ge der Praktikanten Liste �bergibt
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
	    * erstellt und ihm Alle Eintr�ge der Ansprechpartner Liste �bergibt
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
	    * n�tig f�r die Listen der Autocomplete Textfelder
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
