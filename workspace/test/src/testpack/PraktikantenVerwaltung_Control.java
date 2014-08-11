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
			System.out.println("try");
		} catch (Exception e) {
			ID = HoechstePraktID.toString();
			System.out.println("catch");
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
               int updateOrInsert = 0;
               _model.connectToDatabase("jdbc:sqlite:PraktikantenDB.db"); 
               if (datensatz.get(0).equals("") || datensatz.get(0) == null) {
					updateOrInsert = 1;
				}
               String sql;
               sql = schreibeEintragsql(updateOrInsert, datensatz);
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
                ArrayList<String> datensatz = _view.getInhaltPrakt(); 
                int updateOrInsert = 0;
                _model.connectToDatabase("jdbc:sqlite:PraktikantenDB.db"); 
                if (datensatz.get(0).equals("") || datensatz.get(0) == null) {
					updateOrInsert = 1;
				}
                String sql;
                sql = schreibeEintragsql(updateOrInsert, datensatz);
                _model.insertUpdateDeleteTable("//hier übergeben sql");
               _view.setInfoPrakt("lol");
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
	   

	public String schreibeEintragsql(int i, ArrayList<String> liste){
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
}
