package testpack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PraktikantenVerwaltung_Control {
	private PraktikantenVerwaltung_View _view; 
	private PraktikantenVerwaltung_Modell _model; 
	public PraktikantenVerwaltung_Control(){
		this._model = new PraktikantenVerwaltung_Modell(); 
		this._view = new PraktikantenVerwaltung_View(); 
		addListener();
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
		                _model.connectToDatabase("jdbc:sqlite:test.db"); 
		                //hier überprüfen ob insert update
		                _model.insertUpdateDeleteTable("//hier übergeben int(insert/update) sowie der liste");
		               _view.setInfoPrakt("lol");
		            } 
	   }
	   class PraktLoeschenListener implements ActionListener{ 
           public void actionPerformed(ActionEvent e) { 
                ArrayList<String> datensatz = _view.getInhaltPrakt(); 
                _model.connectToDatabase("jdbc:sqlite:test.db"); 
                //hier überprüfen ob insert update
                _model.insertUpdateDeleteTable("//hier übergeben int(insert/update) sowie der liste");
               _view.setInfoPrakt("lol");
            } 
	   }
	   class AnsprSpeichernListener implements ActionListener{ 
           public void actionPerformed(ActionEvent e) { 
                ArrayList<String> datensatz = _view.getInhaltPrakt(); 
                _model.connectToDatabase("jdbc:sqlite:test.db"); 
                //hier überprüfen ob insert update
                _model.insertUpdateDeleteTable("//hier übergeben int(insert/update) sowie der liste");
               _view.setInfoPrakt("lol");
            } 
	   }
	   class AnsprLoeschenListener implements ActionListener{ 
           public void actionPerformed(ActionEvent e) { 
                ArrayList<String> datensatz = _view.getInhaltPrakt(); 
                _model.connectToDatabase("jdbc:sqlite:test.db"); 
                //hier überprüfen ob insert update
                _model.insertUpdateDeleteTable("//hier übergeben int(insert/update) sowie der liste");
               _view.setInfoPrakt("lol");
            } 
	   }
	   

	public void schreibeEintrag(){
		
	}
}
