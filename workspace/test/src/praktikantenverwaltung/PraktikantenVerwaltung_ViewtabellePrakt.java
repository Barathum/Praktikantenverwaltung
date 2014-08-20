package praktikantenverwaltung;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.text.DefaultFormatterFactory;

import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.calendar.DatePickerFormatter;

public class PraktikantenVerwaltung_ViewtabellePrakt extends JFrame {

	/**
	 * erstellen der Fields
	 */
	private JPanel contentPane;
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
            "Letzte Änderung"};
	private Object[][] datenprak;
	private JButton btn_praktbearbeiten;
	private JButton btn_praktloeschen;
	private JButton btn_praktNachrichtSchreiben;
	private PraktikantenVerwaltung_Modell _model;
	private PraktikantenVerwaltung_Control _control;
	private JPanel panel;
	private JTextField textFieldNNPrakt;
	private JTextField textFieldVNPrakt;
	private JTextField textFieldStatusPrakt;
	private JTextField textFieldStartPrakt;
	private JTextField textFieldEndPrakt;
	private JTextField textFieldAnmerkPrakt;
	private JTextField textFieldEditPrakt;
	
	public PraktikantenVerwaltung_ViewtabellePrakt(ArrayList<ArrayList<String>> Tabellen_Eintraege){
		this._model = new PraktikantenVerwaltung_Modell();
		this._control = new PraktikantenVerwaltung_Control();
		setDatenPrakt(_control.ArrayListtoArray(Tabellen_Eintraege));
		setResizable(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(5, 5, 1280, 720);
		
		
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
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(textFieldNNPrakt, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(textFieldVNPrakt, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(textFieldStatusPrakt, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(textFieldStartPrakt, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(textFieldEndPrakt, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(textFieldAnmerkPrakt, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(textFieldEditPrakt, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(14, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
					.addComponent(textFieldNNPrakt, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
					.addComponent(textFieldVNPrakt, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
				.addComponent(textFieldStatusPrakt, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
				.addComponent(textFieldStartPrakt, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
				.addComponent(textFieldEndPrakt, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
				.addComponent(textFieldAnmerkPrakt, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
				.addComponent(textFieldEditPrakt, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
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
		updateTablePrakt();
	}
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
	private JTable getTable(){
		return table_prakt;
	}
	private String loescheEintragPrakt(ArrayList<String> liste){
		String sql;
		sql = "DELETE from PRAKTIKANTEN where NN='" + liste.get(0) + "' AND VN ='" + liste.get(1) + "' AND STATUS ='" + liste.get(2) + "';";
		return sql;
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
}
