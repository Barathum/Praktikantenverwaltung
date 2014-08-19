package praktikantenverwaltung;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
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

import praktikantenverwaltung.PraktikantenVerwaltung_ViewPrakt.PraktSpeichernListener;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PraktikantenVerwaltung_ViewTabelleAnspr extends JFrame {
	
	/**
	 * erstellen der Fields
	 */
	private JPanel contentPane;
	private JPanel mainPanel;
	private JTable table_anspr;
	private JScrollPane scrollPane_SuchlisteAnspr;
	private String[] spaltennamenansprech = {
            "Nachname",
            "Vorname",
            "Telefonnummer",
            "E-Mail-Adresse", 
            "Abteilung", 
            "Raumnummer", 
            "Blockiert ab",
            "Blockiert bis"};
	private Object[][] datenansprech;
	private JButton buttonAnsprBearb;
	private JButton buttonAnsprLoesch;
	private PraktikantenVerwaltung_Modell _model;
	private PraktikantenVerwaltung_Control _control;
	private JPanel panel;
	private JTextField textField_tabelleNN;
	private JTextField textField_tabelleVN;
	private JTextField textField_tabelleTele;
	private JTextField textField_tabelleMail;
	private JTextField textField_tabelleAbt;
	private JTextField textField_tabelleRaum;
	private JTextField textField_tabelleBlockVon;
	private JTextField textField_tabelleBlockBis;

	/**
	 * Create the frame.
	 */
	public PraktikantenVerwaltung_ViewTabelleAnspr(ArrayList<ArrayList<String>> Tabellen_Eintraege) {
		this._model = new PraktikantenVerwaltung_Modell();
		this._control = new PraktikantenVerwaltung_Control();
		setDatenAnspr(_control.ArrayListtoArray(Tabellen_Eintraege));
		setResizable(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(5, 5, 1280, 720);
		
		
		mainPanel = new JPanel();
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new CardLayout(0, 0));
		
		 
		 JPanel panel_tabellenAnspr = new JPanel();
		 mainPanel.add(panel_tabellenAnspr, "card_6");
		 panel_tabellenAnspr.setLayout(new BorderLayout(0, 0));
		 
		 panel = new JPanel();
		 panel_tabellenAnspr.add(panel, BorderLayout.NORTH);
		 
		 textField_tabelleNN = new JTextField();
		 textField_tabelleNN.setColumns(10);
		 
		 textField_tabelleVN = new JTextField();
		 textField_tabelleVN.setColumns(10);
		 
		 textField_tabelleTele = new JTextField();
		 textField_tabelleTele.setColumns(10);
		 
		 textField_tabelleMail = new JTextField();
		 textField_tabelleMail.setColumns(10);
		 
		 textField_tabelleAbt = new JTextField();
		 textField_tabelleAbt.setColumns(10);
		 
		 textField_tabelleRaum = new JTextField();
		 textField_tabelleRaum.setColumns(10);
		 
		 textField_tabelleBlockVon = new JTextField();
		 textField_tabelleBlockVon.setColumns(10);
		 
		 textField_tabelleBlockBis = new JTextField();
		 textField_tabelleBlockBis.setColumns(10);
		 GroupLayout gl_panel = new GroupLayout(panel);
		 gl_panel.setHorizontalGroup(
		 	gl_panel.createParallelGroup(Alignment.LEADING)
		 		.addGroup(gl_panel.createSequentialGroup()
		 			.addComponent(textField_tabelleNN, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
		 			.addGap(1)
		 			.addComponent(textField_tabelleVN, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
		 			.addGap(1)
		 			.addComponent(textField_tabelleTele, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
		 			.addGap(1)
		 			.addComponent(textField_tabelleMail, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
		 			.addGap(1)
		 			.addComponent(textField_tabelleAbt, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
		 			.addGap(1)
		 			.addComponent(textField_tabelleRaum, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
		 			.addGap(1)
		 			.addComponent(textField_tabelleBlockVon, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
		 			.addGap(1)
		 			.addComponent(textField_tabelleBlockBis, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
		 			.addGap(20))
		 );
		 gl_panel.setVerticalGroup(
		 	gl_panel.createParallelGroup(Alignment.TRAILING)
		 		.addComponent(textField_tabelleNN, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
		 		.addComponent(textField_tabelleVN, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
		 		.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
		 			.addComponent(textField_tabelleTele, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
		 			.addComponent(textField_tabelleMail, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
		 			.addComponent(textField_tabelleAbt, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
		 			.addComponent(textField_tabelleRaum, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
		 		.addComponent(textField_tabelleBlockVon, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
		 		.addComponent(textField_tabelleBlockBis, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
		 );
		 panel.setLayout(gl_panel);
		 
		 scrollPane_SuchlisteAnspr = new JScrollPane();
		 panel_tabellenAnspr.add(scrollPane_SuchlisteAnspr);
		 scrollPane_SuchlisteAnspr.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		 
		 JPanel panel_9 = new JPanel();
		 panel_tabellenAnspr.add(panel_9, BorderLayout.SOUTH);
		 
		 buttonAnsprBearb = new JButton("Bearbeiten");
		 
		 buttonAnsprLoesch = new JButton("L\u00F6schen");
		 GroupLayout gl_panel_9 = new GroupLayout(panel_9);
		 gl_panel_9.setHorizontalGroup(
		 	gl_panel_9.createParallelGroup(Alignment.LEADING)
		 		.addGroup(gl_panel_9.createSequentialGroup()
		 			.addGap(20)
		 			.addComponent(buttonAnsprBearb)
		 			.addGap(18)
		 			.addComponent(buttonAnsprLoesch, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
		 			.addContainerGap(1056, Short.MAX_VALUE))
		 );
		 gl_panel_9.setVerticalGroup(
		 	gl_panel_9.createParallelGroup(Alignment.LEADING)
		 		.addGroup(gl_panel_9.createSequentialGroup()
		 			.addGap(9)
		 			.addGroup(gl_panel_9.createParallelGroup(Alignment.BASELINE)
		 				.addComponent(buttonAnsprBearb)
		 				.addComponent(buttonAnsprLoesch))
		 			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		 );
		 panel_9.setLayout(gl_panel_9);
		 
			this.setTabelleFilterListener(new TabelleFilterListener());
			updateTable();
	}
	public void setDatenAnspr(Object[][] daten){
		this.datenansprech = daten;
	}
	public void updateTable(){
		table_anspr = new JTable(new DefaultTableModel_PraktikantenVerwaltung(spaltennamenansprech, datenansprech));
		table_anspr.setSelectionMode(0);
		table_anspr.setAutoCreateRowSorter(true);
		scrollPane_SuchlisteAnspr.setViewportView(table_anspr);
	}
	private void setTabelleFilterListener(DocumentListener l){
	        this.textField_tabelleNN.getDocument().addDocumentListener(l);
	        this.textField_tabelleVN.getDocument().addDocumentListener(l);
	        this.textField_tabelleTele.getDocument().addDocumentListener(l);
	        this.textField_tabelleMail.getDocument().addDocumentListener(l);
	        this.textField_tabelleAbt.getDocument().addDocumentListener(l);
	        this.textField_tabelleRaum.getDocument().addDocumentListener(l);
	        this.textField_tabelleBlockVon.getDocument().addDocumentListener(l);
	        this.textField_tabelleBlockBis.getDocument().addDocumentListener(l);
	}
	class TabelleFilterListener implements DocumentListener{ 

		@Override
		public void changedUpdate(DocumentEvent e) {
			filter();
		}

		@Override
		public void insertUpdate(DocumentEvent e) {
			filter();
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			filter();
		}
		public void filter() { 
			   ArrayList<ArrayList<String>> daten = new ArrayList<ArrayList<String>>();
			   ArrayList<String> datenTextfields = new ArrayList<String>();
			   datenTextfields = getInhaltSuchFelders();
			   daten = _model.getData("SELECT NN , VN , TELE , MAIL , ABTEILUNG , RNR , BLOCKIERENVON , BLOCKIERENBIS FROM ANSPRECHPARTNER WHERE NN LIKE '%" + datenTextfields.get(0) + "%' AND VN LIKE '%" + datenTextfields.get(1) + "%' "
			   		+ "AND TELE LIKE '%" + datenTextfields.get(2) + "%' AND MAIL LIKE '%" + datenTextfields.get(3) + "%' AND ABTEILUNG LIKE '%" + datenTextfields.get(4) + "%' AND RNR LIKE '%" + datenTextfields.get(5) + "%' AND BLOCKIERENVON LIKE '%" + datenTextfields.get(6) + "%'"
			   				+ "AND BLOCKIERENBIS LIKE '%" + datenTextfields.get(7) + "%' ORDER BY NN;");
			   setDatenAnspr(_control.ArrayListtoArray(daten));
			   updateTable();
			} 
	}  
	private ArrayList<String> getInhaltSuchFelders(){
		ArrayList<String> daten = new ArrayList<String>();
		  daten.add(this.textField_tabelleNN.getText());
		  daten.add(this.textField_tabelleVN.getText());
		  daten.add(this.textField_tabelleTele.getText());
		  daten.add(this.textField_tabelleMail.getText());
		  daten.add(this.textField_tabelleAbt.getText());
		  daten.add(this.textField_tabelleRaum.getText());
		  daten.add(this.textField_tabelleBlockVon.getText());
		  daten.add(this.textField_tabelleBlockBis.getText());
	      return daten;
	}
}
