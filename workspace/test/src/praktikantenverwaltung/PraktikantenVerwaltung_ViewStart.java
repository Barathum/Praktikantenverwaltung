package praktikantenverwaltung;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;

import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.BorderFactory;

public class PraktikantenVerwaltung_ViewStart extends JFrame {

	
	private static final long serialVersionUID = 1L;
	/**
	 * erstellen der Fields
	 */
	private JPanel mainPanel;
	private String tempFolder = new String("temp");
	private JTable table_todos = new JTable();
	private String[] spaltennamentodos = {
			"ID",
            "Nachname",
            "Vorname",
            "Hinweis"};
	private Object[][] datentodos;
	JMenuItem menuNeuerPraktikant = new JMenuItem("Neuer Praktikant");
	JMenuItem menuNeuerAnsprechpartner = new JMenuItem("Neuer Ansprechpartner");
	JMenuItem menuAllePraktikanten = new JMenuItem("Zeige Praktikantentabelle");
	JMenuItem menuAlleAnsprechpartner = new JMenuItem("Zeige Ansprechpartnertabelle");
	private JScrollPane scrollPane;
	private PraktikantenVerwaltung_Modell _model;
	private PraktikantenVerwaltung_Control _control;
	ImageIcon iconAktualisieren = new ImageIcon(new ImageIcon("img/aktualisierenIcon.png").getImage().getScaledInstance(29, 29, java.awt.Image.SCALE_SMOOTH));
	private JButton button_aktualisieren;

	/**
	 * Create the frame.
	 */
	public PraktikantenVerwaltung_ViewStart(PraktikantenVerwaltung_Modell model , PraktikantenVerwaltung_Control control , ArrayList<ArrayList<String>> Tabellen_Eintraege) {
		this._model = model;
		this._control = control;
		setDatenTodos(_control.ArrayListtoArray(Tabellen_Eintraege));
		setResizable(true);
		setBounds(5, 5, 1280, 720);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
		mainPanel = new JPanel();
		getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new CardLayout(0, 0));
		
		JPanel panel_Start = new JPanel();
		panel_Start.setLayout(null);
		mainPanel.add(panel_Start, "card_3");
		
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(0, 37, 1264, 624);
		panel_Start.add(scrollPane);
		
		button_aktualisieren = new JButton(iconAktualisieren);
		button_aktualisieren.setBorder(BorderFactory.createEmptyBorder());
		button_aktualisieren.setBounds(1226, 2, 33, 33);
		panel_Start.add(button_aktualisieren);
		
		 /**
		 * Anfügen der Menüeinträge in das Startmenu
		 */
		 JMenuBar menu = new JMenuBar();
		    JMenu start = new JMenu("Start");
		    start.add(menuNeuerPraktikant);
		    start.add(menuNeuerAnsprechpartner);
		    start.add(menuAllePraktikanten);
		    start.add(menuAlleAnsprechpartner);
		    menu.add(start);
		    getContentPane().add(menu, BorderLayout.NORTH);
		    this.addWindowListener(new WindowAdapter() {
				@Override
				public void windowOpened(WindowEvent e) {
				}
				@Override
				public void windowIconified(WindowEvent e) {
				}
				@Override
				public void windowDeiconified(WindowEvent e) {
				}
				@Override
				public void windowDeactivated(WindowEvent e) {
				}
				@Override
				public void windowClosing(WindowEvent e) {
//					int confirmed = JOptionPane.showConfirmDialog(null, 
//					        "Wirklich Schließen? Alle nicht gespeicherten Daten gehen verloren!", "",
//					        JOptionPane.YES_NO_OPTION);
//					    if (confirmed == JOptionPane.YES_OPTION) {
					    	try {
								FileUtils.cleanDirectory(new File(tempFolder));
							} catch (IOException e1) {
								System.out.println("Der Ordner " + tempFolder + "existiert nicht.");
//								e1.printStackTrace();
							}
					    	System.exit(0);
//					    }
				}
				@Override
				public void windowClosed(WindowEvent e) {
					
				}
				@Override
				public void windowActivated(WindowEvent e) {
				}
			});
		    
