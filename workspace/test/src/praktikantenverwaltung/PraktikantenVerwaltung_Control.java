package praktikantenverwaltung;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JTable;

public class PraktikantenVerwaltung_Control {
	/**
	 * setzen der Fields
	 */
	private PraktikantenVerwaltung_ViewOrginal _view;
	private PraktikantenVerwaltung_ViewPrakt _viewprakt;
	private PraktikantenVerwaltung_ViewtabellePrakt _viewprakttabelle;
	private PraktikantenVerwaltung_ViewTabelleAnspr _viewansprtabelle;
	private PraktikantenVerwaltung_ViewAnspr _viewanspr;
	private PraktikantenVerwaltung_ViewStart _viewStart;
	private PraktikantenVerwaltung_Modell _model; 
	private Integer hoechstePraktID = 100000;
	private Integer hoechsteAnsprID = 100000;
	private String neuePraktID = "";
	private String neueAnsprID = "0";
	/**
	 * Konstruktor der Modell und View Initialisiert und die Listener anfügt
	 */
	public PraktikantenVerwaltung_Control(){
		this._model = new PraktikantenVerwaltung_Modell(); 
		this._view = new PraktikantenVerwaltung_ViewOrginal(); 
		this._viewStart = new PraktikantenVerwaltung_ViewStart();
		addListener();
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
//		System.out.println(daten);
		String ID;
		try {
			ID = daten.get(daten.size()-1).get(0).substring(2);
//			System.out.println("try");
		} catch (Exception e) {
			ID = hoechstePraktID.toString();
//			System.out.println("catch");
		}
//		System.out.println(ID);
		return Integer.parseInt(ID);
	}
	/**
	 * Gibt die höchste Ansprechpartner Id aus der Datenbank zurück
	 * @return
	 */
	public Integer getHoechsteAnsprID(){
		 ArrayList<ArrayList<String>> daten = new ArrayList<ArrayList<String>>();
		daten = _model.getData("SELECT * FROM ANSPRECHPARTNER;");
//		System.out.println(daten);
		String ID;
		try {
			ID = daten.get(daten.size()-1).get(0);
//			System.out.println("try");
		} catch (Exception e) {
			ID = hoechsteAnsprID.toString();
//			System.out.println("catch");
		}
//		System.out.println(ID);
		return Integer.parseInt(ID);
	}
	/**
	 * fügt die Listener dem View hinzu
	 */
	   private void addListener(){ 
//		            this._view.setPraktSpeichernListener(new PraktSpeichernListener());
		            this._view.setAnsprSpeichernListener(new AnsprSpeichernListener());
		            this._view.setAnsprLoeschenListener(new AnsprLoeschenListener());
		            this._view.setPraktLoeschenListener(new PraktLoeschenListener());
		            this._view.setAllePraktListener(new AllePraktListener());
		            this._view.setAlleAnsprListener(new AlleAnsprListener());
		            this._view.setSuchePraktListener(new SuchePraktListener());
		            this._view.setSucheAnsprListener(new SucheAnsprListener());
		            this._view.setNeuerEintragListener(new NeuerEintragListener());
		            this._view.setAnsprAusfuellListener1(new AnsprAusfuellListener1());
		            this._view.setAnsprAusfuellListener2(new AnsprAusfuellListener2());
		            this._view.setAnsprAusfuellListener3(new AnsprAusfuellListener3());
		            this._view.setSchulformAusfuellListener(new SchulformAusfuellListener());
		            this._view.setAnsprBearbeitenListener(new AnsprBearbeitenListener());
		            this._view.setPraktBearbeitenListener(new PraktBearbeitenListener());
		            this._view.setAnsprInfoListener(new AnsprInfoListener());
		            this._view.setPraktInfoListener(new PraktInfoListener());
		            this._viewStart.setNeuerPraktListener(new NeuerPraktListener());
		            this._viewStart.setNeuerAnsprListener(new NeuerAnsprListener());
		            this._viewStart.setTabellePraktListener(new TabellePraktListener());
		            this._viewStart.setTabelleAnsprListener(new TabelleAnsprListener());
	   } 
	   class NeuerPraktListener implements ActionListener{ 
           public void actionPerformed(ActionEvent e) { 
                _viewprakt = new PraktikantenVerwaltung_ViewPrakt();
                _viewprakt.setVisible(true);
            } 
	   }
	   class NeuerAnsprListener implements ActionListener{ 
           public void actionPerformed(ActionEvent e) { 
        	   _viewanspr = new PraktikantenVerwaltung_ViewAnspr();
        	   _viewanspr.setVisible(true);
            } 
	   }
	   class TabellePraktListener implements ActionListener{ 
           public void actionPerformed(ActionEvent e) {
        	   ArrayList<ArrayList<String>> daten = new ArrayList<ArrayList<String>>();
       			daten = _model.getData("SELECT NN , VN , STATUS , STARTDATUM , ENDDATUM , ANMERKPRAKT , EDIT FROM PRAKTIKANTEN ORDER BY NN;");
       			
        	   _viewprakttabelle = new PraktikantenVerwaltung_ViewtabellePrakt(daten);
        	   _viewprakttabelle.setVisible(true);
            } 
	   }
	   
