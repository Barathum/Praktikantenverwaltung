package testpack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

public class PraktikantenVerwaltung_Control {
	private PraktikantenVerwaltung_View _view; 
	private PraktikantenVerwaltung_Modell _model; 
	private Integer HoechstePraktID = 100000;
	private Integer HoechsteAnsprID = 100000;
	private String neuePraktID = "";
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
			ID = daten.get(daten.size()-1).get(0);
//			System.out.println("try");
		} catch (Exception e) {
			ID = HoechsteAnsprID.toString();
//			System.out.println("catch");
		}
//		System.out.println(ID);
		return Integer.parseInt(ID);
	}

	   private void addListener(){ 
		            this._view.setPraktSpeichernListener(new PraktSpeichernListener());
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
               _view.setPraktId(neuePraktID);
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
	   class AllePraktListener implements ActionListener{ 
           public void actionPerformed(ActionEvent e) { 
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
	   }
	   class AlleAnsprListener implements ActionListener{ 
           public void actionPerformed(ActionEvent e) { 
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
	   }
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
	   class NeuerEintragListener implements ActionListener{ 
		   public void actionPerformed(ActionEvent e) { 
			   comboBox_autocomplete();
			   _view.setPraktId("");
            }  
	   }
	   class AnsprAusfuellListener1 implements ActionListener{ 
		   public void actionPerformed(ActionEvent e) { 
			   ArrayList<ArrayList<String>> daten = new ArrayList<ArrayList<String>>();
			   ArrayList<String> name = new ArrayList<String>();
        	   name = _view.getNameAnspr1();
        	   System.out.println(name);
       			daten = _model.getData("SELECT ID , NN , VN , TELE , MAIL , ABTEILUNG , RNR , ANMERKEINSATZORT FROM ANSPRECHPARTNER WHERE NN LIKE '" + name.get(0) + "%' ORDER BY NN;");
//       			System.out.println(daten);
       			_view.setInhaltAnspr1(daten);
            }  
	   }
	   class AnsprAusfuellListener2 implements ActionListener{ 
		   public void actionPerformed(ActionEvent e) { 
			   ArrayList<ArrayList<String>> daten = new ArrayList<ArrayList<String>>();
			   ArrayList<String> name = new ArrayList<String>();
        	   name = _view.getNameAnspr2();
       			daten = _model.getData("SELECT ID , NN , VN , TELE , MAIL , ABTEILUNG , RNR , ANMERKEINSATZORT FROM ANSPRECHPARTNER WHERE NN LIKE '" + name.get(0) + "%' ORDER BY NN;");
       			_view.setInhaltAnspr2(daten);
            }  
	   }
	   class AnsprAusfuellListener3 implements ActionListener{ 
		   public void actionPerformed(ActionEvent e) { 
			   ArrayList<ArrayList<String>> daten = new ArrayList<ArrayList<String>>();
			   ArrayList<String> name = new ArrayList<String>();
        	   name = _view.getNameAnspr3();
       			daten = _model.getData("SELECT ID , NN , VN , TELE , MAIL , ABTEILUNG , RNR , ANMERKEINSATZORT FROM ANSPRECHPARTNER WHERE NN LIKE '" + name.get(0) + "%' ORDER BY NN;");
       			_view.setInhaltAnspr3(daten);
            }  
	   }
	   public void comboBox_autocomplete(){
       	   ArrayList<ArrayList<String>> daten_comboBoxItemsWohnort = new ArrayList<ArrayList<String>>();
    	   daten_comboBoxItemsWohnort = _model.getData("SELECT ORT FROM PRAKTIKANTEN GROUP BY ORT");
    	   Object[][] Array = new String[daten_comboBoxItemsWohnort.size()][];
  			for (int i = 0; i < daten_comboBoxItemsWohnort.size(); i++) {
  			    ArrayList<String> row = daten_comboBoxItemsWohnort.get(i);
  			    Array[i] = row.toArray(new String[row.size()]);
  			}
  			Vector V1 = new Vector<String>();
  			for (int i = 0; i < Array.length; i++) {
				V1.add(Array[i][0]);
			}
  			System.out.println(V1);
    	   _view.setComboBoxItems_wohn(V1);
    	   
    	   ArrayList<ArrayList<String>> daten_comboBoxItemsStr= new ArrayList<ArrayList<String>>();
    	   daten_comboBoxItemsStr = _model.getData("SELECT STR FROM PRAKTIKANTEN GROUP BY STR");
    	   Array = new String[daten_comboBoxItemsStr.size()][];
  			for (int i = 0; i < daten_comboBoxItemsStr.size(); i++) {
  			    ArrayList<String> row = daten_comboBoxItemsStr.get(i);
  			    Array[i] = row.toArray(new String[row.size()]);
  			}
  			V1 = new Vector<String>();
  			for (int i = 0; i < Array.length; i++) {
				V1.add(Array[i][0]);
			}
  			System.out.println(V1);
    	   _view.setComboBoxItems_str(V1);
    	   
    	   ArrayList<ArrayList<String>> daten_comboBoxItemsGeburtsort= new ArrayList<ArrayList<String>>();
    	   daten_comboBoxItemsGeburtsort = _model.getData("SELECT GO FROM PRAKTIKANTEN GROUP BY GO");
    	   Array = new String[daten_comboBoxItemsGeburtsort.size()][];
  			for (int i = 0; i < daten_comboBoxItemsGeburtsort.size(); i++) {
  			    ArrayList<String> row = daten_comboBoxItemsGeburtsort.get(i);
  			    Array[i] = row.toArray(new String[row.size()]);
  			}
  			V1 = new Vector<String>();
  			for (int i = 0; i < Array.length; i++) {
				V1.add(Array[i][0]);
			}
  			System.out.println(V1);
    	   _view.setComboBoxItems_geburtsort(V1);
    	   
    	   ArrayList<ArrayList<String>> daten_comboBoxItemsSchule= new ArrayList<ArrayList<String>>();
    	   daten_comboBoxItemsSchule = _model.getData("SELECT SCHULE FROM PRAKTIKANTEN GROUP BY SCHULE");
    	   Array = new String[daten_comboBoxItemsSchule.size()][];
  			for (int i = 0; i < daten_comboBoxItemsSchule.size(); i++) {
  			    ArrayList<String> row = daten_comboBoxItemsSchule.get(i);
  			    Array[i] = row.toArray(new String[row.size()]);
  			}
  			V1 = new Vector<String>();
  			for (int i = 0; i < Array.length; i++) {
				V1.add(Array[i][0]);
			}
  			System.out.println(V1);
    	   _view.setComboBoxItems_schule(V1);
    	   
    	   ArrayList<ArrayList<String>> daten_comboBoxItemsSchulform= new ArrayList<ArrayList<String>>();
    	   daten_comboBoxItemsSchulform = _model.getData("SELECT SCHULFORM FROM PRAKTIKANTEN GROUP BY SCHULFORM");
    	   Array = new String[daten_comboBoxItemsSchulform.size()][];
  			for (int i = 0; i < daten_comboBoxItemsSchulform.size(); i++) {
  			    ArrayList<String> row = daten_comboBoxItemsSchulform.get(i);
  			    Array[i] = row.toArray(new String[row.size()]);
  			}
  			V1 = new Vector<String>();
  			for (int i = 0; i < Array.length; i++) {
				V1.add(Array[i][0]);
			}
  			
  			ArrayList<ArrayList<String>> daten_comboBoxItemsNameAnspr= new ArrayList<ArrayList<String>>();
  			daten_comboBoxItemsNameAnspr = _model.getData("SELECT NN FROM ANSPRECHPARTNER ORDER BY NN");
     	   Array = new String[daten_comboBoxItemsNameAnspr.size()][];
   			for (int i = 0; i < daten_comboBoxItemsNameAnspr.size(); i++) {
   			    ArrayList<String> row = daten_comboBoxItemsNameAnspr.get(i);
   			    Array[i] = row.toArray(new String[row.size()]);
   			}
   			V1 = new Vector<String>();
   			for (int i = 0; i < Array.length; i++) {
 				V1.add(Array[i][0]);
 			}
  			System.out.println(V1);
    	   _view.setComboBoxItems_AnsprNN(V1);
    	   
    	   _view.showNeuerEintrag();
	   }

	public String schreibeEintragPraktsql(int i, ArrayList<String> liste){
		String sql;
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
		Timestamp time = new Timestamp(System.currentTimeMillis());
		String zeit = sdf.format(time);
		if (i == 0) {
			_view.setInfoPrakt("Daten geupdatet am " + zeit);
			sql = "UPDATE PRAKTIKANTEN set ANREDE = '" + liste.get(1) + "', NN = '" + liste.get(2) + "', VN = '" + liste.get(3) +
					"', GB = '" + liste.get(4) + "', GO = '" + liste.get(5) + "', STR = '" + liste.get(6) + "', PLZ = '" + liste.get(7) + "', LAND = '" + liste.get(8) + 
					"', TELE = '" + liste.get(9) + "', MAIL = '" + liste.get(10) + "', MOBIL = '" + liste.get(11) + "', HAUSNR = '" + liste.get(12) + "', ORT = '" + liste.get(13) +
					"', GNN = '" + liste.get(14) + "', GVN = '" + liste.get(15) + "', SCHULE = '" + liste.get(16) + "', SCHULFORM = '" + liste.get(17) + "', PARTNERS = '" + liste.get(18) +
					"', ANMERKSCHULE = '" + liste.get(19) + "', MIKI = '" + liste.get(20) + "', GRAD = '" + liste.get(21) + "', ANMERKPERSON = '" + liste.get(22) + "', STARTDATUM = '" + liste.get(23) +
					"', ENDDATUM = '" + liste.get(24) + "', STATUS = '" + liste.get(25) + "', ANMERKPRAKT = '" + liste.get(26) + "', ANSPR1 = '" + liste.get(27) + "', ANSPR2 = '" + liste.get(28) +
					"', ANSPR3 = '" + liste.get(29) + "', INFO = '" + liste.get(30) + "', EDIT = '" + liste.get(31) +
					"' WHERE ID = '" + liste.get(0) + "';";
			System.out.println("update");
		} else {
			HoechstePraktID = getHoechstePraktID();
			HoechstePraktID++;
			neuePraktID = "SP" + HoechstePraktID.toString();
			
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
		HoechsteAnsprID++;
		String neueAnsprID = "";
		neueAnsprID = HoechsteAnsprID.toString();
		if (i == 1) {
			_view.setInfoAnspr("Daten geupdatet am " + zeit);
			sql = "UPDATE ANSPRECHPARTNER set NN = '" + liste.get(1) + "', VN = '" + liste.get(2) + "', TELE = '" + liste.get(3) +
					"', MAIL = '" + liste.get(4) + "', ABTEILUNG = '" + liste.get(5) + "', RNR = '" + liste.get(6) + "', ANMERKEINSATZORT = '" + liste.get(7) + "', INFO = 'bla' WHERE ID = '" + liste.get(0) + "';";
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