		    button_aktualisieren.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					filterTodos();
				}
			});
		    filterTodos();
	}
	public void setNeuerPraktListener(ActionListener l){ 
        this.menuNeuerPraktikant.addActionListener(l); 
	}
	public void setNeuerAnsprListener(ActionListener l){ 
        this.menuNeuerAnsprechpartner.addActionListener(l); 
	}
	public void setTabellePraktListener(ActionListener l){ 
        this.menuAllePraktikanten.addActionListener(l); 
	}
	public void setTabelleAnsprListener(ActionListener l){ 
        this.menuAlleAnsprechpartner.addActionListener(l); 
	}
	
	public void updateTableTodos(){
		table_todos = new JTable(new DefaultTableModel_PraktikantenVerwaltung(spaltennamentodos, datentodos));
		table_todos.getColumnModel().getColumn(0).setPreferredWidth(20);
		table_todos.getColumnModel().getColumn(1).setPreferredWidth(50);
		table_todos.getColumnModel().getColumn(2).setPreferredWidth(50);
		table_todos.getColumnModel().getColumn(3).setPreferredWidth(700);
		table_todos.addMouseListener(new MouseAdapter() {
			   public void mouseClicked(MouseEvent e) {
			      if (e.getClickCount() == 2) {
			    	  refresh();
			         JTable target = getTable();
			         ArrayList<ArrayList<String>> daten = new ArrayList<ArrayList<String>>();
			            int markierteReiheNR =  target.getSelectedRow();
			            ArrayList<String> liste = new ArrayList<String>();
			            if(markierteReiheNR >= 0){
			            	String id = (String) target.getValueAt(markierteReiheNR, 0);
			                liste.add(id);
			                String sql;
			                sql = getEintragPrakt(liste);
			                daten = _model.getData(sql);
			                try {
			                	PraktikantenVerwaltung_ViewPrakt _viewprakt = null;
			                    _viewprakt = new PraktikantenVerwaltung_ViewPrakt(_control, _model , daten);
			                    _viewprakt.setVisible(true);
							} catch (IndexOutOfBoundsException e2) {
								return;
							}
			            }
			         }
			   }
			});
		scrollPane.setViewportView(table_todos);
	}
	private JTable getTable(){
		return table_todos;
	}
	private void refresh(){
		ListSelectionModel model = getTable().getSelectionModel();
		int[] rows = getTable().getSelectedRows();
		model.clearSelection();
		filterTodos();
		for (int i = 0; i < rows.length; i++) {
			model.addSelectionInterval(rows[i], rows[i]);
		}
		table_todos.setSelectionModel(model);
	}
	private String getEintragPrakt(ArrayList<String> liste){
		String sql;
		sql = "SELECT * from PRAKTIKANTEN where ID='" + liste.get(0) + "';";
		return sql;
	}
	private void setDatenTodos(Object[][] daten){
		this.datentodos = daten;
	}
	public void filterTodos() { 
		DateTimeFormatter dateStringFormat = DateTimeFormat.forPattern("dd.MM.yyyy");
		   SimpleDateFormat sdfToDate = new SimpleDateFormat("dd.MM.yy");
		   Calendar cal = Calendar.getInstance();
			cal.add(Calendar.YEAR, -50);
			sdfToDate.set2DigitYearStart(cal.getTime());
		ArrayList<ArrayList<String>> daten_komplett = new ArrayList<ArrayList<String>>();
		daten_komplett = _model.getData("SELECT * FROM PRAKTIKANTEN ORDER BY NN;");
		   ArrayList<ArrayList<String>> daten_table = new ArrayList<ArrayList<String>>();
//			daten_table = _model.getData("SELECT ID , NN , VN , EDIT FROM PRAKTIKANTEN ORDER BY NN;");
			/**
			 * Hier Rauslöschen der Daten die nicht den Todos entsprechen
			 */
		   DateTime aktuelleZeit = new DateTime();
		   for (int i = 0; i < daten_komplett.size(); i++) {
				String antwortFristString = daten_komplett.get(i).get(33);
				DateTime antwortFristDate;
				
				String endDatum = daten_komplett.get(i).get(24);
				DateTime endDatumDate;
				try {
					antwortFristDate = dateStringFormat.parseDateTime(antwortFristString);
					if (aktuelleZeit.plusDays(3).isAfter(antwortFristDate) && daten_komplett.get(i).get(32).equals("0")) {
						ArrayList<String> datensatzgekürzt = new ArrayList<String>();
						datensatzgekürzt.add(daten_komplett.get(i).get(0));
						datensatzgekürzt.add(daten_komplett.get(i).get(2));
						datensatzgekürzt.add(daten_komplett.get(i).get(3));
						/**
						 * Das ist der Individuelle Hinweis für das Todo
						 */
						if(aktuelleZeit.isAfter(antwortFristDate)){
							datensatzgekürzt.add("Unterlagen unvollständig und Antwortfrist ist an "+antwortFristString+" abgelaufen");
						}else{
							datensatzgekürzt.add("Unterlagen unvollständig und Antwortfrist wird am "+antwortFristString+" ablaufen");
						}
							
						daten_table.add(datensatzgekürzt);
					}
					
					endDatumDate = dateStringFormat.parseDateTime(endDatum);
					if(aktuelleZeit.plusDays(3).isAfter(endDatumDate) && !(daten_komplett.get(i).get(25).equals("abgeschlossen"))){
						ArrayList<String> datensatzgekürzt = new ArrayList<String>();
						datensatzgekürzt.add(daten_komplett.get(i).get(0));
						datensatzgekürzt.add(daten_komplett.get(i).get(2));
						datensatzgekürzt.add(daten_komplett.get(i).get(3));
						/**
						 * Das ist der Individuelle Hinweis für das Todo
						 */
						if(aktuelleZeit.isAfter(endDatumDate)){
							datensatzgekürzt.add("Praktikum wurde am "+endDatum+" beendet und Status ist noch nicht abgeschlossen");
						}else{
							datensatzgekürzt.add("Praktikum endet am "+endDatum);
						}
							
						daten_table.add(datensatzgekürzt);
					}
					
				} catch (Exception e) {
					
				}
			}
		   
		   
//			for (int i = 0; i < daten_komplett.size(); i++) {
//				ArrayList<String> datensatzgekürzt = new ArrayList<String>();
//				datensatzgekürzt.add(daten_komplett.get(i).get(0));
//				datensatzgekürzt.add(daten_komplett.get(i).get(2));
//				datensatzgekürzt.add(daten_komplett.get(i).get(3));
//				datensatzgekürzt.add(daten_komplett.get(i).get(31));
//				daten_table.add(datensatzgekürzt);
//			}
		   setDatenTodos(_control.ArrayListtoArray(daten_table));
		   updateTableTodos();
	}
}