	   class TabelleAnsprListener implements ActionListener{ 
           public void actionPerformed(ActionEvent e) { 
        	   ArrayList<ArrayList<String>> daten = new ArrayList<ArrayList<String>>();
        	   daten = _model.getData("SELECT NN , VN , TELE , MAIL , ABTEILUNG , RNR , BLOCKIERENVON , BLOCKIERENBIS FROM ANSPRECHPARTNER ORDER BY NN;");
      			
        	   _viewansprtabelle = new PraktikantenVerwaltung_ViewTabelleAnspr(daten);
        	   _viewansprtabelle.setVisible(true);
            } 
	   }
	   
	   
	   /**
	    * Innere Klasse für den Praktikanten Löschen Listener
	    * @author Barathum
	    *
	    */
	   class PraktLoeschenListener implements ActionListener{ 
           public void actionPerformed(ActionEvent e) { 
                JTable table = _view.getTable();
                int markierteReiheNR =  table.getSelectedRow();
                ArrayList<String> liste = new ArrayList<String>();
                String id = (String) table.getValueAt(markierteReiheNR, 0);
                liste.add(id);
                String sql;
                sql = schreibeEintragPraktsql(4, liste);
                _model.insertUpdateDeleteTable(sql);
                allePraktAnzeigen();
            } 
	   }
	   class PraktBearbeitenListener implements ActionListener{ 
           public void actionPerformed(ActionEvent e) { 
        	   ArrayList<ArrayList<String>> daten = new ArrayList<ArrayList<String>>();
        	   ArrayList<ArrayList<String>> datenAnspr1 = new ArrayList<ArrayList<String>>();
        	   ArrayList<ArrayList<String>> datenAnspr2 = new ArrayList<ArrayList<String>>();
        	   ArrayList<ArrayList<String>> datenAnspr3 = new ArrayList<ArrayList<String>>();
        	   JTable table = _view.getTable();
               int markierteReiheNR =  table.getSelectedRow();
               ArrayList<String> liste = new ArrayList<String>();
               String id = (String) table.getValueAt(markierteReiheNR, 0);
               liste.add(id);
               String sql;
               sql = getEintragPrakt(0, liste);
               daten = _model.getData(sql);
               _view.setInhaltPraktBearb(daten);
               datenAnspr1 = _model.getData("Select * from ANSPRECHPARTNER where ID = '" + daten.get(0).get(27) + "'");
               datenAnspr2 = _model.getData("Select * from ANSPRECHPARTNER where ID = '" + daten.get(0).get(28) + "'");
               datenAnspr3 = _model.getData("Select * from ANSPRECHPARTNER where ID = '" + daten.get(0).get(29) + "'");
               _view.setInhaltAnspr1(datenAnspr1);
               _view.setInhaltAnspr2(datenAnspr2);
               _view.setInhaltAnspr3(datenAnspr3);
               _view.setStatebutton_SpeichernPrakt(true);
              _view.showPraktBearb();
                
            } 
	   }
	   class PraktInfoListener implements ActionListener{ 
           public void actionPerformed(ActionEvent e) { 
        	   ArrayList<ArrayList<String>> daten = new ArrayList<ArrayList<String>>();
        	   ArrayList<ArrayList<String>> datenAnspr1 = new ArrayList<ArrayList<String>>();
        	   ArrayList<ArrayList<String>> datenAnspr2 = new ArrayList<ArrayList<String>>();
        	   ArrayList<ArrayList<String>> datenAnspr3 = new ArrayList<ArrayList<String>>();
        	   JTable table = _view.getTable();
               int markierteReiheNR =  table.getSelectedRow();
               ArrayList<String> liste = new ArrayList<String>();
               String id = (String) table.getValueAt(markierteReiheNR, 0);
               liste.add(id);
               String sql;
               sql = getEintragPrakt(0, liste);
               daten = _model.getData(sql);
               _view.setInhaltPraktBearb(daten);
               datenAnspr1 = _model.getData("Select * from ANSPRECHPARTNER where ID = '" + daten.get(0).get(27) + "'");
               datenAnspr2 = _model.getData("Select * from ANSPRECHPARTNER where ID = '" + daten.get(0).get(28) + "'");
               datenAnspr3 = _model.getData("Select * from ANSPRECHPARTNER where ID = '" + daten.get(0).get(29) + "'");
               _view.setInhaltAnspr1(datenAnspr1);
               _view.setInhaltAnspr2(datenAnspr2);
               _view.setInhaltAnspr3(datenAnspr3);
               _view.setStatebutton_SpeichernPrakt(false);
              _view.showPraktBearb();
                
            } 
	   }
	   /**
	    * Innere Klasse für den Ansprechpartner Speichern Listener
	    * Prüft ob der aktuell bearbeitete Ansprechpartner eine ID hat
	    * Ruft dann die SQL Methode auf
	    * gibt den fertigen SQL Befehl an das Modell weiter
	    * @author Barathum
	    *
	    */
	   class AnsprSpeichernListener implements ActionListener{ 
		   public void actionPerformed(ActionEvent e) { 
			   ArrayList<String> datensatzAnspr = _view.getInhaltAnsprBearb();
               String sql;
               sql = schreibeEintragAnsprsql(1, datensatzAnspr);
               _model.insertUpdateDeleteTable(sql);
//               HoechstePraktID = getHoechstePraktID();
           }  
	   }
	   /**
	    * Innere Klasse für den Ansprechpartner Löschen Listener
	    * @author Barathum
	    *
	    */
	   class AnsprLoeschenListener implements ActionListener{ 
           public void actionPerformed(ActionEvent e) { 
        	   JTable table = _view.getTable();
               int markierteReiheNR =  table.getSelectedRow();
               ArrayList<String> liste = new ArrayList<String>();
               String nn = (String) table.getValueAt(markierteReiheNR, 0);
               String vn = (String) table.getValueAt(markierteReiheNR, 1);
               String tele = (String) table.getValueAt(markierteReiheNR, 2);
               liste.add(nn);
               liste.add(vn);
               liste.add(tele);
               String sql;
               sql = schreibeEintragAnsprsql(4, liste);
               _model.insertUpdateDeleteTable(sql);
               alleAnprAnzeigen();
            } 
	   }
	   class AnsprBearbeitenListener implements ActionListener{ 
           public void actionPerformed(ActionEvent e) { 
        	   ArrayList<ArrayList<String>> daten = new ArrayList<ArrayList<String>>();
        	   JTable table = _view.getTable();
               int markierteReiheNR =  table.getSelectedRow();
               ArrayList<String> liste = new ArrayList<String>();
               String nn = (String) table.getValueAt(markierteReiheNR, 0);
               String vn = (String) table.getValueAt(markierteReiheNR, 1);
               String tele = (String) table.getValueAt(markierteReiheNR, 2);
               liste.add(nn);
               liste.add(vn);
               liste.add(tele);
               String sql;
               sql = getEintragAnspr(0, liste);
               daten = _model.getData(sql);
               _view.setInhaltAnsprBearb(daten);
               _view.setStatebutton_SpeichernAnspr(true);
               _view.setText_AnprbearbLabel("Ansprechpartner bearbeiten");
               _view.showAnsprBearb();
            } 
	   }
	   class AnsprInfoListener implements ActionListener{ 
           public void actionPerformed(ActionEvent e) { 
        	   ArrayList<ArrayList<String>> daten = new ArrayList<ArrayList<String>>();
        	   JTable table = _view.getTable();
               int markierteReiheNR =  table.getSelectedRow();
               ArrayList<String> liste = new ArrayList<String>();
               String nn = (String) table.getValueAt(markierteReiheNR, 0);
               String vn = (String) table.getValueAt(markierteReiheNR, 1);
               String tele = (String) table.getValueAt(markierteReiheNR, 2);
               liste.add(nn);
               liste.add(vn);
               liste.add(tele);
               String sql;
               sql = getEintragAnspr(0, liste);
               daten = _model.getData(sql);
               _view.setInhaltAnsprBearb(daten);
               _view.setStatebutton_SpeichernAnspr(false);
               _view.setText_AnprbearbLabel("Ansprechpartner Info");
               _view.showAnsprBearb();
            } 
	   }
	   /**
	    * Innere Klasse für den Listener für die gesamte Praktikanten Liste
	    * Wandelt den Return vom Model in ein Array um und übergibt dieses an den View
	    * Zeigt dann im view Das Listen Fenster auf
	    * @author Barathum
	    *
	    */
	   class AllePraktListener implements ActionListener{ 
           public void actionPerformed(ActionEvent e) { 
        	   allePraktAnzeigen();
            } 
	   }
	   public void allePraktAnzeigen(){
		   ArrayList<ArrayList<String>> daten = new ArrayList<ArrayList<String>>();
  			daten = _model.getData("SELECT ID , NN , VN , STATUS , STARTDATUM , ENDDATUM , ANMERKPRAKT , EDIT FROM PRAKTIKANTEN;");
  			Object[][] Array = new String[daten.size()][];
  			for (int i = 0; i < daten.size(); i++) {
  			    ArrayList<String> row = daten.get(i);
  			    Array[i] = row.toArray(new String[row.size()]);
  			}
  			_view.setDatenPraktListe(Array);
           _view.showAllePrakt();
	   }
	   /**
	    * Innere Klasse für den Listener für die gesamte Ansprechpartner Liste
	    * Wandelt den Return vom Model in ein Array um und übergibt dieses an den View
	    * Zeigt dann im view Das Listen Fenster auf
	    * @author Barathum
	    *
	    */
	   class AlleAnsprListener implements ActionListener{ 
           public void actionPerformed(ActionEvent e) { 
        	   alleAnprAnzeigen();
            } 
	   }
	   public void alleAnprAnzeigen(){
		   ArrayList<ArrayList<String>> daten = new ArrayList<ArrayList<String>>();
  			daten = _model.getData("SELECT NN , VN , TELE , MAIL , ABTEILUNG , RNR FROM ANSPRECHPARTNER;");
  			Object[][] Array = new String[daten.size()][];
  			for (int i = 0; i < daten.size(); i++) {
  			    ArrayList<String> row = daten.get(i);
  			    Array[i] = row.toArray(new String[row.size()]);
  			}
  			_view.setDatenAnsprListe(Array);
           _view.showAlleAnspr();
	   }
	   /**
	    * Innere Klasse für den Suche Praktikanten Listener
	    * holt Sich Sucheingabe aus View
	    * Wandelt dann die Suchanfrage in einen SQL Befehl um
	    * Wandelt das Ergebnis um und zeigt es im View an
	    * @author Barathum
	    *
	    */
	   class SuchePraktListener implements ActionListener{ 
           public void actionPerformed(ActionEvent e) { 
        	   ArrayList<ArrayList<String>> daten = new ArrayList<ArrayList<String>>();
        	   ArrayList<String> suche = new ArrayList<String>();
        	   suche = _view.getSuchePrakt();
        	   String suchekrit = new String();
        	   if(suche.get(0) == "Nachname"){
        		   suchekrit = "NN";
        	   }
       			daten = _model.getData("SELECT ID , NN , VN , STATUS , STARTDATUM , ENDDATUM , ANMERKPRAKT , EDIT FROM PRAKTIKANTEN WHERE " + suchekrit + " LIKE '"+ suche.get(1) +"%' ORDER BY NN DESC;");
       			Object[][] Array = new String[daten.size()][];
       			for (int i = 0; i < daten.size(); i++) {
       			    ArrayList<String> row = daten.get(i);
       			    Array[i] = row.toArray(new String[row.size()]);
       			}
        	   _view.setDatenPraktListe(Array);
                _view.showAllePrakt();
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
	    * Innere Klasse für den Suche Ansprechpartner Listener
	    * holt Sich Sucheingabe aus View
	    * Wandelt dann die Suchanfrage in einen SQL Befehl um
	    * Wandelt das Ergebnis um und zeigt es im View an
	    * @author Barathum
	    *
	    */
	   class SucheAnsprListener implements ActionListener{ 
		   public void actionPerformed(ActionEvent e) { 
        	   ArrayList<ArrayList<String>> daten = new ArrayList<ArrayList<String>>();
        	   ArrayList<String> suche = new ArrayList<String>();
        	   suche = _view.getSucheAnspr();
        	   String suchekrit = new String();
        	   if(suche.get(0) == "Nachname"){
        		   suchekrit = "NN";
        	   }
       			daten = _model.getData("SELECT NN , VN , TELE , MAIL , ABTEILUNG , RNR FROM ANSPRECHPARTNER WHERE " + suchekrit + " LIKE '" + suche.get(1) + "%' ORDER BY NN;");
       			Object[][] Array = new String[daten.size()][];
       			for (int i = 0; i < daten.size(); i++) {
       			    ArrayList<String> row = daten.get(i);
       			    Array[i] = row.toArray(new String[row.size()]);
       			}
        	   _view.setDatenAnsprListe(Array);
               _view.showAlleAnspr();
            }  
	   }
	   /**
	    * Innere Klasse für den Neuen Eintrag Listener
	    * Ruft die Methode für die Autovervollständigung der Comboboxen auf
	    * Setzt die Praktikante ID zurück
	    * @author Barathum
	    *
	    */
	   class NeuerEintragListener implements ActionListener{ 
		   public void actionPerformed(ActionEvent e) { 
			   NeuerEintrag();
            }  
	   }
	   public void NeuerEintrag(){
		   comboBox_autocomplete();
		   _view.setPraktId("");
		   _view.setNameAnspr1("");
		   _view.setNameAnspr2("");
		   _view.setNameAnspr3("");
		   _view.setAnspr1Id("0");
		   _view.setAnspr2Id("0");
		   _view.setAnspr3Id("0");
		   _view.resetAnprBearb();
		   _view.setStatebutton_SpeichernPrakt(true);
		   ArrayList<ArrayList<String>> liste = new ArrayList<ArrayList<String>>();
		   ArrayList<String> liste1 = new ArrayList<>();
		   for (int i = 0; i < 32; i++) {
			   liste1.add("");
		}
		   SimpleDateFormat sdfToDate = new SimpleDateFormat("dd.MM.yyyy");
		   String heute = sdfToDate.format(Calendar.getInstance().getTime());
		   liste1.add(4, "01.01.1995");
		   liste1.add(8, "Deutschland");
		   liste1.add(23, heute);
		   liste1.add(24, heute);
		   liste1.add(27, "0");
		   liste1.add(28, "0");
		   liste1.add(29, "0");
		   liste.add(liste1);
		   
		   _view.setInhaltPraktBearb(liste);
		   Anspr1Ausfuellen();
		   Anspr2Ausfuellen();
		   Anspr3Ausfuellen();
//		   ArrayList<ArrayList<String>> daten = new ArrayList<ArrayList<String>>();
//		   ArrayList<String> daten1 = new ArrayList<>();
//		   for (int i = 0; i < 8; i++) {
//			   daten1.add("");
//		   }
//		   daten1.add(0, "0");
//		   daten.add(daten1);
//		   _view.setInhaltAnspr1(daten);
//		   _view.setInhaltAnspr2(daten);
//		   _view.setInhaltAnspr3(daten);
	   }
	   /**
	    * Innere Klasse für das Ausfüllen der Ansprechpartner Felder
	    * @author Barathum
	    *
	    */
	   class AnsprAusfuellListener1 implements ActionListener{ 
		   public void actionPerformed(ActionEvent e) { 
			 Anspr1Ausfuellen(); 
            }  
	   }
	   public void Anspr1Ausfuellen(){
		   ArrayList<ArrayList<String>> daten = new ArrayList<ArrayList<String>>();
		   Integer id = new Integer(0);
    	   id = _view.getAnspr1ID();
//    	   System.out.println(name);
   			daten = _model.getData("SELECT ID , NN , VN , TELE , MAIL , ABTEILUNG , RNR , ANMERKEINSATZORT FROM ANSPRECHPARTNER WHERE ID LIKE '" + id + "%' ORDER BY NN;");
//   			System.out.println(daten);
   			_view.setInhaltAnspr1(daten);
	   }
	   /**
	    * Innere Klasse für das Ausfüllen der Ansprechpartner Felder
	    * @author Barathum
	    *
	    */
	   class AnsprAusfuellListener2 implements ActionListener{ 
		   public void actionPerformed(ActionEvent e) { 
			   Anspr2Ausfuellen();
            }  
	   }
	   public void Anspr2Ausfuellen(){
		   ArrayList<ArrayList<String>> daten = new ArrayList<ArrayList<String>>();
		   Integer id = new Integer(0);
    	   id = _view.getAnspr2ID();
   			daten = _model.getData("SELECT ID , NN , VN , TELE , MAIL , ABTEILUNG , RNR , ANMERKEINSATZORT FROM ANSPRECHPARTNER WHERE ID LIKE '" + id + "%' ORDER BY NN;");
   			_view.setInhaltAnspr2(daten);
	   }
	   /**
	    * Innere Klasse für das Ausfüllen der Ansprechpartner Felder
	    * @author Barathum
	    *
	    */
	   class AnsprAusfuellListener3 implements ActionListener{ 
		   public void actionPerformed(ActionEvent e) { 
			   Anspr3Ausfuellen();
            }  
	   }
	   public void Anspr3Ausfuellen(){
		   ArrayList<ArrayList<String>> daten = new ArrayList<ArrayList<String>>();
		   Integer id = new Integer(0);
    	   id = _view.getAnspr3ID();
   			daten = _model.getData("SELECT ID , NN , VN , TELE , MAIL , ABTEILUNG , RNR , ANMERKEINSATZORT FROM ANSPRECHPARTNER WHERE ID LIKE '" + id + "%' ORDER BY NN;");
   			_view.setInhaltAnspr3(daten);
	   }
	   class SchulformAusfuellListener implements ActionListener{ 
		   public void actionPerformed(ActionEvent e) { 
			   ArrayList<ArrayList<String>> daten = new ArrayList<ArrayList<String>>();
			   ArrayList<String> sname = new ArrayList<String>();
        	   sname = _view.getNameSchule();
       			daten = _model.getData("SELECT SCHULFORM FROM PRAKTIKANTEN WHERE SCHULE LIKE '" + sname.get(0) + "%' AND SCHULFORM IS NOT NULL AND SCHULFORM <> '';");
       			_view.setInhaltSchulform(daten);
            }  
	   }
	   /**
	    * Methode die den ComboBoxen Autovervollständigung gibt
	    * Holt Daten aus DB
	    * Wandelt in einen Vector um
	    * setzt im View den Inhalt der ComboBoxen
	    */
	   public void comboBox_autocomplete(){
		   /**
		    * Wohnort
		    */
       	   ArrayList<ArrayList<String>> daten_comboBoxItemsWohnort = new ArrayList<ArrayList<String>>();
    	   daten_comboBoxItemsWohnort = _model.getData("SELECT ORT FROM PRAKTIKANTEN WHERE ORT IS NOT NULL AND ORT <> 'null' GROUP BY ORT");
    	   Object[][] Array = new String[daten_comboBoxItemsWohnort.size()][];
  			for (int i = 0; i < daten_comboBoxItemsWohnort.size(); i++) {
  			    ArrayList<String> row = daten_comboBoxItemsWohnort.get(i);
  			    Array[i] = row.toArray(new String[row.size()]);
  			}
  			Vector<String> V1 = new Vector<String>();
  			for (int i = 0; i < Array.length; i++) {
				V1.add((String) Array[i][0]);
			}
//  			System.out.println(V1);
    	   _view.setComboBoxItems_wohn(V1);
    	   
    	   /**
    	    * Straße
    	    */
    	   ArrayList<ArrayList<String>> daten_comboBoxItemsStr= new ArrayList<ArrayList<String>>();
    	   daten_comboBoxItemsStr = _model.getData("SELECT STR FROM PRAKTIKANTEN WHERE STR IS NOT NULL AND STR <> 'null' GROUP BY STR");
    	   Array = new String[daten_comboBoxItemsStr.size()][];
  			for (int i = 0; i < daten_comboBoxItemsStr.size(); i++) {
  			    ArrayList<String> row = daten_comboBoxItemsStr.get(i);
  			    Array[i] = row.toArray(new String[row.size()]);
  			}
  			V1 = new Vector<String>();
  			for (int i = 0; i < Array.length; i++) {
				V1.add((String) Array[i][0]);
			}
//  			System.out.println(V1);
    	   _view.setComboBoxItems_str(V1);
    	   
    	   /**
    	    * Geburtsort
    	    */
    	   ArrayList<ArrayList<String>> daten_comboBoxItemsGeburtsort= new ArrayList<ArrayList<String>>();
    	   daten_comboBoxItemsGeburtsort = _model.getData("SELECT GO FROM PRAKTIKANTEN WHERE GO IS NOT NULL AND GO <> 'null' GROUP BY GO");
    	   Array = new String[daten_comboBoxItemsGeburtsort.size()][];
  			for (int i = 0; i < daten_comboBoxItemsGeburtsort.size(); i++) {
  			    ArrayList<String> row = daten_comboBoxItemsGeburtsort.get(i);
  			    Array[i] = row.toArray(new String[row.size()]);
  			}
  			V1 = new Vector<String>();
  			for (int i = 0; i < Array.length; i++) {
				V1.add((String) Array[i][0]);
			}
//  			System.out.println(V1);
    	   _view.setComboBoxItems_geburtsort(V1);
    	   
    	   /**
    	    * Schule
    	    */
    	   ArrayList<ArrayList<String>> daten_comboBoxItemsSchule= new ArrayList<ArrayList<String>>();
    	   daten_comboBoxItemsSchule = _model.getData("SELECT SCHULE FROM PRAKTIKANTEN WHERE SCHULE IS NOT NULL AND SCHULE <> 'null' GROUP BY SCHULE");
    	   Array = new String[daten_comboBoxItemsSchule.size()][];
  			for (int i = 0; i < daten_comboBoxItemsSchule.size(); i++) {
  			    ArrayList<String> row = daten_comboBoxItemsSchule.get(i);
  			    Array[i] = row.toArray(new String[row.size()]);
  			}
  			V1 = new Vector<String>();
  			for (int i = 0; i < Array.length; i++) {
				V1.add((String) Array[i][0]);
			}
//  			System.out.println(V1);
    	   _view.setComboBoxItems_schule(V1);
    	   
    	   /**
    	    * Schulform
    	    */
    	   ArrayList<ArrayList<String>> daten_comboBoxItemsSchulform= new ArrayList<ArrayList<String>>();
    	   daten_comboBoxItemsSchulform = _model.getData("SELECT SCHULFORM FROM PRAKTIKANTEN WHERE SCHULFORM IS NOT NULL AND SCHULFORM <> 'null' GROUP BY SCHULFORM");
    	   Array = new String[daten_comboBoxItemsSchulform.size()][];
  			for (int i = 0; i < daten_comboBoxItemsSchulform.size(); i++) {
  			    ArrayList<String> row = daten_comboBoxItemsSchulform.get(i);
  			    Array[i] = row.toArray(new String[row.size()]);
  			}
  			V1 = new Vector<String>();
  			for (int i = 0; i < Array.length; i++) {
				V1.add((String) Array[i][0]);
			}
  			_view.setComboBoxItems_schulform(V1);
  			
  			/**
  			 * Nachname Ansprechpartner
  			 */
  			ArrayList<ArrayList<String>> daten_comboBoxItemsNameAnspr= new ArrayList<ArrayList<String>>();
  			daten_comboBoxItemsNameAnspr = _model.getData("SELECT NN FROM ANSPRECHPARTNER WHERE NN IS NOT NULL AND NN <> 'null' ORDER BY NN");
     	   Array = new String[daten_comboBoxItemsNameAnspr.size()][];
   			for (int i = 0; i < daten_comboBoxItemsNameAnspr.size(); i++) {
   			    ArrayList<String> row = daten_comboBoxItemsNameAnspr.get(i);
   			    Array[i] = row.toArray(new String[row.size()]);
   			}
   			V1 = new Vector<String>();
   			for (int i = 0; i < Array.length; i++) {
 				V1.add((String) Array[i][0]);
 			}
//  			System.out.println(V1);
    	   _view.setComboBoxItems_AnsprNN(V1);
    	   
    	   /**
    	    * zeigt neuen Eintrag
    	    */
    	   _view.showNeuerEintrag();
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
	/**
	 * Methode zum schreiben der SQL Befehle für Ansprechpartner
	 * @param i update(1)/insert(2)
	 * @param liste Datenliste Ansprechpartner
	 * @return
	 */
	public String schreibeEintragAnsprsql(int i, ArrayList<String> liste){
		String sql;
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
		Timestamp time = new Timestamp(System.currentTimeMillis());
		String zeit = sdf.format(time);
		if (i == 1) {
			String info = "Daten geupdatet am " + zeit;
			_view.setInfoAnspr(info);
			sql = "UPDATE ANSPRECHPARTNER set NN = '" + liste.get(1) + "', VN = '" + liste.get(2) + "', TELE = '" + liste.get(3) +
					"', MAIL = '" + liste.get(4) + "', ABTEILUNG = '" + liste.get(5) + "', RNR = '" + liste.get(6) + "', ANMERKEINSATZORT = '" + liste.get(7) + "', INFO = '" + info + "' WHERE ID = '" + liste.get(0) + "';";
//			System.out.println("update");
		} else if (i == 2) {
			hoechsteAnsprID = getHoechsteAnsprID();
			hoechsteAnsprID++;
			neueAnsprID = hoechsteAnsprID.toString();
			String info = "Daten gespeichert am " + zeit;
			_view.setInfoAnspr(info);
			sql = "INSERT INTO ANSPRECHPARTNER " +
					"VALUES ('" + neueAnsprID +"','"+ liste.get(1) +"','"+ liste.get(2) +"','"+ liste.get(3) +"','"+ liste.get(4) +"','"+ liste.get(5) 
	                   +"','"+ liste.get(6) +"','"+ liste.get(7) +"','" + info + "');";
//			System.out.println("insert");
//			System.out.println(liste.get(1));
		}else if ( i== 4) {
			sql = "DELETE from ANSPRECHPARTNER where NN='" + liste.get(0) + "' AND VN ='" + liste.get(1) + "' AND TELE ='" + liste.get(2) + "';";
		} else {
			sql = "";
		}
		return sql;
	}
	public String getEintragAnspr(int i, ArrayList<String> liste){
		String sql;
		sql = "SELECT * from ANSPRECHPARTNER where NN='" + liste.get(0) + "' AND VN ='" + liste.get(1) + "' AND TELE ='" + liste.get(2) + "';";
		return sql;
	}
	
}
