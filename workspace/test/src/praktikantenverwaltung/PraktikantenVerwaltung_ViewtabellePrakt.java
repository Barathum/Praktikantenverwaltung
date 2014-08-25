package praktikantenverwaltung;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class PraktikantenVerwaltung_ViewtabellePrakt extends JFrame implements ActionListener{

	/**
	 * erstellen der Fields
	 */
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	private JTable table_prakt;
	private JScrollPane scrollPane_Suchliste;
	private String[] spaltennamenprak = {
            "Nachname",
            "Vorname",
            "Status",
            "Startdatum",
            "Enddatum", 
            "Anmerkung",
            "Letzte �nderung"};
	private Object[][] datenprak;
	private JButton btn_praktbearbeiten;
	private JButton btn_praktloeschen;
	private JButton btn_praktNachrichtSchreiben;
	private PraktikantenVerwaltung_Modell _model;
	private PraktikantenVerwaltung_Control _control;
	private PlatzhalterReplacerUndDokumentWriter _replacer;
	private JPanel panel;
	private JTextField textFieldNNPrakt;
	private JTextField textFieldVNPrakt;
	private JTextField textFieldStatusPrakt;
	private JTextField textFieldStartPrakt;
	private JTextField textFieldEndPrakt;
	private JTextField textFieldAnmerkPrakt;
	private JTextField textFieldEditPrakt;
	private JButton button_aktualisieren;
	
	public PraktikantenVerwaltung_ViewtabellePrakt(ArrayList<ArrayList<String>> Tabellen_Eintraege){
		this._model = new PraktikantenVerwaltung_Modell();
		this._control = new PraktikantenVerwaltung_Control();
		this._replacer = new PlatzhalterReplacerUndDokumentWriter();
		setDatenPrakt(_control.ArrayListtoArray(Tabellen_Eintraege));
		setResizable(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(20, 20, 1280, 720);
		
		
		mainPanel = new JPanel();
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new CardLayout(0, 0));
		
		JPanel panel_tabllenPrakt = new JPanel();
		mainPanel.add(panel_tabllenPrakt, "card_4");
		panel_tabllenPrakt.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		panel_tabllenPrakt.add(panel, BorderLayout.NORTH);
		
		textFieldNNPrakt = new JTextField();
		textFieldNNPrakt.setColumns(10);
		
		textFieldVNPrakt = new JTextField();
		textFieldVNPrakt.setColumns(10);
		
		textFieldStatusPrakt = new JTextField();
		textFieldStatusPrakt.setColumns(10);
		
		textFieldStartPrakt = new JTextField();
		textFieldStartPrakt.setColumns(10);
		
		textFieldEndPrakt = new JTextField();
		textFieldEndPrakt.setColumns(10);
		
		textFieldAnmerkPrakt = new JTextField();
		textFieldAnmerkPrakt.setColumns(10);
		
		textFieldEditPrakt = new JTextField();
		textFieldEditPrakt.setColumns(10);
		
		button_aktualisieren = new JButton("");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(textFieldNNPrakt, GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
					.addGap(1)
					.addComponent(textFieldVNPrakt, GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
					.addGap(1)
					.addComponent(textFieldStatusPrakt, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
					.addGap(1)
					.addComponent(textFieldStartPrakt, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
					.addGap(1)
					.addComponent(textFieldEndPrakt, GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
					.addGap(1)
					.addComponent(textFieldAnmerkPrakt, GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
					.addGap(1)
					.addComponent(textFieldEditPrakt, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE)
					.addGap(1)
					.addComponent(button_aktualisieren, GroupLayout.PREFERRED_SIZE, 34, Short.MAX_VALUE)
					.addGap(3))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
					.addComponent(textFieldNNPrakt, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
					.addComponent(textFieldVNPrakt, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
				.addComponent(textFieldStatusPrakt, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
				.addComponent(textFieldStartPrakt, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
				.addComponent(textFieldEndPrakt, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
				.addComponent(textFieldAnmerkPrakt, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
				.addComponent(textFieldEditPrakt, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(1)
					.addComponent(button_aktualisieren, GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
					.addGap(1))
		);
		panel.setLayout(gl_panel);
		
		scrollPane_Suchliste = new JScrollPane();
		panel_tabllenPrakt.add(scrollPane_Suchliste);
		scrollPane_Suchliste.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		JPanel panel_10 = new JPanel();
		panel_tabllenPrakt.add(panel_10, BorderLayout.SOUTH);
		
		btn_praktbearbeiten = new JButton("Bearbeiten");
		
		btn_praktloeschen = new JButton("L\u00F6schen");
		
		btn_praktNachrichtSchreiben = new JButton("Nachricht erstellen");
		GroupLayout gl_panel_10 = new GroupLayout(panel_10);
		gl_panel_10.setHorizontalGroup(
			gl_panel_10.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_10.createSequentialGroup()
					.addGap(20)
					.addComponent(btn_praktbearbeiten)
					.addGap(18)
					.addComponent(btn_praktloeschen, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
					.addGap(47)
					.addComponent(btn_praktNachrichtSchreiben)
					.addContainerGap(888, Short.MAX_VALUE))
		);
		gl_panel_10.setVerticalGroup(
			gl_panel_10.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_10.createSequentialGroup()
					.addGap(9)
					.addGroup(gl_panel_10.createParallelGroup(Alignment.BASELINE)
						.addComponent(btn_praktbearbeiten)
						.addComponent(btn_praktloeschen)
						.addComponent(btn_praktNachrichtSchreiben))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_10.setLayout(gl_panel_10);
		
		this.setTabelleFilterListener(new TabelleFilterListener());
		this.setLoeschenListener(new PraktLoeschenListener());
		this.setBearbeitenListener(new PraktBearbeitenListener());
		this.setNachrichtSendenListener(new NachrichtSendenListener());
		button_aktualisieren.addActionListener(this);
		textFieldNNPrakt.addActionListener(enterAction);
		textFieldVNPrakt.addActionListener(enterAction);
		textFieldStatusPrakt.addActionListener(enterAction);
		textFieldStartPrakt.addActionListener(enterAction);
		textFieldEndPrakt.addActionListener(enterAction);
		textFieldAnmerkPrakt.addActionListener(enterAction);
		textFieldEditPrakt.addActionListener(enterAction);
		updateTablePrakt();
	}
	Action enterAction = new AbstractAction()
	{
		private static final long serialVersionUID = 1L;

		@Override
	    public void actionPerformed(ActionEvent e)
	    {
//	    	try {
	    		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		        manager.getFocusOwner().transferFocus();
//			} catch (Exception e2) {
//				// TODO: handle exception
//			}
	    }
	};
	private void setDatenPrakt(Object[][] daten){
		this.datenprak = daten;
	}

	public void updateTablePrakt(){
		table_prakt = new JTable(new DefaultTableModel_PraktikantenVerwaltung(spaltennamenprak, datenprak));
		table_prakt.setSelectionMode(0);
		table_prakt.setAutoCreateRowSorter(true);
		scrollPane_Suchliste.setViewportView(table_prakt);
	}
	private void setLoeschenListener(ActionListener l){
		this.btn_praktloeschen.addActionListener(l);
	}
	private void setTabelleFilterListener(DocumentListener l){
	        this.textFieldNNPrakt.getDocument().addDocumentListener(l);
	        this.textFieldVNPrakt.getDocument().addDocumentListener(l);
	        this.textFieldStatusPrakt.getDocument().addDocumentListener(l);
	        this.textFieldStartPrakt.getDocument().addDocumentListener(l);
	        this.textFieldEndPrakt.getDocument().addDocumentListener(l);
	        this.textFieldAnmerkPrakt.getDocument().addDocumentListener(l);
	        this.textFieldEditPrakt.getDocument().addDocumentListener(l);
	        
	}
	class PraktLoeschenListener implements ActionListener{ 
	    public void actionPerformed(ActionEvent e) { 
	 	   JTable table = getTable();
	        int markierteReiheNR =  table.getSelectedRow();
	        ArrayList<String> liste = new ArrayList<String>();
	        if(markierteReiheNR >= 0){
		        String nn = (String) table.getValueAt(markierteReiheNR, 0);
		        String vn = (String) table.getValueAt(markierteReiheNR, 1);
		        String status = (String) table.getValueAt(markierteReiheNR, 2);
		        liste.add(nn);
		        liste.add(vn);
		        liste.add(status);
		        String sql;
		        sql = loescheEintragPrakt(liste);
		        _model.insertUpdateDeleteTable(sql);
		        filterPrakt();
	        }
	     } 
	   }
	class PraktBearbeitenListener implements ActionListener{ 
        public void actionPerformed(ActionEvent e) { 
     	   ArrayList<ArrayList<String>> daten = new ArrayList<ArrayList<String>>();
     	   JTable table = getTable();
            int markierteReiheNR =  table.getSelectedRow();
            ArrayList<String> liste = new ArrayList<String>();
            if(markierteReiheNR >= 0){
            	String nn = (String) table.getValueAt(markierteReiheNR, 0);
                String vn = (String) table.getValueAt(markierteReiheNR, 1);
                String status = (String) table.getValueAt(markierteReiheNR, 2);
                liste.add(nn);
                liste.add(vn);
                liste.add(status);
                String sql;
                sql = getEintragPrakt(liste);
                daten = _model.getData(sql);
                PraktikantenVerwaltung_ViewPrakt _viewprakt = new PraktikantenVerwaltung_ViewPrakt(daten);
          	   _viewprakt.setVisible(true);
            }
         } 
	   }
	public String getEintragPrakt(ArrayList<String> liste){
		String sql;
		sql = "SELECT * from PRAKTIKANTEN where NN='" + liste.get(0) + "' AND VN ='" + liste.get(1) + "' AND STATUS ='" + liste.get(2) + "';";
		return sql;
	}
	private JTable getTable(){
		return table_prakt;
	}
	private String loescheEintragPrakt(ArrayList<String> liste){
		String sql;
		sql = "DELETE from PRAKTIKANTEN where NN='" + liste.get(0) + "' AND VN ='" + liste.get(1) + "' AND STATUS ='" + liste.get(2) + "';";
		return sql;
	}
	private void setBearbeitenListener(ActionListener l){
		this.btn_praktbearbeiten.addActionListener(l);
	}
	private void setNachrichtSendenListener(ActionListener l){
		this.btn_praktNachrichtSchreiben.addActionListener(l);
	}
	class TabelleFilterListener implements DocumentListener{ 
	
		@Override
		public void changedUpdate(DocumentEvent e) {
			filterPrakt();
		}
	
		@Override
		public void insertUpdate(DocumentEvent e) {
			filterPrakt();
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			filterPrakt();
		}
	}
	
	public void filterPrakt() { 
		   ArrayList<ArrayList<String>> daten = new ArrayList<ArrayList<String>>();
		   ArrayList<String> datenTextfields = new ArrayList<String>();
		   datenTextfields = getInhaltSuchFelders();
		   daten = _model.getData("SELECT NN , VN , STATUS , STARTDATUM , ENDDATUM , ANMERKPRAKT , EDIT FROM PRAKTIKANTEN WHERE NN LIKE '%" + datenTextfields.get(0) + "%' AND VN LIKE '%" + datenTextfields.get(1) + "%' "
		   		+ "AND STATUS LIKE '%" + datenTextfields.get(2) + "%' AND STARTDATUM LIKE '%" + datenTextfields.get(3) + "%' AND ENDDATUM LIKE '%" + datenTextfields.get(4) + "%' AND ANMERKPRAKT LIKE '%" + datenTextfields.get(5) + "%' AND EDIT LIKE '%" + datenTextfields.get(6)
		   		+ "%' ORDER BY NN;");
		   setDatenPrakt(_control.ArrayListtoArray(daten));
		   updateTablePrakt();
	}
	class NachrichtSendenListener implements ActionListener{ 
		@Override
		public void actionPerformed(ActionEvent arg0) {
			ArrayList<ArrayList<String>> daten = new ArrayList<ArrayList<String>>();
	     	   JTable table = getTable();
	            int markierteReiheNR =  table.getSelectedRow();
	            ArrayList<String> liste = new ArrayList<String>();
	            ArrayList<String> platzhalter = new ArrayList<String>();
	            platzhalter.add("<<ID>>");
	            platzhalter.add("<<Anrede>>");
	            platzhalter.add("<<Nachname>>");
	            platzhalter.add("<<Vorname>>");
	            platzhalter.add("<<Geburtsdatum>>");
	            platzhalter.add("<<Geburtsort>>");
	            platzhalter.add("<<Stra�e>>");
	            platzhalter.add("<<PLZ>>");
	            platzhalter.add("<<Land>>");
	            platzhalter.add("<<Telefon>>");
	            platzhalter.add("<<Mail>>");
	            platzhalter.add("<<Mobil>>");
	            platzhalter.add("<<Hausnummer>>");
	            platzhalter.add("<<Ort>>");
	            platzhalter.add("<<VertreterNachname>>");
	            platzhalter.add("<<VertreterVorname>>");
	            platzhalter.add("<<Schule>>");
	            platzhalter.add("<<Schulform>>");
	            platzhalter.add("<<Partnerschule>>");
	            platzhalter.add("<<AnmerkungenSchule>>");
	            platzhalter.add("<<Miki>>");
	            platzhalter.add("<<Miki-Grad>>");
	            platzhalter.add("<<AnmerkungenPerson>>");
	            platzhalter.add("<<Startdatum>>");
	            platzhalter.add("<<Enddatum>>");
	            platzhalter.add("<<Status>>");
	            platzhalter.add("<<AnmerkungenPraktikum>>");
	            platzhalter.add("<<Anspr1>>");
	            platzhalter.add("<<Anspr2>>");
	            platzhalter.add("<<Anspr3>>");
	            platzhalter.add("<<Info>>");
	            platzhalter.add("<<Edit>>");
	            platzhalter.add("<<Unterlagenvollst>>");
	            platzhalter.add("<<AntwortBis>>");
	            if(markierteReiheNR >= 0){
	            	String nn = (String) table.getValueAt(markierteReiheNR, 0);
	                String vn = (String) table.getValueAt(markierteReiheNR, 1);
	                String status = (String) table.getValueAt(markierteReiheNR, 2);
	                liste.add(nn);
	                liste.add(vn);
	                liste.add(status);
	                String sql;
	                sql = getEintragPrakt(liste);
	                daten = _model.getData(sql);
	                _replacer.schreibeNeuesWordDokumentVonTemplate("template.docx", daten.get(0).get(2) + daten.get(0).get(3) + "-Anschreiben.docx",
	                		platzhalter, daten.get(0));
	            }
		}
	}
	private ArrayList<String> getInhaltSuchFelders(){
		ArrayList<String> daten = new ArrayList<String>();
		  daten.add(this.textFieldNNPrakt.getText());
		  daten.add(this.textFieldVNPrakt.getText());
		  daten.add(this.textFieldStatusPrakt.getText());
		  daten.add(this.textFieldStartPrakt.getText());
		  daten.add(this.textFieldEndPrakt.getText());
		  daten.add(this.textFieldAnmerkPrakt.getText());
		  daten.add(this.textFieldEditPrakt.getText());
	      return daten;
	}
	public void actionPerformed(ActionEvent evt) {
		Object src = evt.getSource();
		   if (src == button_aktualisieren) {
			   filterPrakt();
			}
	}
}
